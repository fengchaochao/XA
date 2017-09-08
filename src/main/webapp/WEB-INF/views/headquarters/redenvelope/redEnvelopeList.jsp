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
       <span class="leftNotice">红包活动</span>
       <p><button type="button" id="btn_add" class="greenBtn">新增</button></p>
    </div>
    <!--end pageHeadline -->

	<div class="whiteBox">
	
	    <form id="formSearch" class="form-horizontal">
	        <div class="form-group" style="margin-top: 15px;margin-left: -31px;">
	           <label class="control-label" for="redEnvelopeState" style="float: left;margin-left: 3%;">红包状态</label>
                <div class="col-sm-2">
                    <select name="merchantsState" id = "redEnvelopeState" class="form-control" >
                        <option value="">请选择状态</option>
                        <option value="0">待发布</option>
                        <option value="1">已发布</option>
                    </select> 
                </div> 
	        </div>
			<div class="form-group" style="margin-top: 15px;margin-left: -31px;">
				<label class="control-label" for="beginDate" style="float: left;margin-left: 3%;">发布时间</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</div>
				<em style="float:left;margin-top:5px;">-</em>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</div>
				
				<div id="queryDiv" class="col-sm-3" style="text-align: left;padding-left: 0px;">
					<button type="button" id="btn_query" class="inquiryComBtn">查询</button>
				</div>
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
			
			// 添加
            $('#btn_add').click(function () {
    			window.location.href='<spring:url value="/headquarters/redEnvelope/doEnRedEnvelopeAdd" />';
            });
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/redEnvelope/doReadRedEnvelopeList" />',
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
					title : '红包日期'
				}, {
					field : 'createTime',
					title : '红包时间'
				}, {
					field : 'totalPrice',
					title : '红包总金额'
				}, {
					field : 'number',
					title : '红包个数'
				}, {
					field : 'expirationDate',
					title : '红包失效时间（分钟）'
				}, {
					field : 'redEnvelopeState',
					title : '红包状态',
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
        	 //获取当前时间
        	var myDate = new Date();
			//获取当前年
			var year=myDate.getFullYear();
			//获取当前月
			var month=myDate.getMonth()+1;
			//获取当前日
			var date=myDate.getDate(); 
			var h=myDate.getHours();       //获取当前小时数(0-23)
			var m=myDate.getMinutes();     //获取当前分钟数(0-59)
			var s=myDate.getSeconds();  
			
			var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        	//先判断日期，
        	var createDate=row.createDate+" "+row.createTime;
        	var dateP=(new Date(createDate).getTime()-new Date(now).getTime())/1000/60;
     		if(dateP<15){
				top.layer.alert("红包将在"+Math.round(dateP)+"分钟后发布，您不能修改！", {icon : 2});
				return;
			}
			window.location.href='<spring:url value="/headquarters/redEnvelope/doEnRedEnvelopeEdit" />'+'?redEnvelopeId='+row.id;
        }
        // 查看详情
        function viewRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
			window.location.href='<spring:url value="/headquarters/redEnvelope/doEnRedEnvelopeView" />'+'?redEnvelopeId='+row.id;
        }
         // 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='1')
            	return "已发布";
           	if(value=='2')
           		return "已领取";
           	if(value=='3')
           		return "已失效";
            if(value=='0')
            	return "待发布";
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
        	var row=$table.bootstrapTable('getData')[index];
        	var state=row.redEnvelopeState;
        	if(state=='1'||state=='2'||state=='3'){
        		var e = '<button type="button" class="blueText" onclick="viewRecord(\''+ index + '\');">查看</button>';  
        	}
        	if(state=='0'){
        		var e = '<button type="button" class="blueText" onclick="viewRecord(\''+ index + '\');">查看</button> <button type="button" class="orangeText" onclick="editRecord(\''+ index + '\');">修改</button> <button type="button" class="redText" onclick="delRecord(\''+ index + '\');">删除</button>';  
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
				redEnvelopeState: $("#redEnvelopeState").val(),
				beginDate : $("#beginDate").val(),
				endDate : $("#endDate").val()
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
         function delRecord(index){
       	   var row=$table.bootstrapTable('getData')[index];
        	layer.confirm('确认删除吗？', {
			  btn: ['确认','取消'] //按钮
			  }, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/redEnvelope/doDelRedEnvelope"/>',
					data:{
						redEnvelopeId:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							location.reload();
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
	</script>

</body>
</html>
