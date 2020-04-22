<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;

import ${basepackage}.mapper.${className}Mapper;
import ${basepackage}.model.${className};
import java.util.List;

import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class ${className}ServiceImpl extends GenericService<${className}, Integer> implements ${className}Service {

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

     /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfo<${className}> findByPage(Page<${className}> page, ${className} ${classNameLower}) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<${className}> ${classNameLower}List = ${classNameLower}Mapper.listByProperty(${classNameLower});
        Page pageList = (Page<${className}>) ${classNameLower}List;

        PageInfo pageInfo = new PageInfo<${className}>();
        if(null == ${classNameLower} || ${classNameLower}List.size() == 0){
            return pageInfo;
        }
        pageInfo.setList(pageList.getResult());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
