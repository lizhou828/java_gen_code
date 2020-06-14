<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkSqlName = c.sqlName >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.mapper;

import java.util.List;

import ${basepackage}.model.${className};
import ${basepackage}.mapper.GenericIBatisMapper;

public interface ${className}Mapper extends GenericIBatisMapper <${className}, ${pkColumnJavaType}>{

}