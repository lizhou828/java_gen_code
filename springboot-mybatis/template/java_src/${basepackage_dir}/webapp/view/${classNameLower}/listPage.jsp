<#assign className = table.className>
<#assign tableName = table.sqlName>
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="/assets/js/vendor/vue/vue_v2.6.10_dev.js"></script>

<!-- 生产环境版本，优化了尺寸和速度 -->
<%--<script src="/assets/js/vendor/vue/vue_v2.6.10.js"></script>--%>


<!-- page header -->
<div class="pageheader">
    <h2><i class="fa fa-table" style="line-height: 48px;"></i>${table.remarks?if_exists}<span>列表页</span></h2>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li>当前位置：</li>
            <li><a href="javascript:getPageByUrl('/indexAjax')" target="_blank">后台管理中心</a></li>
            <li><a href="javascript:(void(0))">${table.remarks?if_exists}</a></li>
            <li class="active"><a href="javascript:getPageByUrl('/${classNameLower}/listPage')" target="_blank">列表页</a>></li>
        </ol>
    </div>
</div>
<!-- /page header -->


<!-- content main container -->
<div class="main">


    <!-- row -->
    <div class="row">

        <!-- col 12 -->
        <div class="col-md-12">

            <!-- tile -->
            <section class="tile color transparent-black" id="ajaxPageTable">

                <!-- tile header -->
                <div class="tile-header">
                    <%--<h1><strong>自定义样式</strong>表格</h1>--%>
                    <%--<div class="controls">--%>
                    <%--<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>--%>
                    <%--<a href="#" class="remove"><i class="fa fa-times"></i></a>--%>
                    <%--</div>--%>
                </div>
                <!-- /tile header -->

                <!-- tile widget -->
                <div class="form-horizontal" style="color: #fafafa;">
                    <div class="row">
                        <#list table.columns as c>
                            <#if c_index+1 <= 4>
                            <div class="form-group col-md-3">
                                <label class="col-sm-3 control-label" for="${c.columnNameLower}">${c.remarks?if_exists}</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="${c.columnNameLower}" placeholder="${c.remarks?if_exists}">
                                </div>
                            </div>
                            </#if>
                        </#list>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-3">
                            <div class="col-sm-12" style="text-align: center" id="buttonList">
                                <button type="submit" class="btn btn-greensea btn-greensea-border" style="background-color: transparent;border: 1px solid #1ccdaa;" @click="searchData"><i class="fa fa-search"></i>搜&nbsp;&nbsp;索</button>
                                <button type="reset" class="btn btn-red" style="background-color: transparent;border: 1px solid #ff7b76;">重&nbsp;&nbsp;置</button>
                                <button type="reset" class="btn btn-orange" style="background-color: transparent;border: 1px solid #ffc100;" @click="gotoAddPage">新&nbsp;&nbsp;增</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /tile widget -->

                <!-- tile body -->
                <div class="tile-body nopadding">
                    <table class="table table-bordered table-sortable table-hover">
                        <thead id="table_thead">
                        <tr>
                            <#list table.columns as c>
                                <#if c_index+1 <= 10>
                                    <#if c.pk>
<%--                                        <th>--%>
<%--                                            <div class="checkbox check-transparent">--%>
<%--                                                <input type="checkbox" id="allchck_${c.columnNameLower}">--%>
<%--                                                <label for="allchck_${c.columnNameLower}"></label>--%>
<%--                                            </div>--%>
<%--                                        </th>--%>
                                        <th class="sortable sort-alpha">${c.remarks?if_exists}</th>
                                    <#else>
                                        <th class="sortable sort-alpha">${c.remarks?if_exists}</th>
                                    </#if>
                                </#if>
                            </#list>
                            <th style="width: 5%">操&nbsp;&nbsp;作</th>
                        </tr>
                        </thead>

                        <tbody id="table_body">
                            <tr v-for="(data,i) in dataList" :key="i">
