/*package com.qolsys.cassandra.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.TableMetadata;
import com.qolsys.cassandra.beans.PanelSettings;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;

public class CreateAccessorEntityOperations {
	private static StringBuilder accssorEntityPackages = new StringBuilder();
	//private static StringBuilder mapperquery = new StringBuilder();
	private static Collection<TableMetadata> clusterInstanceMetaData;
	public static Collection<TableMetadata> getClusterMetaData(int port, String... hosts){
		if(clusterInstanceMetaData == null){
			clusterInstanceMetaData =  CassandraCluster.getClusterInstance(port, hosts)
					.getMetadata().getKeyspace(Constants.CASSANDRA_KEYSPACE).getTables();
		}
		return clusterInstanceMetaData;
	}

	public void createentity(){
		Collection<TableMetadata> tablesList = CreateAccessorEntityOperations.getClusterMetaData(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
		for(TableMetadata tableMetadata : tablesList){
			String entitytableNameModified = CreateJavaBeans.nameCC(tableMetadata.getName(), true);	
			accssorEntityPackages.append("package "+Constants.CASSANDRA_CRUD_PACKAGE+";\n\n")
			.append("import com.datastax.driver.mapping.MappingManager;\n")
			.append("import com.qolsys.cassandra.constants.Constants;\n")
			.append("import com.datastax.driver.mapping.Result;\n\n")
			.append("import com.qolsys.cassandra.beans.*;\n")
			.append("import com.qolsys.cassandra.connection.CassandraCluster;\n")
			.append("import java.util.List;\n")
			.append("import org.slf4j.Logger;\n")
			.append("import org.slf4j.LoggerFactory;\n\n")
		    .append("public class "+entitytableNameModified+"Dao {\n")
		    .append("\tprivate static Logger logger = LoggerFactory.getLogger("+entitytableNameModified+"Dao.class);\n\n")
		    .append("\tprivate static List<"+entitytableNameModified+"> fetch"+entitytableNameModified+"Details(){\n")
		    .append("\tList<"+entitytableNameModified+"> table = null;\n")
		    .append("\t\ttry{\n")
			.append("\t\tMappingManager manager = new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
			.append("\t\t"+entitytableNameModified+"Accessor mapper1 = manager.createAccessor("+entitytableNameModified+"Accessor.class);\n")
			.append("\t\tResult<"+entitytableNameModified+"> result = mapper1.getAll();\n")
			.append("\t\tif(result != null)\n")
			.append("\t\t\ttable = result.all();\n")
			.append("\t\t}catch(Exception e){\n")
			.append("\t\t\tlogger.error(\"Can't retrive table info using mapper\", e);\n")
			.append("\t\t}\n")
			.append("\t\treturn table;\n")
			.append("\t}\n")
			.append("}\n");
			File createDir = new File(Constants.CASSANDRA_CRUD_DIR);
			if(!createDir.exists())
				createDir.mkdirs();
			File file = new File(Constants.CASSANDRA_CRUD_DIR+"/"+entitytableNameModified+"Dao.java");
			FileWriter stream;
			try {
				stream = new FileWriter(file);
				stream.write(accssorEntityPackages.toString());
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			accssorEntityPackages = new StringBuilder();

		}
	}
}
 */


/**
 * 
 */
package com.qolsys.cassandra.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
public class CrudOperations {
	
	
	private static StringBuilder packageInfo = new StringBuilder();
	private static StringBuilder selects = null;
	private static StringBuilder deletes = null;
	private static StringBuilder selectjsons = null;
	//private static StringBuilder getobjects = null;

	//private static StringBuilder packageInfo = new StringBuilder();
	//private static StringBuilder methBody = null;

	/**
	 * @param args
	 */
	
