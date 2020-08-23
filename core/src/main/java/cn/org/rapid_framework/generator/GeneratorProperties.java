package cn.org.rapid_framework.generator;

import cn.org.rapid_framework.generator.provider.db.DbTypeEnum;
import cn.org.rapid_framework.generator.util.PropertiesHelper;
import cn.org.rapid_framework.generator.util.StringHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * 用于装载generator.properties文件
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class GeneratorProperties {

	static final String PROPERTIES_FILE_NAME = "generator.properties";
	public static final String DB_TYPE = "dbType";
	
	static PropertiesHelper props;
	private GeneratorProperties(){}

	/**
	 * 加载全局的配置文件
	 */
	private static void loadProperties() {
		try {
			System.out.println("Load [generator.properties] from classpath");
			props = new PropertiesHelper(loadAllPropertiesByClassLoader(PROPERTIES_FILE_NAME));
			
			String basepackage = getRequiredProperty("basepackage");//包名
			String basepackage_dir = basepackage.replace('.', '/'); //包名所在的路径名
			props.setProperty("basepackage_dir", basepackage_dir);
			
			for(Iterator it = props.entrySet().iterator();it.hasNext();) {
				Map.Entry entry = (Map.Entry)it.next();
				System.out.println("[Property] "+entry.getKey()+"="+entry.getValue());
			}

			/* 塞入不同数据库类型的全局变量，从而生成不同的SQL语句（例如获取时间的函数就不一样） */
			String jdbcUrl = props.getProperty("jdbcUrl");
			String jdbcDriver = props.getProperty("jdbcDriver");
			if(StringHelper.isNotEmpty(jdbcUrl) && StringHelper.isNotEmpty(jdbcDriver)){
				if(jdbcUrl.startsWith("jdbc:mysql:") && jdbcDriver.startsWith("com.mysql.jdbc")){
					props.setProperty(DB_TYPE, DbTypeEnum.MySQL.toString());
				}else if(jdbcUrl.startsWith("jdbc:oracle:") && jdbcDriver.startsWith("oracle.jdbc.driver")){
					props.setProperty(DB_TYPE, DbTypeEnum.Oracle.toString());
				}
				//TODO 其他数据库的处理
			}

		}catch(IOException e) {
			throw new RuntimeException("Load Properties error",e);
		}
	}
	
	public static Properties getProperties() {
		return getHelper().getProperties();
	}
	
	private static PropertiesHelper getHelper() {
		if(props == null)
			loadProperties();
		return props;
	}
	
	public static String getProperty(String key, String defaultValue) {
		return getHelper().getProperty(key, defaultValue);
	}
	
	public static String getProperty(String key) {
		return getHelper().getProperty(key);
	}
	
	public static String getRequiredProperty(String key) {
		return getHelper().getRequiredProperty(key);
	}
	
	public static int getRequiredInt(String key) {
		return getHelper().getRequiredInt(key);
	}
	
	public static boolean getRequiredBoolean(String key) {
		return getHelper().getRequiredBoolean(key);
	}
	
	public static String getNullIfBlank(String key) {
		return getHelper().getNullIfBlank(key);
	}
	
	public static void setProperty(String key,String value) {
		getHelper().setProperty(key, value);
	}
	
	public static void setProperties(Properties v) {
		props = new PropertiesHelper(v);
	}

	public static Properties loadAllPropertiesByClassLoader(String resourceName) throws IOException {
		Properties properties = new Properties();
		Enumeration urls = GeneratorProperties.class.getClassLoader().getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			InputStream input = null;
			try {
				URLConnection con = url.openConnection();
				con.setUseCaches(false);
				input = con.getInputStream();
				properties.load(input);
			}
			finally {
				if (input != null) {
					input.close();
				}
			}
		}
		return properties;
	}
}
