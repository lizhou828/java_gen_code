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


/**
 */
public interface I${className}Service {

        ${className} getByPK(${pkColumnJavaType} ${pkColumn});

}
