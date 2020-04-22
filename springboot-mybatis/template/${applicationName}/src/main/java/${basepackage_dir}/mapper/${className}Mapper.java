<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.mapper;


import ${basepackage}.model.${className};

public interface ${className}Mapper extends GenericIBatisMapper <${className}, Integer>{

}