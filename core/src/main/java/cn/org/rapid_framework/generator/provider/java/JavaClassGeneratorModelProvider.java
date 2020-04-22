package cn.org.rapid_framework.generator.provider.java;

import cn.org.rapid_framework.generator.IGeneratorModelProvider;
import cn.org.rapid_framework.generator.provider.db.model.Table;
import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class JavaClassGeneratorModelProvider implements IGeneratorModelProvider {
	JavaClass clazz;
	
	public JavaClassGeneratorModelProvider(JavaClass clazz) {
		super();
		this.clazz = clazz;
	}

	public String getDisaplyText() {
		return "JavaClass:"+clazz.getClassName();
	}

	public void mergeFilePathModel(Map model) throws Exception {
		model.putAll(BeanUtils.describe(clazz));
	}

	public void mergeTemplateModel(Map model) throws Exception {
		model.put("clazz",clazz);
	}

	public Table getTable() {
		return null;
	}
}
