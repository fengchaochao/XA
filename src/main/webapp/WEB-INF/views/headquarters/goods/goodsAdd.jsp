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
    <script type="text/javascript" src='<spring:url value="/js/plugin/ckeditor/ckeditor.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/ckeditor/samples/js/sample.js" />'></script>
    <link href='<spring:url value="/js/plugin/uploadifive/uploadifive.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/goods.css" />' rel="stylesheet"/>
    <style>
    .tabInform span{width:210px;}
    .tabInform input[type="text"]{width:200px;}
    </style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商品新增</span>
       <p>
	       <input type="button" value="保存" class="keepBtn" id="commit" />
	       <input type="button" value="取消" class="cancelBtn col-sm-offset-1" id="cancel" />
       </p>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
	<form id="submitForm" class="form-horizontal">
	    <div class="form-group rowshortBox" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品名称</label>
			<div class="col-sm-10">
			    <input type="text" class="form-control" id="commodityName" name="commodityName" />
			</div>
		</div>
	    <div class="form-group rowshortBox" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品分类</label>
			<div class="col-sm-10">
			    <select class="form-control"  name="commodityTypeId" id = "commodityTypeId" >
					<option value="">请选择商品分类</option>
					<c:forEach items="${goodsClassificationList}" var="var">
						<option value="${var.id}">${var.categoryName}</option>
					</c:forEach>
				</select> 
			</div>
		</div>
        <div class="form-group rowshortBox">
            <label class="control-label col-sm-2">推广费</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="promotionFee" name="promotionFee" />
            </div>
        </div>
        <div class="form-group rowshortBox">
            <label class="control-label col-sm-2">配送费</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="shippingFee" name="shippingFee" />
            </div>
        </div>
        <p style="width:825px;text-align:right;margin-bottom: 0px;"><button id="addSpecifications" class="greenText">新增规格表</button></p>
        <div class="form-group">
            <label class="control-label col-sm-2">规格表</label>
            <div class="col-sm-10">
                <div class="tabInform">
                    <p class="tabName">
                        <span>规格名称</span><span>库存</span><span>价格</span><span style="width:50px;">操作</span>
                    </p>
                    <p class="specifications">
                        <input type="text" class="" id="specificationsName1" />
                        <input type="text" class="" id="inventory1"/>
                        <input type="text" class="" id="price1"/>
                        <button class="redText">删除</button>
                    </p>
                    <p class="specifications">
                        <input type="text" class="" id="specificationsName2"/>
                        <input type="text" class="" id="inventory2"/>
                        <input type="text" class="" id="price2"/>
                        <button class="delete redText">删除</button>
                    </p>
                    <p class="specifications">
                        <input type="text" class="" id="specificationsName3"/>
                        <input type="text" class="" id="inventory3"/>
                        <input type="text" class="" id="price3"/>
                        <button class="delete redText">删除</button>
                    </p>
                    
                </div>
            </div>
        </div>
      
		<div class="form-group" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品描述</label>
			<div class="col-sm-10">
			    <textarea id="commodityDescription" name="commodityDescription" style="width:712px;max-width:712px;"></textarea>
			</div>
		</div>
		  <div class="form-group">
            <label class="control-label col-sm-2">商品详情图片</label>
            <div class="col-sm-10" style="width:760px;">
                    <input type="hidden" disabled="disabled" id="goodsPhotos" class="form-control"/>
                    <div id="material1"></div>
                    <input type="file" id="file_upload1" name="goodsPhotos"  multiple="false" />
                    
            </div>
        </div>
		<div class="form-group">
			<label class="control-label col-sm-2">商品轮播图片</label>
			<div class="col-sm-10" style="width:760px;">
                    <input type="hidden" disabled="disabled" id="commodityImages" class="form-control"/>
                    <div id="material"></div>
					<input type="file" id="file_upload" name="upufdmfile"  multiple="false" />
			</div>
		</div>
		
	</form> 
	</div>
	<script type="text/javascript">
		var addfileType = "material"; 
		var addfileType1 = "material1"; 
		$(function() {
			$('#file_upload').uploadify({
			'buttonImage' : false,
			'swf'      : '<spring:url value="/js/plugin/uploadify-3.2.1/uploadify.swf" />',
			'method'   :  'post',
			'uploader' : '<spring:url value="/filesvr/uploadify" />',
			'fileObjName'   : 'upufdmfile',
	    	'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>",
	    	'fileExt' : 'image/*',
	    	'uploadLimit' :'5',
	    	'fileSizeLimit':'0',
			'onUploadSuccess' : function(file, data, response){
	            var jsonData = $.parseJSON(data);
	        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
	        		var upfilepath = jsonData.body.upfileFilePath;
	        		var upfilename = jsonData.body.upfileFileName;
	        		var pic=$("#commodityImages").val();
	        		if(pic==null||pic==''){
	        			$("#commodityImages").val(upfilepath);
	        		}else{
	        			$("#commodityImages").val(pic+','+upfilepath);
	        		}
		        	var num=$(".pics").length;
	       			var b='<spring:url value="/'+upfilepath+'" />';
	       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pics" style="width: 130px;height: 90px"/></div>';
	       			$("#material").append(a);
		        	$(".imgDelet").click(function(){
				   		var a=$(this).attr("id");
				   		if(($("#commodityImages").val().indexOf(a)+a.length)==$("#commodityImages").val().length){
				   			$("#commodityImages").val($("#commodityImages").val().replace(a, ""));
				   		}else{
				   			$("#commodityImages").val($("#commodityImages").val().replace(a+",", ""));
				   		}
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
	    	'buttonText': "<a>添加图片</a> <em class='addChoose'>(可以多选)</em>",
	    	'fileExt' : 'image/*',
	    	'uploadLimit' :'9',
	    	'fileSizeLimit':'0',
			'onUploadSuccess' : function(file, data, response){
	            var jsonData = $.parseJSON(data);
	        	if(jsonData.body.upfileFilePath!=null && jsonData.body.upfileFilePath!=''){
	        		var upfilepath = jsonData.body.upfileFilePath;
	        		var upfilename = jsonData.body.upfileFileName;
	        		var pic=$("#goodsPhotos").val();
	        		if(pic==null||pic==''){
	        			$("#goodsPhotos").val(upfilepath);
	        		}else{
	        			$("#goodsPhotos").val(pic+','+upfilepath);
	        		}
		        	var num=$(".pic").length;
	       			var b='<spring:url value="/'+upfilepath+'" />';
	       			var a='<div style="display:inline-block;margin:5px;"><span class="imgDelet1" id="'+upfilepath+'" style="display:block;cursor: pointer;color:##337ab7;">删除</span><img alt="" src="'+b+'" id="pic'+(num+1)+'" class="pic" style="width: 130px;height: 90px"/></div>';
	       			$("#material1").append(a);
		        	$(".imgDelet1").click(function(){
				   		var a=$(this).attr("id");
				   		if(($("#goodsPhotos").val().indexOf(a)+a.length)==$("#goodsPhotos").val().length){
				   			$("#goodsPhotos").val($("#goodsPhotos").val().replace(a, ""));
				   		}else{
				   			$("#goodsPhotos").val($("#goodsPhotos").val().replace(a+",", ""));
				   		}
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
	       		commodityName : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },
	               }
	           },
	            commodityTypeId : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   }
	                 }
	               },
	           promotionFee : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /^(([0-9][0-9]*)|(([0]\.\d{1,2}|[0-9][0-9]*\.\d{1,2})))$/,
	                       message: '格式错误!'
	                   }
	               }
	           } ,
	           shippingFee : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /^(([0-9][0-9]*)|(([0]\.\d{1,2}|[0-9][0-9]*\.\d{1,2})))$/,
	                       message: '格式错误!'
	                   }
	               }
	           }   
	       }
	 	});
	   $("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/goods/doEnGoodsList" />';
	   });
	   /*规格表格新增 */
	   $("#addSpecifications").click(function(){
	  	    var num=$(".specifications").length;
	   		var cloneHtml='<p class="specifications">\
	   		                  <input type="text" class="" id="specificationsName'+(num+1)+'"/>\
	   		                  <input type="text" class="" id="inventory'+(num+1)+'"/>\
	   		                  <input type="text" class="" id="price'+(num+1)+'"/>\
	   		                  <button class="delete redText">删除</button></p>';
	   		$(".tabInform").append(cloneHtml);
	   		$(".delete").click(function(){
	   			$(this).parents("p").remove();
	   		});
	   });
	   $(".delete").click(function(){
   			$(this).parents("p").remove();
   		});
	 	$("#commit").bind("click",function(){
	 		var num=$(".specifications").length;
	 		var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
	 		var regs=/^[0-9]*$/;
	 		var specifications = "";
	 		var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
		 		for ( var i= 1; i < (num+1); i++) {
					var specificationsName=$("#specificationsName"+i).val();
					if(specificationsName==null||specificationsName==""){
						top.layer.alert("规格名称不能为空！",{icon:2});
						return;
					}
					var inventory=$("#inventory"+i).val();
					if(inventory==null||inventory==""){
						top.layer.alert("库存不能为空！",{icon:2});
						return;
					}
					var price=$("#price"+i).val();
					if(price==null||price==""){
						top.layer.alert("价格不能为空！",{icon:2});
						return;
					}
					if(!regs.test(inventory)){
						top.layer.alert("库存输入格式不正确！",{icon:2});
						$("#inventory"+i).val("");
						$("#inventory"+i).focus();
						return;
					}
					if(!reg.test(price)){
						top.layer.alert("价格输入格式不正确！",{icon:2});
						$("#price"+i).val("");
						$("#price"+i).focus();
						return;
					}
					if(specifications==""||specifications==null){
						specifications+=specificationsName+"@"+inventory+"@"+price;
					}else{
						specifications+=","+specificationsName+"@"+inventory+"@"+price;
					}
				}
				if(specifications==null||specifications==""){
					top.layer.alert("规格不能为空！",{icon:2});
					return;
				}
				if(specifications==""||specifications==null){
					top.layer.alert("商品规格不能为空！",{icon:2});
					return;
				}
				if($('#commodityDescription').val()==null||$('#commodityDescription').val()==""){
					top.layer.alert("商品描述不能为空！",{icon:2});
					return;
				}
				if($('#goodsPhotos').val()==null||$('#goodsPhotos').val()==""){
					top.layer.alert("商品图片不能为空！",{icon:2});
					return;
				}
				if($('#commodityImages').val()==null||$('#commodityImages').val()==""){
					top.layer.alert("商品轮播图片不能为空！",{icon:2});
					return;
				}
				$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/goods/doSaveGoods" />',
				data:{
					commodityName:$("#commodityName").val(),
					commodityTypeId:$('#commodityTypeId').val(),
					commodityDescription:$('#commodityDescription').val(),
					goodsPhotos:$('#goodsPhotos').val(),
					commodityImages:$('#commodityImages').val(),
					promotionFee:$('#promotionFee').val(),
					shippingFee:$('#shippingFee').val(),
					specifications:specifications
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						window.location.href='<spring:url value="/headquarters/goods/doEnGoodsList" />';
						layer.msg('操作成功', {icon: 1});
					}
					else{
						layer.msg('操作失败', {icon: 6});
					}
				}
			 });
		   }
		});	
	});
	</script>
</body>
</html>
