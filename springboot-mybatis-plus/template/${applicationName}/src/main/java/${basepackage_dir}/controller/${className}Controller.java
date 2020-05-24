<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.controller;

import ${basepackage}.model.PageInfoDto;
import ${basepackage}.enums.DropStateEnum;
import ${basepackage}.model.ResponseObject;
import ${basepackage}.model.${className};
import ${basepackage}.model.${className}Example;
import ${basepackage}.service.I${className}Service;
import ${basepackage}.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.*;
import java.sql.Timestamp;


/**
 * @author Code Generator
 * @date 创建于${.now?string("yyyy-MM-dd hh:mm:SS,sss")}  <#-- freemarker特殊变量参考 http://freemarker.foofun.cn/ref_specvar.html  -->
 * ${table.remarks?if_exists}管理
 */
@RestController
@RequestMapping(value = "/api/${classNameLower}",name = "${table.remarks?if_exists}管理")
@Api(value="${table.remarks?if_exists}",tags = "${table.remarks?if_exists}接口")
public class ${className}Controller extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private I${className}Service ${classNameLower}Service;

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{${pkColumn}}")
    public ResponseObject<${className}> getByPK(@PathVariable("${pkColumn}") ${pkColumnJavaType} ${pkColumn}){
        if(null == ${pkColumn} || ${pkColumn} < 0){
            return ResponseObject.error("非法参数");
        }
        ${className} ${classNameLower} = ${classNameLower}Service.getByPK(${pkColumn});
        return new ResponseObject<>(${classNameLower});
    }


}
