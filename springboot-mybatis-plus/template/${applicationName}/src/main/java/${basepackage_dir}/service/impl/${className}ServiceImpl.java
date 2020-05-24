<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${basepackage}.mapper.${className}DAO;
import ${basepackage}.model.${className};
import java.util.ArrayList;
import java.util.List;

import ${basepackage}.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ${className}ServiceImpl implements I${className}Service {

    private static final Logger log = LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Autowired
    private ${className}DAO ${classNameLower}DAO;



}
