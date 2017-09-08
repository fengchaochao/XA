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
        .showLawyer{position: fixed;width: 100%;height: 100%;background: #000;z-index: 9999;
    opacity: 0.3;top: 0px;left: 0px;display: none;}
		.showBox{display:none;border-radius:5px;position:fixed;z-index:100000;background:#fff;
		padding:15px;top:6%;left:25%;width: 400px;padding-left:25px;min-height: 150px;}
		.showBox .closeBtnRight{float:right;margin:5px 10px;cursor: pointer;}
		.showBox p{margin-top:15px;line-height:20px;margin-bottom: 20px;padding-left:30px;}
		.form-group select,#endDate{margin-right:20px;}
		#endDate{margin-left:5px;}
		#beginDate{margin-right:5px;}
		
		#formSearch select,#formSearch input{margin-bottom:15px;}
		#formSearch #btn_reset{margin-bottom:0px;}
		
	</style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">推荐商家查看</span>
    </div>
    <!--end pageHeadline -->
    
    <!-- start lawyer -->
	<div class="showLawyer"></div>
	<div class="showBox" id ="showBox">
		<img class="closeBtnRight" src='<spring:url value="/img/login/clos_comleft.png"/>' onclick="cancel()" />
		营业执照：
		<p id="licenPic"></p>
		店铺照片：
		<p id="idNoPhoto"></p>
    </div>
    <!-- end lawyer -->
    
	<div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
			<div class="form-group" style="margin-top: 15px;">
				<!-- <label class="control-label" for="businessState" style="float: left;margin-left: 3%;">状态</label>
				<div class="col-sm-2">
					<select name="businessState" id = "businessState" class="form-control" >
						<option value="">请选择用户状态</option>
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select> 
				</div> -->
				<label class="control-label" for="consumerNo" style="float: left;">是否已入驻</label>
				<div class="col-sm-2">
					<select name="effectState" id = "effectState" class="form-control" >
						<option value="">全部</option>
						<option value="1">否</option>
						<option value="0">是</option>
					</select> 
				</div>
				<label class="control-label" for="founderName" style="float: left;">推荐人名称</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="founderName"/>
				</div>
				<div class="chooseTimeCom" >
					<label class="control-label" for="beginDate" style="float: left;">推荐时间</label>
					<div>
						<input type="text" class="form-control" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div>
						<input type="text" class="form-control" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
				</div>
				<div id="queryDiv" class="col-sm-1" style="text-align: center;padding-left:0px;">
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
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/recommendBusinessInformation/doReadRecommendBusinessInformationList" />',
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
					field : 'source',
					title : '推荐人角色',
					formatter : statusFormatter
				}, {
					field : 'consumerNo',
					title : '推荐人编号'
				}, {
					field : 'founderName',
					title : '推荐人名称'
				}, {
					field : 'phone',
					title : '推荐人电话'
				}, {
					field : 'createDate',
					title : '推荐时间'
				}, {
					field : 'vendorName',
					title : '店名'
				}, {
					field : 'organizationCode',
					title : '组织机构代码'
				}, {
					field : 'merchantPhone',
					title : '电话'
				}, {
					field : 'address',
					title : '地址'
				}, {
					field : 'businessIcensePhoto',
					title : '照片',
					formatter : showPic
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
		function showPic(data,row,index){
    		return '<a href="#" class = "campus blueText" mce_href="#" onclick="datails(\''+ index + '\')">查看</a>';
        }
        function datails(index){
        	var row=$table.bootstrapTable('getData')[index];
        	var businessIcensePhotos=row.businessIcensePhoto.split(",");
        	$("#licenPic").html("");
        	$("#idNoPhoto").html("");
        	for ( var int = 0; int < businessIcensePhotos.length; int++) {
				var businessIcensePhoto = businessIcensePhotos[int];
				$("#licenPic").append('<img src="<spring:url value="/'+businessIcensePhoto+'"/>" alt="" style="width: 90px;height: 90px" />')
				
			}
			var storePhoto=row.storePhotos;
			$("#idNoPhoto").append('<img src="<spring:url value="/'+storePhoto+'"/>" alt="" style="width: 90px;height: 90px" />')
	        $(".showBox,.showLawyer").show();
        }
		// 编辑操作
        function editRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
			window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+row.id+'&flag=2';
        }
        // 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='1')
            	return "商家";
            if(value=='2')
            	return "消费商";
        }
		// 操作列格式化
        function operateFormatter(value, row, index) {
           var row=$table.bootstrapTable('getData')[index];
        	var state=row.effectState;
        	if(state=="0"){
           		 var e = '<a href="#" class="blueText" mce_href="#" onclick="editRecord(\''+ index + '\')">查看</a>';  
        	}
        	if(state=="1"){
           		 var e = '';  
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
				consumerNo : $("#consumerNo").val(),
				founderName: $("#founderName").val(),
				effectState: $("#effectState").val(),
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
		 function cancel(){
        	$(".showBox,.showLawyer").hide();
        }
	</script>

</body>
</html>
