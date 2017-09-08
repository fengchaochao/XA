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
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
    <input type="hidden" id="id" value="${consumers.id}"/>
	     <c:if test="${consumers.isXfconsumers=='0' }">
	       <span class="leftNotice">消费者详情</span>
	     </c:if>
	     <c:if test="${consumers.isXfconsumers=='1' }">
	       <span class="leftNotice">消费商详情</span>
	     </c:if>
       <p>
            <em>状态</em>
            <c:if test="${consumers.merchantsState=='0' }">
                <input type="hidden" id="merchantsState" value="${consumers.merchantsState}"/>正常
            </c:if>
            <c:if test="${consumers.merchantsState=='1' }">
                <input type="hidden" id="merchantsState" value="${consumers.merchantsState}"/>停用
            </c:if>
            <c:if test="${consumers.merchantsState=='0' }">
                <input type="button" value="启用" class="startBtn"  disabled="disabled"/>
                <input type="button" value="停用" class="closeBtn" onclick="delRecord();" /> 
            </c:if>
            <c:if test="${consumers.merchantsState=='1' }">
                <input type="button" value="启用" class="closeBtn"  onclick="delRecord();"/>
                <input type="button" value="停用" class="startBtn"  disabled="disabled"/> 
            </c:if>
       </p>
    </div>
    <!--end pageHeadline -->
    
    <form id="submitForm" class="form-horizontal">
    <div class="whiteBox">
        <div class="form-group">
	         <c:if test="${consumers.isXfconsumers=='0' }">
		      <label class="control-label col-sm-3">消费者编号</label>
		     </c:if>
		     <c:if test="${consumers.isXfconsumers=='1' }">
		       <label class="control-label col-sm-3">消费商编号</label>
		     </c:if>
            <div class="col-sm-9">
               ${consumers.customerNumber} 
            </div>
        </div>
        <div class="form-group">
	         <c:if test="${consumers.isXfconsumers=='0' }">
		      <label class="control-label col-sm-3">消费者昵称</label>
		     </c:if>
		     <c:if test="${consumers.isXfconsumers=='1' }">
		       <label class="control-label col-sm-3">消费商昵称</label>
		     </c:if>
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
               ${consumers.provinces}省${consumers.city}${consumers.area}${consumers.address} 
            </div>
        </div>
    </div>
    <div class="whiteBox">
        <div class="form-group" style="margin-bottom:20px;">
            <label class="control-label col-sm-3">消费账户</label>
            <div class="col-sm-9">
	        <c:forEach items="${consumersAccountList }" var="var">
	            <div class="patternConsumption">
	                <span>
	                    <c:if test="${var.userType=='1' }">微信账户</c:if>
	                    <c:if test="${var.userType=='2' }">支付宝账户</c:if>
	                </span>
	                <span>${var.userAccount}</span>
	                <input type="button" style="display:inline-block;" value="交易记录" class="orangeText col-sm-offset-1" onclick="transactionRecords('${var.id}');" />
	                <p style="border-top:1px dashed #ddd;margin-top:10px;width:400px;"></p>
	            </div>
	        </c:forEach>
            </div>
        </div>
        <c:if test="${consumers.isXfconsumers=='1' }">
            <div class="form-group" style="display:inline-block;width:35%;">
                <label class="control-label col-sm-3">锁定用户</label>
                <div class="col-sm-9">
                    ${consumers.localUser} 
                    <input type="button" value="查看" class="orangeText col-sm-offset-1" onclick="checkUser();" />
                </div>
            </div>
        </c:if>
        <c:if test="${consumers.isXfconsumers=='1' }">
            <div class="form-group" style="display:inline-block;width:30%;">
                <label class="control-label col-sm-3">推荐商家</label>
                <div class="col-sm-9">
                   ${consumers.recommendedBusiness} 
                   <input type="button" value="查看" class="orangeText col-sm-offset-1" onclick="recommendedBusiness();" />
                </div>
            </div>
        </c:if>
    </div>
    <c:if test="${consumers.isXfconsumers=='1' }">
        <div class="whiteBox">
            <div class="form-group">
                <label class="control-label col-sm-3">消费商信息</label>
                <div class="col-sm-9">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">上级代理商</label>
                <div class="col-sm-9">
                   ${consumers.agent.agentName}
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">姓名</label>
                <div class="col-sm-9">
                   ${consumers.userName}
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">身份证号</label>
                <div class="col-sm-9">
                   ${consumers.idCard}
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">地址</label>
                <div class="col-sm-9">
                    ${consumers.userProvinces}省${consumers.userCity}${consumers.userArea}${consumers.userAddress}
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">身份证照片</label>
                <div class="col-sm-9">
                       <c:forEach items="${idNoPhoto}" var="i">
                        <img src="<spring:url value='${i}'/>" alt="" style="width: 90px;height: 90px" />
                    </c:forEach>
                </div>
              </div>
          </div>
      </c:if>
      <div class="form-group">
          <div class="col-sm-12">
            <div class="navbar-btn">
                <input type="button" value="返回" class="yellowReturnRad" id="return" />
            </div>
          </div>
      </div>
    </form> 
    <script type="text/javascript">
    $(function() {
        $("#return").bind("click",function(){
            window.location.href='<spring:url value="/headquarters/consumers/doEnConsumersList" />';
        });
    })
        
    //停用
    function delRecord(){
        var state=$("#merchantsState").val();
        var id=$("#id").val();
        if(state=="0"){
            layer.confirm('确认停用吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                $.ajax({
                    type: "POST",
                    url : '<spring:url value="/headquarters/consumers/doDelConsumers"/>',
                    data:{
                        consumersId:id
                    },
                    dataType: "json",
                    async: false,
                    success: function(data){
                        if("0000000"==data.head.respCode){
                            window.location.href='<spring:url value="/headquarters/consumers/doEnConsumersEdit"/>'+'?consumersId='+id;
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
                    url : '<spring:url value="/headquarters/consumers/doDelConsumers"/>',
                    data:{
                        consumersId:id
                    },
                    dataType: "json",
                    async: false,
                    success: function(data){
                        if("0000000"==data.head.respCode){
                            window.location.href='<spring:url value="/headquarters/consumers/doEnConsumersEdit"/>'+'?consumersId='+id;
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
    //交易记录
    function transactionRecords(accountId){
       window.location.href='<spring:url value="/headquarters/consumers/doEnAccountTransactionRecords" />?accountId='+accountId;
    }
    //锁定用户列表
    function checkUser(){
        window.location.href='<spring:url value="/headquarters/consumersAccount/doEnLocalUserList" />?businessInformationId='+$("#id").val();
    }
    //推荐商家列表
    function recommendedBusiness(){
        window.location.href='<spring:url value="/headquarters/businessInformation/doEnCounsumersRecommendList" />?founder='+$("#id").val();
    }
</script>
</body>
</html>
