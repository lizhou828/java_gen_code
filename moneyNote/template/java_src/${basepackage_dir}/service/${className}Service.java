<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkSqlName = c.sqlName >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.service;

import ${basepackage}.model.${className};
import ${basepackage}.model.dto.Pagination;
import java.util.List;

public interface ${className}Service{

    /**
     * 通过主键查询实体对象
     *
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public ${className} getByPK(${pkColumnJavaType} primaryKey) ;

    /**
     * 查询所有记录
     * @return
     * @throws Exception
     */
    public List<${className}> list() ;

    /**
     * 根据查询条件查询所有记录
     * @return
     * @throws Exception
     */
    public List<${className}> listByProperty(${className} ${classNameLower}) ;


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public int deleteByPK(${pkColumnJavaType} primaryKey) ;

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     * @throws Exception
     */
    public void deleteByPKeys(List<${pkColumnJavaType}>primaryKeys) ;

    /**
     * 根据传入参数删除记录
     * @param ${classNameLower}
     * @return
     * @throws Exception
     */
    public int deleteByProperty(${className} ${classNameLower}) ;

    /**
     * 保存记录
     * @param ${classNameLower}
     * @return
     * @throws Exception
     */
    public void save(${className} ${classNameLower}) ;

    /**
     * 更新记录
     * @param ${classNameLower}
     * @return
     * @throws Exception
     */
    public int update(${className} ${classNameLower}) ;

    /**
     * 根据条件查询记录条数
     * @param ${classNameLower}
     * @return
     * @throws Exception
     */
    public int findByCount (${className} ${classNameLower}) ;

    /**
     * 根据查询条件查询分页记录
     * @return
     * @throws Exception
     */
    public Pagination<${className}> findByPage(Integer pageNum, Integer pageSize, ${className} ${classNameLower});

}
