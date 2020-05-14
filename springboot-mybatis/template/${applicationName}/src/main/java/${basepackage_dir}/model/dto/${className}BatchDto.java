<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#list table.columns as c>
    <#if  c.pk  >
        <#assign pkColumn = c.columnNameLower >
        <#assign pkColumnJavaType = c.javaType >
    </#if>
</#list>
package ${basepackage}.model.dto;

import ${basepackage}.model.${className};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "${table.remarks?if_exists}批量DTO")
public class ${className}BatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "${pkColumn}List",value = "${pkColumn}集合",example = "",required = true)
    private List<${pkColumnJavaType}> ${pkColumn}List;

    @ApiModelProperty(name = "${classNameLower}",value = "商品sku",example = "",required = true)
    private ${className} ${classNameLower};

    @ApiModelProperty(name = "${classNameLower}List",value = "集合(批量新增时使用)",example = "",required = true)
    private List<${className}> ${classNameLower}List;

    public List<${pkColumnJavaType}> get${pkColumn?cap_first}List() {
        return ${pkColumn}List;
    }

    public void set${pkColumn?cap_first}List(List<${pkColumnJavaType}> ${pkColumn}List) {
        this.${pkColumn}List = ${pkColumn}List;
    }

    public ${className} get${className}() {
        return ${classNameLower};
    }

    public void set${className}(${className} ${classNameLower}) {
        this.${classNameLower} = ${classNameLower};
    }

    public List<${className}> get${className}List() {
        return ${classNameLower}List;
    }

    public void set${className}List(List<${className}> ${classNameLower}List) {
        this.${classNameLower}List = ${classNameLower}List;
    }

    @Override
    public String toString() {
        return "${className}BatchDto{" +
            "${pkColumn}List=" + ${pkColumn}List +
            ", ${classNameLower}=" + ${classNameLower} +
            ", ${classNameLower}List=" + ${classNameLower}List +
        '}';
    }
}