
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
public class CreateDaoClasses {


	private static StringBuilder packageInfo = new StringBuilder();
	private static StringBuilder selects = null;
	private static StringBuilder deletes = null;
	private static StringBuilder selectjsons = null;

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
			startClass.append("\n/**\n * "+daoName+" performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to "
					+tableName+ " \n * table in database. It contains following features as described below:<br>"
							+ "\n * 1.Fetch pojos based on search criteria<br>\n * 2.Fetch records as an array of Json Strings<br>"
							+ "\n * 3.Save or Update a record by pojo or Json<br>\n * 4.Insert multiple records via batch statements<br>"
							+ "\n * 5.Delete records based on recommended criteria\n *\n * @author cassandraIDC\n * \n */");
			startClass.append("\npublic class "+daoName+"{\n\n")
			.append("\tprivate static Logger logger = LoggerFactory.getLogger("+daoName+".class);\n\n");
			List<ColumnMetadata> partitionKeys = tableMetadata.getPartitionKey();
			int cklen = tableMetadata.getClusteringColumns().size();
			int pklen = partitionKeys.size();
			List<ColumnMetadata> primaryKeys = tableMetadata.getPrimaryKey();
			//StringBuilder getobject = new StringBuilder();
			for(int i=-2; i<cklen; i++){
				List<String> varNames = new ArrayList<String>();
				List<String> colNames = new ArrayList<String>();
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
						String javaType = getJavaDataType(metadata.getType());
						if(javaType.equalsIgnoreCase("int") || javaType.equalsIgnoreCase("int")){
							varNames.add("*"+colVarName);
						}else{
							varNames.add(colVarName);
						}
						colNames.add(dbcolName);

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
				selects.append(createFetchMethods(beanSign, beanName, methSign, accessorName, accessorVarName, methCall, daoName, allStr, varNames));
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
			File createDir = new File(Constants.CASSANDRA_DAO_PACKAGE_DIR);
			if(!createDir.exists())
				createDir.mkdirs();
			File file = new File(Constants.CASSANDRA_DAO_PACKAGE_DIR+"/"+daoName+".java");
			FileWriter stream;
			try {
				stream = new FileWriter(file);
				stream.write(packageInfo.append(startClass).append(selects).append(selectjsons).append(deletes).toString());
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DAO's are successfully created ... ");
		//CassandraCluster.closeCluster();
	}

	/////////// fetch method //////////////
	static StringBuilder createFetchMethods(String beanSign, String beanName, StringBuilder methSign, String accessorName, String accessorVarName, StringBuilder methCall, String daoName, String allStr, List<String> varNames){
		StringBuilder body = new StringBuilder();
		body.append("\t/**\n\t* Fetch Method for all the records based on search criteria for the Class ["+daoName+"]");
		for(String name:varNames){
			name = name.replace("*", "");
			body.append("\n\t* @param "+name+" "+paramInfo.map.get(name));
		}
		body.append("\n\t* @exception Exception");
		body.append("\n\t* @return "+beanSign);
		body.append("\n\t*/");
		body.append("\n\tpublic "+beanSign+" "+methSign.toString()+"{\n")
		.append("\t\t"+beanSign+" list = null;\n").append("\t\ttry{\n")
		.append("\t\t\tMappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));\n")		
		.append("\t\t\t"+accessorName+" "+accessorVarName+" = mappingManager.createAccessor("+accessorName+".class);\n")
		.append("\t\t\tResult<"+beanName+"> result = "+accessorVarName+"."+methCall.toString()+";\n")
		.append("\t\t\tif(result != null)\n").append("\t\t\t\tlist = result."+allStr+"();\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured in "+methCall.toString()+" of "+daoName+" Class\", e);\n\t\t}\n\t\treturn list;\n\t}\n\n");
		return body;
	}


	/////////////// delete method //////////////////////////
	static StringBuilder createDeleteMethods(StringBuilder methSign, List<String> varnames, List<String> colnames, String tableName){
		StringBuilder body = new StringBuilder();
		body.append("\t/**\n\t* Delete method for all the records based on search criteria for the Class ["+tableName+"]");
		for(String name:varnames){
			name = name.replace("*", "");
			body.append("\n\t* @param "+name+" "+paramInfo.map.get(name));
		}
		body.append("\n\t* @exception Exception");
		body.append("\n\t* @return boolean");
		body.append("\n\t*/");
		body.append("\n\tpublic boolean "+methSign.toString().replace("fetch", "delete")+"{\n")
		.append("\t\tboolean status = false;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tString deleteQuery = \"delete from "+tableName+" where ");
		int len = varnames.size();
		for(int i=0; i < len; i++){
			String varName = varnames.get(i);
			if(varName.contains("*")){
				body.append(colnames.get(i)+"=\"+"+varName.replace("*", ""));
			}else{
				body.append(colnames.get(i)+"='\"+"+varnames.get(i));
			}
			if(i != (len-1)){
				if(varName.contains("*")){
					body.append("+\" and ");
				}else{
					body.append("+\"' and ");
				}
			}
			else{
				if(varName.contains("*")){
					body.append("+\"\";\n");
				}else{
					body.append("+\"'\";\n");
				}
			}
		}
		body.append("\t\t\tsession.execute(deleteQuery);\n").append("\t\t\tstatus = true;\n")
		.append("\t\t}catch(Exception e){\n")
		.append("\t\t\tlogger.error(\"Exception Occured while performing delete() on "+tableName+" table\", e);\n\t\t}\n\t\treturn status;\n\t}\n\n");
		return body;
	}


	////////// fetch create json /////////////////
	static StringBuilder createFetchJsonMethods(StringBuilder methSign, List<String> varnames, List<String> colnames, String tableName){
		StringBuilder body = new StringBuilder();
		body.append("\t/**\n\t* Fetch Method for all the Json records in string array based on search criteria for the Class ["+tableName+"]");
		for(String name:varnames){
			name = name.replace("*", "");
			body.append("\n\t* @param "+name+" "+paramInfo.map.get(name));
		}
		body.append("\n\t* @exception Exception");
		body.append("\n\t* @return String[]");
		body.append("\n\t*/");
		body.append("\n\tpublic String[] "+methSign.toString().replace("fetch", "fetchJson")+"{\n")
		.append("\t\tString str[] = null;\n").append("\t\ttry{\n")
		.append("\t\t\tSession session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);\n")
		.append("\t\t\tString fetchJsonQuery = \"select json * from "+tableName+" where ");
		int len = varnames.size();
		for(int i=0; i < len; i++){
			String varName = varnames.get(i);
			if(varName.contains("*")){
				body.append(colnames.get(i)+"=\"+"+varName.replace("*", ""));
			}else{
				body.append(colnames.get(i)+"='\"+"+varnames.get(i));
			}
			if(i != (len-1)){
				if(varName.contains("*")){
					body.append("+\" and ");
				}else{
					body.append("+\"' and ");
				}
			}
			else{
				if(varName.contains("*")){
					body.append("+\"\";\n");
				}else{
					body.append("+\"'\";\n");
				}
			}
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
		builder.append("\t/**\n\t* Save or Update Method for one record via object mapper for bean "+beanName);
		builder.append("\n\t* @param "+beanVarName+" variable referencing the object corresponds to "+beanName);
		builder.append("\n\t* @exception Exception");
		builder.append("\n\t* @return boolean");
		builder.append("\n\t*/");
		builder.append("\n\tpublic boolean save"+beanName+"("+beanName+" "+beanVarName+"){\n")
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
		builder.append("\t/**\n\t* Insert record into database which takes Json as input param for table "+tableName);
		builder.append("\n\t* @param json json representation for record that refers table "+tableName);
		builder.append("\n\t* @exception Exception");
		builder.append("\n\t* @return boolean");
		builder.append("\n\t*/");
		builder.append("\n\tpublic boolean insertBy"+beanName+"Json(String json){\n")
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
		builder.append("\t/**\n\t* Bulk inserts or updates for the bean "+beanName);
		builder.append("\n\t* @param list List of "+beanName+" objects to be inserted or updated");
		builder.append("\n\t* @exception Exception");
		builder.append("\n\t* @return boolean");
		builder.append("\n\t*/");
		builder.append("\n\tpublic boolean bulk"+beanName+"Insert(List<"+beanName+"> list){\n")
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
		packageInfo.append("package "+Constants.CASSANDRA_DAO_PACKAGE+";\n\n")
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
