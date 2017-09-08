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
    .blueText,.greenText,.redText,.orangeText,.cyanText,.purpleText,.grayText{min-width:68px;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商品管理</span>
       <span id="btn_add" class="greenBtn" >新增商品</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox" style="min-height:500px;">
	    <form id="formSearch" class="form-horizontal">
			<div class="form-group" style="margin-top: 15px;">
			    <div class="chooseTimeCom">
					<label class="control-label" for="userno" style="float: left;">发布时间</label>
					<div>
						<input type="text" class="form-control" id="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div>
						<input type="text" class="form-control" id="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
				</div>
				<label class="control-label" for="commodityTypeId" style="float: left;">商品分类</label>
				<div class="col-sm-2">
					<select name="commodityTypeId" id = "commodityTypeId" class="form-control" >
						<option value="">请选择商品分类</option>
						<c:forEach items="${goodsClassificationList}" var="var">
							<option value="${var.id}">${var.categoryName}</option>
						</c:forEach>
					</select> 
				</div>
				<label class="control-label" for="commodityName" style="float: left;">商品名称</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="commodityName"/>
				</div>
				<div id="queryDiv" class="col-sm-2" style="text-align: left;padding-left:0px;">
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
    			window.location.href='<spring:url value="/headquarters/goods/doEnGoodsAdd" />';
            });
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/goods/doReadGoodsList" />',
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
					title : '发布时间'
				}, {
					field : 'merchantsNo',
					title : '商家编号'
				}, {
					field : 'merchantsName',
					title : '商家名称'
				}, {
					field : 'commodityName',
					title : '商品名称'
				}, {
					field : 'typeName',
					title : '商品分类'
				}, {
					field : 'promotionFee',
					title : '推广佣金'
				}, {
					field : 'sales',
					title : '销量'
				}, {
					field : 'goodsState',
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
		
		// 修改
        function editRecord(index) {
         	var row=$table.bootstrapTable('getData')[index];
			window.location.href='<spring:url value="/headquarters/goods/doEnGoodsEdit"/>'+'?goodsId='+row.id;
        }
        // 查看
        function viewRecord(index) {
         	var row=$table.bootstrapTable('getData')[index];
			window.location.href='<spring:url value="/headquarters/goods/doEnGoodsView"/>'+'?goodsId='+row.id;
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
       	    var row=$table.bootstrapTable('getData')[index];
        	if(row.goodsState=='0'){
            	var e = '<button type="button" class="blueText"  onclick="viewRecord(\''+ index + '\')">查看</button> '+
            	'<button type="button" class="greenText" onclick="editRecord(\''+ index + '\')">修改</button> '
            	+'<button type="button" class="grayText" onclick="editRecord(\''+ index + '\')" disabled="disabled">交易记录</button> '
            	+'<button type="button" class="purpleText" onclick="checkRecord(\''+ index + '\')">提交审核</button>';
        	}
            if(row.goodsState=='1'){
           		var e = '<button type="button" class="blueText"  onclick="viewRecord(\''+ index + '\')">查看</button> '+
            	'<button type="button" class="grayText" onclick="editRecord(\''+ index + '\')" disabled="disabled">修改</button> '
            	+'<button type="button" class="grayText" onclick="tradingRecord(\''+ index + '\')" disabled="disabled">交易记录</button> '
            	+'<button type="button" class="grayText" onclick="shelvesRecord(\''+ index + '\')" disabled="disabled">下架</button>';
        	}
            if(row.goodsState=='2'){
            	var e = '<button type="button" class="blueText"  onclick="viewRecord(\''+ index + '\')">查看</button> '+
            	'<button type="button" class="greenText" onclick="editRecord(\''+ index + '\')" >修改</button> '
            	+'<button type="button" class="grayText" onclick="tradingRecord(\''+ index + '\')" disabled="disabled">交易记录</button> '
            	+'<button type="button" class="purpleText" onclick="checkRecord(\''+ index + '\')" >提交审核</button>';
        	}
            if(row.goodsState=='3'){
           	 	var e = '<button type="button" class="blueText"  onclick="viewRecord(\''+ index + '\')">查看</button> '+
            	'<button type="button" class="grayText" onclick="editRecord(\''+ index + '\')" disabled="disabled">修改</button> '
            	+'<button type="button" class="cyanText" onclick="tradingRecord(\''+ index + '\')">交易记录</button> '
            	+'<button type="button" class="redText" onclick="shelvesRecord(\''+ index + '\')" >下架</button>';
        	}
            if(row.goodsState=='4'){
           		 var e = '<button type="button" class="blueText"  onclick="viewRecord(\''+ index + '\')">查看</button> '+
            	'<button type="button" class="greenText" onclick="editRecord(\''+ index + '\')">修改</button> '
            	+'<button type="button" class="cyanText" onclick="tradingRecord(\''+ index + '\')">交易记录</button> '
            	+'<button type="button" class="purpleText" onclick="checkRecord(\''+ index + '\')" >提交审核</button>';
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
				commodityTypeId : $("#commodityTypeId").val(),
				commodityName: $("#commodityName").val()
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
		// 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
           if(value=='0')
            	return "未发布";
            if(value=='1')
            	return "待审核";
            if(value=='2')
            	return "未通过";
            if(value=='3')
            	return "已发布";
            if(value=='4')
            	return "已下架";
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
		//提交审核
        function checkRecord(index){
        	var row=$table.bootstrapTable('getData')[index];
       	 	layer.confirm('确认提交审核？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/goods/doCheckGoods"/>',
					data:{
						goodsId:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/goods/doReadGoodsList" />'});
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
		//商品下架
        function shelvesRecord(index){
        	var row=$table.bootstrapTable('getData')[index];
       	 	layer.confirm('确认将该商品下架？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/goods/doShelvesGoods"/>',
					data:{
						goodsId:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/goods/doReadGoodsList" />'});
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
		//交易记录
        function tradingRecord(index){
        	var row=$table.bootstrapTable('getData')[index];
        	window.location.href='<spring:url value="/headquarters/goods/doEnGoodsTransactionRecords"/>'+'?goodsId='+row.id;
		}
	</script>

</body>
</html>
