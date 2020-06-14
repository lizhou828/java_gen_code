<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.columns as c>
    /* ${c.remarks} */
    private ${c.javaType} ${c.columnNameLower};

</#list>

<#list table.columns as c>
    /* get ${c.remarks} */
    public ${c.javaType} get${c.columnNameUpper}() {
        return ${c.columnNameLower};
    }

    /* set ${c.remarks} */
    public void set${c.columnNameUpper}(${c.javaType} ${c.columnNameLower}) {
        this.${c.columnNameLower} = ${c.columnNameLower};
    }
</#list>

    public String toString() {
        return "${className} {" +
            <#list table.columns as column>
                " , ${column.columnNameLower} = " + ${column.columnNameLower} +
            </#list>
            "}";
        }
}