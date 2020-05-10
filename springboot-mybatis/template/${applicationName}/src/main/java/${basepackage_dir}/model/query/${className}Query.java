<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model.query;

import ${basepackage}.model.${className};
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class ${className}Query extends ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 排序字段 */
    @ApiModelProperty(name = "orderBy",value = "排序字段(自定义)",example = "",required = false)
    private String orderBy;

    /* 排序顺序 */
    @ApiModelProperty(name = "ascDesc",value = "排序顺序",example = "固定枚举值：'desc' 或者 'asc' ",required = false)
    private String ascDesc;

    @ApiModelProperty(value = "每页的数据条数",example = "20")
    private Integer pageSize;

    @ApiModelProperty(value = "第几页（从1开始）",example = "1")
    private Integer pageNum;



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


    @Override
    public String toString() {
        return "${className}Query{" +
        "orderBy='" + orderBy + '\'' +
        ", ascDesc='" + ascDesc + '\'' +
        ", pageSize=" + pageSize +
        ", pageNum=" + pageNum +
        '}';
    }
}