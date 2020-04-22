package cn.org.rapid_framework.generator.provider.db;


import cn.org.rapid_framework.generator.IGeneratorModelProvider;
import cn.org.rapid_framework.generator.provider.db.model.Table;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 
 * @author badqiu
 *
 */
public class DbTableGeneratorModelProvider implements IGeneratorModelProvider {
	Table table;
	
	public DbTableGeneratorModelProvider(Table table) {
		super();
		this.table = table;
	}

	public String getDisaplyText() {
		return table.toString();
	}

	public void mergeFilePathModel(Map model) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		model.putAll(BeanUtils.describe(table));
	}

	public void mergeTemplateModel(Map model) {
		model.put("table",table);
	}

	public Table getTable() {
		return table;
	}
}
