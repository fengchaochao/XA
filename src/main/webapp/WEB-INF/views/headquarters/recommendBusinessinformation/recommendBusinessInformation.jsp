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
    <style>
    .col-sm-2{width:120px;}
    .showFileCssStyle img{height:90px;width:130px;margin-top:4px;}
    #merchantsClassificationId{width:220px;} 
    .col-sm-3{width:16%;}
    </style>
</head>
<script>
	/*
	 * 通过地址获取经纬度
	 */
	function getJWD(){
		var sheng = $("#sel_province").find("option:selected").text();
		var city = $("#sel_city").find("option:selected").text();
		var area = $("#sel_area").find("option:selected").text();
		var address = $("#address").val();
		
		$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/fromAddressGetJYD" />',
					data:{
						address:sheng+city+area+address
					},
					dataType: "json",
					async: false,
					success: function(data){
						$("#jyd").val(data.data);
					}
				});
	}
</script>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">推荐商家</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
        <br/>
		<form id="submitForm" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-2">商家名称</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="vendorName" name="vendorName" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">商家分类</label>
				<div class="col-sm-10">
				    <select name="merchantsClassificationId" id = "merchantsClassificationId" class="form-control" >
						<option value="">请选择商家分类</option>
						<c:forEach items="${businessClassificationList}" var="var">
							<option value="${var.id}">${var.categoryName}</option>
						</c:forEach>
					</select> 
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">组织机构代码</label>
				<div class="col-sm-10">
				   <input type="text" class="form-control" id="organizationCode" name="organizationCode" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">手机号码</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="merchantPhone" name="merchantPhone" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">地址</label>
				<div class="col-sm-3">
		            <select id="sel_country" style="display:none;"  class="form-control" ></select>
		            <select id="sel_province" name="sel_province"  class="form-control" ></select>
			    </div>
			    <div class="col-sm-3">
		            <select id="sel_city" name="sel_city"   class="form-control" ></select>
				</div>
			    <div class="col-sm-3">
		            <select id="sel_area" name="sel_area"   class="form-control" ></select>
				</div>
			</div>
			<input type="hidden" class="form-control" id="jyd" name="" readonly="readonly"/>
			<div class="form-group">
				<label class="control-label col-sm-2">详细地址</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="address" name="address" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">营业执照照片</label>
				<div class="col-sm-10">
	                    <input type="hidden" disabled="disabled" id="businessIcensePhoto" class="form-control"/>
	                    <div id="material"></div>
						<input type="file" id="file_upload" name="upufdmfile"  multiple="true" />
						
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">店铺照片</label>
				<div class="col-sm-10">
	                    <input type="hidden" disabled="disabled" id="storePhotos" class="form-control"/>
	                    <div id="material2"></div>
						<input type="file" id="file_upload2" name="storePhotos"  multiple="true" />
				</div>
			</div>
			<div class="form-group">
		        <div class="col-sm-4">
				  	  <input type="button" value="提交" class="referBtn col-sm-offset-1" id="commit" style="margin-left:80px;"/>
		        </div>
	        </div>
		</form> 
	</div>
	<script type="text/javascript">
	var addfileType = "material"; 
	var addfileType2 = "material2";
	$(function() {
		select_Init();
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
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pics" style="width: 130px;height: 90px"/></div>'
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
	                       message: '格式不正确!'
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
	           address : {
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
	                   },regexp: {
	                       regexp: /^[A-Za-z0-9]+$/,
	                       message: '只能是数字或者字母!'
	                   }
	                 }
	          }
	        }
	 	});
		$("#commit").bind("click",function(){
			var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
				getJWD();
				if($('#jyd').val()=="经纬度获取失败！"){
					top.layer.alert("您输入的地址不正确！",{icon:2});
					return;
				}
				if($('#businessIcensePhoto').val()==null||$('#businessIcensePhoto').val()==""){
					top.layer.alert("营业执照不能为空！",{icon:2});
					return;
				}
				if($('#storePhotos').val()==null||$('#storePhotos').val()==""){
					top.layer.alert("店铺照片不能为空！",{icon:2});
					return;
				}
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/recommendBusinessInformation/doSaveBusinessInformation" />',
					data:{
						merchantsClassificationId:$('#merchantsClassificationId').val(),
						vendorName:$('#vendorName').val(),
						merchantPhone:$('#merchantPhone').val(),
						provincesId:$('#sel_province').val(),
						cityId:$('#sel_city').val(),
						areaId:$('#sel_area').val(),
						address:$('#address').val(),
						organizationCode:$('#organizationCode').val(),
						businessIcensePhoto:$('#businessIcensePhoto').val(),
						jyd:$('#jyd').val(),
						storePhotos:$('#storePhotos').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							window.location.href='<spring:url value="/headquarters/recommendBusinessInformation/doEnRecommendBusinessInformation" />';
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
	// 下拉列表初始化
	function select_Init(){
		// 初始化国家
		country_Init();
		// 默认选中中国且不可修改
		$("#sel_country").find("option[value='1']").attr("selected", true);
		$("#sel_country").attr("disabled",true);
		
		// 初始化省份
		province_Init();
        
		// 初始化城市
		city_Init();
        
        // 初始化县区
		area_Init();
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
	</script>
</body>
</html>