	public void create(boolean isBatchNeeded) {
		Collection<TableMetadata> tablesList = CassandraCluster.getClusterInstance(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS)
				.getMetadata().getKeyspace(Constants.CASSANDRA_KEYSPACE).getTables();
		for(TableMetadata tableMetadata : tablesList){
			selects = new StringBuilder();
			deletes = new StringBuilder();
			selectjsons = new StringBuilder();
			//getobjects = new StringBuilder();
			String tableName = tableMetadata.getName();
			String beanName = CreateJavaBeans.nameCC(tableName, true);
			String accessorName = beanName+"Accessor";
			String accessorVarName = CreateJavaBeans.nameCC(tableName, false)+"Accessor";
			String daoName = beanName+"Dao";
			String beanSign = "List<"+beanName+">";
			StringBuilder methSign = new StringBuilder("getAll()");
			StringBuilder methCall = new StringBuilder("getAll()");
			String allStr = "all";
			packageInfo = new StringBuilder();
			getPackageInfo(beanName, accessorName);
			StringBuilder startClass = new StringBuilder();
			startClass.append("\npublic class "+daoName+"{\n\n")
			.append("\tprivate static Logger logger = LoggerFactory.getLogger("+daoName+".class);\n\n");
			List<ColumnMetadata> partitionKeys = tableMetadata.getPartitionKey();
			int cklen = tableMetadata.getClusteringColumns().size();
			int pklen = partitionKeys.size();
			List<ColumnMetadata> primaryKeys = tableMetadata.getPrimaryKey();
			//StringBuilder getobject = new StringBuilder();
			for(int i=-2; i<cklen; i++){
				List<String> varNames = new ArrayList<>();
				List<String> colNames = new ArrayList<>();
				allStr = "all";
				beanSign = "List<"+beanName+">";
				if(i != -2){
					methSign = new StringBuilder();
					methCall = new StringBuilder();
					methSign.append("fetch"+beanName+"Impl(");
					methCall.append("fetch"+beanName+"(");
					
					//getobject = new StringBuilder();
					//getobject.append(""+beanName+" get"+beanName+"(");
					
					for(int j=0; j<pklen+i+1;j++){
						ColumnMetadata metadata = primaryKeys.get(j);
						String dbcolName = metadata.getName();
						String colVarName = CreateJavaBeans.nameCC(dbcolName, false);
						varNames.add(colVarName);
						colNames.add(dbcolName);
						String javaType = getJavaDataType(metadata.getType());
						if(javaType.equalsIgnoreCase("UUID") && ! packageInfo.toString().contains("java.util.UUID"))
							packageInfo.append("import java.util.UUID;\n");
						methSign.append(javaType+" "+colVarName);
						methCall.append(colVarName);
						if(j == pklen+i){
							methSign.append(")");
							methCall.append(")");
						}else{
							methSign.append(", ");
							methCall.append(", ");
						}
					}
					deletes.append(createDeleteMethods(methSign, varNames, colNames, tableName));
					selectjsons.append(createFetchJsonMethods(methSign, varNames, colNames, tableName));
					if(i==cklen-1){
						allStr = "one";
						beanSign = beanName;
					}else{
						allStr = "all";
						beanSign = "List<"+beanName+">";
					}
				}
				selects.append(createFetchMethods(beanSign, beanName, methSign, accessorName, accessorVarName, methCall, daoName, allStr));
				//getobjects.append(getSingleObject(beanName, getobject, methCall));
			}

			// adds save object, build dynamic update, fetch json, save/update via json
			//batch insert
			selects.append(createSaveOrUpdateMeth(beanName, CreateJavaBeans.nameCC(tableName, false)))
			.append(createJsonInsertMeth(beanName, tableName));
			
			if(isBatchNeeded && !packageInfo.toString().contains("com.datastax.driver.core.BatchStatement")){
				packageInfo.append("import com.datastax.driver.core.BatchStatement;\n");
				selects.append(createBatch(beanName));
			}
			deletes.append("}");
			File createDir = new File(Constants.CASSANDRA_CRUD_PACKAGE_DIR);
			if(!createDir.exists())
				createDir.mkdirs();
			File file = new File(Constants.CASSANDRA_CRUD_PACKAGE_DIR+"/"+daoName+".java");
			FileWriter stream;
			try {
				stream = new FileWriter(file);
				stream.write(packageInfo.append(startClass).append(selects).append(selectjsons).append(deletes).toString());
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DAO's or CRUD are successfully created ... ");
		//CassandraCluster.closeCluster();
	}
	
	

	
	/////////// fetch method //////////////
	static StringBuilder createFetchMethods(String beanSign, String beanName, StringBuilder methSign, String accessorName, String accessorVarName, StringBuilder methCall, String daoName, String allStr){
		StringBuilder body = new StringBuilder();
		body.append("\tpublic "+beanSign+" "+methSign.toString()+"{\n")
		.append("\t\t"+beanSign+" list = null;\n").append("\t\ttry{\n")
		.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")		.append("\t\t\t"+accessorName+" "+accessorVarName+" = mappingManager.createAccessor("+accessorName+".class);\n")
		.append("\t\t\tResult<"+beanName+"> result = "+accessorVarName+"."+methCall.toString()+";\n")
		.append("\t\t\tif(result != null)\n").append("\t\t\t\tlist = result."+allStr+"();\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured in "+methCall.toString()+" of "+daoName+" Class\", e);\n\t\t}\n\t\treturn list;\n\t}\n\n");
		return body;
	}
	
	
	/////////////// delete method //////////////////////////
	static StringBuilder createDeleteMethods(StringBuilder methSign, List<String> varnames, List<String> colnames, String tableName){
		StringBuilder body = new StringBuilder();
		body.append("\tpublic boolean "+methSign.toString().replace("fetch", "delete")+"{\n")
		.append("\t\tboolean status = false;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tString deleteQuery = \"delete from "+tableName+" where ");
		int len = varnames.size();
		for(int i=0; i < len; i++){
			body.append(colnames.get(i)+"='\"+"+varnames.get(i));
			if(i != (len-1))
				body.append("+\"' and ");
			else
				body.append("+\"'\";\n");
		}
		body.append("\t\t\tsession.execute(deleteQuery);\n").append("\t\t\tstatus = true;\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing delete() on "+tableName+" table\", e);\n\t\t}\n\t\treturn status;\n\t}\n\n");
		return body;
	}
	
	
	////////// fetch create json /////////////////
	static StringBuilder createFetchJsonMethods(StringBuilder methSign, List<String> varnames, List<String> colnames, String tableName){
		StringBuilder body = new StringBuilder();
		body.append("\tpublic String[] "+methSign.toString().replace("fetch", "fetchJson")+"{\n")
		.append("\t\tString str[] = null;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tString fetchJsonQuery = \"select json * from "+tableName+" where ");
		int len = varnames.size();
		for(int i=0; i < len; i++){
			body.append(colnames.get(i)+"='\"+"+varnames.get(i));
			if(i != (len-1))
				body.append("+\"' and ");
			else
				body.append("+\"'\";\n");
		}
		body.append("\t\t\tResultSet resultSet = session.execute(fetchJsonQuery);\n").append("\t\t\tList<Row> list =resultSet.all();\n")
		.append("\t\t\tint len = list.size();\n").append("\t\t\tstr = new String[len];\n").append("\t\t\tfor(int i=0; i<len; i++){\n")
		.append("\t\t\t\tstr[i] = list.get(i).getString(0);\n\t\t\t}\n").append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing delete() on "+tableName+" table\", e);\n\t\t}\n\t\treturn str;\n\t}\n\n");
		return body;
	}
	
	//////////// createSaveOrUpdateMeth //////////////////
	static StringBuilder createSaveOrUpdateMeth(String beanName, String beanVarName){
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic boolean save"+beanName+"("+beanName+" "+beanVarName+"){\n")
		.append("\t\tboolean status = false;\n").append("\t\ttry{\n")
		.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
		.append("\t\t\tMapper<"+beanName+"> mapper = mappingManager.mapper("+beanName+".class);\n")
		.append("\t\t\tmapper.save("+beanVarName+");\n").append("\t\t\tstatus = true;\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing insert or update "+beanName+" Object\", e);\n\t\t}\n\t\treturn status;\n\t}\n\n");
		return builder;
	}
	
	/////////////// createJsonInsertMeth //////////////////////
	static StringBuilder createJsonInsertMeth(String beanName, String tableName){
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic boolean insertBy"+beanName+"Json(String json){\n")
		.append("\t\tboolean status = false;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tString insertJsonQuery = \"insert into "+tableName+" json '\"+json+\"'\";\n")
		.append("\t\t\tsession.execute(insertJsonQuery);\n").append("\t\t\tstatus = true;\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing insert by json String "+beanName+" Object\", e);\n\t\t}\n\t\treturn status;\n\t}\n\n");
		return builder;
	}
	
	////////////// createBatch //////////////////////
	static StringBuilder createBatch(String beanName){
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic boolean bulk"+beanName+"Insert(List<"+beanName+"> list){\n")
		.append("\t\tboolean status = false;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tMappingManager mappingManager = new MappingManager(session);\n")
		.append("\t\t\tMapper<"+beanName+"> mapper = mappingManager.mapper("+beanName+".class);\n")
		.append("\t\t\tBatchStatement batchStatement = new BatchStatement();\n")
		.append("\t\t\tfor("+beanName+" bean:list){\n").append("\t\t\t\tStatement statement = mapper.saveQuery(bean);\n")
		.append("\t\t\t\tbatchStatement.add(statement);\n").append("\t\t\t}\n")
		.append("\t\t\tsession.execute(batchStatement);\n").append("\t\t\tstatus = true;\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing Batch-Statement Execution "+beanName+" Object\", e);\n\t\t}\n\t\treturn status;\n\t}\n\n");
		return builder;
	}
	
	
	
	
	
	/////////////// get single object /////////////////////
	/*static StringBuilder getSingleObject(String beanName, StringBuilder getobject, StringBuilder methCall){
		StringBuilder builder = new StringBuilder();
		builder.append("\tpublic "+getobject.toString()+"{\n")
		.append("\t\ttry{\n")
		.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
		.append("\t\t\tMapper<"+beanName+"> mapper = mappingManager.mapper("+beanName+".class);\n")
		.append("\t\t\tmapper.get("+methCall+";\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception occured in get"+beanName+"("+beanName+" (....))\");\n")
		.append("\t\t}\n")
		.append("\t\treturn get"+beanName+"("+methCall+";\n")
		.append("\t}\n\n");
		return builder;	
	}*/
	//////////////////////////////////////////////////////


	public static String getJavaDataType(DataType type) {
		String dataType = type.toString();
		if(dataType.equals("int")){
			return "int";
		}else if(dataType.equals("varint")){
			return "long";
		}else if(dataType.equals("text") || dataType.equals("varchar")){
			return "String";
		}else if(dataType.equalsIgnoreCase("timestamp")){
			if(! packageInfo.toString().contains("java.util.Date"))
				packageInfo.append("import java.util.Date;\n");
			return "Date";
		}else if(dataType.equalsIgnoreCase("date")){
			if(! packageInfo.toString().contains("com.datastax.driver.core.LocalDate"))
				packageInfo.append("import com.datastax.driver.core.LocalDate;\n");
			return "LocalDate";
		}else if(dataType.equals("boolean")) {
			return "boolean";
		}else if(dataType.equalsIgnoreCase("uuid") || dataType.equalsIgnoreCase("timeuuid")){
			if(! packageInfo.toString().contains("com.datastax.driver.core.utils.UUIDs"))
				packageInfo.append("import com.datastax.driver.core.utils.UUIDs;\n");
			return "UUIDs";
		}
		return null;
	}

	private static void getPackageInfo(String beanName, String accessorName) {
		packageInfo.append("package "+Constants.CASSANDRA_CRUD_PACKAGE+";\n\n")
		.append("import com.qolsys.cassandra.connection.CassandraCluster;\n")
		.append("import "+Constants.CASSANDRA_PACKAGE+"."+beanName+";\n")
		.append("import "+Constants.CASSANDRA_ACCESSOR_PACKAGE+"."+accessorName+";\n")
		.append("import com.qolsys.cassandra.constants.Constants;\n\n")
		.append("import com.datastax.driver.mapping.Mapper;\n")
		.append("import com.datastax.driver.mapping.MappingManager;\n")
		.append("import com.datastax.driver.core.Session;\n")
		.append("import com.datastax.driver.core.Statement;\n")
		.append("import com.datastax.driver.core.Row;\n")
		.append("import com.datastax.driver.core.ResultSet;\n")
		.append("import com.datastax.driver.mapping.Result;\n\n")
		.append("import java.util.List;\n\n")
		.append("import org.slf4j.Logger;\n")
		.append("import org.slf4j.LoggerFactory;\n");
	}
}



/*	public void create() {
Collection<TableMetadata> tablesList = CassandraCluster.getClusterInstance(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS)
		.getMetadata().getKeyspace(Constants.CASSANDRA_KEYSPACE).getTables();
for(TableMetadata tableMetadata : tablesList){
	methBody = new StringBuilder();
	String tableName = tableMetadata.getName();
	String beanName = CreateJavaBeans.nameCC(tableName, true);
	String accessorName = beanName+"Accessor";
	String accessorVarName = CreateJavaBeans.nameCC(tableName, false)+"Accessor";
	String daoName = beanName+"Dao";
	String beanSign = "List<"+beanName+">";
	StringBuilder methSign = new StringBuilder("getAll()");
	StringBuilder methCall = new StringBuilder("getAll()");
	StringBuilder methSign1 = new StringBuilder();
	StringBuilder methCall1 = new StringBuilder();
	
	StringBuilder getCall = new StringBuilder();
	StringBuilder methCall2 = new StringBuilder();
	
	String allStr = "all";
	packageInfo = new StringBuilder();
	getPackageInfo(beanName, accessorName);
	StringBuilder startClass = new StringBuilder();

	startClass.append("\npublic class "+daoName+"{\n\n")
	.append("\tprivate static Logger logger = LoggerFactory.getLogger("+daoName+".class);\n\n");
	List<ColumnMetadata> partitionKeys = tableMetadata.getPartitionKey();
	int cklen = tableMetadata.getClusteringColumns().size();
	//List<ColumnMetadata> ckeys = tableMetadata.getClusteringColumns();
	int pklen = partitionKeys.size();
	List<ColumnMetadata> primaryKeys = tableMetadata.getPrimaryKey();
	
	for(int i=-2; i<cklen; i++){
		allStr = "all";
		beanSign = "List<"+beanName+">";

		if(i == -2){
			methBody.append(createMethod(beanSign, beanName, methSign, accessorName, accessorVarName, methCall, daoName, allStr));

		}else{
			methSign = new StringBuilder();
			methCall = new StringBuilder();
			methSign.append("fetch"+beanName+"Impl(");
			methCall.append("fetch"+beanName+"(");

			methCall1 = new StringBuilder();
			methSign1 = new StringBuilder();

			methSign1.append("delete"+beanName+"Impl(");
			methCall1.append("delete"+beanName+"(");
			
			getCall = new StringBuilder();
			methCall2 = new StringBuilder();
			
			getCall.append(""+beanName+" get"+beanName+"(");
			//methCall2.append("mapper.get(");

			for(int j=0; j<pklen+i+1;j++){
				ColumnMetadata metadata = primaryKeys.get(j);
				String javaType = getJavaDataType(metadata.getType());
				//System.out.println(":::"+ javaType);
				methSign.append(javaType+" "+CreateJavaBeans.nameCC(metadata.getName(), false));
				methCall.append(CreateJavaBeans.nameCC(metadata.getName(), false));
				


				methSign1.append(javaType+" "+CreateJavaBeans.nameCC(metadata.getName(), false));
				methCall1.append(CreateJavaBeans.nameCC(metadata.getName(), false));
				
				getCall.append(javaType+" "+CreateJavaBeans.nameCC(metadata.getName(), false));
				//System.out.println("###"+ getCall);
				methCall2.append(CreateJavaBeans.nameCC(metadata.getName(), false));
				//System.out.println("&&&"+ methCall2);

				if(j == pklen+i){
					methSign.append(")");
					methCall.append(")");

					methSign1.append(")");
					methCall1.append(")");
					
					getCall.append(")");
					methCall2.append(")");

				}else{
					methSign.append(", ");
					methCall.append(", ");

					methSign1.append(", ");
					methCall1.append(", ");
					
					getCall.append(", ");
					methCall2.append(", ");

				}
			}
			if(i==cklen-1){
				allStr = "one";
				beanSign = beanName;
			}else{
				allStr = "all";
				beanSign = "List<"+beanName+">";
			}
			methBody.append(createFetchMethods(beanSign, beanName, methSign, accessorName, accessorVarName, methCall, daoName, allStr));

			methBody.append(createDeleteMethods(beanSign, beanName, methSign1, accessorName, accessorVarName, methCall1, daoName, allStr));

			
			methBody.append(createMethod2(beanName, getCall, methCall2));
		}
	}

	

	
	methBody.append("}");
	File createDir = new File(Constants.CASSANDRA_CRUD_PACKAGE_DIR);
	if(!createDir.exists())
		createDir.mkdirs();
	File file = new File(Constants.CASSANDRA_CRUD_PACKAGE_DIR+"/"+daoName+".java");
	FileWriter stream;
	try {
		stream = new FileWriter(file);
		stream.write(packageInfo.append(startClass).append(selects).append(selectjsons).append(deletes).toString());
		//stream.write(packageInfo.append(startClass).append(saveClass).append(methBody).toString());
		//stream.write(packageInfo.append(startClass).append(methBody).toString());
		//stream.write(packageInfo.append(saveClass).append(methBody).toString());
		stream.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
System.out.println("CRUD's are successfully created ... ");
CassandraCluster.closeCluster();
}*/



////////////////////create method ////////////////////////
/*private static StringBuilder createMethod(String beanSign, String beanName, StringBuilder methSign, String accessorName, String accessorVarName, StringBuilder methCall, String daoName, String allStr){
StringBuilder singleMethBody = new StringBuilder();
singleMethBody.append("\tpublic "+beanSign+" "+methSign.toString()+"{\n")
.append("\t\t"+beanSign+" list = null;\n").append("\t\ttry{\n")
.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
.append("\t\t\t"+accessorName+" "+accessorVarName+" = mappingManager.createAccessor("+accessorName+".class);\n")
.append("\t\t\tResult<"+beanName+"> result = "+accessorVarName+"."+methCall.toString()+";\n")
.append("\t\t\tif(result != null)\n").append("\t\t\t\tlist = result."+allStr+"();\n").append("\t\t}catch(Exception e){\n")
.append("\t\t\tlogger.error(\"Exception Occured in "+methCall.toString()+" of "+daoName+" Class\", e);\n\t\t}\n\t\treturn list;\n\t}\n\n");
return singleMethBody;
}*/



/*///////////// delete method ////////////////////////////
private static StringBuilder createMethod1(String beanSign, String beanName, StringBuilder methSign1, String accessorName, String accessorVarName, StringBuilder methCall1, String daoName, String allStr){
	StringBuilder singleMethBody = new StringBuilder();
	singleMethBody.append("\tpublic "+beanSign+" "+methSign1.toString()+"{\n")
	.append("\t\t"+beanSign+" list = null;\n").append("\t\ttry{\n")
	.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
	.append("\t\t\t"+accessorName+" "+accessorVarName+" = mappingManager.createAccessor("+accessorName+".class);\n")
	.append("\t\t\tResult<"+beanName+"> result = "+accessorVarName+"."+methCall1.toString()+";\n")
	.append("\t\t\tif(result != null)\n").append("\t\t\t\tlist = result."+allStr+"();\n").append("\t\t}catch(Exception e){\n")
	.append("\t\t\tlogger.error(\"Exception Occured in "+methCall1.toString()+" of "+daoName+" Class\", e);\n\t\t}\n\t\treturn list;\n\t}\n\n");
	return singleMethBody;
}*/
///////////////////////////////////////////////////////////


//////////////save object  /////////////////
/*StringBuilder saveClass = new StringBuilder();
saveClass.append("\tpublic "+beanName+" save"+beanName+"("+beanName+" object){\n")
.append("\t\ttry {\n")
.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")
.append("\t\t\tMapper<"+beanName+"> mapper = mappingManager.mapper("+beanName+".class);\n")
.append("\t\t\tmapper.save(object);\n")	
.append("\t\t} finally {\n")
.append("\t\t\tlogger.error(\"Exception occured in save"+beanName+"("+beanName+" object)\");\n")
.append("\t\t}\n")
.append("\t\treturn object;\n")
.append("\t}\n\n");*/
//////////////////////////////////////////
