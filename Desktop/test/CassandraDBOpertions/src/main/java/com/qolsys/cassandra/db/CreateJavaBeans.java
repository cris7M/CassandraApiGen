package com.qolsys.cassandra.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.datastax.driver.core.ColumnMetadata;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.TableMetadata;
import com.datastax.driver.core.UserType;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;
/**
 * @author suresh
 *
 */
public class CreateJavaBeans {

	private static StringBuilder packages = new StringBuilder();
	private static Collection<TableMetadata> clusterInstanceMetaData;
	private static Collection<TableMetadata> getClusterMetaData(int port, String... hosts){
		if(clusterInstanceMetaData == null){
			clusterInstanceMetaData =  CassandraCluster.getClusterInstance(port, hosts)
					.getMetadata().getKeyspace(Constants.CASSANDRA_KEYSPACE).getTables();
		}
		return clusterInstanceMetaData;
	}

	private static int value = 0;

	public void create(){
		Collection<TableMetadata> tablesList = CreateJavaBeans.getClusterMetaData(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
		for(TableMetadata tableMetadata : tablesList){
			
			packages.append("import com.datastax.driver.mapping.annotations.Table;\n");
			packages.append("import com.datastax.driver.mapping.annotations.Column;\n");
			String tableNameModified = nameCC(tableMetadata.getName(), false);
			String className = tableNameModified.substring(0,1).toUpperCase()+tableNameModified.substring(1);
			//create class per table
			StringBuilder javaClass1 = new StringBuilder();
			StringBuilder javaClass2 = new StringBuilder();
			javaClass1.append("package "+Constants.CASSANDRA_PACKAGE+";\n\n");
			javaClass1.append("@Table(name=\""+tableMetadata.getName()+"\", keyspace=\""+tableMetadata.getKeyspace().getName()+"\")");
			javaClass1.append("\npublic class "+className+" {\n\n");
			List<String> clusteringColumnNames = prepareList(tableMetadata.getClusteringColumns());
			List<String> partitionColumnNames = prepareList(tableMetadata.getPartitionKey());
			int primaryKeyLength = clusteringColumnNames.size()+partitionColumnNames.size();
			int indexing = 0;			
			List<ColumnMetadata> columns = tableMetadata.getColumns();
			
			StringBuilder toStringVal = new StringBuilder();
			toStringVal.append("\t/**\n\t * String representation of Table(Entity) Object:"+className+
					" \n\t * \n\t * @see java.lang.Object#toString()\n\t */\n");
			toStringVal.append("\t@Override\n\tpublic String toString() {\n\t\treturn \"[");
			
			for(ColumnMetadata columnMetadata : columns){
				String dbColName = columnMetadata.getName();
				String cassandraColumnName = nameCC(dbColName, false);
				String javaDataType = getJavaDataType(columnMetadata.getType(), columnMetadata);
				if(value == 1){
					if(! packages.toString().contains("com.datastax.driver.mapping.annotations.FrozenKey"))
						packages.append("import com.datastax.driver.mapping.annotations.FrozenKey;\n");
					javaClass1.append("\t@FrozenKey\n");
				}else if(value == 2){
					if(! packages.toString().contains("com.datastax.driver.mapping.annotations.FrozenValue"))
						packages.append("import com.datastax.driver.mapping.annotations.FrozenValue;\n");
					javaClass1.append("\t@FrozenValue\n");
				}
				value = 0;
				if(indexing < primaryKeyLength){
					if(partitionColumnNames.contains(dbColName)){
						javaClass1.append("\t@PartitionKey("+partitionColumnNames.indexOf(dbColName)+")\n");
						if(! packages.toString().contains("com.datastax.driver.mapping.annotations.PartitionKey"))
							packages.append("import com.datastax.driver.mapping.annotations.PartitionKey;\n");
					}else if(clusteringColumnNames.contains(dbColName)){
						javaClass1.append("\t@ClusteringColumn("+clusteringColumnNames.indexOf(dbColName)+")\n");
						if(! packages.toString().contains("com.datastax.driver.mapping.annotations.ClusteringColumn"))
							packages.append("import com.datastax.driver.mapping.annotations.ClusteringColumn;\n");
					}
				}
				
				toStringVal.append(cassandraColumnName+"::\"+"+cassandraColumnName);
				if(indexing == columns.size()-1){
					toStringVal.append("+\"]\";\n\t}\n\n");
				}else{
					if(indexing%3 ==0){
						toStringVal.append("+\n\t\t\", ");
					}else
						toStringVal.append("+\", ");
				}
				
				indexing++;
				if(columnMetadata.getType().isFrozen())
					javaClass1.append("\t@Frozen\n");
				javaClass1.append("\t@Column(name=\""+columnMetadata.getName()+"\")\n");
				javaClass1.append("\tprivate "+javaDataType+" "+cassandraColumnName+";\n\n");
				String methName = cassandraColumnName.substring(0,1).toUpperCase()+cassandraColumnName.substring(1);
				javaClass2.append("\t/**\n\t * @return "+cassandraColumnName+" \n\t */\n");
				javaClass2.append("\tpublic "+javaDataType+" get"+methName+"(){\n\t\treturn "+cassandraColumnName+";\n\t}\n\n");
				javaClass2.append("\t/**\n\t * @param "+cassandraColumnName+" "+paramInfo.map.get(cassandraColumnName)+"\n\t */\n");
				javaClass2.append("\tpublic void set"+methName+"("+javaDataType+" "+cassandraColumnName+"){\n"+"\t\tthis."+cassandraColumnName+" = "+cassandraColumnName+";\n\t}\n\n");
			}

			javaClass2.append(toStringVal);
			javaClass2.append("}");
			String JavaClassDetails = javaClass1.toString()+"\n"+javaClass2.toString();
			packages.append("\n/**\n * "+className+" class corresponds to java bean class to table("
					+tableMetadata.getName()+ ") in database("+tableMetadata.getKeyspace().getName()+")\n *\n * @author cassandraIDC\n * \n */");
			File createDir = new File(Constants.CASSANDRA_BEAN_DIR);
			String verifyLiteral = "@Table";
			String modified = JavaClassDetails.toString().replace(verifyLiteral, packages.toString()+"\n"+verifyLiteral);

			if(!createDir.exists())
				createDir.mkdirs();
			File file = new File(Constants.CASSANDRA_BEAN_DIR+"/"+className+".java");
			FileWriter stream;
			try {
				stream = new FileWriter(file);
				stream.write(modified);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			packages = new StringBuilder();
		}
		System.out.println("Completed Java Beans Creation");
	}

	//get corresponding java type based on cassandra datatype
	public static String getJavaDataType(DataType type, ColumnMetadata columnMetadata) {
		String dataType = type.toString();
		if(dataType.equals("int")){
			return "int";
		}else if(dataType.equals("varint") || dataType.equals("counter")){
			return "long";
		}else if(dataType.equals("text") || dataType.equals("varchar")){
			return "String";
		}else if(dataType.equalsIgnoreCase("date")){
			if(! packages.toString().contains("com.datastax.driver.core.LocalDate"))
				packages.append("import com.datastax.driver.core.LocalDate;\n");
			return "LocalDate";
		}
		else if(dataType.equals("boolean")) {
			return "boolean";
		}else if(dataType.equalsIgnoreCase("timestamp")){
			if(! packages.toString().contains("java.util.Date"))
				packages.append("import java.util.Date;\n");
			return "Date";
		}else if(dataType.equalsIgnoreCase("uuid") || dataType.equalsIgnoreCase("timeuuid")){
			if(! packages.toString().contains("com.datastax.driver.core.utils.UUIDs"))
				packages.append("import com.datastax.driver.core.utils.UUIDs;\n");
			return "UUIDs";
		}
		else if(type.isFrozen()){
			if(! packages.toString().contains("com.datastax.driver.mapping.annotations.Frozen"))
				packages.append("import com.datastax.driver.mapping.annotations.Frozen;\n");
			String udt = nameCC(columnMetadata.getName(), true);
			//use UDT to create UDTClass along with column metadata
			UserType myUdt = (UserType) columnMetadata.getType();
			createUDT(myUdt, udt);
			return udt;
		}else if(type.isCollection()){
			return createJavaCollectionString(type);
		}else{
			return "tokka";
		}
	}

	static String nameCC(String name, boolean isFirstCharUC){
		char prev = '.';
		int len1 = name.replaceAll("[_]", "").length();
		char[] newCharArr = new char[len1];
		int incr = 0;
		char[] columnChars = name.toCharArray();
		for(char ch : columnChars){
			if(ch != '_'){
				if(prev == '_' || isFirstCharUC){
					isFirstCharUC = false;
					ch = Character.toUpperCase(ch);
				}
				newCharArr[incr++] = ch;  
			}
			prev = ch;
		}
		incr = 0;
		name = new String(newCharArr).trim();
		return name;
	}

	private static void createUDT(UserType type, String udtClassName){
		StringBuilder udtPackages = new StringBuilder();
		StringBuilder udtClass1 = new StringBuilder();
		StringBuilder udtClass2 = new StringBuilder();
		udtPackages.append("package "+Constants.CASSANDRA_PACKAGE+";\n\n");
		udtPackages.append("import com.datastax.driver.mapping.annotations.UDT;\n");
		udtPackages.append("import com.datastax.driver.mapping.annotations.Field;\n");
		udtClass1.append("\n/**\n * "+udtClassName+" class corresponds to java bean class to User Defined Type("
				+type.getTypeName()+ ") in database("+type.getKeyspace()+")\n *\n * @author cassandraIDC\n * \n */\n");
		udtClass1.append("@UDT(name=\""+type.getTypeName()+"\", keyspace=\""+type.getKeyspace()+"\")\n");
		udtClass1.append("public class "+udtClassName+" {\n\n");
		Set<String> set = (Set<String>) type.getFieldNames();
		
		StringBuilder toStringVal = new StringBuilder();
		toStringVal.append("\t/**\n\t * String representation of UDT Object:"+udtClassName+
				" \n\t * \n\t * @see java.lang.Object#toString()\n\t */\n");
		toStringVal.append("\t@Override\n\tpublic String toString() {\n\t\treturn \"[");
		int indexing = 0;
		
		for(String str : set){
			String udtFieldName = nameCC(str, false);
			String javaDataType = getJavaDataType(type.getFieldType(str), null);
			if(type.getFieldType(str).toString().contains("map") && 
					! udtPackages.toString().contains("java.util.Map"))
				udtPackages.append("import java.util.Map;\n");
			if(type.getFieldType(str).toString().contains("list") && 
					! udtPackages.toString().contains("java.util.List"))
				udtPackages.append("import java.util.List;\n");
			udtClass1.append("\t@Field(name=\""+str+"\")\n");
			
			toStringVal.append(udtFieldName+"::\"+"+udtFieldName);
			if(indexing == set.size()-1){
				toStringVal.append("+\"]\";\n\t}\n\n");
			}else{
				if(indexing%3 ==0){
					toStringVal.append("+\n\t\t\", ");
				}else
					toStringVal.append("+\", ");
			}
			indexing++;
			
			udtClass1.append("\tprivate "+javaDataType+" "+udtFieldName+";\n");
			String methName = udtFieldName.substring(0,1).toUpperCase()+udtFieldName.substring(1);
			udtClass2.append("\t/**\n\t * @return "+udtFieldName+" \n\t */\n");
			udtClass2.append("\tpublic "+javaDataType+" get"+methName+"(){\n\t\treturn "+udtFieldName+";\n\t}\n\n");
			udtClass2.append("\t/**\n\t * @param "+udtFieldName+" "+paramInfo.map.get(udtFieldName)+"\n\t */\n");
			udtClass2.append("\tpublic void set"+methName+"("+javaDataType+" "+udtFieldName+"){\n"+"\t\tthis."+udtFieldName+" = "+udtFieldName+";\n\t}\n\n");
		}
		
		udtClass2.append(toStringVal);
		
		udtClass2.append("}");
		String JavaClassDetails =  udtPackages.toString()+udtClass1.toString()+"\n"+udtClass2.toString();
		File createDir = new File(Constants.CASSANDRA_BEAN_DIR);

		if(!createDir.exists())
			createDir.mkdirs();
		File file = new File(Constants.CASSANDRA_BEAN_DIR+"/"+udtClassName+".java");
		FileWriter stream;
		try {
			stream = new FileWriter(file);
			stream.write(JavaClassDetails);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String createJavaCollectionString(DataType dataType){
		if(dataType != null){
			if(dataType.toString().contains("map")){
				if(! packages.toString().contains("java.util.Map"))
					packages.append("import java.util.Map;\n");

				List<DataType> argsList = dataType.getTypeArguments();
				StringBuilder mapStr = new StringBuilder("Map<");
				int len = argsList.size();
				for(int i=0; i<len; i++){
					DataType collDataType = argsList.get(i);
					if(collDataType.toString().equals("text")){
						mapStr.append("String");
					}else if(collDataType.toString().equals("int")){
						mapStr.append("Integer");
					}else if(collDataType.isFrozen()){
						if(i == 0)
							value = 1;
						else
							value = 2;
						UserType type = (UserType) collDataType;
						String udtClName = nameCC(type.getTypeName(), true);
						mapStr.append(udtClName);
						createUDT(type, udtClName);
					}
					if(i != len-1)
						mapStr.append(",");
					else
						mapStr.append(">");
				}
				return mapStr.toString();	
			}
			else if(dataType.toString().contains("list")){
				if(! packages.toString().contains("java.util.List"))
					packages.append("import java.util.List;\n");

				List<DataType> argsList = dataType.getTypeArguments();
				StringBuilder listStr = new StringBuilder("List<");
				DataType collDataType = argsList.get(0);
				if(collDataType.toString().equals("text")){
					listStr.append("String");
				}else if(collDataType.toString().equals("int")){
					listStr.append("Integer");
				}else if(collDataType.isFrozen()){
					value = 2;
					UserType type = (UserType) collDataType;
					String udtClName = nameCC(type.getTypeName(), true);
					listStr.append(udtClName);
					createUDT(type, udtClName);
				}
				listStr.append(">");
				return listStr.toString();
			}
		}
		return null;
	}

	private static List<String> prepareList(List<ColumnMetadata> columns){
		return columns.stream().map(ColumnMetadata::getName).collect(Collectors.toList());
	}
}

