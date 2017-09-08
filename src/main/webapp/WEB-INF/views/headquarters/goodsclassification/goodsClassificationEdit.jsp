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
    .specification input{background:none;}
    .newHeadline{font-size: 16px;margin: 10px 0px 30px 0px;font-weight: bold;}
    </style>
</head>

<body class="mainContant" style="background:#fff;width:97%;">
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
		    <p class="newHeadline">新增分类</p>
			<input type="hidden" id="id" value="${goodsClassification.id}" />
		    <%-- <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">编号</label>
				<div class="col-sm-10">
				    <c:if test="${goodsClassificationId==null||goodsClassificationId==''}">
					 	<input type="hidden" class="form-control" id="goodsNumber" value="${goodsNumber}" />${goodsNumber}
					</c:if>
				   <c:if test="${goodsClassificationId!=null&&goodsClassificationId!=''}">
					 	<input type="hidden" class="form-control" id="goodsNumber" value="${goodsClassification.goodsNumber}" />${goodsClassification.goodsNumber}
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">分类名称</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="goodsClassification" value="${goodsClassification.categoryName}" />
				</div>
			</div> --%>
			<table>
                <thead>
                   <tr>
                      <th>编号</th><th>分类名称</th>
                   </tr>
                </thead>
                <tbody>
                   <tr>
                      <td>
                          <c:if test="${goodsClassificationId==null||goodsClassificationId==''}">
	                         <input type="hidden" class="form-control" id="goodsNumber" value="${goodsNumber}"  />${goodsNumber}
	                      </c:if>
	                      <c:if test="${goodsClassificationId!=null&&goodsClassificationId!=''}">
	                         <input type="hidden" class="form-control" id="goodsNumber" value="${goodsClassification.goodsNumber}"  />${goodsClassification.goodsNumber}
	                      </c:if>
                      </td>
                      <td class="specification">
                          <input type="text" id="goodsClassification" value="${goodsClassification.categoryName}" maxlength="30"/>
                      </td>
                   </tr>
                </tbody>
            </table>
			<div class="form-group">
		        <div class="col-sm-12">
				  <div class="navbar-btn" style="margin:40px 0px 0px 0px;">
				  	  <input type="button" value="保存" class="confirm" id="commit" />
				 	  <input type="button" value="取消" class="cancelBtn" id="cancel" />
				  </div>
		        </div>
	        </div>
		</form> 
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		$("#commit").bind("click",function(){
		if($("#goodsClassification").val()==null||$("#goodsClassification").val()==""){
			top.layer.alert("分类名称不能为空！",{icon:2});
			return;
		}
		var patt1=new RegExp(/^[\u4e00-\u9fa5]+$/);
		if(!patt1.test($('#goodsClassification').val())){
			top.layer.alert("分类名称只能输入中文！",{icon:2});
			return;
		}
		var id=$("#id").val();
		if(id==null||id==""){
			$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/goodsClassification/toRepeatGoodsClassification" />',
				data:{
					categoryName:$("#goodsClassification").val()
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("1"==data.body){
						top.layer.alert("该分类名称已存在，请重新输入！",{icon:2});
						$("#goodsClassification").val("");
						$("#goodsClassification").focus("");
						return;
					}else{
						$.ajax({
							type: "POST",
							url : '<spring:url value="/headquarters/goodsClassification/doSaveGoodsClassification" />',
							data:{
								goodsNumber:$('#goodsNumber').val(),
								categoryName:$('#goodsClassification').val()
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
				url : '<spring:url value="/headquarters/goodsClassification/doEditGoodsClassification" />',
				data:{
					id:$("#id").val(),
					goodsNumber:$('#goodsNumber').val(),
					categoryName:$('#goodsClassification').val()
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
	});	
	
  });
</script>
</html>
