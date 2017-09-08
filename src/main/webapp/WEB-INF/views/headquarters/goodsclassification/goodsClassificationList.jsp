<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
    .whiteBox{min-height:500px;}
    #tb_board th:nth-child(2){width:40px;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商品分类</span>
       <span id="btn_add" class="greenBtn">新增分类</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
		</form>
		<table id="tb_board"></table> 
	</div>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
			// 添加
            $('#btn_add').click(function () {
            	parent.layer.open({
    				title:"添加",
    			    type: 2,
    			    area: ['40%', '60%'],
    			    fix: false, //不固定
    			    maxmin: true,
    			    content: '<spring:url value="/headquarters/goodsClassification/doEnGoodsClassificationEdit" />'
    			});
            });
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/goodsClassification/doReadGoodsClassificationList" />',
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
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
					field : 'goodsNumber',
					title : '编号'
				}, {
					field : 'categoryName',
					title : '商品分类'
				},{
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    formatter:operateFormatter
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
		// 编辑操作
        function editRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
			parent.layer.open({
				title:"编辑窗口",
			    type: 2,
			    area: ['40%', '60%'], 
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/headquarters/goodsClassification/doEnGoodsClassificationEdit"/>'+'?goodsClassificationId='+row.id
			});
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
            var e = '<a href="#" class="blueText" mce_href="#" onclick="editRecord(\''+ index + '\')">修改</a> ';  
            return e;  
        }
		//被子页面调用
		function beCalled(data){
			$('#tb_board').bootstrapTable('refresh');
			if(data.body){
				top.layer.msg("更新成功!",{icon:1});
			}else{
				top.layer.msg("更新失败！",{icon:2});
			}
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