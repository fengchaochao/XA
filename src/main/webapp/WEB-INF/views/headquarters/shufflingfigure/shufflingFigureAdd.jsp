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
    <link href='<spring:url value="/js/plugin/uploadifive/uploadifive.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/common/shufflingFigure.css" />' rel="stylesheet"/>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">图片设置</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
			<img src="" alt="" id="showPic" style="width: 440px;height: 250px;margin-bottom:20px;"/>
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">图片尺寸</label>
				<div class="col-sm-10">
				    <input type="hidden" class="form-control" id="picSize" name="picSize" /><em id="size"></em>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">图片格式</label>
				<div class="col-sm-10">
				    <input type="hidden" class="form-control" id="picFormat" name="picFormat" /><em id="format"></em>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">渠道</label>
				<div class="col-sm-10">
				    <input type="hidden" class="form-control" id="typeName"  name="typeName" value="${typeName}"/>${typeName}
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">图片序号</label>
				<div class="col-sm-10">
				    <input type="hidden" class="form-control" id="serialNumber" name="serialNumber" value="${serialNumber}"/>${serialNumber}
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">跳转链接</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="linkAddress" name="linkAddress"  />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">图片上传</label>
				<div class="col-sm-10">
	                <input type="hidden" disabled="disabled" id="pic" class="form-control"/>
					<input type="file" id="file_upload" name="pic"  multiple="true" />
					<div id="material"></div>
				</div>
			</div>
			
		</form>
	</div> 
	
   <div class="form-group">
       <div class="col-sm-12">
         <div class="navbar-btn">
             <input type="button" value="确定" class="yellowReturnRad" id="commit" />
             <input type="button" value="取消" class="redBtnRad col-sm-offset-1" id="cancel" />
         </div>
       </div>
   </div>
</body>
<script type="text/javascript">
	var addfileType = "material"; 
	$(function() {
		$('#file_upload').uploadify({
			'buttonImage' : false,
			'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
			'method'   :  'post',
			'uploader' : '<spring:url value="/filesvr/uploadifyFigure" />',
			'fileObjName'   : 'upufdmfile',
	    	'buttonText': "<a>添加图片</a>",
	    	'fileExt' : '*.jpg;*.png;*.jpeg',
	    	'fileSizeLimit':'0',
			'onUploadSuccess' : function(file, data, response){
	            var jsonData = $.parseJSON(data);
	        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
	        		var upfilepath = jsonData.body.upfileFilePath;
	        		var upfilename = jsonData.body.upfileFileName;
	        		var arr=upfilepath.split(".");
	        		$("#showPic").attr("src",'<spring:url value="/'+upfilepath+'" />')
	        		$("#pic").val(upfilepath);
	        		$("#picFormat").val(arr[1]);
	        		$("#format").html(arr[1]);
	        		$("#picSize").val(jsonData.body.size);
	        		$("#size").html(jsonData.body.size);
	        	}else{
	        		alert("文件上传异常："+jsonData.head.respContent);
	        	}
	        },
	   });
		$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/shufflingFigure/doEnShufflingFigureList" />';
		});
	});
		
	$("#commit").bind("click",function(){
		if($('#pic').val()==null||$('#pic').val()==""){
			top.layer.alert("图片不能为空！",{icon:2});
			return;
		}
		if($('#linkAddress').val()==null||$('#linkAddress').val()==""){
			top.layer.alert("跳转链接不能为空！",{icon:2});
			return;
		}
		$.ajax({
			type: "POST",
			url : '<spring:url value="/headquarters/shufflingFigure/doEditShufflingFigure" />',
			data:{
				picSize:$('#picSize').val(),
				picFormat:$('#picFormat').val(),
				typeName:$('#typeName').val(),
				serialNumber:$('#serialNumber').val(),
				linkAddress:$('#linkAddress').val(),
				pic:$('#pic').val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					window.location.href='<spring:url value="/headquarters/shufflingFigure/doEnShufflingFigureList" />';
					layer.msg('保存成功', {icon: 1});
				}
				else{
					layer.msg('保存失败', {icon: 6});
				}
			}
		});
	});	
</script>
</html>
