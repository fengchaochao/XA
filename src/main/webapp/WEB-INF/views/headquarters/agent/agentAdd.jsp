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
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/agentAdd.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/js/plugin/uploadifive/uploadifive.css"/>' rel="stylesheet" />
</head>
<script>
	/*
	 * 通过地址获取经纬度
	 */
	function getJWD(){
		var sheng = $("#sel_province4").find("option:selected").text();
		var city = $("#sel_city4").find("option:selected").text();
		var area = $("#sel_area4").find("option:selected").text();
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
       <span class="leftNotice">新增代理商</span>
    </div>
    <!--end pageHeadline -->
    
	<form id="submitForm" class="form-horizontal">
	    <div class="whiteBox">
	       <div class="form-group" style="margin-top: 15px" >
	            <label class="control-label col-sm-2">代理商编号</label>
	            <div class="col-sm-10">
	                <input type="hidden" id="agentCode" value="${agentCode}" />${agentCode}
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">代理商名称</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="agentName" name="agentName" />
	            </div>
	            <label class="control-label col-sm-2">联系电话</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="phone" name="phone" />
	            </div>
	            <label class="control-label col-sm-2">钻石卡号</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="diamondCard" name="diamondCard" />
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">地址</label>
	            <div class="col-sm-10">
	                <select id="sel_country4" style="display:none;"  class="form-control" ></select>
	                <select id="sel_province4" name="sel_province4"  class="form-control" ></select>
	            </div>
	            <div class="col-sm-10">
	                <select id="sel_city4" name="sel_city4"   class="form-control" ></select>
	            </div>
	            <div class="col-sm-10">
	                <select id="sel_area4" name="sel_area4"   class="form-control" ></select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">详细地址</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="address" name="address" />
	            </div>
	        </div>
	        <div class="form-group">
                <label class="control-label col-sm-2">代理区域</label>
                <div class="col-sm-10">
                    <div class="chooseResult" onclick="agentArea();">选择代理区域</div>
                </div>
            </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">代理商类型</label>
	            <div class="col-sm-10">
	                <input type="radio" id="agentType" name="agentType" value="1" onclick="companyAgentType();" checked="checked"/> 公司代理商
	                <input type="radio" id="agentType" name="agentType" value="2" onclick="personAgentType();"/> 个人代理商
	            </div>
	        </div>
	    </div>
	    <div class="whiteBox">
			<div class="form-group">
				<label class="control-label col-sm-2"></label>
				<div class="col-sm-10">
				    <input type="hidden" id="state" value="0" /> 
				</div>
			</div>
			<div class="informateBox" id="companyAgent">
			<input type="hidden" class="form-control" id="jyd" name="" readonly="readonly"/>
				<div class="leftBox">
					<div class="form-group">
						<label class="control-label col-sm-2">公司名称</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="companyName" name="companyName" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">组织机构代码</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="organizationCode" name="organizationCode" />
						</div>
					</div>
					<div class="form-group">
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
					<div class="form-group">
						<label class="control-label col-sm-2">详细地址</label>
						<div class="col-sm-10">
						    <input type="text" class="form-control" id="companyAddress" name="companyAddress" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">营业执照照片</label>
						<div class="col-sm-10 imgLongRow">
		                    <input type="hidden" disabled="disabled" id="businessLicensePhoto" class="form-control"/>
							<div id="material"></div>
						    <input type="file" id="file_upload" name="upufdmfile"  multiple="true"  />
						</div>
					</div>
				</div>
				<div class="rightBox">
					<div class="form-group">
						<label class="control-label col-sm-2">姓名</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="userName" name="userName" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">身份证号</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="idNo" name="idNo" />
						</div>
					</div>
					<div class="form-group">
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
					<div class="form-group">
						<label class="control-label col-sm-2">详细地址</label>
						<div class="col-sm-10">
						    <input type="text" class="form-control" id="userAddress" name="userAddress" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">身份证照片</label>
						<div class="col-sm-10 imgLongRow">
		                    <input type="hidden" disabled="disabled" id="idNoPhoto" class="form-control"/>
		                    <div id="material1"></div>
							<input type="file" id="file_upload1" name="idNoPhoto"  multiple="false" />
							
						</div>
					</div>
				</div>
			</div>
			<div class="informateBox" style="display: none" id="personAgent">
				<div class="leftBox">
					<div class="form-group">
						<label class="control-label col-sm-2">姓名</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="userName1" name="userName1" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">身份证号</label>
						<div class="col-sm-10">
						   <input type="text" class="form-control" id="idNo1" name="idNo1" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">地址</label>
						<div class="col-sm-10">
				            <select id="sel_country3" style="display:none;"  class="form-control" ></select>
				            <select id="sel_province3" name="sel_province3"  class="form-control" ></select>
					    </div>
					    <div class="col-sm-10">
				            <select id="sel_city3" name="sel_city3"   class="form-control" ></select>
						</div>
					    <div class="col-sm-10">
				            <select id="sel_area3" name="sel_area3"   class="form-control" ></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">详细地址</label>
						<div class="col-sm-10">
						    <input type="text" class="form-control" id="userAddress1" name="userAddress1" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">身份证照片</label>
						<div class="col-sm-10">
		                    <input type="hidden" disabled="disabled" id="idNoPhoto1" class="form-control"/>
							<input type="file" id="file_upload2" name="idNoPhoto1"  multiple="false" />
							<div id="material2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="showLawyer"></div>
		<div class="areaBox" style="display: none">
	       <p class="agencyAreaLine">代理区域</p> 
	       <div class="headThreeLevels">请选择代理范围&nbsp;&nbsp;&nbsp;&nbsp;
	           <span class="scopeOfAgent" id="0">省级</span>
	           <span id="1">市级</span>
	           <span id="2">区级</span>
	       </div>
	       <div class="tabBoxMatter provinceBox">
	           <div class="chooseProBox">
		         <c:forEach items="${provinceList}" var="var" varStatus="vat">
	           		<span id="${var.areaId}">${var.areaName}省</span>
	           	 </c:forEach>
	           </div>
	           <p style="margin:15px 0px;">您选择的代理区域</p>
	           <div class="comThreeArea">
		           <div class="resultProBox">
		           </div>
	           </div>
	       </div>
	       <div class="tabBoxMatter cityBox">
	           	 <p>请选择省/市</p>
	            <select id="sel_country5" style="display:none;"  class="form-control" ></select>
	            <select id="sel_province5" name="sel_province5"  class="form-control selectProvin" onchange="selectPro(this);"></select>
	           <div class="chooseCityBox" id="selectCity">
                   
               </div>
               <p style="margin:15px 0px;">您选择的代理区域</p>
               <div class="comThreeArea">
	               <div class="resultCityBox">
	               </div>
               </div>
	       </div>
	       <div class="tabBoxMatter regionBox">
	           <p>请选择省/市</p>
	            <select id="sel_country6" style="display:none;"  class="form-control selectProvin" ></select>
	            <select id="sel_province6" name="sel_province6"  class="form-control selectProvin"></select>
	           <select id="sel_city6" name="sel_city6"   class="form-control selectProvin" onchange="selectCt(this);" ></select>
               <div class="chooseRegionBox" id="selectArea">
                  
               </div>
               <p style="margin:15px 0px;">您选择的代理区域</p>
               <div class="comThreeArea">
	               <div class="resultRegionBox">
	               </div>
               </div> 
	       </div>
	       <p class="footerBtn"><button class="confirm" onclick="closeAgentArea();">确定</button><button class="cancelBtn" >取消</button></p>
	    </div> 
		<div class="form-group">
	        <div>
			  <div class="navbar-btn">
			  	  <input type="button" value="确定" class="greenBtnRad" id="commit" />
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
	
	select_Init4();
	select_Init1();
	select_Init2();
	select_Init3();
	select_Init5();
	select_Init6();
	
	$('#file_upload').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	/* 'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>", */
    	'buttonImg':'<spring:url value="/img/load_up_img.png" />',
    	'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.gif', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc 
    	'fileSizeLimit':'0',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
       			$("#businessLicensePhoto").val(upfilepath);
        		var num=$(".pics").length;
       			var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pics" style="width: 130px;height: 90px"/></div>'
       			$("#material").html(a);
	        	$(".imgDelet").click(function(){
			   		$("#businessLicensePhoto").val("");
			   		$(this).parent("div").remove();
			   });
        	}
        	else{
        		alert("文件上传异常："+jsonData.head.respContent);
        	}
        }
   });
   $('#file_upload1').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	/* 'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>", */
    	"buttonImg":'<spring:url value="/img/load_up_img.png" />',
    	'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.gif', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc 
    	'uploadLimit' :'2',
    	'fileSizeLimit':'0',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
        		var pic=$("#idNoPhoto").val();
        		if(pic==null||pic==''){
        			$("#idNoPhoto").val(upfilepath);
        		}else{
        			$("#idNoPhoto").val(pic+','+upfilepath);
        		}
        		var num=$(".photos").length;
       			var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet1" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="photos" style="width: 130px;height: 90px"/></div>'
       			$("#material1").append(a);
	        	$(".imgDelet1").click(function(){
			   		var a=$(this).attr("id");
			   		if(($("#idNoPhoto").val().indexOf(a)+a.length)==$("#idNoPhoto").val().length){
			   			$("#idNoPhoto").val($("#idNoPhoto").val().replace(a, ""));
			   		}else{
			   			$("#idNoPhoto").val($("#idNoPhoto").val().replace(a+",", ""));
			   		}
			   		$(this).parent("div").remove();
			   		$('#file_upload1').uploadify('settings','uploadLimit', ++uploadLimit);
			   });
        	}
        	else{
        		alert("文件上传异常："+jsonData.head.respContent);
        	}
        }
   });
   $('#file_upload2').uploadify({
		'buttonImage' : false,
		'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
		'method'   :  'post',
		'uploader' : '<spring:url value="/filesvr/uploadify" />',
		'fileObjName'   : 'upufdmfile',
    	/* 'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>", */
    	"buttonImg":'<spring:url value="/img/load_up_img.png" />',
    	'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.gif', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc 
    	'uploadLimit' :'2',
    	'fileSizeLimit':'0',
		'onUploadSuccess' : function(file, data, response){
            var jsonData = $.parseJSON(data);
        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
        		var upfilepath = jsonData.body.upfileFilePath;
        		var upfilename = jsonData.body.upfileFileName;
        		var pic=$("#idNoPhoto1").val();
        		if(pic==null||pic==''){
        			$("#idNoPhoto1").val(upfilepath);
        		}else{
        			$("#idNoPhoto1").val(pic+','+upfilepath);
        		}
	        	var num=$(".pictures").length;
       			var b='<spring:url value="/'+upfilepath+'" />';
       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet2" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pictures" style="width: 130px;height: 90px"/></div>'
       			$("#material2").append(a);
	        	$(".imgDelet2").click(function(){
			   		var a=$(this).attr("id");
			   		if(($("#idNoPhoto1").val().indexOf(a)+a.length)==$("#idNoPhoto1").val().length){
			   			$("#idNoPhoto1").val($("#idNoPhoto1").val().replace(a, ""));
			   		}else{
			   			$("#idNoPhoto1").val($("#idNoPhoto1").val().replace(a+",", ""));
			   		}
			   		$(this).parent("div").remove();
			   		$('#file_upload2').uploadify('settings','uploadLimit', ++uploadLimit);
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
       		agentName : {
               validators: {
                   notEmpty: {
                       message: '不能为空'
                   },
               }
           },
            phone : {
               validators: {
                   notEmpty: {
                       message: '不能为空'
                   },regexp: {
                       regexp: /^1[34578]\d{9}$/,
                       message: '格式不正确!'
                   }
                 }
               },
	           sel_province4 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_city4 : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           sel_area4 : {
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
           diamondCard : {
               validators: {
                   notEmpty: {
                       message: '不能为空'
                   },regexp: {
                       regexp: /^[0-9]+$/,
                       message: '只能是数字!'
                   }
               }
           }  
       }
 	});
   	$("#cancel").bind("click",function(){
		window.location.href='<spring:url value="/headquarters/agent/doEnAgentList" />';
	});
	$("#commit").bind("click",function(){
		var areaId="";
		var area="";
		var data={};
		var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
	    bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			getJWD();
			if($('#jyd').val()=="经纬度获取失败！"){
					top.layer.alert("您输入的地址不正确！",{icon:2});
					return;
			}
			/********************代理区域取值*************************/
			var level=$(".scopeOfAgent").attr("id");
			//省级代理商
			if(level=='0'){
				var num=$(".resultProBox span").length;
				for ( var i = 0; i < num; i++) {
					 if(areaId==""){
					 	areaId+=$(".resultProBox span").eq(i).attr("id");
					 }else{
					 	areaId+=","+$(".resultProBox span").eq(i).attr("id");
					 }
				}
				area=$(".resultProBox").parent(".comThreeArea").html();
			}
			//市级代理商
			if(level=='1'){
				var cityN=$(".addCity").length;
				for(var i=0;i<cityN;i++){
				var spanN=$(".addCity").eq(i).find("span").length;
					for(var h=0;h<spanN;h++){
						if(areaId==""){
						 	areaId+=$(".addCity").eq(i).find("span").eq(h).attr("id");
						}else{
						 	areaId+=","+$(".addCity").eq(i).find("span").eq(h).attr("id");
						} 
					}
				}
				area=$(".resultCityBox").parent(".comThreeArea").html();
			}
			//区级代理商
			if(level=='2'){
				var cityN=$(".regionCont").length;
				for(var i=0;i<cityN;i++){
				var spanN=$(".regionCont").eq(i).find("span").length;
					for(var h=0;h<spanN;h++){
						if(areaId==""){
						 	areaId+=$(".regionCont").eq(i).find("span").eq(h).attr("id");
						}else{
						 	areaId+=","+$(".regionCont").eq(i).find("span").eq(h).attr("id");
						} 
					}
				}
				area=$(".resultRegionBox").parent(".comThreeArea").html();
			}
			/************************************/
			if(areaId==""){
				top.layer.alert("请选择代理区域！",{icon:2});
				return;
			}
			if($('input[name="agentType"]:checked').val()=='1'){
				if($('#companyName').val()==null||$('#companyName').val()==""){
					top.layer.alert("公司名称不能为空！",{icon:2});
					return;
				}
				var ap=/^[A-Za-z0-9]+$/
				if($('#organizationCode').val()==null||$('#organizationCode').val()==""){
					top.layer.alert("组织机构代码不能为空！",{icon:2});
					return;
				}
				if(!ap.test($('#organizationCode').val())){
					top.layer.alert("组织机构代码只能为数字或字母！",{icon:2});
					return;
				}
				if($('#companyAddress').val()==null||$('#companyAddress').val()==""){
					top.layer.alert("地址不能为空！",{icon:2});
					return;
				}
				if($('#businessLicensePhoto').val()==null||$('#businessLicensePhoto').val()==""){
					top.layer.alert("营业执照不能为空！",{icon:2});
					return;
				}
				if($('#userName').val()==null||$('#userName').val()==""){
					top.layer.alert("姓名不能为空！",{icon:2});
					return;
				}
				if($('#idNo').val()==null||$('#idNo').val()==""){
					top.layer.alert("身份证号不能为空！",{icon:2});
					return;
				}
				var reg=/(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
				if(!reg.test($('#idNo').val())){
					top.layer.alert("身份证号格式不正确，重新输入！",{icon:2});
					return;
				}
				if($('#sel_province1').val()==null||$('#sel_province1').val()==""){
					top.layer.alert("请选择省份！",{icon:2});
					return;
				}
				if($('#sel_city1').val()==null||$('#sel_city1').val()==""){
					top.layer.alert("请选择市！",{icon:2});
					return;
				}
				if($('#sel_area1').val()==null||$('#sel_area1').val()==""){
					top.layer.alert("请选择县区！",{icon:2});
					return;
				}
				if($('#sel_province2').val()==null||$('#sel_province2').val()==""){
					top.layer.alert("请选择省份！",{icon:2});
					return;
				}
				if($('#sel_city2').val()==null||$('#sel_city2').val()==""){
					top.layer.alert("请选择市！",{icon:2});
					return;
				}
				if($('#sel_area2').val()==null||$('#sel_area2').val()==""){
					top.layer.alert("请选择县区！",{icon:2});
					return;
				}
				if($('#userAddress').val()==null||$('#userAddress').val()==""){
					top.layer.alert("地址不能为空！",{icon:2});
					return;
				}
				if($('#idNoPhoto').val()==null||$('#idNoPhoto').val()==""){
					top.layer.alert("身份证照片不能为空！",{icon:2});
					return;
				}
				if($('#idNoPhoto').val().split(",").length!='2'){
					top.layer.alert("身份证照片必须上传正反两张照片！",{icon:2});
					return;
				}
				data={
					"agentCode":$("#agentCode").val(),
					"agentName":$("#agentName").val(),
					"phone":$("#phone").val(),
					"resellerLevel":$(".scopeOfAgent").attr("id"),
					"provincesId":$('#sel_province4').val(),
					"cityId":$('#sel_city4').val(),
					"areaId":$('#sel_area4').val(),
					"address":$("#address").val(),
					"diamondCard":$("#diamondCard").val(),
					"agentState":$('#state').val(),
					"agentType":$('input[name="agentType"]:checked').val(),
					"companyName":$('#companyName').val(),
					"organizationCode":$('#organizationCode').val(),
					"companyAddress":$('#companyAddress').val(),
					"businessLicensePhoto":$('#businessLicensePhoto').val(),
					"userName":$('#userName').val(),
					"idNo":$('#idNo').val(),
					"userAddress":$('#userAddress').val(),
					"agentAreaId":areaId,
					"showAgentArea":area,
					"companyProvincesId":$('#sel_province1').val(),
					"companyCityId":$('#sel_city1').val(),
					"companyAreaId":$('#sel_area1').val(),
					"userProvincesId":$('#sel_province2').val(),
					"userCityId":$('#sel_city2').val(),
					"userAreaId":$('#sel_area2').val(),
					"jyd":$("#jyd").val(),
					"idNoPhoto":$('#idNoPhoto').val()
				}
		}
		if($('input[name="agentType"]:checked').val()=='2'){
			if($('#userName1').val()==null||$('#userName1').val()==""){
				top.layer.alert("姓名不能为空！",{icon:2});
				return;
			}
			if($('#idNo1').val()==null||$('#idNo1').val()==""){
				top.layer.alert("身份证号不能为空！",{icon:2});
				return;
			}
			var reg=/(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
			if(!reg.test($('#idNo1').val())){
				top.layer.alert("身份证号格式不正确，重新输入！",{icon:2});
				return;
			}
			if($('#sel_province3').val()==null||$('#sel_province3').val()==""){
				top.layer.alert("请选择省份！",{icon:2});
				return;
			}
			if($('#sel_city3').val()==null||$('#sel_city3').val()==""){
				top.layer.alert("请选择市！",{icon:2});
				return;
			}
			if($('#sel_area3').val()==null||$('#sel_area3').val()==""){
				top.layer.alert("请选择县区！",{icon:2});
				return;
			}
			if($('#userAddress1').val()==null||$('#userAddress1').val()==""){
				top.layer.alert("地址不能为空！",{icon:2});
				return;
			}
			if($('#idNoPhoto1').val()==null||$('#idNoPhoto1').val()==""){
				top.layer.alert("身份证照片不能为空！",{icon:2});
				return;
			}
			if($('#idNoPhoto1').val().split(",").length!='2'){
				top.layer.alert("身份证照片必须上传正反两张照片！",{icon:2});
				return;
			}
			data={
				"agentCode":$("#agentCode").val(),
				"agentName":$("#agentName").val(),
				"phone":$("#phone").val(),
				"resellerLevel":$(".scopeOfAgent").attr("id"),
				"provincesId":$('#sel_province4').val(),
				"cityId":$('#sel_city4').val(),
				"areaId":$('#sel_area4').val(),
				"address":$("#address").val(),
				"diamondCard":$("#diamondCard").val(),
				"agentState":$('#state').val(),
				"agentType":$('input[name="agentType"]:checked').val(),
				"userName":$('#userName1').val(),
				"idNo":$('#idNo1').val(),
				"userAddress":$('#userAddress1').val(),
				"agentAreaId":areaId,
				"showAgentArea":area,
				"userProvincesId":$('#sel_province3').val(),
				"userCityId":$('#sel_city3').val(),
				"userAreaId":$('#sel_area3').val(),
				"jyd":$("#jyd").val(),
				"idNoPhoto":$('#idNoPhoto1').val()
			}
		}
		$.ajax({
			type: "POST",
			url : '<spring:url value="/headquarters/agent/doSaveAgent" />',
			data:data,
			dataType: "json",
			async: false,
			success: function(data){
				if(data.body=="该手机号对应的用户信息已存在"){
					layer.msg(data.body, {icon: 6});
					return;
				}else{
					layer.msg(data.body, {icon: 1});
					window.location.href='<spring:url value="/headquarters/agent/doEnAgentList" />';
				}
			}
		});
	  }
	});	
	//代理区域弹层 省、市、区等级选择
	$(".headThreeLevels span").click(function(){
	   $(".chooseProBox span").removeClass("proChooseCss");
	   $(".resultRegionBox,.resultCityBox").hide();
	   $(".resultProBox,.resultCityBox,.chooseCityBox,.resultRegionBox,.chooseRegionBox").html("");
	   select_Init5();
	   select_Init6();
	   $(this).addClass("scopeOfAgent").siblings().removeClass("scopeOfAgent");
	   $(".tabBoxMatter").hide();
	   $(".tabBoxMatter").eq($(this).index()).show();
	});
	
	
	//省级代理商选择
	$(".chooseProBox span").click(function(){
	   $(this).addClass("proChooseCss").siblings().removeClass("proChooseCss");
	   var id=$(this).attr("id");
	   var proHtml="<span id="+id+">"+$(this).html()+"</span>";
	   $(".resultProBox").html(proHtml);
	});
        
  });
	//代理商类型切换
	function companyAgentType(){
		 $("#companyAgent").show();
		 $("#personAgent").hide();
	}
	function personAgentType(){
		 $("#companyAgent").hide();
		 $("#personAgent").show();
	}
	//打开代理商区域弹层
	function agentArea(){  
         $(".showLawyer,.areaBox").show();
	}
	//代理商区域弹层 确认
	function closeAgentArea(){
		 $(".areaBox,.showLawyer").hide();

	}
	//代理商区域弹层 取消
	$(".cancelBtn").click(function(){
		$(".resultProBox").html("");
		$(".resultCityBox").html("");
		$(".resultRegionBox").html("");
	    $(".areaBox,.showLawyer").hide();
	    $(".chooseProBox span").removeClass("proChooseCss");
	    $(".chooseCityBox span").removeClass("proChooseCss");
	    $(".chooseRegionBox span").removeClass("proChooseCss");
	    $(".headThreeLevels span").removeClass("scopeOfAgent");
	    $(".headThreeLevels span").eq(0).addClass("scopeOfAgent");
	    $(".tabBoxMatter").hide();
	    $(".tabBoxMatter").eq(0).show();
	});
	//市级代理商取值
	function select_Init5(){
		// 初始化国家
		country_Init5();
		// 默认选中中国且不可修改
		$("#sel_country5").find("option[value='1']").attr("selected", true);
		$("#sel_country5").attr("disabled",true);
		
		// 初始化省份
		province_Init5();
	        
	}
	// 国家下拉事件
	$('#sel_country5').change(function(){
		province_Init5();
	});
	// 初始化国家
	function country_Init5(){
		$("#sel_country5").append('<option value="">请选择</option>');
		<c:forEach var="item" items="${countryList}">
	    if('${item.parentId==0}'){
	    	$("#sel_country5").append('<option value=${item.areaId}>${item.areaName}</option>');
	    }
		</c:forEach>
	}
	// 初始化省份
	function province_Init5(){
		$("#sel_province5").empty();
		$("#sel_province5").append('<option value="">请选择省</option>');
		<c:forEach var="item" items="${provinceList}">
            if($('#sel_country5 option:selected').val()=='${item.parentId}'){
            	$("#sel_province5").append('<option value=${item.areaId}>${item.areaName}</option>');
            }
        </c:forEach>
	}
	//市级代理商显示市区
	function selectPro(obj){
	    $(".resultCityBox").show();
		var provice=$("#sel_province5").find("option:selected").text();
		var num=$(".proCityAdd").length;
		var sameP=$(".resultCityBox .proCityAdd").length;
		$("#selectCity").empty();
		<c:forEach var="item" items="${cityList}" varStatus="vat">
          if($(obj).val()=='${item.parentId}'){
                $("#selectCity").append('<span title=${item.areaId} id="city${vat.index}">${item.areaName}</span>');
          }
        </c:forEach>
		for(var u=0;u<sameP;u++){
			if($(".proCityAdd").eq(u).find("p").html()==provice+"省"){
				$(".proCityAdd").eq(u).remove();
			}
			if($(".proCityAdd").eq(u).find($(".addCity")).html()==""){
                $(".proCityAdd").eq(u).remove();
            }
		}
		var proId=$("#sel_province5").val();
		var prosAdd='<div class="proCityAdd" id="pros'+proId+'">'+
                     '<p >'+provice+'省</p>'+
                     '<div class="addCity"></div></div>';
        $(".resultCityBox").append(prosAdd);
		$(".chooseCityBox span").click(function(){
              var id=$(this).attr("title");
              if($(this).hasClass("proChooseCss")){
                  var num1=$(".chooseCityBox .proChooseCss").length;
                  $(this).removeClass("proChooseCss");
                  var jqObj=$(this).html();
                  for(var i=0;i<num1;i++){
                       if($("#pros"+proId).find($(".addCity span")).eq(i).html()==jqObj){
                         $("#pros"+proId).find($(".addCity span")).eq(i).remove();
                       } 
                  }
              }else{
                  $(this).addClass("proChooseCss");
                  $("#pros"+proId).find($(".addCity")).append("<span id="+id+">"+$(this).html()+"</span>");
              }
        });
	}
	/******区级代理商取值********/
	//区级代理商取值
	
	function select_Init6(){
		// 区级代理商 初始化国家
		country_Init6();
		// 默认选中中国且不可修改
		$("#sel_country6").find("option[value='1']").attr("selected", true);
		$("#sel_country6").attr("disabled",true);
		
		// 区级代理商 初始化省份
		province_Init6();
        
		// 区级代理商 初始化城市
		city_Init6();
	}
	// 区级代理商 国家下拉事件
	$('#sel_country6').change(function(){
		province_Init6();
	});
	
	// 区级代理商 省份下拉事件
	$('#sel_province6').change(function(){
	    city_Init6();
	    $(".resultRegionBox").show();
        var provice=$("#sel_province6").find("option:selected").text();
        var proId=$("#sel_province6").val();
		$("#a").html(provice);
		$("#selectArea").html("");
		for(var u=0;u<$(".prolist").length;u++){
            if($(".prolist").eq(u).find("p").html()==provice+"省"){
                $(".prolist").eq(u).remove();
            }
             if($(".prolist").eq(u).find($(".addCityAndRegion")).html()==undefined){
                $(".prolist").eq(u).remove();
            } 
        }
        var proHtml='<div class="prolist" id="pro'+proId+'">'+
                    '<p>'+provice+'省</p>'+
                    '</div>';
                   $(".resultRegionBox").append(proHtml);
	});
	// 区级代理商 初始化国家
	function country_Init6(){
		$("#sel_country6").append('<option value="">请选择</option>');
		<c:forEach var="item" items="${countryList}">
	    if('${item.parentId==0}'){
	    	$("#sel_country6").append('<option value=${item.areaId}>${item.areaName}</option>');
	    }
		</c:forEach>
	}
	// 区级代理商 初始化省份
	function province_Init6(){
		$("#sel_province6").empty();
		$("#sel_province6").append('<option value="">请选择省</option>');
		$("#sel_city6").empty();
		$("#sel_city6").append('<option value="">请选择市</option>');
		<c:forEach var="item" items="${provinceList}">
            if($('#sel_country6 option:selected').val()=='${item.parentId}'){
            	$("#sel_province6").append('<option value=${item.areaId}>${item.areaName}</option>');
            }
        </c:forEach>
        
	}
	// 区级代理商 初始化城市
	function city_Init6(){
		$("#sel_city6").empty();
		$("#sel_city6").append('<option value="">请选择市</option>');
		$("#sel_area6").empty();
		$("#sel_area6").append('<option value="">请选择区</option>');
		<c:forEach var="item" items="${cityList}">
            if($('#sel_province6 option:selected').val()=='${item.parentId}'){
            	$("#sel_city6").append('<option value=${item.areaId}>${item.areaName}</option>');
            }
        </c:forEach>
	}
	//区级代理商 显示区域
	function selectCt(obj){
	    var choosePro=$("#sel_province6").find("option:selected").text();
	    var chooseProId=$("#sel_province6").val();
		var city=$("#sel_city6").find("option:selected").text();
		var chooseCityId=$("#sel_city6").val();
		var sameR=$(".resultRegionBox .prolist").length;
		var cityB='<div class="addCityAndRegion" id="city'+chooseCityId+'">'+
                   '<div class="cityCont" id="b">'+city+'</div>'+
                   '<div class="regionCont"> </div></div>';
           for(var i=0;i<$("#pro"+chooseProId).find(".addCityAndRegion").length;i++){
            if($("#pro"+chooseProId).find(".addCityAndRegion").eq(i).find(".cityCont").html()==city){
                $("#pro"+chooseProId).find(".addCityAndRegion").eq(i).remove();
            }
           } 
        $("#pro"+chooseProId).append(cityB);
            
		$("#selectArea").empty();
		
		
		<c:forEach var="item" items="${areaList}" varStatus="vat">
          if($(obj).val()=='${item.parentId}'){
           	 $("#selectArea").append('<span title=${item.areaId} id="area${vat.index}">${item.areaName}</span>');
          }
        </c:forEach>
        
		$(".chooseRegionBox span").click(function(){
		  	var id=$(this).attr("title");
            if($(this).hasClass("proChooseCss")){
              var num1=$(".chooseRegionBox .proChooseCss").length;
              $(this).removeClass("proChooseCss");
               var jqObj=$(this);
              for(var i=0;i<num1;i++){
                   if($("#pro"+chooseProId).find($("#city"+chooseCityId)).find($(".regionCont span")).eq(i).html()==jqObj.html()){
                     $("#pro"+chooseProId).find($("#city"+chooseCityId)).find($(".regionCont span")).eq(i).remove();
                   } 
              } 
          }else{
              $(this).addClass("proChooseCss");
              $("#pro"+chooseProId).find($("#city"+chooseCityId)).find($(".regionCont")).append("<span id="+id+">"+$(this).html()+"</span>");
          }
        });
            
       
	}
	
	/*********地址下拉框选择***********/
	// 下拉列表初始化
	function select_Init4(){
			// 初始化国家
			country_Init4();
			// 默认选中中国且不可修改
			$("#sel_country4").find("option[value='1']").attr("selected", true);
			$("#sel_country4").attr("disabled",true);
			
			// 初始化省份
			province_Init4();
	        
			// 初始化城市
			city_Init4();
	        
	        // 初始化县区
			area_Init4();
		}
		
		// 国家下拉事件
		$('#sel_country4').change(function(){
			province_Init4();
		});
		
		// 省份下拉事件
		$('#sel_province4').change(function(){
			city_Init4();
		});
		
		// 市下拉事件
		$('#sel_city4').change(function(){
			area_Init4();
		});
		
		// 初始化国家
		function country_Init4(){
			$("#sel_country4").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country4").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init4(){
			$("#sel_province4").empty();
			$("#sel_province4").append('<option value="">请选择省</option>');
			$("#sel_city4").empty();
			$("#sel_city4").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_province4").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init4(){
			$("#sel_city4").empty();
			$("#sel_city4").append('<option value="">请选择市</option>');
			$("#sel_area4").empty();
			$("#sel_area4").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_city4").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init4(){
			$("#sel_area4").empty();
			$("#sel_area4").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_area4").append('<option value=${item.areaId}>${item.areaName}</option>');
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
	        
			// 初始化城市
			city_Init1();
	        
	        // 初始化县区
			area_Init1();
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
	        
			// 初始化城市
			city_Init2();
	        
	        // 初始化县区
			area_Init2();
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
		// 下拉列表初始化
		function select_Init3(){
			// 初始化国家
			country_Init3();
			// 默认选中中国且不可修改
			$("#sel_country3").find("option[value='1']").attr("selected", true);
			$("#sel_country3").attr("disabled",true);
			
			// 初始化省份
			province_Init3();
	        
			// 初始化城市
			city_Init3();
	        
	        // 初始化县区
			area_Init3();
		}
		
		// 国家下拉事件
		$('#sel_country3').change(function(){
			province_Init3();
		});
		
		// 省份下拉事件
		$('#sel_province3').change(function(){
			city_Init3();
		});
		
		// 市下拉事件
		$('#sel_city3').change(function(){
			area_Init3();
		});
		
		// 初始化国家
		function country_Init3(){
			$("#sel_country3").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country3").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init3(){
			$("#sel_province3").empty();
			$("#sel_province3").append('<option value="">请选择省</option>');
			$("#sel_city3").empty();
			$("#sel_city3").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country3 option:selected').val()=='${item.parentId}'){
	            	$("#sel_province3").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init3(){
			$("#sel_city3").empty();
			$("#sel_city3").append('<option value="">请选择市</option>');
			$("#sel_area3").empty();
			$("#sel_area3").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province3 option:selected').val()=='${item.parentId}'){
	            	$("#sel_city3").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init3(){
			$("#sel_area3").empty();
			$("#sel_area3").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city3 option:selected').val()=='${item.parentId}'){
	            	$("#sel_area3").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
</script>
</body>
</html>
