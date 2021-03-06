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
    #tb_board th:nth-child(3){width:25%;}
    #tb_board th:last-child{width:20%;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商家分类及抽成设置</span>
       <p style="width:580px;">
           <em>商家实收款</em>
           <c:if test="${showAmount=='0' }">
	           <input type="button" value="显示" class="closeBtn" id="1" onclick="isShow(this);"/>
	           <input type="button" value="不显示" id="0" class="startBtn" disabled="disabled"/>
           </c:if>
            <c:if test="${showAmount=='1' }">
	           <input type="button" value="显示" id="1" class="startBtn"   disabled="disabled"/>
	           <input type="button" value="不显示" id="0" class="closeBtn"  onclick="isShow(this);"/>
           </c:if>
           
           <input type="button" id="btn_add" value="新增分类" class="greenBtn" style="margin-top:13px;"/>
       </p>
       
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox" style="min-height:500px;">
		<div id="mainContent">
		    <form id="formSearch" class="form-horizontal">
			</form>
			<table id="tb_board" ></table> 
		</div>
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
    			    area: ['60%', '60%'],
    			    fix: false, //不固定
    			    maxmin: true,
    			    content: '<spring:url value="/headquarters/businessClassification/doEnBusinessClassificationEdit" />'
    			});
            });
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/businessClassification/doReadBusinessClassificationList" />',
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
					field : 'classificationNumber',
					title : '编号'
				}, {
					field : 'categoryName',
					title : '分类名称'
				}, {
					field : 'firstLockInto',
					title : '第一次锁定抽成(%)'
				}, {
					field : 'consumption',
					title : '锁定消费者消费抽成(%)'
				}, {
					field : 'drainageConsumption',
					title : '引流消费抽成(%)'
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
			    area: ['60%', '60%'], 
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/headquarters/businessClassification/doEnBusinessClassificationEdit"/>'+'?businessClassificationId='+row.id
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
        function isShow(obj){
	        var isShow=$(obj).attr("id");
        	if(isShow=="0"){
       	 	   layer.confirm('确认隐藏商家实收款？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessClassification/doIsShowBusinessAmount"/>',
					dataType: "json",
					async: false,
					success: function(data){
						top.layer.msg(data.body,{icon:1});
						window.location.href='<spring:url value="/headquarters/businessClassification/doEnBusinessClassificationList" />';
					}
				});
			}, function(){
				layer.msg('已取消', {icon: 1});
			});
	       	 }
	          if(isShow=="1"){
	       	 	layer.confirm('确认显示商家实收款？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessClassification/doIsShowBusinessAmount"/>',
					dataType: "json",
					async: false,
					success: function(data){
						top.layer.msg(data.body,{icon:1});
						window.location.href='<spring:url value="/headquarters/businessClassification/doEnBusinessClassificationList" />';
					}
				});
			}, function(){
				layer.msg('已取消', {icon: 1});
			});
	       	 }   
        
        }
	</script>

</body>
</html>