<%--                                <td>--%>
<%--                                    <div class="checkbox check-transparent">--%>
<%--                                        <input type="checkbox" :value="data.id" id="chck01">--%>
<%--                                        <label for="chck01"></label>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
                                <td>{{data.id}}</td>
                    <#list table.columns as c>
                        <#if c_index+1 <= 10>
                            <#if !c.pk && c.javaType != "java.sql.Timestamp">
                                <td>{{data.${c.columnNameLower}}}</td>
                            <#elseif  c.javaType = "java.sql.Timestamp">
                                <td>{{formatTime(data.${c.columnNameLower})}}</td>
                            </#if>
                        </#if>
                    </#list>
                                <td>
                                        <span style="width: 50%;text-align: center;margin-left: 15%">
                                            <a href="javascript:void(0)" @click="getPageByUrl(data.id)" class="fa fa-edit" title="编辑"></a>
                                        </span>

                                        <span style="width: 50%;text-align: center;margin-left: 15%">
                                            <a href="javascript:void(0)" @click="deleteById(data.id)" class="check-toggler" title="删除"></a>
                                        </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /tile body -->


                <!-- tile footer -->
                <div class="tile-footer bg-transparent-black-2 rounded-bottom-corners">
                    <div class="row">
                        <div class="col-sm-6 text-left">
                            <%--<small class="inline table-options paging-info">显示第1至 10 条结果，共 <span id="totalCount">11,235</span> 条</small>--%>
                            <small class="inline table-options paging-info" style="margin-right: 15px;">共{{pageCount}}页，{{totalCount}}条记录</small>
                            <ul class="pagination pagination-xs nomargin pagination-custom" >
                                <li class="active" @click="changePageSize(10,$event)"><a href="javascript:void(0)">10</a></li>
                                <li @click="changePageSize(20,$event)"><a href="#">20</a></li>
                                <li @click="changePageSize(50,$event)"><a href="#">50</a></li>
                            </ul>
                            <span style="margin-right: 30px;vertical-align:middle">条每页</span>
                        </div>

                        <div class="col-sm-6 text-right sm-center">
                            <%--自定义vue组件      开始--%>
                            <pagination :cur="currentPage" :all="pageCount" :page-num="pageSize" v-on:to_parent="gotoPage"></pagination>
                            <%--自定义vue组件      结束--%>
                        </div>
                    </div>
                </div>
                <!-- /tile footer -->
            </section>
            <!-- /tile -->
        </div>
        <!-- /col 12 -->
    </div>
    <!-- /row -->
</div>
<!-- /content container -->

<script type="text/template" id="paginationTpl">
    <ul class="pagination pagination-xs nomargin pagination-custom" v-if="all > 1">
        <li :class="showFirst ? '':'disabled'"><a href="javascript:void(0)" @click="cur--"><i class="fa fa-angle-double-left"></i></a></li>

        <li v-for="index in indexes"  :class="{ 'active': cur == index}">
            <a @click="btnClick(index)" href="javascript:void(0)">{{index}}</a>
        </li>

        <li :class="showLast ? '':'disabled'" ><a  @click="cur++" href="javascript:void(0)"><i class="fa fa-angle-double-right"></i></a></li>
    </ul>
