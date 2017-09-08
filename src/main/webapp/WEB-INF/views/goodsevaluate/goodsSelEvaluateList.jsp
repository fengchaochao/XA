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
    .rowshortBox{display:inline-block;width:49%;}
    .rowshortBox .col-sm-10{width:80%;}
    .rowshortBox input[type="text"],.rowshortBox select{margin:0px;width:100%;}
    .pics{border-radius:5px;}
    .greenReplyBtn{min-width: 90px;margin: 0px;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商品评论</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
	    	<input type="hidden" id = "goodsId" value ="${goods.id }"/>
			<div class="form-group rowshortBox" style="margin-top: 15px;">
				<label class="control-label" for="commodityName" style="float: left;">商品名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="" value="${goods.commodityName }" readonly="readonly"/>
				</div>
			</div>
			<div class="form-group rowshortBox" style="margin-top: 15px;">
				<label class="control-label" for="commodityName" style="float: left;">商品分类</label>
				<div class="col-sm-10">
					<select id = "" class="form-control" disabled="disabled">
						<c:forEach items="${classificationList }" var ="var">
							<option value = "${var.id }"
							<c:if test="${goods.commodityTypeId eq var.id }">selected:selected</c:if>>
							${var.categoryName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group" style="margin-top: 15px;">
				<label class="control-label" for="commodityName" style="float: left;">商品图片</label>
				<div class="col-sm-9">
					<c:forEach items="${commodityImages}" var="var">
						<div style="display:inline-block;margin:5px;">
							<img alt="" id="licencePicture_img" src="<spring:url value='${var}' />"  class="pics" style="width: 130px;height: 90px"/>
						</div>
					</c:forEach>
				</div>
			</div>
		</form>
		<ul class="nav nav-tabs">
			<li  class="active"><a href="<spring:url value="/business/goodsEvaluate/doEnGoodsSelById?goodsId=${goods.id }"/>">回复评论</a></li>
			<li><a href="<spring:url value="/business/goodsEvaluate/doEnSelEvaluateStatus?goodsId=${goods.id }"/>">查看评论</a></li>
		</ul>
		<br/>
		<table id="tb_board"></table> 
	</div>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
	<script type="text/javascript">
	    var $table;
		$(function() {
		
			$(".nav li").click(function() {
				//点击对应的tab之后调用ajax将类别和查询条件传至后台重写x和y的值。
				$(this).addClass("active");
				$(this).siblings().removeClass("active");
			});
		
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
                url : '<spring:url value="/business/goodsEvaluate/doReadEvaluateList" />',
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
                columns : [ 
                 {
					field : 'consumersName',
					title : '消费者'
				},  {
					field : 'evaluateContent',
					title : '评论内容'
				}, {
                    title: '回复评论',
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
		
        // 回复
        function updEvaluate(index) {
        	var row = $table.bootstrapTable('getData')[index];
   			
   			parent.layer.open({
				title:"回复评论",
			    type: 2,
			    area: ['50%', '60%'], 
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/business/goodsEvaluate/doEnGoodsEvaluateEdit"/>'+'?goodsEvaluateId='+row.id
			});
        
        }
        
       
		// 操作列格式化
        function operateFormatter(value, row, index) {
           	var e = '<button type="button" class="greenReplyBtn"  onclick="updEvaluate(\''+ index + '\')">回复</button> ';
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
				goodsId : $("#goodsId").val(),
				status:"1"
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
	</script>

</body>
</html>
