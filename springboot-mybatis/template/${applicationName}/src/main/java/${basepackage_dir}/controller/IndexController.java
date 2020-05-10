package ${basepackage}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    public Object index() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "Hello world!";
    }
}