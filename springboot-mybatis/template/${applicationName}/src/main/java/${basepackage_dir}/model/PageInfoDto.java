package ${basepackage}.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhou on 2020/4/23.
 */
@ApiModel(value = "通用分页对象DTO")
public class PageInfoDto<T> implements Serializable{

    private static final long serialVersionUID = 5578733873852383382L;
    @ApiModelProperty(value = "当前页码(从1开始)",dataType = "int",example = "1",required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "每页数据条数",dataType = "int",example = "10",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "总页数",dataType = "int",example = "10",required = false)
    private Integer pages;

    @ApiModelProperty(value = "总数据条数",dataType = "int",example = "10",required = false)
    private Integer total;

    @ApiModelProperty( value = "返回数据")
    private List<T> list;

    public PageInfoDto() {
    }

    public PageInfoDto(Integer pageNum, Integer pageSize, Integer pages, Integer total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
    }

    public PageInfoDto(Integer pageNum, Integer pageSize, Integer pages, Integer total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        if(null == list){
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