</script>
<script type="text/javascript">
    Vue.component('pagination',{
        template:'#paginationTpl',
        replace:true,
        props:[
            'cur',   //当前页
            'all',   //所有页数
            'pageNum'//每页条数
        ],
        methods:{
            //页码点击事件
            btnClick: function(index){
                if(index != this.cur){
                    this.cur = index;
//                    this.$emit('to_parent', this.cur);
                }
            }
        },
        watch:{
            cur : function(val,oldVal) {
//                console.info("分页组件监听当前页是否发生: val= " + val + ",oldVal ="+oldVal );
//                用$emit来触发一个自定义gotoPage事件，并传递一个参数给父组件
                this.$emit('to_parent', this.cur);
            }
        },
        computed:{
            indexes : function(){
                var list = [];
                //计算左右页码
                var mid = parseInt(this.pageNum / 2);//中间值
                var left = Math.max(this.cur - mid,1);
                var right = Math.max(this.cur + this.pageNum - mid -1,this.pageNum);
                if (right > this.all ) { right = this.all}
                while (left <= right){
                    list.push(left);
                    left ++;
                }
//                console.info("计算页码：this.cur=" + this.cur + ",this.pageNum=" + this.pageNum);
                return list;
            },
            showLast: function(){
                return this.cur != this.all;
            },
            showFirst: function(){
                return this.cur != 1;
            }
        }
    });

    var vum = new Vue({
        el:"#ajaxPageTable",
        data:{
            pageSize:10, //每页数据条数
            dataList:"",//结果集合
            totalCount:"",//总数据条数
            pageCount:"",//总页数
            currentPage:"" //当前页
        },
        methods:{
            searchData:function(){
                loadData()
            },
            changePageSize:function(_pageSize,event){
                this.pageSize = _pageSize;
                $(event.currentTarget).siblings().removeClass("active");
                $(event.currentTarget).addClass("active");
            },
            gotoPage:function(_page){
//                alert("gotoPage ，跳转到第" + _page +"页");
                this.currentPage = _page;
            },
            getPageByUrl:function (_id) {
                getPageByUrl('/${classNameLower}/formPage?id='+_id);
            },
            deleteById:function(_id){
                deleteById(_id)
            },
            gotoAddPage:function(){
                getPageByUrl('/${classNameLower}/formPage');
            },
            formatTime:function(_data){
                return formatTimeStapsToDateStr(_data);
            }

        },
        watch:{
            pageSize:function(val,oldVal){
                loadData();
            },
            currentPage:function(val,oldVal){
                loadData();
            }

        }
    });

    $(function(){
        //check all checkboxes
        $('table thead input[type="checkbox"]').change(function () {
            $(this).parents('table').find('tbody input[type="checkbox"]').prop('checked', $(this).prop('checked'));
        });

        // sortable table
        $('.table.table-sortable th.sortable').click(function() {
            var o = $(this).hasClass('sort-asc') ? 'sort-desc' : 'sort-asc';
            $(this).parents('table').find('th.sortable').removeClass('sort-asc').removeClass('sort-desc');
            $(this).addClass(o);
        });

        //chosen select input
        $(".chosen-select").chosen({disable_search_threshold: 10});

        //check toggling
//        $('.check-toggler').on('click', function(){
//            $(this).toggleClass('checked');
//        });

        //禁止输入框使用历史数据，防止样式变糟
        $("input").attr("autocomplete","off");

        loadData();

    })

    function deleteById(id){
        var url = "/${classNameLower}/delete?id="+id;
        if(confirm("是否删除该条记录？")) {
            $.ajax({
                type: "POST",
                url: url,
                success: function (result) {
                    if (result.code == 200) {
                        getPageByUrl(result.data);
                    } else {
                        alert(result.message)
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    }

    function loadData() {
    <#list table.columns as c>
        <#if c.javaType !="java.sql.Timestamp" >
            var ${c.columnNameLower} = $("#${c.columnNameLower}").val();
        </#if>
    </#list>

        $.ajax({
            url:"/${classNameLower}/findByPage",
            type:"post",
            data:{
            <#list table.columns as c>
                <#if c.javaType !="java.sql.Timestamp" >
                '${c.columnNameLower}':${c.columnNameLower},
              </#if>
           </#list>
                pageNum:vum.currentPage,
                pageSize:vum.pageSize
            },
            success:function(data){
//                console.info(data);
//                vum.message = "返回总数据条数：" + data.totalCount;
                vum.dataList = data.data;
                vum.totalCount = data.totalCount;
                vum.pageCount = Math.ceil(data.totalCount / vum.pageSize);//计算总页数
//                alert("AJAX查询后的数据：vum.pageSize=" + vum.pageSize + ",data.totalCount =" + data.totalCount + ",vum.pageCount=" + vum.pageCount );
            },
            error:function (e) {
                console.error(e)
            }
        })
    }
</script>




