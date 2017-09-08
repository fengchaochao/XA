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
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">用户后台管理</span>
       <p>
            <span id="btn_add" class="greenBtn">添加</span>
       </p>
    </div>
    <!--end pageHeadline -->

	<div class="whiteBox">
	    <form id="formSearch" class="form-horizontal">
	    <input type="hidden" id="userState" value="${sysUsers.userState }"/>
	    <input type="hidden" id="id" value="${sysUsers.id}"/>
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
					<button type="button" id="btn_query" class="inquiryComBtn">查询</button>
				</div>
			</div>
		</form>
	
		<!-- <div id="toolbar" style="margin-bottom: 1.5%;margin-top: 1%;">
			<button id="btn_delete" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
			</button>
		</div> -->
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
					field : 'userState',
					title : '用户角色',
					formatter : statusFormatter
				},{
					field : 'telephone',
					title : '电话'
				},{
					field : 'userStatus',
					title : '状态',
					formatter : statusFormatter1 
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
		// 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='0')
            	return "总部";
            if(value=='1')
            	return "代理商";
           	if(value=='2')
            	return "商家";
           	if(value=='3')
            	return "消费商";
           	if(value=='4')
            	return "消费者";
           	if(value=='5')
            	return "消费者账户";
           	if(value=='6')
            	return "代理商员工";
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
		// 显示数据格式化 statusFormatter
        function statusFormatter1(value, row, index) {
            if(value=='1')
            	return "停用";
            if(value=='0')
            	return "正常";
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
        	var row=$table.bootstrapTable('getData')[index];
        	var states=$("#userState").val();
        	var state=row.userStatus;
        	var id=$("#id").val();
        	if(states=="0"){
        		 var e = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="editRecord(\''+ index + '\')">编辑</a> ';  
        		 var g = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="resetPasswords(\''+ index + '\')">重置密码</a> ';  
        		 if(id!=row.id){
        		 	 if(state=="0"){
           		         var f = '<a class="redText" href="#" mce_href="#" onclick="delRecord(\''+ index + '\')">停用</a>';  
	        	     }
		        	 if(state=="1"){
		           		 var f = '<a class="greenText" href="#" mce_href="#" onclick="delRecord(\''+ index + '\')">启用</a>';  
		        	 }
		        	 return e +g+ f;  
        		 }
        		 return e +g;  
        	}else{
	        	if(id==row.id){
	        		 var e = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="editRecord(\''+ index + '\')">编辑</a> ';  
        		 	  var g = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="resetPasswords(\''+ index + '\')">重置密码</a> ';  
        		 	 return e+g; 
	        	}else{
		        	 var e = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="editRecord(\''+ index + '\')">编辑</a> ';  
		        	  var g = '<a href="#" class="blueText" mce_href="#" style="margin:0px 5px;" onclick="resetPasswords(\''+ index + '\')">重置密码</a> ';  
		             var d = '<a href="#" class="redText" mce_href="#"  style="margin:0px 5px;" onclick="deleteRecordByCondition(\''+ row.id +'\')">删除</a> ';  
		             if(state=="0"){
           		    	 var f = '<a class="redText" href="#" mce_href="#" onclick="delRecord(\''+ index + '\')">停用</a>';  
		        	  }
			         if(state=="1"){
			           	 var f = '<a class="greenText" href="#" mce_href="#" onclick="delRecord(\''+ index + '\')">启用</a>';  
			         }
		             return e+g+d+f; 
	        	}
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
        //停用、启用
        function delRecord(index){
        	var row=$table.bootstrapTable('getData')[index];
        	var state=row.userStatus;
        	if(state=="0"){
	       	 	  layer.confirm('确认禁用吗？', {
				  btn: ['确认','取消'] //按钮
				  }, function(){
					$.ajax({
						type: "POST",
						url : '<spring:url value="/sysuser/doForbiddenSysUser"/>',
						data:{
							id:row.id
						},
						dataType: "json",
						async: false,
						success: function(data){
							if("0000000"==data.head.respCode){
								$table.bootstrapTable('refresh', {url: '<spring:url value="/sysuser/doReadSysUserList" />'});
								layer.msg('操作成功', {icon: 1});
							}else{
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
					url : '<spring:url value="/sysuser/doForbiddenSysUser"/>',
					data:{
						id:row.id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/sysuser/doReadSysUserList" />'});
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
         function resetPasswords(index){
        	var row=$table.bootstrapTable('getData')[index];
	       	 	  layer.confirm('确定重置密码？', {
				  btn: ['确认','取消'] //按钮
				  }, function(){
					$.ajax({
						type: "POST",
						url : '<spring:url value="/sysuser/doResetPasswords"/>',
						data:{
							id:row.id
						},
						dataType: "json",
						async: false,
						success: function(data){
							if("0000000"==data.head.respCode){
								$table.bootstrapTable('refresh', {url: '<spring:url value="/sysuser/doReadSysUserList" />'});
								layer.msg('操作成功', {icon: 1});
							}else{
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
