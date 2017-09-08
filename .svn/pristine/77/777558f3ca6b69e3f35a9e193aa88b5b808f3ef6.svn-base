<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>租户用户</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <script type="text/javascript" src='<spring:url value="/js/common/listLib.js" />'></script>
</head>
<body>
<div id="mainContent">
    <form id="formSearch" class="form-horizontal">
		<div class="form-group" style="margin-top: 15px;margin-left: -31px;">
			<label class="control-label" for="description" style="float: left;margin-left: 3%;">租户名称</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="description"/>
			</div>
			
			<label class="control-label" for="tenanttypeCode" style="float: left;">租户类型</label>
			<div class="col-sm-2">
				<select class="form-control" id="tenanttypeCode"></select>
			</div>
			
			<label class="control-label" for="tenantstatusCode" style="float: left;">租户状态</label>
			<div class="col-sm-2">
				<select class="form-control" id="tenantstatusCode"></select>
			</div>
			
			<label class="control-label" for="businessdurationCode" style="float: left;">营业年限</label>
			<div class="col-sm-2">
				<select class="form-control" id="businessdurationCode"></select>
			</div>
			<div style="margin-bottom: 1%;margin-left: -31px;clear: both;margin-top: 4.5%;">
				<label class="control-label" for="enterprisescaleCode" style="margin-left: 5.5%;float: left;">企业规模</label>
				<div class="col-sm-2 RL">
					<select class="form-control" id="enterprisescaleCode">
					</select>
				</div>
				
				<div id="queryDiv" class="col-sm-4 RL" style="text-align: left;float: left;">
					<button type="button" id="btn_query" class="btn btn-primary">查询</button>
				</div>
			</div>
		</div>
	</form>


	<table id="tb_board"></table> 
</div>

    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js" />'></script>
    
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
			// 搜索
			$('#btn_query').click(function(){
				$('#tb_board').bootstrapTable('getOptions').pageNumber=1;
				$('#tb_board').bootstrapTable('refresh');
			});
			// 删除
            $('#btn_delete').click(function () {
            	deleteRecord();
            });
			

			// 初始化租户类型
			initSingleSelect('tenanttypeCode',tenantTypeList);
            
			// 初始化租户状态
			initSingleSelect('tenantstatusCode',tenantStatusList);
			
			// 初始化营业年限
			initSingleSelect('businessdurationCode',businessDurationList);
			
			// 初始化企业规模
			initSingleSelect('enterprisescaleCode',enterpriseScaleList);

			
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/tenant/doReadTenantList" />',
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
                queryParams: queryParams,
                responseHandler: responseHandler,
                onDblClickRow: doubleClick,
                columns : [ {
                	field: 'state',
					checkbox : true
				}, {
					field : 'description',
					title : '企业名称'
				}, {
					field : 'tenanttypeDesc',
					title : '租户类型'
				}, {
					field : 'businessdurationDesc',
					title : '营业年限'
				}, {
					field : 'enterprisescaleDesc',
					title : '企业规模'
				}, {
					field : 'tenantstatusDesc',
					title : '租户状态'
				}, {
					field : 'lastupdatedate',
					title : '操作时间',
					formatter: function (value, row, index){
						return dateFormat(value, "yyyy-mm-dd HH:MM:ss");
					}
				}, {
                    title: '操作',
                    field: 'id',
                    align: 'center',
                    formatter:operateFormatter
                }],
                onLoadSuccess:function(){

                },
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });
        }

		// 查询参数设置  
		function queryParams(params) {
			var guidStr = generateGuid();
			var temp = {
				//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的    
				limit : params.limit, //页面大小    
				offset : params.offset, //页码   
				description : $("#description").val(),
				tenanttypeCode: $("#tenanttypeCode").val(),
				tenantstatusCode: $("#tenantstatusCode").val(),
				businessdurationCode: $("#businessdurationCode").val(),
				enterprisescaleCode: $("#enterprisescaleCode").val(),
				mobile : $("#mobile").val(),
				guidStr:guidStr
			};
			return temp;
		};
		
		function doubleClick(row){
			parent.layer.open({
				title:"租户详细",
			    type: 2,
			    fix: false, //不固定
			    area : ['90%', '90%'],
			    maxmin: true,
			    content: '<spring:url value="/tendet/doEnViewTenantDetail"/>'+'?tenantId='+row.id
			});
		}
				
		// 编辑操作
        function editRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
			parent.layer.open({
			    type: 2,
			    area: ['900px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/tenant/doEnTenantEdit"/>'+'?tenantId='+row.id
			});
        }
		
		//被子页面调用
		function beCalled(data){
			$('#tb_board').bootstrapTable('refresh');
			console.log(data);
		}
		
		
		// 操作列格式化
        function operateFormatter(value, row, index) {
            return '<a href="#" mce_href="#" onclick="editRecord(\''+ index + '\')">编辑</a> ';  
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
