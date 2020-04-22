<#assign className = table.className>
<#assign tableName = table.sqlName>
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- page header -->
<div class="pageheader">
    <h2><i class="fa fa-table" style="line-height: 48px;"></i>${table.remarks?if_exists}<span>管理</span></h2>
    <%--<div class="breadcrumbs">--%>
        <%--<ol class="breadcrumb">--%>
            <%--<li>当前位置：</li>--%>
            <%--<li><a href="javascript:getPageByUrl('/indexAjax')" target="_blank">后台</a></li>--%>
            <%--<li><a href="javascript:(void(0))">所有表格</a></li>--%>
            <%--<li class="active"><a href="javascript:getPageByUrl('/form')" target="_blank">表单</a>></li>--%>
        <%--</ol>--%>
    <%--</div>--%>
</div>
<!-- /page header -->


<script type="text/javascript">
    function saveOrUpdate(){
        var url = $('#${classNameLower}Form').attr("action");
        $.ajax({
            type: "POST",
            url: url ,
            data: $('#${classNameLower}Form').serialize(),
            success: function (result) {
                if (result.code == 200) {
                    getPageByUrl(result.data);
                }else{
                    alert(result.message)
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.error(XMLHttpRequest.status);
                console.error(XMLHttpRequest.readyState);
                console.error(textStatus);
            }
        });
    }
    function cancel(){
        getPageByUrl("/${classNameLower}/listPage");
    }
</script>


<!-- content main container -->
<div class="main">

    <!-- row -->
    <div class="row">

        <!-- col 12 -->
        <div class="col-md-6">

            <!-- tile -->
            <section class="tile color transparent-black">

                <!-- tile header -->
                <div class="tile-header">
                    <h1><strong>${r"${"}id!= null && id > 0 ? '修改':'新建' ${r"}"}${table.remarks?if_exists}</strong>信息</h1>
                    <%--<div class="controls">--%>
                    <%--<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>--%>
                    <%--<a href="#" class="remove"><i class="fa fa-times"></i></a>--%>
                    <%--</div>--%>
                </div>
                <!-- /tile header -->

                <!-- tile body -->
                <div class="tile-body">

                    <form class="form-horizontal" role="form" action=" ${r"${"}${classNameLower} != null ? '/${classNameLower}/edit' : '/${classNameLower}/add' ${r"}"} " id="${classNameLower}Form" >
                        <input name="id" type="hidden" value='${r"${"} ${classNameLower} != null ? ${classNameLower}.id : '' ${r"}"}' />
                        <#list table.columns as c>
                            <#if !c.pk && c.javaType !="java.sql.Timestamp" >
                                <div class="form-group">
                                    <label for="${c.columnNameLower}" class="col-sm-4 control-label">${c.remarks?if_exists}</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="${c.columnNameLower}" id="${c.columnNameLower}" value="${r"${"}${classNameLower} != null ? ${classNameLower}.${c.columnNameLower} : '' ${r"}"}">
                                    </div>
                                </div>
                            </#if>
                        </#list>


                        <div class="form-group form-footer">
                            <div class="col-sm-offset-4 col-sm-8">
                                <button type="button" onclick="saveOrUpdate()" class="btn btn-primary">
                                    <c:choose>
                                        <c:when test='${r"${"} !empty ${classNameLower}${r"}"}'>
                                            保存
                                        </c:when>
                                        <c:otherwise>
                                            新增
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                                <button type="button" onclick="cancel()" class="btn btn-default">取消</button>
                            </div>
                        </div>

                    </form>

                </div>
                <!-- /tile body -->

            </section>
            <!-- /tile -->

        </div>
        <!-- /col 6 -->

    </div>
    <!-- /row -->

</div>