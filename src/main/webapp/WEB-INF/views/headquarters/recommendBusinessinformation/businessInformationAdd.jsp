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
       <span class="leftNotice">新增商家信息</span>
    </div>
    <!--end pageHeadline -->
    
	<form id="submitForm" class="form-horizontal">
	     <div class="whiteBox">
		    <input type="hidden" id="id" value="${businessInformation.id}"/>
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">商家编号</label>
				<div class="col-sm-10">
				    <input type="hidden" id="merchantNumber" value="${merchantNumber}" />${merchantNumber}
				</div>
			</div>
			<div class="form-group rowThree">
				<label class="control-label col-sm-2">商家分类</label>
				<div class="col-sm-10">
				    <select name="merchantsClassificationId" id = "merchantsClassificationId" class="form-control" >
						<option value="">请选择商家分类</option>
						<c:forEach items="${businessClassificationList}" var="var">
							<option value="${var.id}" <c:if test="${businessInformation.merchantsClassificationId==var.id}"> selected="selected" </c:if>>${var.categoryName}</option>
						</c:forEach>
					</select> 
				</div>
				<label class="control-label col-sm-2">商家名称</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="vendorName" name="vendorName" value="${businessInformation.vendorName}"/>
				</div>
				<label class="control-label col-sm-2">手机号码</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="merchantPhone" name="merchantPhone" value="${businessInformation.merchantPhone}"/>
				</div>
			</div>
			<div class="form-group rowAddress">
				<label class="control-label col-sm-2">地址</label>
				<div class="col-sm-10">
		            <select id="sel_country" style="display:none;"  class="form-control" ></select>
		            <select id="sel_province" name="sel_province"  class="form-control" ></select>
			    </div>
			    <div class="col-sm-10">
		            <select id="sel_city" name="sel_city"   class="form-control" ></select>
				</div>
			    <div class="col-sm-10">
		            <select id="sel_area" name="sel_area"   class="form-control" ></select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">详细地址</label>
				<div class="col-sm-10 rowLong">
				    <input type="text" class="form-control" id="address" name="address" value="${businessInformation.address}"/>
				</div>
				<label class="control-label col-sm-2">钻石卡号</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="diamondCard" value="${agent.diamondCard}" disabled="disabled"/>
				</div>
			</div>
		</div>
		<div class="whiteBox">
		<div class="informateBox" id="companyAgent">
			<div class="leftBox">
				<div class="form-group">
					<label class="control-label col-sm-2">工商登记名称</label>
					<div class="col-sm-10">
					   <input type="text" class="form-control" id="registrationName" name="registrationName" value="${businessInformation.registrationName}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">组织机构代码</label>
					<div class="col-sm-10">
					   <input type="text" class="form-control" id="organizationCode" name="organizationCode" value="${businessInformation.organizationCode}"/>
					</div>
				</div>
				<div class="form-group rowShortAddress">
					<label class="control-label col-sm-2">地址</label>
					<div class="col-sm-10">
			            <select id="sel_country1" style="display:none;"  class="form-control" ></select>
			            <select id="sel_province1" name="sel_province1"  class="form-control" ></select>
				    </div>
				    <div class="col-sm-10">
			            <select id="sel_city1" name="sel_city1"   class="form-control" ></select>
					</div>
				    <div class="col-sm-10">
			            <select id="sel_area1" name="sel_area1"   class="form-control" ></select>
					</div>
				</div>
				<div class="form-group rowTwo">
					<label class="control-label col-sm-2">详细地址</label>
					<div class="col-sm-10">
					    <input type="text" class="form-control" id="registrationAddress" name="registrationAddress" value="${businessInformation.registrationAddress}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">营业执照照片</label>
					<div class="col-sm-10" style="width:83%;">
	                    <input type="hidden" disabled="disabled" id="businessIcensePhoto" class="form-control" value="${businessInformation.businessIcensePhoto}"/>
						<div id="material">
                            <div style="display:inline-block;margin:5px;">
                                <span class="imgDelet" id="${businessInformation.businessIcensePhoto}'/>" style="display:block;cursor: pointer;color:#337ab7;">删除</span>
                                <img alt="" src="<spring:url value='${businessInformation.businessIcensePhoto}'/>" id="pic${indx.index}" class="pics" style="width: 130px;height: 90px"/>
                            </div>
                        </div>
						<input type="file" id="file_upload" name="upufdmfile"  multiple="true" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">店铺照片</label>
					<div class="col-sm-10" style="width:83%;">
		                    <input type="hidden" disabled="disabled" id="storePhotos" class="form-control" value="${businessInformation.storePhotos}"/>
                            <div id="material2">
                                <div style="display:inline-block;margin:5px;">
                                    <span class="imgDelet2" id="${businessInformation.storePhotos}'/>" style="display:block;cursor: pointer;color:#337ab7;">删除</span>
                                    <img alt="" src="<spring:url value='${businessInformation.storePhotos}'/>" id="pic" class="photos" style="width: 130px;height: 90px"/>
                                </div>
                            </div>
							<input type="file" id="file_upload2" name="storePhotos"  multiple="true" />
					</div>
				</div>
			</div>
			<div class="rightBox">
				<div class="form-group">
					<label class="control-label col-sm-2">姓名</label>
					<div class="col-sm-10">
					   <input type="text" class="form-control" id="userName" name="userName" value="${businessInformation.userName}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">身份证号</label>
					<div class="col-sm-10">
					   <input type="text" class="form-control" id="idNumber" name="idNumber" value="${businessInformation.idNumber}"/>
					</div>
				</div>
				<div class="form-group rowShortAddress">
					<label class="control-label col-sm-2">地址</label>
					<div class="col-sm-10">
			            <select id="sel_country2" style="display:none;"  class="form-control" ></select>
			            <select id="sel_province2" name="sel_province2"  class="form-control" ></select>
				    </div>
				    <div class="col-sm-10">
			            <select id="sel_city2" name="sel_city2"   class="form-control" ></select>
					</div>
				    <div class="col-sm-10">
			            <select id="sel_area2" name="sel_area2"   class="form-control" ></select>
					</div>
				</div>
				<div class="form-group rowTwo">
					<label class="control-label col-sm-2">详细地址</label>
					<div class="col-sm-10">
					    <input type="text" class="form-control" id="userAddress" name="userAddress" value="${businessInformation.userAddress}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">身份证照片</label>
					<div class="col-sm-10" style="width:83%;">
	                    <input type="hidden" disabled="disabled" id="idPhoto" class="form-control" value="${businessInformation.idPhoto}"/>
                        <div id="material1">
                            <c:forEach items="${idNoPhoto}" var="i" varStatus="indx">
                                    <div style="display:inline-block;margin:5px;">
                                        <span class="imgDelet1" id="${i}'/>" style="display:block;cursor: pointer;color:#337ab7;">删除</span>
                                        <img alt="" src="<spring:url value='${i}'/>" id="pic${indx.index}" class="photos" style="width: 140px;height: 140px"/>
                                    </div>
                            </c:forEach>
                        </div>
						<input type="file" id="file_upload1" name="idPhoto"  multiple="false" />
					</div>
				</div>
			</div>
			</div>
		</div>
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			  	  <input type="button" value="保存" class="greenBtnRad" id="commit" />
			 	  <input type="button" value="取消" class="redBtnRad" id="cancel" />
			  </div>
	        </div>
        </div>
	</form> 
	<script type="text/javascript">
	var addfileType = "material"; 
	var addfileType1 = "material1";
	var addfileType2 = "material2";
	$(function() {
		select_Init();
		select_Init1();
		select_Init2();
		$('#file_upload').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>",
    	'fileExt' : '*.jpg;*.png;*.jpeg',
    	'fileSizeLimit':'0',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
       			$("#businessIcensePhoto").val(upfilepath);
        		var num=$(".pics").length;
       			var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet" id="'+upfilepath+'" style="display:block;cursor: pointer;color:#337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pics" style="width: 140px;height: 140px"/></div>'
       			$("#material").html(a);
	        	$(".imgDelet").click(function(){
			   		$("#businessIcensePhoto").val("");
			   		$(this).parent("div").remove();
			   });
        	}else{
        		alert("文件上传异常："+jsonData.head.respContent);
        	}
        },
   });
	$('#file_upload1').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>",
    	'fileExt' : '*.jpg;*.png;*.jpeg',
    	'fileSizeLimit':'0',
    	'uploadLimit' :'2',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
        		var pic=$("#idPhoto").val();
        		if(pic==null||pic==''){
        			$("#idPhoto").val(upfilepath);
        		}else{
        			$("#idPhoto").val(pic+','+upfilepath);
        		}
	        	var num=$(".photos").length;
       			var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet1" id="'+upfilepath+'" style="display:block;cursor: pointer;color:#337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" style="width: 140px;height: 140px"/></div>'
       			$("#material1").append(a);
	        	$(".imgDelet1").click(function(){
			   		var a=$(this).attr("id");
			   		if(($("#idPhoto").val().indexOf(a)+a.length)==$("#idPhoto").val().length){
			   			$("#idPhoto").val($("#idPhoto").val().replace(a, ""));
			   		}else{
			   			$("#idPhoto").val($("#idPhoto").val().replace(a+",", ""));
			   		}
			   		$(this).parent("div").remove();
			   		$('#file_upload1').uploadify('settings','uploadLimit', ++uploadLimit);
			   });
        	}
        	else{
        		alert("文件上传异常："+jsonData.head.respContent);
        	}
        },
   });
   $(".imgDelet").click(function(){
   		var a=$(this).attr("id");
   		if(($("#businessIcensePhoto").val().indexOf(a)+a.length)==$("#businessIcensePhoto").val().length){
   			$("#businessIcensePhoto").val($("#businessIcensePhoto").val().replace(a, ""));
   		}else{
   			$("#businessIcensePhoto").val($("#businessIcensePhoto").val().replace(a+",", ""));
   		}
   		$(this).parent("div").remove();
   });
   $(".imgDelet1").click(function(){
   		var a=$(this).attr("id");
   		if(($("#idPhoto").val().indexOf(a)+a.length)==$("#idPhoto").val().length){
   			$("#idPhoto").val($("#idPhoto").val().replace(a, ""));
   		}else{
   			$("#idPhoto").val($("#idPhoto").val().replace(a+",", ""));
   		}
   		$(this).parent("div").remove();
   });
   $(".imgDelet2").click(function(){
  	    $("#storePhotos").val("");
   		$(this).parent("div").remove();
   });
   $('#file_upload2').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	'buttonText': "<a>添加图片<em class='addChoose'>(单图上传)</em></a>",
    	'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
    	'fileSizeLimit':'0',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
        		$("#storePhotos").val(upfilepath);
        		var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet2" id="'+upfilepath+'" style="display:block;cursor: pointer;color:#337ab7;">删除</span><img alt="" src="'+b+'" id="pic" class="photos" style="width: 140px;height: 140px"/></div>'
       			$("#material2").html(a);
	        	$(".imgDelet2").click(function(){
			   		$("#storePhotos").val("");
			   		$(this).parent("div").remove();
			   });
        	}
        	else{
        		alert("文件上传异常："+jsonData.head.respContent);
        	}
        }
   });
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
	                       message: '只能是数字!'
	                   }
	                 }
	               },
	           sel_province : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_city : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_area : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_province1 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_city1 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_area1 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_province2 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_city2 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_area2 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           address : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           registrationName : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	            organizationCode : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	          } ,
	            registrationAddress : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	          } ,
	            userName : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	          } ,
	            idNumber : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/,
	                       message: '格式不正确!'
	                   }
	                 }
	          } ,
	            userAddress : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	          	} 
	        }
	 	});
	   	$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/recommendBusinessInformation/doEnAgentShowBusinessInformationList" />';
		});
		$("#commit").bind("click",function(){
			var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
				if($('#businessIcensePhoto').val()==null||$('#businessIcensePhoto').val()==""){
					top.layer.alert("营业执照不能为空！",{icon:2});
					return;
				}
				if($('#storePhotos').val()==null||$('#storePhotos').val()==""){
					top.layer.alert("店铺照片不能为空！",{icon:2});
					return;
				}
				if($('#idPhoto').val()==null||$('#idPhoto').val()==""){
					top.layer.alert("身份证照片不能为空！",{icon:2});
					return;
				}
				
				if($('#idPhoto').val().split(",").length!='2'){
					top.layer.alert("身份证照片必须上传正反两张照片！",{icon:2});
					return;
				}
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/recommendBusinessInformation/doEditBusinessInformation" />',
					data:{
						id:$("#id").val(),
						merchantNumber:$("#merchantNumber").val(),
						merchantsClassificationId:$('#merchantsClassificationId').val(),
						vendorName:$('#vendorName').val(),
						merchantPhone:$('#merchantPhone').val(),
						provincesId:$('#sel_province').val(),
						cityId:$('#sel_city').val(),
						areaId:$('#sel_area').val(),
						companyProvincesId:$('#sel_province1').val(),
						companyCityId:$('#sel_city1').val(),
						companyAreaId:$('#sel_area1').val(),
						userProvincesId:$('#sel_province2').val(),
						userCityId:$('#sel_city2').val(),
						userAreaId:$('#sel_area2').val(),
						address:$('#address').val(),
						registrationName:$('#registrationName').val(),
						diamondCard:$('#diamondCard').val(),
						organizationCode:$('#organizationCode').val(),
						registrationAddress:$('#registrationAddress').val(),
						userName:$('#userName').val(),
						idNumber:$('#idNumber').val(),
						userAddress:$('#userAddress').val(),
						businessIcensePhoto:$('#businessIcensePhoto').val(),
						storePhotos:$('#storePhotos').val(),
						idPhoto:$('#idPhoto').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if(data.body=="保存成功"){
							layer.msg(data.body, {icon: 1});
							window.location.href='<spring:url value="/headquarters/recommendBusinessInformation/doEnAgentShowBusinessInformationList" />';
							
						}else{
							layer.msg(data.body, {icon: 6});
							return;
						}
					}
				});
			}
		});	
	})
		
		// 下拉列表初始化
		function select_Init(){
			// 初始化国家
			country_Init();
			// 默认选中中国且不可修改
			$("#sel_country").find("option[value='1']").attr("selected", true);
			$("#sel_country").attr("disabled",true);
			
			// 初始化省份
			province_Init();
	        $("#sel_province").find("option[value='${businessInformation.provincesId}']").attr("selected", true);
			// 初始化城市
			city_Init();
			 $("#sel_city").find("option[value='${businessInformation.cityId}']").attr("selected", true);
	        
	        // 初始化县区
			area_Init();
			$("#sel_area").find("option[value='${businessInformation.areaId}']").attr("selected", true);
		}
		
		// 国家下拉事件
		$('#sel_country').change(function(){
			province_Init();
		});
		
		// 省份下拉事件
		$('#sel_province').change(function(){
			city_Init();
		});
		
		// 市下拉事件
		$('#sel_city').change(function(){
			area_Init();
		});
		
		// 初始化国家
		function country_Init(){
			$("#sel_country").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init(){
			$("#sel_province").empty();
			$("#sel_province").append('<option value="">请选择省</option>');
			$("#sel_city").empty();
			$("#sel_city").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country option:selected').val()=='${item.parentId}'){
	            	$("#sel_province").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init(){
			$("#sel_city").empty();
			$("#sel_city").append('<option value="">请选择市</option>');
			$("#sel_area").empty();
			$("#sel_area").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province option:selected').val()=='${item.parentId}'){
	            	$("#sel_city").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init(){
			$("#sel_area").empty();
			$("#sel_area").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city option:selected').val()=='${item.parentId}'){
	            	$("#sel_area").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		// 下拉列表初始化
		function select_Init1(){
			// 初始化国家
			country_Init1();
			// 默认选中中国且不可修改
			$("#sel_country1").find("option[value='1']").attr("selected", true);
			$("#sel_country1").attr("disabled",true);
			
			// 初始化省份
			province_Init1();
	        $("#sel_province1").find("option[value='${businessInformation.companyProvincesId}']").attr("selected", true);
			// 初始化城市
			city_Init1();
			 $("#sel_city1").find("option[value='${businessInformation.companyCityId}']").attr("selected", true);
	        
	        // 初始化县区
			area_Init1();
			$("#sel_area1").find("option[value='${businessInformation.companyAreaId}']").attr("selected", true);
		}
		
		// 国家下拉事件
		$('#sel_country1').change(function(){
			province_Init1();
		});
		
		// 省份下拉事件
		$('#sel_province1').change(function(){
			city_Init1();
		});
		
		// 市下拉事件
		$('#sel_city1').change(function(){
			area_Init1();
		});
		
		// 初始化国家
		function country_Init1(){
			$("#sel_country1").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country1").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init1(){
			$("#sel_province1").empty();
			$("#sel_province1").append('<option value="">请选择省</option>');
			$("#sel_city1").empty();
			$("#sel_city1").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country1 option:selected').val()=='${item.parentId}'){
	            	$("#sel_province1").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init1(){
			$("#sel_city1").empty();
			$("#sel_city1").append('<option value="">请选择市</option>');
			$("#sel_area1").empty();
			$("#sel_area1").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province1 option:selected').val()=='${item.parentId}'){
	            	$("#sel_city1").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init1(){
			$("#sel_area1").empty();
			$("#sel_area1").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city1 option:selected').val()=='${item.parentId}'){
	            	$("#sel_area1").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		// 下拉列表初始化
		function select_Init2(){
			// 初始化国家
			country_Init2();
			// 默认选中中国且不可修改
			$("#sel_country2").find("option[value='1']").attr("selected", true);
			$("#sel_country2").attr("disabled",true);
			
			// 初始化省份
			province_Init2();
	        $("#sel_province2").find("option[value='${businessInformation.userProvincesId}']").attr("selected", true);
			// 初始化城市
			city_Init2();
			 $("#sel_city2").find("option[value='${businessInformation.userCityId}']").attr("selected", true);
	        
	        // 初始化县区
			area_Init2();
			$("#sel_area2").find("option[value='${businessInformation.userAreaId}']").attr("selected", true);
		}
		
		// 国家下拉事件
		$('#sel_country2').change(function(){
			province_Init2();
		});
		
		// 省份下拉事件
		$('#sel_province2').change(function(){
			city_Init2();
		});
		
		// 市下拉事件
		$('#sel_city2').change(function(){
			area_Init2();
		});
		
		// 初始化国家
		function country_Init2(){
			$("#sel_country2").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country2").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init2(){
			$("#sel_province2").empty();
			$("#sel_province2").append('<option value="">请选择省</option>');
			$("#sel_city2").empty();
			$("#sel_city2").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country2 option:selected').val()=='${item.parentId}'){
	            	$("#sel_province2").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init2(){
			$("#sel_city2").empty();
			$("#sel_city2").append('<option value="">请选择市</option>');
			$("#sel_area2").empty();
			$("#sel_area2").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province2 option:selected').val()=='${item.parentId}'){
	            	$("#sel_city2").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init2(){
			$("#sel_area2").empty();
			$("#sel_area2").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city2 option:selected').val()=='${item.parentId}'){
	            	$("#sel_area2").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
	</script>
</body>
</html>
