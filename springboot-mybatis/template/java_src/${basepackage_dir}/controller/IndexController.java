package ${basepackage}.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger log = LogManager.getLogger(IndexController.class);

    @GetMapping("/index")
    public Object index() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "Hello world!";
    }
}