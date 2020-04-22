package cn.org.rapid_framework.generator;


import cn.org.rapid_framework.generator.provider.db.DbTableFactory;
import cn.org.rapid_framework.generator.provider.db.DbTableGeneratorModelProvider;
import cn.org.rapid_framework.generator.provider.db.model.Table;
import cn.org.rapid_framework.generator.provider.java.JavaClassGeneratorModelProvider;
import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.util.StringHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author badqiu
 *
 */
public class GeneratorFacade {

	private String tableSqlNamePrefix;
	private String deleteTableSqlNamePrefix;
	private String applicationPath;

	public GeneratorFacade() {
		tableSqlNamePrefix = GeneratorProperties.getRequiredProperty("tableSqlNamePrefix");
		deleteTableSqlNamePrefix = GeneratorProperties.getRequiredProperty("deleteTableSqlNamePrefix");
		applicationPath = getApplicationPath();
	}
	
	public void printAllTableNames() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		System.out.println("\n----All TableNames BEGIN----");
		for(int i = 0; i < tables.size(); i++ ) {
			String sqlName = ((Table)tables.get(i)).getSqlName();
			System.out.println("g.generateTable(\""+sqlName+"\");");
		}
		System.out.println("----All TableNames END----");
	}
	
	public void generateByAllTable() throws Exception {
		List<Table> tables = DbTableFactory.getInstance().getAllTables();
		for(int i = 0; i < tables.size(); i++ ) {
			generateByTable(tables.get(i).getSqlName());
		}
	}
	
	public void generateByTable(String tableName) throws Exception {

		/* 创建生成器（含模板路径和输出路径） */
		Generator g = createGeneratorForDbTable();

		/* 根据提供的表名，连接数据库，获取该表的信息，并转成java模型 */
		Table table = DbTableFactory.getInstance().getTable(tableName);

		/* 数据库表生成Java类名时，是否去除数据库表名的前缀 */
		table = generateCustomizeClassName(table);

		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}

	/**
	 * 自定义生成类名
	 * 即：数据库表生成Java类名时，是否去除数据库表名的前缀
	 * @param table
	 * @return
     */
	private Table generateCustomizeClassName(Table table) {
		if(!"true".equals(deleteTableSqlNamePrefix)){
			return table;
		}
		if(StringHelper.isNotEmpty(tableSqlNamePrefix) && table.getSqlName().toLowerCase().indexOf(tableSqlNamePrefix) == 0){
			String newTableName  = table.getSqlName().substring(tableSqlNamePrefix.length() ,table.getSqlName().length());
			newTableName = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(newTableName));
			table.setClassName(newTableName);
		}
		return table;
	}

	public void generateByTable(String tableName,String className) throws Exception {
		Generator g = createGeneratorForDbTable();
		Table table = DbTableFactory.getInstance().getTable(tableName);
		table.setClassName(className);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByClass(Class clazz) throws Exception {
		Generator g = createGeneratorForJavaClass();
		g.generateByModelProvider(new JavaClassGeneratorModelProvider(new JavaClass(clazz)));
	}
	
	public void clean() throws IOException {
		Generator g = createGeneratorForDbTable();
		g.clean();
	}
	
	public Generator createGeneratorForDbTable() {
		Generator g = new Generator();
		String templatePath = applicationPath + "/template";
		g.setTemplateRootDir(new File(templatePath).getAbsoluteFile());
		g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		return g;
	}
	
	private Generator createGeneratorForJavaClass() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template/javaclass").getAbsoluteFile());
		g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		return g;
	}

	/**
	 * 获取当前项目的根路径
	 * @return
     */
	private String getApplicationPath(){
		String applicationPath = "";
		applicationPath = getClass().getResource("/").getPath();
		int idx = applicationPath.lastIndexOf("/");
		applicationPath = applicationPath.substring(0, idx);
		idx = applicationPath.lastIndexOf("/");
		applicationPath = applicationPath.substring(0, idx);
		idx = applicationPath.lastIndexOf("/");
		applicationPath = applicationPath.substring(0, idx);
		System.out.println(String.format("the application path is %s", applicationPath));
		return applicationPath;
	}
}
