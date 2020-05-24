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


import ${basepackage}.mapper.MyBatisBaseDao;
import ${basepackage}.model.${className};
import ${basepackage}.model.${className}Example;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ${className}DAO extends MyBatisBaseDao<${className}, ${pkColumnJavaType}, ${className}Example> {

//        int batchUpdate(@Param("${pkColumn}List") List<${pkColumnJavaType}> ${pkColumn}List, @Param("${classNameLower}") ${className} ${classNameLower});

}