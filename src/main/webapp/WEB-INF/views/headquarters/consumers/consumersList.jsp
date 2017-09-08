<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <script type="text/javascript" src='<spring:url value="/js/My97DatePicker/WdatePicker.js" />'></script><!-- 时间插件 -->
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">消费者管理</span>
    </div>
    <!--end pageHeadline -->
	<div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
			<div class="form-group" style="margin-top: 15px;">
				<label class="control-label" for="merchantsState" style="float: left;">状态</label>
				<div class="col-sm-2">
					<select name="merchantsState" id = "merchantsState" class="form-control" style="margin-bottom:15px;" >
						<option value="">请选择用户状态</option>
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select> 
				</div>
				<label class="control-label" for="isXfconsumers" style="float: left;">消费商</label>
				<div class="col-sm-2">
					<select name="isXfconsumers" id = "isXfconsumers" class="form-control" style="margin-bottom:15px;" >
						<option value="">请选择是否是消费商</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select> 
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="keySeach" style="margin-bottom:15px;" placeholder="请输入昵称或编号"/>
				</div>
				<div class="chooseTimeCom">
					<label class="control-label" for="userno" style="float: left;">注册时间</label>
					<div>
						<input type="text" class="form-control" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-bottom:15px;"/>
					</div>
					<div>
						<input type="text" class="form-control" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="margin-bottom:15px;"/>
					</div>
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
				var beginDate=$("#beginDate").val();
				var endDate=$("#endDate").val();
				if(new Date(endDate).getTime()-new Date(beginDate)<0){
					top.layer.alert("开始时间不能大于结束时间!",{icon:2});
					return;
				}else{
					$('#tb_board').bootstrapTable('getOptions').pageNumber=1;
					$('#tb_board').bootstrapTable('refresh');
				}
			});
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/consumers/doReadConsumersList" />',
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
					field : 'createDate',
					title : '注册日期'
				}, {
					field : 'customerNumber',
					title : '消费者编号'
				}, {
					field : 'nickName',
					title : '消费者昵称'
				}, {
					field : 'phone',
					title : '联系电话'
				}, {
					field : 'address',
					title : '地址'
				}, {
					field : 'totalPrice',
					title : '总消费额'
				}, {
					field : 'isXfconsumers',
					title : '消费商',
					formatter : consumersStatusFormatter 
				}, {
					field : 'localUser',
					title : '锁定用户数',
					formatter : localUserFormatter 
				}, {
					field : 'recommendedBusiness',
					title : '推广商家数',
					formatter : recommendedBusinessFormatter 
				}, {
					field : 'agent.agentName',
					title : '上级代理商',
					formatter : agentNameFormatter 
				}, {
					field : 'merchantsState',
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
			window.location.href='<spring:url value="/headquarters/consumers/doEnConsumersEdit"/>'+'?consumersId='+row.id;
        }
        // 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='1')
            	return "停用";
            if(value=='0')
            	return "正常";
        }
        // 显示数据格式化 
        function consumersStatusFormatter(value, row, index) {
            if(value=='1')
            	return "是";
            if(value=='0')
            	return "否";
        }
        // 显示数据格式化 
        function localUserFormatter(value, row, index) {
         	var row=$table.bootstrapTable('getData')[index];
         	var state=row.isXfconsumers;
         	 if(state=='0')
            	return "无";
            if(state=='1')
            	return value;
        }
        // 显示数据格式化 
        function recommendedBusinessFormatter(value, row, index) {
            var row=$table.bootstrapTable('getData')[index];
         	var state=row.isXfconsumers;
         	 if(state=='0')
            	return "无";
            if(state=='1')
            	return value;
        }
        // 显示数据格式化 
        function agentNameFormatter(value, row, index) {
            var row=$table.bootstrapTable('getData')[index];
         	var state=row.isXfconsumers;
         	 if(state=='0')
            	return "无";
            if(state=='1')
            	return value;
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
            var row=$table.bootstrapTable('getData')[index];
        	var state=row.merchantsState;
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
				beginDate : $("#beginDate").val(),
				endDate : $("#endDate").val(),
				merchantsState : $("#merchantsState").val(),
				isXfconsumers : $("#isXfconsumers").val(),
				keySeach: $("#keySeach").val()
			};
			return temp;
		};
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
        	var state=row.merchantsState;
        	if(state=="0"){
	       	 	  layer.confirm('确认停用吗？', {
				  btn: ['确认','取消'] //按钮
				  }, function(){
					$.ajax({
						type: "POST",
						url : '<spring:url value="/headquarters/consumers/doDelConsumers"/>',
						data:{
							consumersId:row.id
						},
						dataType: "json",
						async: false,
						success: function(data){
							if("0000000"==data.head.respCode){
								$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/consumers/doReadConsumersList" />'});
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
						url : '<spring:url value="/headquarters/consumers/doDelConsumers"/>',
						data:{
							consumersId:row.id
						},
						dataType: "json",
						async: false,
						success: function(data){
							if("0000000"==data.head.respCode){
								$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/consumers/doReadConsumersList" />'});
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
