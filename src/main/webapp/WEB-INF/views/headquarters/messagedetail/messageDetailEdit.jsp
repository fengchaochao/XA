<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
</head>
<body style="width: 96%;">
<input type="hidden" id="id" value="${messageId}" />
<div id="mainContent">

	<table id="tb_board"></table> 
	
</div>

    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
    
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
		});
		var id=$("#id").val();
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/message/doGetMessageDetailList" />?messageId='+id,
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
                queryParams: queryParams,
                showRefresh : true,
				striped : true,
				silent : true,
				sortable: true, 
				sortOrder: "asc",
                responseHandler: responseHandler,
                columns : [ {
                	field: 'state',
					checkbox : true
				}, {
					field : 'userno',
					title : '户号',
					sortable:true
				}, {
					field : 'username',
					title : '用户姓名',
					sortable:true
				}],
                onLoadSuccess:function(){

                },
                formatLoadingMessage : function() {
									return "请稍等，正在加载中...";
								},
				formatNoMatches : function() { //没有匹配的结果
					return "无符合条件的记录";
				},
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });
        }
		
		
		// json 数据处理
        function responseHandler(res) {
			if (res.body.total > 0) {
				return {
					"rows" : res.body.rows,
					"total" : res.body.total
				}
			} else {
				return {
					"rows" : [],
					"total" : 0
				}
			}
		}
        
	</script>

</body>
</html>
