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
    .col-sm-2{padding:0px;width:80px;}
    .whiteBox{min-height:500px;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">抽成分配</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
			<input type="hidden" id="id" value="${distribution.id}" />
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">平台分成</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="platform" value="${distribution.platform}" disabled="disabled" />
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">代理商分成</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="agent"  value="${distribution.agent}" disabled="disabled"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">推荐人</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="referees" value="${distribution.referees}" disabled="disabled"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">锁定人</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="lockingPeople" value="${distribution.lockingPeople}" disabled="disabled"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">其他</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="other" value="${distribution.other}" disabled="disabled"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			
			<div class="form-group" style="padding-left:85px;padding-top:20px;">
			  	  <input type="button" value="修改" class="confirm" id="update" />
			  	  <input type="button" value="保存" class="referBtn" id="commit" style="display: none"/>
			 	  <input type="button" value="取消" class="cancelBtn" id="cancel" style="display: none"/>
	        </div>
		</form> 
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/distribution/doEnDistributionList" />';
		});
		//修改
	   	$("#update").bind("click",function(){
			$('#platform').attr("disabled",false); 
			$('#agent').attr("disabled",false); 
			$('#referees').attr("disabled",false); 
			$('#lockingPeople').attr("disabled",false); 
			$('#other').attr("disabled",false); 
			$("#cancel").show();
			$("#commit").show();
			$("#update").hide();
			
		});
	});
	$("#commit").bind("click",function(){
		var reg= /^[0-9]*$/;
		if($('#platform').val()==null||$('#platform').val()==""){
			top.layer.alert("平台分成不能为空！",{icon:2});
			return;
		}
		if(!reg.test($('#platform').val())){
			top.layer.alert("平台分成格式不正确！",{icon:2});
			return;
		}
		if($('#agent').val()==null||$('#agent').val()==""){
			top.layer.alert("代理商分成不能为空！",{icon:2});
			return;
		}
		if(!reg.test($('#agent').val())){
			top.layer.alert("代理商分成格式不正确！",{icon:2});
			return;
		}
		if($('#referees').val()==null||$('#referees').val()==""){
			top.layer.alert("推荐人不能为空！",{icon:2});
			return;
		}
		if(!reg.test($('#referees').val())){
			top.layer.alert("推荐人格式输入不正确！",{icon:2});
			return;
		}
		if($('#lockingPeople').val()==null||$('#lockingPeople').val()==""){
			top.layer.alert("锁定人不能为空！",{icon:2});
			return;
		}
		if(!reg.test($('#lockingPeople').val())){
			top.layer.alert("锁定人格式输入不正确！",{icon:2});
			return;
		}
		if($('#other').val()==null||$('#other').val()==""){
			top.layer.alert("其他不能为空！",{icon:2});
			return;
		}
		if(!reg.test($('#other').val())){
			top.layer.alert("其他格式输入不正确！",{icon:2});
			return;
		}
		var num=Number($('#platform').val())+Number($('#agent').val())+Number($('#referees').val())+Number($('#lockingPeople').val())+Number($('#other').val())
		if(num!=100){
			top.layer.alert("抽成比例总和应该为100！",{icon:2});
			return;
		}
		var id=$("#id").val();
		if(id==null||id==""){
			$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/distribution/doSaveDistribution" />',
				data:{
					platform:$('#platform').val(),
					agent:$('#agent').val(),
					referees:$('#referees').val(),
					lockingPeople:$('#lockingPeople').val(),
					other:$('#other').val()
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						top.layer.alert("保存成功！",{icon:1});
						window.location.href='<spring:url value="/headquarters/distribution/doEnDistributionList"/>';
					}
					else{
						top.layer.alert("保存失败！",{icon:2});
					}
				}
			 });
		}else{
			$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/distribution/doEditDistribution" />',
				data:{
					id:$("#id").val(),
					platform:$('#platform').val(),
					agent:$('#agent').val(),
					referees:$('#referees').val(),
					lockingPeople:$('#lockingPeople').val(),
					other:$('#other').val()
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						top.layer.alert("修改成功！",{icon:1});
						window.location.href='<spring:url value="/headquarters/distribution/doEnDistributionList"/>';
					}else{
						top.layer.alert("修改失败！",{icon:2});
					}
				}
			 });
	   	}
		
	});	
</script>
</html>
