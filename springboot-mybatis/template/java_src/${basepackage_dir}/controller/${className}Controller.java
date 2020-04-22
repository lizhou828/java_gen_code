<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ${basepackage}.constants.ResponseCode;
import ${basepackage}.model.ResponseObject;
import ${basepackage}.model.${className};
import ${basepackage}.model.query.${className}Query;
import ${basepackage}.service.${className}Service;
import com.diyun.dubbo.commonUtils.utils.ObjectUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
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
    @Reference
    private ${className}Service ${classNameLower}Service;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfo<${className}Query>> findByPage(@RequestBody ${className}Query ${classNameLower}Query) {
        ${className} ${classNameLower} = new ${className}();
        BeanUtils.copyProperties(${classNameLower}Query,${classNameLower});
        if(${classNameLower}Query.getPageNum() == null || ${classNameLower}Query.getPageNum() <= 0 ){
            ${classNameLower}Query.setPageNum(1);
        }
        if(${classNameLower}Query.getPageSize() == null || ${classNameLower}Query.getPageSize() <= 0 ){
            ${classNameLower}Query.setPageSize(20);
        }
        PageInfo<${className}> pageInfo  = ${classNameLower}Service.findByPage(${classNameLower}Query.getPageNum(),${classNameLower}Query.getPageSize(),${classNameLower});

        if(CollectionUtils.isEmpty(pageInfo.getList())){
            PageInfo<${className}Query> pageInfoQuery = new PageInfo<${className}Query>();
            pageInfoQuery.setList(new ArrayList<>());
            return new ResponseObject<PageInfo<${className}Query>>().ok(pageInfoQuery);
        }

        List<${className}Query> ${classNameLower}QueryList = new ArrayList<>();
        ${className}Query ${classNameLower}QueryNew = null;
        for(${className} ${classNameLower}New : pageInfo.getList()){
            ${classNameLower}QueryNew = new ${className}Query();
            BeanUtils.copyProperties(${classNameLower}New,${classNameLower}QueryNew);
            ${classNameLower}QueryList.add(${classNameLower}QueryNew);
        }
        PageInfo<${className}Query> result = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,result);
        result.setList(${classNameLower}QueryList);
        return new ResponseObject().ok(result);
    }


    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody ${className} ${classNameLower}) {
        int resultCount = 0;
        if(!ObjectUtils.allFieldWithoutValue(${classNameLower})){
            ${classNameLower}.setCreateTime(new Timestamp(System.currentTimeMillis()));
            resultCount = ${classNameLower}Service.save(${classNameLower});
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setMessage(resultCount > 0 ? "操作成功" : "操作失败");
        return responseObject;
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody ${className} ${classNameLower}) {
        int resultCount = 0;
        if(null != ${classNameLower} && ${classNameLower}.getId() != null && ${classNameLower}.getId() > 0){
            resultCount = ${classNameLower}Service.update(${classNameLower});
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setMessage(resultCount > 0 ? "操作成功" : "操作失败");
        return responseObject;
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete",method = RequestMethod.POST,name = "删除操作")
    public ResponseObject delete(Integer id) {
        int resultCount = 0;
        if( id != null && id > 0){
            resultCount = ${classNameLower}Service.deleteByPK(id);
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setMessage(resultCount > 0 ? "操作成功" : "操作失败");
        return responseObject;
    }

}
