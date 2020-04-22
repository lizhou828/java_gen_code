<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

import ${basepackage}.constant.ResponseCode;
import ${basepackage}.model.ResponseObject;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;
import com.diyun.commonDubboService.common.utils.ObjectUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.sql.Timestamp;


/**
 * @author Code Generator
 * @date 创建于${.now?string("yyyy-MM-dd hh:mm:SS,sss")}  <#-- freemarker特殊变量参考 http://freemarker.foofun.cn/ref_specvar.html  -->
 * ${table.remarks?if_exists}管理
 */
@Controller
@RequestMapping(value = "/${classNameLower}",name = "${table.remarks?if_exists}管理")
public class ${className}Controller extends BaseController{
    @Autowired
    private ${className}Service ${classNameLower}Service;

    @RequestMapping(value="listPage",method = RequestMethod.GET,name = "列表页面")
    public String listPage() {
        return "${classNameLower}/listPage";
    }

    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "分页查询")
    @ResponseBody
    public Map findByPage(${className} ${classNameLower}) {
        Page page = getPageInfoFromRequest();
        PageInfo<${className}>  pageInfo = ${classNameLower}Service.findByPage(page.getPageNum(),page.getPageSize(), ${classNameLower});
        return handlePageInfoToJson(pageInfo);
    }

    /**
     * 表单页
     * 新增和编辑功能共用
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value="formPage",method = RequestMethod.GET,name = "编辑或新增页面")
    public String editPage(Integer id ,ModelMap map) {
        ${className} ${classNameLower} = null;
        if(id != null && id > 0){
            ${classNameLower} = ${classNameLower}Service.getByPK(id);
        }
        if(null != ${classNameLower}){
            map.put("${classNameLower}",${classNameLower});
            map.put("id",id);
        }
        return "${classNameLower}/formPage";
    }

    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    @ResponseBody
    public ResponseObject add(${className} ${classNameLower}) {
        int resultCount = 0;
        if(!ObjectUtils.allFieldWithoutValue(${classNameLower})){
            ${classNameLower}.setCreateTime(new Timestamp(System.currentTimeMillis()));
            resultCount = ${classNameLower}Service.save(${classNameLower});
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setData("/${classNameLower}/listPage");
        return responseObject;
    }

    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    @ResponseBody
    public ResponseObject editPage(${className} ${classNameLower}) {
        int resultCount = 0;
            if(null != ${classNameLower} && ${classNameLower}.getId() != null && ${classNameLower}.getId() > 0){
            resultCount = ${classNameLower}Service.update(${classNameLower});
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setData("/${classNameLower}/listPage");
        return responseObject;
    }

    @RequestMapping(value="delete",method = RequestMethod.POST,name = "删除操作")
    @ResponseBody
    public ResponseObject delete(Integer id) {
        int resultCount = 0;
        if( id != null && id > 0){
            resultCount = ${classNameLower}Service.deleteByPK(id);
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(resultCount > 0 ? ResponseCode.OK : ResponseCode.ERROR);
        responseObject.setData("/${classNameLower}/listPage");
        return responseObject;
    }

}
