<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.mapper;


import ${basepackage}.model.${className};
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ${className}Mapper extends GenericIBatisMapper <${className}, Integer>{

        int batchUpdate(@Param("${pkColumn}List") List<${pkColumnJavaType}> ${pkColumn}List, @Param("${classNameLower}") ${className} ${classNameLower});

}