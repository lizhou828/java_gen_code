<#include "/java_copyright.include">
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

package ${basepackage}.service;

import ${basepackage}.model.${className};
import ${basepackage}.model.PageInfoDto;
import ${basepackage}.model.query.${className}Query;

/**
 */
public interface ${className}Service extends GenericIService<${className},Integer>{

        PageInfoDto<${className}> findByPage(${className}Query ${classNameLower}Query);

}
