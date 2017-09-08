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
    <style>
    .form-group{display:inline-block;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">消费者账号详情</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox">
		<div class="form-group" style="margin-top: 15px;">
			<label class="control-label" for="userno" style="float: left;">消费者账户</label>
			<div class="col-sm-2">
				${consumersAccount.userAccount}
			</div>
		</div>
		<p style="margin:0px;height:1px;margin:-10px 0px;"></p>
		<div class="form-group" style="margin-top: 15px;">
			<label class="control-label" for="userno" style="float: left;">&nbsp;&nbsp;&nbsp;账号类别</label>
			<div class="col-sm-2" style="width:200px;">
				<c:if test="${consumersAccount.userType=='1' }">微信</c:if>
				<c:if test="${consumersAccount.userType=='2' }">支付宝</c:if>
			</div>
		</div>
		<p style="display:block;"><hr/></p>
		<p style="margin:30px 0px;font-weight: bold; font-size: 15px;">交易记录</p>
	    <form id="formSearch" class="form-horizontal">
	    <div class="form-group" style="width:100%;">
	        <input type="hidden" id="id" value="${accountId}"/>
	        <div class="chooseTimeCom">
				<label class="control-label" for="userno" style="float: left;">交易时间</label>
				<div>
					<input type="text" class="form-control" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</div>
				<div>
					<input type="text" class="form-control" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</div>
			</div>
			<div id="queryDiv" class="col-sm-3" style="text-align: left;padding-left: 0px;">
				<button type="button" id="btn_query" class="inquiryComBtn">查询</button>
			</div>
		</form>
		<table id="tb_board"></table> 
	</div>
	<div class="form-group" style="width:100%;">
	    <div class="col-sm-12">
	      <div class="navbar-btn">
	          <button type="button" class="yellowReturnRad" onclick="history.go(-1)">返回</button>
	      </div>
	    </div>
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
                url : '<spring:url value="/headquarters/consumers/doReadAccountTransactionRecordsList" />?accountId='+$("#id").val(),
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
					field : 'createtime',
					title : '消费时间'
				}, {
					field : 'totalPrice',
					title : '消费金额'
				}, {
					field : 'bonus',
					title : '抽成',
					formatter:goodsAsFormat
					
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
		// 查询参数设置  
		function queryParams(params) {
			var temp = {
				//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的    
				limit : params.limit, //页面大小    
				offset : params.offset, //页码   
				order : params.order,
				orderName : params.sort,
				startTime : $("#beginDate").val(),
				endTime : $("#endDate").val()
			};
			return temp;
		};
		  // 显示数据格式化 
        function goodsAsFormat(value, row, index) {
            var row=$table.bootstrapTable('getData')[index];
         	var isBonus=row.isBonus;
         	if(isBonus=='1'){
            	return value;
         	}
         	if(value==''||value==null){
            	return 0;
         	}
         	if(isBonus=='0'){
            	return "无";
         	}
        }
	</script>

</body>
</html>
