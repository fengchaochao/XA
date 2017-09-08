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
    <link href='<spring:url value="/css/common/consumers.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/js/plugin/uploadifive/uploadifive.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/common/businessInformationAdd.css" />' rel="stylesheet"/>
    <style>
    .leftBox,.rightBox{background:#fff;padding:15px;min-height:215px;}
    .form-group {margin-bottom: 10px;}
    .whiteBox{padding: 20px 5px;}
    .swfupload{background:none;}
    .uploadify-button-text{display:block;}
    .swfupload, .uploadify-button-text{height:30px!important;border: 1px solid #236adc;
        text-align: center;border-radius: 50px;color:#236adc;}
    .uploadify{margin-top:0px;}
    #pic{width:320px;height:245px;}
    .imgDelet{color:#f74f4c;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">消费者详情页</span>
       <p style="margin-right:70px;">
           <em>状态</em>
           <c:if test="${consumers.merchantsState=='0' }">
               <input type="hidden" id="merchantsState" value="${consumers.merchantsState}"/>正常
           </c:if>
           <c:if test="${consumers.merchantsState=='1' }">
               <input type="hidden" id="merchantsState" value="${consumers.merchantsState}"/>停用
           </c:if>
       </p>
    </div>
    <!--end pageHeadline -->
    
	<form id="submitForm" class="form-horizontal">
		<input type="hidden" id="id" value="${consumers.id}" />
		<div class="informateBox" id="companyAgent" style="padding:0px;margin-bottom:20px;">
			<div class="leftBox">
				<div class="form-group">
					<label class="control-label col-sm-3">消费者编号</label>
					<div class="col-sm-9">
					   ${consumers.customerNumber} 
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">消费者昵称</label>
					<div class="col-sm-9">
					   ${consumers.nickName} 
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">性别</label>
					<div class="col-sm-9">
					   ${consumers.customerSex} 
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">生日</label>
					<div class="col-sm-9">
					   ${consumers.birthday} 
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">地址</label>
					<div class="col-sm-9">
					   ${consumers.address} 
					</div>
				</div>
				
			</div>
			<div class="rightBox" style="float:right;">
				<div class="form-group">
					<label class="control-label col-sm-3">消费账户</label>
					<div class="col-sm-9">
					   <span style="margin-right:15px;">支付宝账户</span><span>${payAccount}</span>
					</div>
				</div>
				<div class="form-group" >
                    <label class="control-label col-sm-3"></label>
                    <div class="col-sm-9" style="margin-top:5px;">
                       <p style="width:180%;height:1px;border-top:1px dashed #ddd;"></p>
                    </div>
                </div> 
				<div class="form-group">
                    <label class="control-label col-sm-3"></label>
                    <div class="col-sm-9">
                       <span style="margin-right:15px;">微信账户</span><span>${weixinAccount}</span>
                    </div>
                </div>
			</div>
		</div>
		<div class="whiteBox">
		     <div class="form-group">
                 <label class="control-label col-sm-2">收款凭证</label>
                 <div class="col-sm-10">
                     <input type="hidden" disabled="disabled" id="certificatePhotos" class="form-control" value="${consumers.certificatePhotos}"/>
                     <input type="file" id="file_upload0" name="upufdmfile"  multiple="true" />
                     <div id="material">
                         <div style="display:inline-block;margin:5px;">
                             <c:if test="${consumers.certificatePhotos!=null&&consumers.certificatePhotos!=''}">
                                 <span class="imgDelet" id="${consumers.certificatePhotos}" style="display:block;cursor: pointer;">删除</span>
                                 <img alt="" src="<spring:url value="${consumers.certificatePhotos}" />" id="pic" class="pics"/>
                             </c:if>
                         </div>
                     </div>
                 </div>
             </div>
		</div>
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			 	  <input type="button" value="确认已收款" class="greenBtnRad" id="sure1" <c:if test="${consumers.applyUpgrade=='1' }"> disabled="disabled"</c:if> onclick="surePay();" />
			 	  <input type="button" value="开通消费商" class="yellowReturnRad" id="sure" <c:if test="${consumers.applyUpgrade=='0' }"> disabled="disabled"</c:if> onclick="sureConsumers();"/>
			  </div>
	        </div>
	    </div>
	</form> 
	<script type="text/javascript">
	var addfileType = "material";
	$(function() {
		$('#sure').attr("disabled",true); 
		 $('#file_upload0').uploadify({
				'buttonImage' : false,
				'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
				'method'   :  'post',
				'uploader' : '<spring:url value="/filesvr/uploadify" />',
				'fileObjName'   : 'upufdmfile',
		    	'buttonText': "<a>上传收款凭证</a>",
		    	'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
		    	'fileSizeLimit':'0',
				'onUploadSuccess' : function(file, data, response){
		            var jsonData = $.parseJSON(data);
		        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
		        		var upfilepath = jsonData.body.upfileFilePath;
		        		var upfilename = jsonData.body.upfileFileName;
		        		$("#certificatePhotos").val(upfilepath);
		        		var b='<spring:url value="/'+upfilepath+'" />';
		        		var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet" id="'+upfilepath+'" style="display:block;cursor: pointer;color:#337ab7;">删除</span><img alt="" src="'+b+'" id="pic" class="photos"/></div>'
       					$("#material").html(a);
		        		$("#pic").attr('src',b);
			        	$(".imgDelet").click(function(){
					   		$(this).parent("div").remove();
					   		$("#certificatePhotos").val("");
					   });
		        	}
		        	else{
		        		alert("文件上传异常："+jsonData.head.respContent);
		        	}
		        }
		   });
			$(".imgDelet").click(function(){
		   		$(this).parent("div").remove();
		   		$("#certificatePhotos").val("");
		   });
	})
	function surePay(){
		if($("#certificatePhotos").val()==null||$("#certificatePhotos").val()==""){
			top.layer.alert("请上传收款凭证！",{icon:2});
			return;
		}
		$.ajax({
			type: "POST",
			url : '<spring:url value="/headquarters/applicationConsumers/doEditConsumers" />',
			data:{
				id:$("#id").val(),
				certificatePhotos:$('#certificatePhotos').val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					layer.msg('确认成功', {icon: 1});
					$('#sure').attr("disabled",false); 
					$('#sure1').attr("disabled",true); 
					
				}else{
					layer.msg('确认失败', {icon: 6});
				}
			}
		});
	
	}
	function sureConsumers(){
		$.ajax({
			type: "POST",
			url : '<spring:url value="/headquarters/applicationConsumers/doEditConsumersState" />',
			data:{
				id:$("#id").val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					layer.msg('升级成功', {icon: 1});
					window.location.href='<spring:url value="/headquarters/applicationConsumers/doEnConsumersList"/>';
				}else{
					layer.msg('升级失败', {icon: 6});
				}
			}
		});
	
	}
</script>
</body>
</html>
