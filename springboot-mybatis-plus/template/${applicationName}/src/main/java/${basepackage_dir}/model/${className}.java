<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@ApiModel(value = "${table.remarks?if_exists}")
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.columns as c>
    /* ${c.remarks?if_exists} */
<#if  c.pk  >
    @ApiModelProperty(name = "${c.columnNameLower}",value = "主键id",example = "",required = false)
<#else>
    @ApiModelProperty(name = "${c.columnNameLower}",value = "${c.remarks?if_exists}",example = "",required = true)
</#if>
    private ${c.javaType} ${c.columnNameLower};
</#list>

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

    public String toString() {
        return "${className} {" +
            <#list table.columns as column>
                <#if column.pk>
                    " ${column.columnNameLower} = " + ${column.columnNameLower} +
                <#else>
                    " , ${column.columnNameLower} = " + ${column.columnNameLower} +
                </#if>
            </#list>
            "}";
        }
}