<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>权限-角色管理</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
</head>
<body>
	<div id="mainContent">
		<div style="margin-bottom: 1%;">
			<button id="btn_add"    type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
			</button>
			<button id="btn_delete" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
		</div>
		
	    <table id="tb_role"></table>
	    
	    <!-- 编辑模态框 -->
		<div id="editModal" class="modal fade" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content"></div>
			</div>
		</div>
	</div>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js" />'></script>
    
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
			// 搜索
			$('#btn_query').click(function(){
				$('#tb_role').bootstrapTable('refresh');
			});
			// 添加
            $('#btn_add').click(function () {
            	addRecord();
            });
			// 删除
            $('#btn_delete').click(function () {
            	deleteRecord();
            });
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_role').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/role/doReadRoleList" />',
                pagination: false,
                sidePagination: "server", //服务端请求
                queryParams: queryParams,
                responseHandler: responseHandler,
                columns : [ {
                	field: 'state',
					checkbox : true
				}, {
					field : 'roleName',
					title : '角色名称'
				}, {
					field : 'roleType',
					title : '角色类型'
				}, {
					field : 'userNum',
					title : '用户数'
				}, {
					field : 'roleIntroduce',
					title : '简介'
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
        function deleteRecord() {
        	top.layer.confirm('确定要删除记录吗?', {icon: 3, title:'注意'}, function(index){
        		top.layer.close(index);
        		var ids = "";
			    var rows = $table.bootstrapTable('getSelections');
			    for (var i = 0; i < rows.length; i++) {
			        ids += rows[i].id + ',';
			    }
			    ids = ids.substring(0, ids.length - 1);
			    if (ids == "") {
			    	top.layer.alert("请选择您要删除的记录！",{icon:2});
			        return;
			    }
			    $.post('<spring:url value="/role/doDelRole" />',{
			    	roleIds : ids
			    },function(res,status){
			    	if("0000000"==res.head.respCode){
			    		top.layer.msg("删除成功!",{icon:1});
			    		$('#tb_role').bootstrapTable('refresh');
			    	}
			    },"json");
        	});

		}

      //被子页面调用
		function beCalled(data){
			$('#tb_role').bootstrapTable('refresh');
			top.layer.msg("操作成功!",{icon:1});
		}
      
		// 编辑操作
		function editRecord(index) {
			
			var row=$table.bootstrapTable('getData')[index];
			parent.layer.open({
				title:"编辑角色",
			    type: 2,
			    area: ['800px', '500px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/role/doEnEditRole?perRoleId=" />'+row.id
			});
		}
		
		//添加操作
		function addRecord(){
			parent.layer.open({
				title:"添加角色",
			    type: 2,
			    area: ['800px', '600px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: '<spring:url value="/role/doEnAddRole" />'
			});
		}

		// 操作列格式化
		function operateFormatter(value, row, index) {
			var e = '<a href="#" mce_href="#" onclick="editRecord(\'' + index	+ '\')">编辑</a> ';
			return e;
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