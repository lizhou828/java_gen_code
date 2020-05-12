<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.service;

import ${basepackage}.model.${className};
import ${basepackage}.model.PageInfoDto;
import ${basepackage}.model.query.${className}Query;
import java.util.List;

/**
 */
public interface ${className}Service extends GenericIService<${className},Integer>{

        PageInfoDto<${className}> findByPage(${className}Query ${classNameLower}Query);

        int batchUpdate(List<${pkColumnJavaType}> ${pkColumn}List, ${className} ${classNameLower});

}
