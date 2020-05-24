/*
 *Project: manager
 *File: com.diyun.manager.controller.BaseController.java <2018年12月05日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package ${basepackage}.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhou
 * @version 1.0
 * @Date 2018年12月05日 15时16分
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 统一的异常处理
     * @param ex
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error(ex.getMessage(),ex);
        HashMap map = new HashMap();
        map.put("statusCode", 500);
        map.put("message", ex.getMessage());
        return map;
    }

    /**
     * 带条件的分页查询---处理前端的请求参数(jquery.datatable插件)
     * @return
     */
    public Page getPageInfoFromRequest(){
        String pageNumStr = request.getParameter("pageNum");//从数据库中哪个位置开始查询
        String pageSizeStr = request.getParameter("pageSize"); //每页展示的条数
        int pageNum = Integer.parseInt(StringUtils.isEmpty(pageNumStr) ? "1" : pageNumStr);
        int pageSize = Integer.parseInt(StringUtils.isEmpty(pageSizeStr) ? "10" : pageSizeStr);
        Page page = new Page();
        page.setPageNum( pageNum <= 0 ?  1 : pageNum);
        page.setPageSize(pageSize <= 0 ?  10 :pageSize);
        return page;
    }

    public Map handlePageInfoToJson(PageInfo pageInfo){
        Map map = new HashMap<>();
        if(null == pageInfo || CollectionUtils.isEmpty(pageInfo.getList())){
            pageInfo = new PageInfo();
        }
        map.put("data",pageInfo.getList());
        map.put("pageSize",pageInfo.getPageSize());
        map.put("totalCount",pageInfo.getTotal());
        return map;
    }
}
