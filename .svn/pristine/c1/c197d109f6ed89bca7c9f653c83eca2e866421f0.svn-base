<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrapValidator/bootstrapValidator.min.js" />'></script>
	<script type="text/javascript" src='<spring:url value="/js/plugin/bootstrapValidator/zh_CN.js" />'></script>
    <link href='<spring:url value="/js/plugin/uploadifive/uploadifive.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/businessInformationAdd.css" />' rel="stylesheet"/>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商家详情</span>
       <p>
           <em>状态</em>
           <c:if test="${businessInformation.businessState=='0' }">
               <input type="hidden" id="businessState" value="${businessInformation.businessState}"/>正常
           </c:if>
           <c:if test="${businessInformation.businessState=='1' }">
               <input type="hidden" id="businessState" value="${businessInformation.businessState}"/>停用
           </c:if>
           <c:if test="${businessInformation.businessState=='0' }">
               <input type="button" value="启用" class="startBtn"  disabled="disabled"/>
               <input type="button" value="停用" class="closeBtn" onclick="delRecord();" /> 
           </c:if>
           <c:if test="${businessInformation.businessState=='1' }">
               <input type="button" value="启用" class="closeBtn"  onclick="delRecord();"/>
               <input type="button" value="停用" class="startBtn"  disabled="disabled"/> 
           </c:if>
       </p>
    </div>
    <!--end pageHeadline -->
    
	<form id="submitForm" class="form-horizontal">
	   <div class="whiteBox">
	        <input type="hidden" id="id" value="${businessInformation.id}" />
	        <input type="hidden" id="flag" value="${flag}" />
	        <input type="hidden" id="higherAgentId" value="${businessInformation.higherAgentId}" />
	        <div class="form-group" style="margin-top: 15px" >
	            <label class="control-label col-sm-2">商家编号</label>
	            <div class="col-sm-10">
	                <input type="hidden" id="merchantNumber" value="${businessInformation.merchantNumber}" />${businessInformation.merchantNumber}
	            </div>
	            <p class="flRight">
		            <input type="button" value="修改" class="blueModifyBtn" id="update" />
		            <input type="button" value="保存" class="greenPreserveBtn" id="commit" style="display: none" />
		            <input type="button" value="取消" class="orangeCancelBtn" id="cancel" style="display: none"/>
		            <input type="button" value="自定义抽成比例" class="orangeDefaultBtn"id="customAs"/>
	            </p>
	        </div>
	        <div class="form-group rowThree">
	            <label class="control-label col-sm-2">商家分类</label>
	            <div class="col-sm-10">
	                <select name="merchantsClassificationId" id = "merchantsClassificationId" class="form-control" disabled="disabled" >
	                    <option value="">请选择商家分类</option>
	                    <c:forEach items="${businessClassificationList}" var="var">
	                        <option value="${var.id}" 
	                        <c:if test="${var.id==businessInformation.merchantsClassificationId}">
	                            selected="selected"
	                        </c:if>
	                        >${var.categoryName}</option>
	                    </c:forEach>
	                </select> 
	            </div>
	            <label class="control-label col-sm-2">商家名称</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="vendorName" name="vendorName" value="${businessInformation.vendorName}" disabled="disabled" />
	            </div>
	            <label class="control-label col-sm-2">手机号码</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="merchantPhone" name="merchantPhone" value="${businessInformation.merchantPhone}" disabled="disabled"/>
	            </div>
	        </div>
	        <div class="form-group rowThree">
	            <label class="control-label col-sm-2">详细地址</label>
	            <div class="col-sm-10 rowLong">
	                <div class="form-control" disabled="disabled">${businessInformation.provinces}省${businessInformation.city}市${businessInformation.area}${businessInformation.address}</div>
	            </div>
	        </div>
	        <div class="form-group rowThree">
                 <label class="control-label col-sm-2">上级代理</label>
                 <div class="col-sm-10">
                    <div class="form-control" disabled="disabled">${businessInformation.agentName}</div> 
                 </div>
                 <label class="control-label col-sm-2">推荐人</label>
                 <div class="col-sm-10">
                    <div class="form-control" disabled="disabled">${businessInformation.founderName}</div> 
                 </div>
             </div>
             <div class="form-group rowThree">
                 <label class="control-label col-sm-2">锁定用户</label>
                 <div class="col-sm-10">
                     ${businessInformation.localUser} 
                     <span class="orangeText col-sm-offset-1" onclick="checkUser();">查看</span>
                 </div>
                 <label class="control-label col-sm-2">推荐商家</label>
                 <div class="col-sm-10">
                    ${businessInformation.recommendedBusiness} 
                    <span class="orangeText col-sm-offset-1" onclick="recommendedBusiness();">查看</span>
                 </div>
             </div>
	     </div>
			<div class="informateBox" id="companyAgent">
				<div class="leftBox" style="float:left;">
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">工商登记名称</label>
						<div class="col-sm-10">
						   <div class="form-control lookOverRow">${businessInformation.registrationName}</div> 
						</div>
					</div>
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">组织机构代码</label>
						<div class="col-sm-10 ">
						  <div class="form-control lookOverRow">${businessInformation.organizationCode}</div>
						</div>
					</div>
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">地址</label>
						<div class="col-sm-10">
						     <div class="form-control lookOverRow">${businessInformation.companyProvinces}省${businessInformation.companyCity}市${businessInformation.companyArea}${businessInformation.registrationAddress}</div>
						</div>
					</div>
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">营业执照照片</label>
						<div class="col-sm-10">
						  <img src="<spring:url value='${businessInformation.businessIcensePhoto}'/>" alt="" style="width: 90px;height: 90px" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">店铺照片</label>
						<div class="col-sm-10">
			                  <img src="<spring:url value='${businessInformation.storePhotos}'/>" style="width: 90px;height: 90px" />
						</div>
					</div>
				</div>
				<div class="rightBox">
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">姓名</label>
						<div class="col-sm-10">
						   <div class="form-control lookOverRow">${businessInformation.userName}</div>
						</div>
					</div>
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">身份证号</label>
						<div class="col-sm-10">
						   <div class="form-control lookOverRow">${businessInformation.idNumber}</div>
						</div>
					</div>
					<div class="form-group rowTwo">
						<label class="control-label col-sm-2">地址</label>
						<div class="col-sm-10">
						     <div class="form-control lookOverRow">${businessInformation.userProvinces}省${businessInformation.userCity}市${businessInformation.userArea}${businessInformation.userAddress}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">身份证照片</label>
						<div class="col-sm-10">
		                    <c:forEach items="${idNoPhoto}" var="i">
								<img src="<spring:url value='${i}'/>" alt="" style="width: 90px;height: 90px" />
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		<div class="form-group" >
	        <div class="col-sm-12" style="margin-top:40px;">
			  <div class="navbar-btn">
			 	  <input type="button" class="yellowReturnRad" value="返回" id="return" />
			  </div>
	        </div>
        </div>
	</form> 
	<script type="text/javascript">
	$(function() {
   		//输入验证
		$('#submitForm').bootstrapValidator({
	       message: 'This value is not valid',
	       feedbackIcons: {
	           valid: 'glyphicon glyphicon-ok',
	           invalid: 'glyphicon glyphicon-remove',
	           validating: 'glyphicon glyphicon-refresh'
	       },
	       fields: {
	       		merchantsClassificationId : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },
	               }
	           },
	       		vendorName : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },
	               }
	           },
	            merchantPhone : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /^1[34578]\d{9}$/,
	                       message: '格式不正确!'
	                   }
	                 }
	               
	          	} 
	        }
	 	});
	   	$("#return").bind("click",function(){
			if($("#flag").val()=='1'){
				window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationList" />';
		   	}
	   	 	if($("#flag").val()=='2'){
				window.location.href='<spring:url value="/headquarters/recommendBusinessInformation/doEnRecommendBusinessInformationList" />';
		   	}
		   	if($("#flag").val()=='3'){
				window.location.href='<spring:url value="/headquarters/agent/doEnAgentEdit"/>'+'?agentId='+$("#higherAgentId").val();
		   	}
		   	if($("#flag").val()=='4'){
				window.location.href='<spring:url value="/headquarters/memberStatistics/doEnMemberStatisticsList"/>';
		   	}
		   	if($("#flag").val()=='5'){
				window.location.href='<spring:url value="/headquarters/memberStatistics/doEnAgentEdit"/>'+'?agentId='+$("#higherAgentId").val();
		   	}
		});
		//修改
	   	$("#update").bind("click",function(){
			$('#merchantsClassificationId').attr("disabled",false); 
			$('#vendorName').attr("disabled",false); 
			$('#merchantPhone').attr("disabled",false); 
			$("#cancel").show();
			$("#commit").show();
			$("#update").hide();
			
		});
		$('#customAs').click(function () {
           	parent.layer.open({
   				title:"自定义抽成比例",
   			    type: 2,
   			    area: ['60%', '50%'],
   			    fix: false, //不固定
   			    maxmin: true,
   			    content: '<spring:url value="/headquarters/businessInformation/doEnBusinessInformationCustomAs" />?businessInformationId='+$("#id").val()
   			});
        });
		//取消
	   	$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+$("#id").val()+'&flag='+$("#flag").val();				
		});
		$("#commit").bind("click",function(){
			var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doEditBusinessInformation" />',
					data:{
						id:$('#id').val(),
						merchantsClassificationId:$('#merchantsClassificationId').val(),
						vendorName:$('#vendorName').val(),
						merchantPhone:$('#merchantPhone').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+$("#id").val()+'&flag='+$("#flag").val();	
							layer.msg('操作成功', {icon: 1});
						}
						else{
							layer.msg('操作失败', {icon: 6});
						}
					}
				});
			}
		});	
	})
		
	//停用
	function delRecord(){
	   	var state=$("#businessState").val();
	   	var id=$("#id").val();
	   	if(state=="0"){
		 	layer.confirm('确认停用吗？', {
	  			btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doDelBusinessInformation"/>',
					data:{
						businessInformationId:id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+id+'&flag='+$("#flag").val();	
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
	     if(state=="1"){
	      	layer.confirm('确认启用吗？', {
		 		 btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doDelBusinessInformation"/>',
					data:{
						businessInformationId:id
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessInformationEdit"/>'+'?businessInformationId='+id+'&flag='+$("#flag").val();	
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
	//被子页面调用
	function beCalled(data){
		if(data.body){
			top.layer.msg("更新成功!",{icon:1});
		}else{
			top.layer.msg("更新失败！",{icon:2});
		}
	}
	
	//锁定用户
	function checkUser(){
		window.location.href='<spring:url value="/headquarters/consumersAccount/doEnBusinessLocalUserList" />?businessInformationId='+$("#id").val()+'&flag='+$("#flag").val();
	}
	//推荐商家列表
	function recommendedBusiness(){
		window.location.href='<spring:url value="/headquarters/businessInformation/doEnBusinessRecommendList" />?founder='+$("#id").val()+'&flag='+$("#flag").val();
	}
</script>
</body>
</html>
