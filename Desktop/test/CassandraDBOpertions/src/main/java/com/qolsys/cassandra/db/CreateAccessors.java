package com.qolsys.cassandra.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.datastax.driver.core.ColumnMetadata;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.TableMetadata;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;

/**
 * @author suresh
 *
 */
public class CreateAccessors {

	private static StringBuilder accssorPackages = new StringBuilder();
	private static StringBuilder query = new StringBuilder();
	private static Collection<TableMetadata> clusterInstanceMetaData;
	private static Collection<TableMetadata> getClusterMetaData(int port, String... hosts){
		if(clusterInstanceMetaData == null){
			clusterInstanceMetaData =  CassandraCluster.getClusterInstance(port, hosts)
					.getMetadata().getKeyspace(Constants.CASSANDRA_KEYSPACE).getTables();
		}
		return clusterInstanceMetaData;
	}

	public void create(){
		Collection<TableMetadata> tablesList = CreateAccessors.getClusterMetaData(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
		for(TableMetadata tableMetadata : tablesList){
			String crudtableNameModified = CreateJavaBeans.nameCC(tableMetadata.getName(), true);
			accssorPackages.append("package "+Constants.CASSANDRA_ACCESSOR_PACKAGE+";\n\n")
			.append("import "+Constants.CASSANDRA_PACKAGE+"."+crudtableNameModified+";\n")
			.append("import com.datastax.driver.mapping.Result;\n")
			.append("import com.datastax.driver.mapping.annotations.Accessor;\n")
			.append("import com.datastax.driver.mapping.annotations.Param;\n")
			.append("import com.datastax.driver.mapping.annotations.Query;\n");
			List<ColumnMetadata> partitionKeys = tableMetadata.getPartitionKey();
			List<ColumnMetadata> clusteringKeys = tableMetadata.getClusteringColumns();
			int pklen = partitionKeys.size();
			int cklen = clusteringKeys.size();
			for(int i = -2; i<cklen; i++){
				if(i == -2){
					query.append("\n/**\n * "+crudtableNameModified+"Accessor class corresponds to java bean class to support"
							+ " all fetch queries that are \n * not supported by Object mapper framework and also eases the transformation to list "
							+ "of java beans \n * from database records"
							+ "\n *\n * @author cassandraIDC\n * \n */\n");
					query.append("\n@Accessor\n")
					.append("public interface "+crudtableNameModified+"Accessor {\n\n")
					.append("\t@Query(\"select * from "+tableMetadata.getName()+"\")\n")
					.append("\tpublic Result<"+crudtableNameModified+"> getAll();\n\n");
				}else{
					query.append(getQuery(tableMetadata, pklen+(i+1), crudtableNameModified));
				}
			}
			query.append("}");
			accssorPackages.append(query);
			File createDir = new File(Constants.CASSANDRA_ACCESSOR_DIR);
			if(!createDir.exists())
				createDir.mkdirs();
			File file = new File(Constants.CASSANDRA_ACCESSOR_DIR+"/"+crudtableNameModified+"Accessor.java");
			FileWriter stream;
			try {
				stream = new FileWriter(file);
				stream.write(accssorPackages.toString());
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			accssorPackages = new StringBuilder();
			query = new StringBuilder();
		}
		System.out.println("package creation done");
	}

	private static String ss = null;
	
	private static StringBuilder getQuery(TableMetadata tableMetadata, int pklen, String crudtableNameModified){
		ss = null;
		StringBuilder tempQuery = new StringBuilder();
		StringBuilder tempQuery1 = new StringBuilder();
		tempQuery.append("\t@Query(\"select * from "+tableMetadata.getName()+" where ");
		for(int j=0; j<pklen; j++){
			ColumnMetadata pkcol = tableMetadata.getPrimaryKey().get(j);
			String colName = pkcol.getName();
			String colCCName = CreateJavaBeans.nameCC(colName, false);
			String typeStr = getJavaDataType(pkcol.getType());
			
			if(typeStr.equalsIgnoreCase("UUID") && ! accssorPackages.toString().contains("java.util.UUID"))
			accssorPackages.append("import java.util.UUID;\n");
			
			tempQuery.append(" "+colName+"=:"+colCCName);
			tempQuery1.append("@Param(\""+colCCName+"\")"+typeStr+" "+colCCName);
			if(j != pklen-1){
				tempQuery.append(" and ");
				tempQuery1.append(", ");
			}else{
				tempQuery.append("\")\n");
				tempQuery.append("\tpublic Result<"+crudtableNameModified+"> fetch"+crudtableNameModified+"(");
				tempQuery1.append(");\n\n");
				//tempQuery.append("\tpublic Result<"+crudtableNameModified+"> delete"+crudtableNameModified+"(");
				//tempQuery1.append(");\n\n");
			}
		}
		tempQuery.append(tempQuery1);
		/*String ss = tempQuery.toString().replace("select * ", "delete ").replace("fetch", "delete")+"\n";
		tempQuery.append(ss);*/
		return tempQuery;
	}

	public static String getJavaDataType(DataType type) {
		String dataType = type.toString();
		if(dataType.equals("int")){
			return "int";
		}else if(dataType.equals("varint")){
			return "long";
		}else if(dataType.equals("text") || dataType.equals("varchar")){
			return "String";
		}else if(dataType.equalsIgnoreCase("timestamp")){
			if(! accssorPackages.toString().contains("java.util.Date"))
				accssorPackages.append("import java.util.Date;\n");
			return "Date";
		}else if(dataType.equalsIgnoreCase("date")){
			if(! accssorPackages.toString().contains("com.datastax.driver.core.LocalDate"))
				accssorPackages.append("import com.datastax.driver.core.LocalDate;\n");
			return "LocalDate";
		}else if(dataType.equals("boolean")) {
			return "boolean";
		}else if(dataType.equalsIgnoreCase("uuid") || dataType.equalsIgnoreCase("timeuuid")){
			if(! accssorPackages.toString().contains("com.datastax.driver.core.utils.UUIDs"))
				accssorPackages.append("import com.datastax.driver.core.utils.UUIDs;\n");
			return "UUIDs";
		}
		return null;
	}
}