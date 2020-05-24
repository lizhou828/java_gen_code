package ${basepackage}.service;
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
import ${basepackage}.ApplicationTests;
import ${basepackage}.model.${className};
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class ${className}ServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(${className}ServiceTest.class);
    @Autowired
    private ${className}Service ${classNameLower}Service;


    @Test
    public void getByPKTest() throws Exception {
        ${className} ${classNameLower}  = ${classNameLower}Service.getByPK(1);
        System.out.println(${classNameLower});
    }

}
