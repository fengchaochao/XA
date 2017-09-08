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
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商家管理</span>
       <span class="greenBtn" id="btn_add">新增商家</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
			<div class="form-group" style="margin-top: 15px;">
				<label class="control-label" for="businessState" style="float: left;">商家分类</label>
				<div class="col-sm-2">
					<select name="merchantsClassificationId" id = "merchantsClassificationId" class="form-control" >
						<option value="">请选择商家分类</option>
						<c:forEach items="${businessClassificationList}" var="var">
							<option value="${var.id}">${var.categoryName}</option>
						</c:forEach>
					</select> 
				</div>
				<label class="control-label" for="businessState" style="float: left;margin-left: 3%;">状态</label>
				<div class="col-sm-2">
					<select name="businessState" id = "businessState" class="form-control" >
						<option value="">请选择用户状态</option>
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select> 
				</div>
				<label class="control-label" for="keySeach" style="float: left;"></label>
				<div class="col-sm-2" style="width:245px;">
					<input type="text" class="form-control" id="keySeach" style="width:210px;" placeholder="请输入商家名称或商家编号"/>
				</div>
				<button type="button" id="btn_query" class="inquiryComBtn">查询</button>
			</div>
		</form>
		<table id="tb_board"></table> 
	</div>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
			// 搜索
			$('#btn_query').click(function(){
				$('#tb_board').bootstrapTable('getOptions').pageNumber=1;
				$('#tb_board').bootstrapTable('refresh');
			});
			// 添加
            $('#btn_add').click(function () {
    			window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationAdd" />';
            });
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/businessInformation/doReadBusinessInformationList" />',
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
					field : 'merchantNumber',
					title : '商家编号'
				}, {
					field : 'vendorName',
					title : '商家名称'
				}, {
					field : 'typeName',
					title : '商家分类'
				}, {
					field : 'merchantPhone',
					title : '手机号码'
				}, {
					field : 'address',
					title : '地址'
				}, {
					field : 'agentName',
					title : '上级代理商'
				}, {
					field : 'localUser',
					title : '锁定用户数'
				}, {
					field : 'recommendedBusiness',
					title : '推荐商家数'
				}, {
					field : 'businessState',
					title : '状态',
					formatter : statusFormatter
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
			window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+row.id+'&flag=1';
        }
        // 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='1')
            	return "停用";
            if(value=='0')
            	return "正常";
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
           var row=$table.bootstrapTable('getData')[index];
        	var state=row.businessState;
        	if(state=="0"){
           		 var e = '<a href="#" class="blueText" mce_href="#" onclick="editRecord(\''+ index + '\')">查看</a> <a href="#" class="redText" mce_href="#" onclick="delRecord(\''+ index + '\')">停用</a>';  
        	}
        	if(state=="1"){
           		 var e = '<a href="#" class="blueText" mce_href="#" onclick="editRecord(\''+ index + '\')">查看</a> <a href="#" class="greenText" mce_href="#" onclick="delRecord(\''+ index + '\')">启用</a>';  
        	}
            return e;  
        }
		// 查询参数设置  
		function queryParams(params) {
			var temp = {
				//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的    
				limit : params.limit, //页面大小    
				offset : params.offset, //页码   
				order : params.order,
				orderName : params.sort,
				merchantsClassificationId : $("#merchantsClassificationId").val(),
				businessState : $("#businessState").val(),
				//merchantNumber : $("#merchantNumber").val(),
				keySeach: $("#keySeach").val()
			};
			return temp;
		};
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
		//停用
        function delRecord(index){
        	var row=$table.bootstrapTable('getData')[index];
        	var state=row.businessState;
        	if(state=="0"){
       	 	   layer.confirm('确认停用吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doDelBusinessInformation"/>',
					data:{
						businessInformationId:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/businessInformation/doReadBusinessInformationList" />'});
							layer.msg('操作成功', {icon: 1});
						}
						else{
							layer.msg('操作失败', {icon: 6});
						}
					}
				});
			}, function(){
				layer.msg('已取消', {icon: 1});
			});
	       	 }
	          if(state=="1"){
	       	 	layer.confirm('确认启用吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doDelBusinessInformation"/>',
					data:{
						businessInformationId:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/businessInformation/doReadBusinessInformationList" />'});
							layer.msg('操作成功', {icon: 1});
						}
						else{
							layer.msg('操作失败', {icon: 6});
						}
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
