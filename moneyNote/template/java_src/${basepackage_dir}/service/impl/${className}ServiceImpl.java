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
package ${basepackage}.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.model.${className};
import ${basepackage}.model.dto.Pagination;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class ${className}ServiceImpl   implements ${className}Service {

    private ${className}Mapper ${classNameLower}Mapper;

    @Autowired
    public void set${className}Mapper(${className}Mapper ${classNameLower}Mapper) {
        this.${classNameLower}Mapper = ${classNameLower}Mapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     * @
     */
    public ${className} getByPK(${pkColumnJavaType} primaryKey)  {
        return ${classNameLower}Mapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     * @
     */
    public List<${className}> list()  {
        return ${classNameLower}Mapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     * @
     */
    public List<${className}> listByProperty(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.listByProperty(${classNameLower});
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     * @
     */
    public int deleteByPK(${pkColumnJavaType} primaryKey)  {
        return ${classNameLower}Mapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     * @
     */
    public void deleteByPKeys(List<${pkColumnJavaType}> primaryKeys)  {
        ${classNameLower}Mapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param ${classNameLower}
     * @return
     * @
     */
    public int deleteByProperty(${className} ${classNameLower})  {
        return ${classNameLower}Mapper.deleteByProperty(${classNameLower});
    }

    /**
     * 保存记录
     * @param ${classNameLower}
     * @return
     * @
     */
    public void save(${className} ${classNameLower})  {
         ${classNameLower}Mapper.save(${classNameLower});
    }

    /**
     * 更新记录
     * @param ${classNameLower}
     * @return
     * @
     */
    public int update(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.update(${classNameLower});
    }

    /**
     * 根据条件查询记录条数
     * @param ${classNameLower}
     * @return
     * @
     */
    public int findByCount(${className} ${classNameLower})  {
        return ${classNameLower}Mapper.findByCount(${classNameLower});
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    public Pagination<${className}> findByPage(Integer pageNum, Integer pageSize, ${className} ${classNameLower}) {
        Pagination<${className}> pagination = new Pagination<${className}>();
        pagination.setPageNo(pageNum);
        pagination.setPageSize(pageSize);
        PageHelper.startPage(pageNum,pageSize);
        List<${className}> ${classNameLower}List = ${classNameLower}Mapper.listByProperty(${classNameLower});
        if(null == ${classNameLower}List || ${classNameLower}List.size() == 0){
            return pagination;
        }
        Page<${className}> page =  (Page<${className}>)${classNameLower}List;
        pagination.setTotal(Integer.valueOf(page.getTotal()+""));
        pagination.setTotalPage(page.getPages());
        pagination.setResultList(${classNameLower}List);
        return pagination;
    }
}
