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
import ${basepackage}.model.dto.${className}BatchDto;
import ${basepackage}.model.query.${className}Query;
import ${basepackage}.service.${className}Service;
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
    private ${className}Service ${classNameLower}Service;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<${className}>> findByPage(@RequestBody ${className}Query ${classNameLower}Query) {
        PageInfoDto<${className}> pageInfoDto = null;
        if(null == ${classNameLower}Query){
            pageInfoDto = new PageInfoDto<${className}>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(${classNameLower}Query.getPageNum() == null || ${classNameLower}Query.getPageNum() <= 0 ){
            ${classNameLower}Query.setPageNum(1);
        }
        if(${classNameLower}Query.getPageSize() == null || ${classNameLower}Query.getPageSize() <= 0 ){
            ${classNameLower}Query.setPageSize(20);
        }
        pageInfoDto  = ${classNameLower}Service.findByPage(${classNameLower}Query);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<${className}>(${classNameLower}Query.getPageNum(),${classNameLower}Query.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{${pkColumn}}")
    public ResponseObject<${className}> getByPK(@PathVariable("${pkColumn}") Integer ${pkColumn}){
        if(null == ${pkColumn} || ${pkColumn} < 0){
            return ResponseObject.error("非法参数");
        }
        ${className} ${classNameLower} = ${classNameLower}Service.getByPK(${pkColumn});
        return new ResponseObject<>(${classNameLower});
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<${className}>> listByProperty(@RequestBody ${className} ${classNameLower}) {
        List<${className}> ${classNameLower}List =  ${classNameLower}Service.listByProperty(${classNameLower});
        return new ResponseObject(${classNameLower}List);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody ${className} ${classNameLower}) {
        if(null == ${classNameLower} ){
            log.error("非法参数");
            return null;
        }
        ${classNameLower}.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ${classNameLower}.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = ${classNameLower}Service.save(${classNameLower});
    <#if pkColumnJavaType == "java.lang.Integer">
        int id = resultCount > 0 ? ${classNameLower}.get${pkColumn?cap_first}() : 0;
    <#elseif  pkColumnJavaType == "java.lang.Long">
        long id = resultCount > 0 ? ${classNameLower}.get${pkColumn?cap_first}() : 0L;
    <#else>
        String id = resultCount > 0 ? ${classNameLower}.get${pkColumn?cap_first}() : "";
    </#if>
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody ${className} ${classNameLower}) {
        if(null == ${classNameLower} || StringUtils.isEmpty(${classNameLower}.get${pkColumn?cap_first}()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = ${classNameLower}Service.update(${classNameLower});
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{${pkColumn}}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<${className}> detail(@PathVariable("${pkColumn}") Integer ${pkColumn}) {
        if(null == ${pkColumn} || ${pkColumn} < 0){
            return ResponseObject.error("非法参数");
        }
        ${className} ${classNameLower} = ${classNameLower}Service.getByPK(${pkColumn});
        return new ResponseObject(${classNameLower});
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{${pkColumn}}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("${pkColumn}") Integer ${pkColumn}) {
        if(null == ${pkColumn} || ${pkColumn} < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = ${classNameLower}Service.deleteByPK(${pkColumn});
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody ${className} ${classNameLower}) {
        if(null == ${classNameLower} || ObjectUtils.allFieldWithoutValue(${classNameLower})){
            log.error("非法参数,${classNameLower}=" + ${classNameLower});
            return ResponseObject.error("非法参数");
        }
        int count = ${classNameLower}Service.findByCount(${classNameLower});
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody ${className}BatchDto ${classNameLower}BatchDto){
        if(null == ${classNameLower}BatchDto || CollectionUtils.isEmpty(${classNameLower}BatchDto.get${pkColumn?cap_first}List())
                || null == ${classNameLower}BatchDto.get${className}()  || ObjectUtils.allFieldWithoutValue(${classNameLower}BatchDto.get${className}())){
            log.error("非法参数！${classNameLower}BatchDto=" + ${classNameLower}BatchDto);
            return ResponseObject.error("非法参数");
        }
        ${className} ${classNameLower} = ${classNameLower}BatchDto.get${className}();
        ${classNameLower}.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =${classNameLower}Service.batchUpdate(${classNameLower}BatchDto.get${pkColumn?cap_first}List(),${classNameLower});
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

}
