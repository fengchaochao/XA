<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
    table{width:100%;}
    th{background:#fafafa;}
    table,td,th{border:1px solid #ddd;}
    td,th{padding-left:15px;height:35px;}
    input[type="text"]{border:none;width:150px;background:#fafafa;border:1px solid #ddd;}
    .newHeadline{font-size: 16px;margin: 10px 0px 30px 0px;font-weight: bold;}
    </style>
</head>

<body class="mainContant" style="background:#fff;width:97%;">
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
		    <p class="newHeadline">新增分类</p>
			<input type="hidden" id="id" value="${businessClassification.id}" />
			<table>
		        <thead>
		           <tr>
		              <th>编号</th><th>分类名称</th><th>第一次锁定抽成(%)</th>
		              <th>锁定消费者消费抽成(%)</th><th>引流消费抽成(%)</th>
		           </tr>
		        </thead>
		        <tbody>
		           <tr>
		              <td>
		                  <c:if test="${businessClassificationId==null||businessClassificationId==''}">
	                         <input type="hidden" style="width:40px;margin-right: 5px;" id="classificationNumber" value="${classificationNumber}" /> ${classificationNumber} 
	                      </c:if>
	                      <c:if test="${businessClassificationId!=null&&businessClassificationId!=''}">
	                         <input type="hidden" style="width:40px;margin-right: 5px;" id="classificationNumber" value="${businessClassification.classificationNumber}" /> ${businessClassification.classificationNumber}
	                      </c:if>
		              </td>
		              <td>
		                 
                         <input type="text" class="" id="categoryName" name="categoryName" value="${businessClassification.categoryName}" />
		              </td>
		              <td>
                          <input type="text" class="" id="firstLockInto" name="firstLockInto" value="${businessClassification.firstLockInto}" />
                      </td>
                      <td>
                          <input type="text" class="" id="consumption" name="consumption" value="${businessClassification.consumption}" />
                      </td>
                      <td>
                          <input type="text" class="" id="drainageConsumption" name="drainageConsumption" value="${businessClassification.drainageConsumption}" />
                      </td>
		           </tr>
		        </tbody>
		    </table>
			<div class="form-group">
		        <div class="col-sm-12">
				  <div class="navbar-btn" style="margin:60px 0px 0px 0px;">
				  	  <input type="button" value="保存" class="confirm " id="commit" />
				 	  <input type="button" value="取消" class="cancelBtn col-sm-offset-1" id="cancel" />
				  </div>
		        </div>
	        </div>
		</form>
	</div> 
<script type="text/javascript">
	$(function() {
		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		$("#commit").bind("click",function(){
			var categoryName=$("#categoryName").val();
			var reg=/^[0-9]*$/;
			
			if($('#categoryName').val()==null||$('#categoryName').val()==""){
				top.layer.alert("分类名称不能为空！",{icon:2});
				return;
			}
			var patt1=new RegExp(/^[\u4e00-\u9fa5]+$/);
			if(!patt1.test($('#categoryName').val())){
				top.layer.alert("分类名称只能输入中文！",{icon:2});
				return;
			}
			if($('#firstLockInto').val()==null||$('#firstLockInto').val()==""){
				top.layer.alert("第一次锁定抽成不能为空！",{icon:2});
				return;
			}
			if(!reg.test($('#firstLockInto').val())){
				top.layer.alert("第一次锁定抽成格式不正确，请重新输入！",{icon:2});
				return;
			}
			if($('#consumption').val()==null||$('#consumption').val()==""){
				top.layer.alert("锁定消费者消费抽成不能为空！",{icon:2});
				return;
			}
			if(!reg.test($('#firstLockInto').val())){
				top.layer.alert("锁定消费者消费抽成格式不正确，请重新输入！",{icon:2});
				return;
			}
			if($('#drainageConsumption').val()==null||$('#drainageConsumption').val()==""){
				top.layer.alert("引流消费抽成不能为空！",{icon:2});
				return;
			}
			if(!reg.test($('#firstLockInto').val())){
				top.layer.alert("引流消费抽成格式不正确，请重新输入！",{icon:2});
				return;
			}
			var id=$("#id").val();
			if(id==null||id==""){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessClassification/toRepeatBusinessClassification" />',
					data:{
						categoryName:categoryName
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("1"==data.body){
							top.layer.alert("该分类名称已存在，请重新输入！",{icon:2});
							$("#categoryName").val("");
							$("#categoryName").focus("");
							return;
						}else{
							$.ajax({
								type: "POST",
								url : '<spring:url value="/headquarters/businessClassification/doSaveBusinessClassification" />',
								data:{
									classificationNumber:$('#classificationNumber').val(),
									categoryName:$('#categoryName').val(),
									firstLockInto:$('#firstLockInto').val(),
									consumption:$('#consumption').val(),
									drainageConsumption:$('#drainageConsumption').val()
								},
								dataType: "json",
								async: false,
								success: function(data){
									if("0000000"==data.head.respCode){
										parent.window.contFrame.window.beCalled(data);
					 					closeWindow(window.name);
									}
									else{
										alert(data.head.respContent);
									}
								}
							});

						}
					}
				});
				
		  }else{
		  	$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/businessClassification/doEditBusinessClassification" />',
				data:{
					id:$('#id').val(),
					classificationNumber:$('#classificationNumber').val(),
					categoryName:$('#categoryName').val(),
					firstLockInto:$('#firstLockInto').val(),
					consumption:$('#consumption').val(),
					drainageConsumption:$('#drainageConsumption').val()
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						parent.window.contFrame.window.beCalled(data);
	 					closeWindow(window.name);
					}
					else{
						alert(data.head.respContent);
					}
				}
			});
		  
		  }
	  })	
	});
</script>
</body>
</html>
