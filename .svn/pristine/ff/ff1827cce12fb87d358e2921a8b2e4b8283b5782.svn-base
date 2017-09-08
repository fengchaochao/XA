<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统用户列表</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
</head>
<body>
<div id="mainContent">
    <form id="formSearch" class="form-horizontal">
		<div class="form-group" style="margin-top: 15px; margin-left:-31px">
			<label class="control-label" for="loginName" style="float: left;margin-left: 3%;">登录名</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="loginName"/>
			</div>
			<label class="control-label" for="realName" style=" float:left">姓名</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="realName"/>
			</div>
			<label class="control-label" for="mobile" style="float: left;">手机</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="mobile"/>
			</div>
			<div id="queryDiv" class="col-sm-3" style="text-align: left;padding-left: 0px;">
				<button type="button" id="btn_query" class="btn btn-primary">查询</button>
			</div>
		</div>
	</form>

	<div id="toolbar" style="margin-bottom: 1.5%;margin-top: 1%;">
		<button id="btn_add" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
		</button>
		<button id="btn_delete" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
	</div>
	<table id="tb_sysuser"></table> 
</div>

    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js" />'></script>
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
			// 搜索
			$('#btn_query').click(function(){
				$('#tb_sysuser').bootstrapTable('getOptions').pageNumber=1;
				$('#tb_sysuser').bootstrapTable('refresh');
			});
			// 删除
            $('#btn_delete').click(function () {
            	deleteRecordByCondition(null);
            });
			// 添加
            $('#btn_add').click(function () {
            	parent.layer.open({
    				title:"添加用户",
    			    type: 2,
    			    area: ['90%', '90%'],
    			    fix: false, //不固定
    			    maxmin: true,
    			    content: '<spring:url value="/sysuser/doEnAddSysUser" />'
    			});
            });
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_sysuser').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/sysuser/doReadSysUserList" />',
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
                queryParams: queryParams,
                responseHandler: responseHandler,
                columns : [ {
                	field: 'state',
					checkbox : true
				}, {
					field : 'loginName',
					title : '登录名'
				}, {
					field : 'realName',
					title : '姓名'
				}, {
					field : 'mobile',
					title : '手机号'
				}, {
					field : 'depName',
					title : '部门名称'
				},{
					field : 'telephone',
					title : '电话'
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
				realName : $("#realName").val(),
				loginName: $("#loginName").val(),
				mobile : $("#mobile").val(),
				guidStr:guidStr
			};
			return temp;
		};
		
		// 删除操作
        function deleteRecordByCondition(id) {
			var ids="";
        	if(null==id){//多选删除按钮点击
				var rows = $table.bootstrapTable('getSelections');
				for (var i = 0; i < rows.length; i++) {
	                ids += rows[i].id + ',';
	            }
				ids = ids.substring(0, ids.length - 1);
	            if (ids == "") {
	            	top.layer.alert("请选择您要删除的记录！",{icon:7});
	                return;
	            }else{
	            	deleteRecord(ids);
	            }
			}else{
				deleteRecord(id);
			}
		}
		
		function deleteRecord(ids){
			top.layer.confirm('确定要删除记录吗?', {icon: 3, title:'注意'}, function(index){
				top.layer.close(index);
  	            $.post("<spring:url value='/sysuser/doDelSysUser'/>",{
  	            	ids : ids
  	            },function(res,status){
  	            	$('#tb_sysuser').bootstrapTable('refresh');
  	            },"json");
	        });
		}
		
		// 编辑操作
        function editRecord(index) {
        	var row=$table.bootstrapTable('getData')[index];
			parent.layer.open({
				title:"编辑用户",
			    type: 2,
			    area: ['90%', '90%'], 
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/sysuser/doEnEditSysUser"/>'+'?sysUserId='+row.id
			});
        }
		
		//被子页面调用
		function beCalled(data){
			$('#tb_sysuser').bootstrapTable('refresh');
			if(data.body){
				top.layer.msg("更新成功!",{icon:1});
			}else{
				top.layer.msg("更新失败！",{icon:2});
			}
		}
		
		
		// 操作列格式化
        function operateFormatter(value, row, index) {
            var e = '<a href="#" mce_href="#" onclick="editRecord(\''+ index + '\')">编辑</a> ';  
            var d = '<a href="#" mce_href="#" onclick="deleteRecordByCondition(\''+ row.id +'\')">删除</a> ';  
            return e+d;  
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
