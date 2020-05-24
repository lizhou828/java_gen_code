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


/**
 */
public interface I${className}Service {


}
