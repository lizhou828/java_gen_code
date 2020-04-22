<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model.query;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class ${className}Query implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.columns as c>
    /* ${c.remarks?if_exists} */
    @ApiModelProperty(name = "${c.columnNameLower}",value = "${c.remarks?if_exists}",example = "",required = true)
    private ${c.javaType} ${c.columnNameLower};

</#list>

    @ApiModelProperty(value = "每页的数据条数",example = "20")
    private Integer pageSize;

    @ApiModelProperty(value = "第几页（从1开始）",example = "1")
    private Integer pageNum;


<#list table.columns as c>
    /* get ${c.remarks?if_exists} */
    public ${c.javaType} get${c.columnNameUpper}() {
        return ${c.columnNameLower};
    }

    /* set ${c.remarks?if_exists} */
    public void set${c.columnNameUpper}(${c.javaType} ${c.columnNameLower}) {
        this.${c.columnNameLower} = ${c.columnNameLower};
    }
</#list>

    public Integer getPageSize() {
            return pageSize;
    }

    public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
    }

    public Integer getPageNum() {
            return pageNum;
    }

    public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
    }


    public String toString() {
        return "${className}Query {" +
            <#list table.columns as column>
                <#if column.pk>
                    " ${column.columnNameLower} = " + ${column.columnNameLower} +
                <#else>
                    " , ${column.columnNameLower} = " + ${column.columnNameLower} +
                </#if>
            </#list>
                    ", pageSize=" + pageSize +
                    ", pageNum=" + pageNum +
            "}";
        }
}