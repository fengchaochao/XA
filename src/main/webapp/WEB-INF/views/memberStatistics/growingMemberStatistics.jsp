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
    .memberClassifiTab{width:100%;}
    th{background:#fafafa;width:18%;}
    th,td{border:1px solid #ddd;line-height:38px;border-left:none;border-right:none;}
    </style>
</head>
<body class="mainContant">
   <!--start pageHeadline -->
   <div class="pageHeadline">
      <span class="leftNotice">日新增会员统计</span>
   </div>
   <!--end pageHeadline -->
   
   
   <div class="whiteBox">
   		 <div class="form-group" style="margin-top: 15px;">
	   		 <c:if test="${sysUsers.userState=='0' }">
	   		 	<table class="memberClassifiTab table-hover">
		   		   <thead>
		   		   <tr>
			   		   <th>会员分类</th>
			           <th>代理商</th>
			           <th>商家</th>
			           <th>消费者账户</th>
			           <th>消费者</th>
			           <th>消费商</th>
		   		   </tr>
		   		   </thead>
		   		   <tbody>
			   		   <tr>
			   		   <td>数量</td><td>${agentNum}</td><td>${businessNum}</td><td>${consumersAcountNum}</td><td>${consumersNum}</td><td>${xfBusinessNum}</td>
			   		   </tr>
		   		   </tbody>
	   		  	</table> 
	   		 </c:if>
	   		 <c:if test="${sysUsers.userState=='1' }">
	   		 	<table>
		   		   <thead>
		   		   <tr>
			   		   <th>会员分类</th>
			           <th>商家</th>
			           <th>消费商</th>
		   		   </tr>
		   		   </thead>
		   		   <tbody>
			   		   <tr>
			   		   <td>数量</td><td>${businessNum}</td><td>${xfBusinessNum}</td>
			   		   </tr>
		   		   </tbody>
	   		  	</table> 
	   		 </c:if>
	   		 <c:if test="${sysUsers.userState=='2' }">
	   		 	<table>
		   		   <thead>
		   		   <tr>
			   		   <th>会员分类</th>
			           <th>商家</th>
			           <th>消费者账户</th>
		   		   </tr>
		   		   </thead>
		   		   <tbody>
			   		   <tr>
			   		   <td>数量</td><td>${businessNum}</td><td>${consumersAcountNum}</td>
			   		   </tr>
		   		   </tbody>
	   		  	</table> 
	   		 </c:if>
         </div>
       <form id="formSearch" class="form-horizontal">
	        <div class="form-group" style="margin: 60px 0px -20px -31px;">
	            <label class="control-label" for="usertype" style="float: left;margin-left: 3%;">会员分类</label>
	            <div class="col-sm-2">
	                <select name="usertype" id = "usertype" class="form-control" >
	                    <option value="">请选择会员分类</option>
	                     <c:if test="${sysUsers.userState=='0' }">
		                    <option value="0">代理商</option>
		                    <option value="1">商家</option>
		                    <option value="2">消费者</option>
		                    <option value="4">消费者账户</option>
		                    <option value="3">消费商</option>
	                    </c:if>
	                    <c:if test="${sysUsers.userState=='1' }">
		                    <option value="1">商家</option>
		                    <option value="3">消费商</option>
	                    </c:if>
	                    <c:if test="${sysUsers.userState=='2' }">
		                    <option value="1">商家</option>
		                    <option value="4">消费者账户</option>
	                    </c:if>
	                </select> 
	            </div>
	            <div id="queryDiv" class="col-sm-3" style="text-align: left;padding-left: 0px;">
	                <button type="button" id="btn_query" class="inquiryComBtn">查询</button>
	            </div>
	        </div>
	    </form>
	    <table id="tb_board"></table> 
   </div>
   <p style="text-align:center;padding-top:30px;"><button type="button" class="yellowReturnRad" onclick="history.go(-1)">返回</button></p>
   
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
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/memberStatistics/doReadMemberStatisticsList" />',
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
					title : '会员编号'
				}, {
					field : 'usertype',
					title : '会员分类',
					formatter : statusFormatter
				}, {
					field : 'username',
					title : '会员名称'
				}, {
					field : 'phone',
					title : '联系电话',
					formatter : phoneFormatter
				}, {
					field : 'address',
					title : '地址',
					formatter : phoneFormatter
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
		
		// 查看操作
        function editRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
        	var state=row.usertype;
        	//代理商详情
        	if(state=='0'){
        		window.location.href='<spring:url value="/headquarters/memberStatistics/doEnAgentEdit"/>'+'?agentId='+row.id;
        	}
        	//商家详情
        	if(state=='1'){
        		window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+row.id+'&flag=4';
        	}
        	//消费者或消费商详情
        	if(state=='2'||state=='3'){
        	window.location.href='<spring:url value="/headquarters/consumersMarketers/doEnConsumersEdit"/>'+'?consumersId='+row.id+'&flag=4';
        	}
			
        }
        
		// 操作列格式化
        function operateFormatter(value, row, index) {
       	    var row=$table.bootstrapTable('getData')[index];
        	var state=row.usertype;
        	if(state=="4"){
           		 var e = '';  
        	}else{
        		 var e = '<a class="blueText" href="#" mce_href="#" onclick="editRecord(\''+ index + '\')">查看</a>';  
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
				usertype: $("#usertype").val()
			};
			return temp;
		};
		// 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='0')
            	return "代理商";
            if(value=='1')
            	return "商家";
            if(value=='2')
            	return "消费者";
            if(value=='3')
            	return "消费商";
            if(value=='4')
            	return "消费者账户";
        }
        // 显示数据格式化 statusFormatter
        function phoneFormatter(value, row, index) {
        	var row=$table.bootstrapTable('getData')[index];
        	var state=row.usertype;
	           if(state=="4"){
	          	 return "无";
	           }else{
	          	 return value;
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
