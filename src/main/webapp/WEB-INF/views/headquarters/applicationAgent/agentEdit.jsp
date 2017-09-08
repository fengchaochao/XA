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
    <link href='<spring:url value="/css/common/agentEdit.css" />' rel="stylesheet"/>
    <style>
    
    </style>
</head>
<body class="mainContant">
	
	<!--start pageHeadline -->
	<div class="pageHeadline">
	   <span class="leftNotice">新增代理商</span>
	</div>
        
	<form id="submitForm" class="form-horizontal">
	    <div class="whiteBox">
	       <input type="hidden" id="id" value="${agent.id}"/>
	       <div class="form-group" style="margin-top: 15px" >
	            <label class="control-label col-sm-2">代理商编号</label>
	            <div class="col-sm-10">
	                <input type="hidden" id="agentCode" value="${agent.agentCode}" />${agent.agentCode}
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">代理商名称</label>
	            <div class="col-sm-10" style="display:inline-block;">
	               <input type="text" class="form-control" id="agentName" name="agentName" value="${agent.agentName}" disabled="disabled" />
	            </div>
	            <label class="control-label col-sm-2">联系电话</label>
	            <div class="col-sm-10">
	               <input type="text" class="form-control" id="phone" name="phone" value="${agent.phone}" disabled="disabled" />
	            </div>
	            <label class="control-label col-sm-2">钻石卡号</label>
	            <div class="col-sm-10">
	           		 <input type="text" class="form-control" id="diamondCard"/>
	            </div>
	        </div>
	        <div class="form-group"> 
                <label class="control-label col-sm-2">地址</label>
                <div class="col-sm-10">
                    <select id="sel_province4" name="sel_province4"  class="form-control" disabled="disabled"><option>${agent.provinces}省</option></select>
                    
                </div>
                <div class="col-sm-10">
                    <select id="sel_city4" name="sel_city4"   class="form-control"  disabled="disabled"><option>${agent.city}市</option></select>
                </div>
                <div class="col-sm-10">
                    <select id="sel_area4" name="sel_area4"   class="form-control"  disabled="disabled"><option>${agent.area}</option></select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">详细地址</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="address" name="address" value="${agent.address}" disabled="disabled" />
                </div>
            </div>
	        <div class="form-group">
                <label class="control-label col-sm-2">代理区域</label>
                <div class="col-sm-10">
					<div class="chooseResult">查看代理区域</div>
               </div>
           </div>
	    </div>
		<div class="whiteBox">
			<input type="hidden" class="form-control" id="jyd" readonly="readonly" value="${agent.jyd}"/>
		    <c:if test="${agent.agentType=='1' }">
	        <div class="informateBox" id="companyAgent">
	            <div class="leftBox">
	                <div class="form-group">
	                    <label class="control-label col-sm-2">公司名称</label>
	                    <div class="col-sm-10">
	                       ${agent.companyName}
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-2">组织机构代码</label>
	                    <div class="col-sm-10">
	                       ${agent.organizationCode}
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-2">地址</label>
	                    <div class="col-sm-10">
	                        ${agent.companyProvinces}省${agent.companyCity}市${agent.companyArea}${agent.companyAddress}
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-2">营业执照照片</label>
	                    <div class="col-sm-10">
	                        <c:forEach items="${businessLicensePhoto}" var="i">
	                                <img src="<spring:url value='${i}'/>" alt="" style="width: 90px;height: 90px" />
	                        </c:forEach>
	                    </div>
	                </div>
	            </div>
	            <div class="rightBox">
	                <div class="form-group">
	                    <label class="control-label col-sm-2">姓名</label>
	                    <div class="col-sm-10">
	                       ${agent.userName}
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-2">身份证号</label>
	                    <div class="col-sm-10">
	                      ${agent.idNo} 
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-2">地址</label>
	                    <div class="col-sm-10">
	                        ${agent.userProvinces}省${agent.userCity}市${agent.userArea}${agent.userAddress}
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
	        </c:if>
	        <c:if test="${agent.agentType=='2' }">
	            <div class="informateBox"  id="personAgent">
                    <div class="form-group">
                        <label class="control-label col-sm-2">姓名</label>
                        <div class="col-sm-10">
                           ${agent.userName}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">身份证号</label>
                        <div class="col-sm-10">
                            ${agent.idNo}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">地址</label>
                        <div class="col-sm-10">
                            ${agent.userProvinces}省${agent.userCity}市${agent.userArea}${agent.userAddress}
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
	        </c:if>
	        
		</div>
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			 	  <input type="button" value="发送" class="yellowReturnRad" id="commit" />
			 	  <input type="button" value="取消" class="redBtnRad" id="return" />
			  </div>
	        </div>
        </div>
        <!-- 查看代理区域弹层 -->
        <div class="showLawyer"></div>
        <div class="areaBox" style="display: none">
           <p class="agencyAreaLine">查看代理区域<img class="areaCloseBtn"  src='<spring:url value="/img/login/clos_comleft.png" />'/></p> 
           <div class="tabBoxMatter provinceBox">
               <p style="margin:15px 0px;font-size:15px;">代理范围
                   <i style="color:#ff7223;font-style:normal;margin-left:20px;">
                       <c:if test="${agent.resellerLevel=='0' }">
                                                    省级
                       </c:if>
                       <c:if test="${agent.resellerLevel=='1' }">
                                                     市级
                       </c:if>
                       <c:if test="${agent.resellerLevel=='2' }">
                                                     区级
                       </c:if>
                    
                   </i>
               </p>
               <p style="font-size:15px;margin-bottom:10px;">代理区域</p>
               <div class="comResult" style="padding-bottom:50px;">
                    ${agent.showAgentArea}
               </div>
           </div>
        </div> 
	</form> 
	<script type="text/javascript">
	$(function() {
	   	$("#return").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/applicationAgent/doEnAgentList" />';
		});
		$("#commit").bind("click",function(){
		    if($('#diamondCard').val()==""||$('#diamondCard').val()==null){
				top.layer.alert("钻石卡号不能为空！",{icon:2});
				return;
			}
			data={
				"id":$("#id").val(),
				"diamondCard":$('#diamondCard').val()
			}
			$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/applicationAgent/doEditAgent" />',
				data:data,
				dataType: "json",
				async: false,
				success: function(data){
					if(data.body=="发送成功"){
						top.layer.alert(data.body,{icon:1});
						window.location.href='<spring:url value="/headquarters/applicationAgent/doEnAgentList" />';
					}else{
						top.layer.alert(data.body,{icon:2});
					}
				}
			});
		});	
		
		//查看代理区域
		$(".chooseResult").click(function(){
		   $(".showLawyer,.areaBox").show();
		});
		$(".areaCloseBtn").click(function(){$(".showLawyer,.areaBox").hide();});
	});
	</script>
</body>

</html>
