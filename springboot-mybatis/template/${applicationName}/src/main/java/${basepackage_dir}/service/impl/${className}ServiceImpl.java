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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.model.${className};
import ${basepackage}.model.query.${className}Query;
import ${basepackage}.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class ${className}ServiceImpl extends GenericService<${className}, Integer> implements ${className}Service {

    private static final Logger log = LoggerFactory.getLogger(${className}ServiceImpl.class);

    private ${className}Mapper ${classNameLower}Mapper;

    @Autowired
    public void set${className}Mapper(${className}Mapper ${classNameLower}Mapper) {
        this.${classNameLower}Mapper = ${classNameLower}Mapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public ${className} getByPK(java.lang.Integer primaryKey) {
        return ${classNameLower}Mapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<${className}> list() {
        return ${classNameLower}Mapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<${className}> listByProperty(${className} ${classNameLower}){
        return ${classNameLower}Mapper.listByProperty(${classNameLower});
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return ${classNameLower}Mapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        ${classNameLower}Mapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param ${classNameLower}
     * @return
     */
    public int deleteByProperty(${className} ${classNameLower}){
        return ${classNameLower}Mapper.deleteByProperty(${classNameLower});
    }

    /**
     * 保存记录
     * @param ${classNameLower}
     * @return
     */
    public int save(${className} ${classNameLower}){
        return ${classNameLower}Mapper.save(${classNameLower});
    }

    /**
     * 更新记录
     * @param ${classNameLower}
     * @return
     */
    public int update(${className} ${classNameLower}){
        return ${classNameLower}Mapper.update(${classNameLower});
    }

    /**
     * 根据条件查询记录条数
     * @param ${classNameLower}
     * @return
     */
    public int findByCount(${className} ${classNameLower}){
        return ${classNameLower}Mapper.findByCount(${classNameLower});
    }

    public int batchInsert(List<${className}> ${classNameLower}List){
        return ${classNameLower}Mapper.batchInsert(${classNameLower}List);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<${className}> findByPage(${className}Query ${classNameLower}Query) {
        PageHelper.startPage(${classNameLower}Query.getPageNum(), ${classNameLower}Query.getPageSize());
        List<${className}> ${classNameLower}List = ${classNameLower}Mapper.listByProperty(${classNameLower}Query);
        PageInfoDto pageInfoDto = new PageInfoDto<${className}>(${classNameLower}Query.getPageNum(),${classNameLower}Query.getPageSize(),0,0);
        if(null == ${classNameLower}List || ${classNameLower}List.size() == 0){
            pageInfoDto.setList(new ArrayList<${className}>());
            return pageInfoDto;
        }
        Page pageList = (Page<${className}>) ${classNameLower}List;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<${pkColumnJavaType}> ${pkColumn}List, ${className} ${classNameLower}) {
        return ${classNameLower}Mapper.batchUpdate( ${pkColumn}List, ${classNameLower});
    }
}
