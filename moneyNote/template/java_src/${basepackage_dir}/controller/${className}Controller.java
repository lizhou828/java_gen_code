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

package ${basepackage}.controller;

import ${basepackage}.model.${className};

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liz.mn.model.dto.Pagination;
import com.liz.mn.model.dto.RequestModel;
import ${basepackage}.service.${className}Service;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/${classNameLower}")
public class ${className}Controller {

    private static Log log = LogFactory.getLog(${className}Controller.class);

    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
     * 通过主键查询实体对象
     * @return
     */
    @RequestMapping(value = "/getByPK/{key}", method = RequestMethod.GET)
    public ResponseObject<${className}> getByPK(@PathVariable("key") ${pkColumnJavaType} key) throws Exception {
        ${className} ${classNameLower} = ${classNameLower}Service.getByPK(key);
        return new ResponseObject(${classNameLower});
    }

    /**
     * 分页查询记录
     * @return
     */
    @RequestMapping(value = {"/listPg"}, method = RequestMethod.POST)
    public ResponseObject<Pagination<${className}>> findByPagination(@RequestBody RequestModel<${className}> requestModel) throws Exception {
        if(null == requestModel){
            requestModel = new RequestModel<>();
            requestModel.setPageNo(1);
            requestModel.setPageSize(20);
        }
        Pagination<${className}> ${classNameLower}Pagination =  ${classNameLower}Service.findByPage(requestModel.getPageNo(),requestModel.getPageSize(), requestModel.getParam());
        return new ResponseObject(${classNameLower}Pagination);
    }

    /**
     * 新增记录
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody ${className} ${classNameLower}) throws Exception {
        if(null == ${classNameLower} ){
            return ResponseObject.error("非法参数");
        }
        ${classNameLower}Service.save(${classNameLower});
        if(null != ${classNameLower}.get${pkColumn?cap_first}() && ${classNameLower}.get${pkColumn?cap_first}() > 0){
            return ResponseObject.ok();
        }
        return ResponseObject.error();
    }

    /**
     * 根据多条主键值删除记录
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseObject delete(@RequestBody List<${pkColumnJavaType}> list) throws Exception {
        try{
            ${classNameLower}Service.deleteByPKeys(list);
            return ResponseObject.ok();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ResponseObject.error();
    }

    /**
     * 修改记录
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseObject update(@RequestBody ${className} ${classNameLower}) throws Exception {
        int result = ${classNameLower}Service.update(${classNameLower});
        return result > 0 ? ResponseObject.ok() : ResponseObject.error();
    }

}


