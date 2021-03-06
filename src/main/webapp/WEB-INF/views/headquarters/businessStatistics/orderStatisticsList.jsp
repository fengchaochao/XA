<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
    .memberClassifiTab{width:96%;}
    tr:first-child td{background:#fafafa;width:18%;}
    th,td{border:1px solid #ddd;line-height:35px;border-left:none;border-right:none;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">订单管理</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox">
	
	    <form id="formSearch" class="form-horizontal">
			<div class="form-group" style="margin-top: 15px;margin-left: -31px;">
			
			     <c:if test="${type eq '0' }">
                    <label class="control-label" for="state" style="float: left;margin-left: 3%;">所在地区</label>
                    <div class="col-sm-2">
                        <select id="sel_country" style="display:none;"  class="form-control" ></select>
                        <select id="sel_province" name="sel_province"  class="form-control" ></select>
                    </div>
                    <div class="col-sm-2">
                        <select id="sel_city" name="sel_city"   class="form-control" ></select>
                    </div>
                    <div class="col-sm-2">
                        <select id="sel_area" name="sel_area"   class="form-control" ></select>
                    </div>
                </c:if>
                <div class="chooseTimeCom" style="margin-left: 3%;">
                    <label class="control-label" for="state" style="float: left;">订单时间</label>
                    <div>
                        <input type="text" class="form-control" id="startDate" name="startDate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </div><em style="margin-left:8px;">-</em>
                    <div>
                        <input type="text" class="form-control" id="endDate" name="endDate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </div>
                </div>
	            <label class="control-label" for="state" style="float: left;margin-left: 3%;margin-top:15px;clear:both;">交易方式</label>
	            <div class="col-sm-2" style="margin-top:15px;">
	                <select name="transactionMode" id = "transactionMode" class="form-control" >
	                    <option value="">请选择交易方式</option>
	                    <option value="1">扫码支付</option>
	                    <option value="2">在线支付</option>
	                </select> 
	            </div>
	            <label class="control-label" for="state" style="float: left;margin-left: 3%;margin-top:15px;">订单状态</label>
	            <div class="col-sm-2" style="margin-top:15px;">
	                <select name="status" id = "status" class="form-control" >
	                    <option value="">请选择订单状态</option>
	                    <option value="1">待配送</option>
	                    <option value="2">待收货</option>
	                    <option value="3">已完成</option>
	                </select> 
	            </div>
	            
			
				<div id="queryDiv" class="col-sm-3" style="text-align: left;padding-left: 0px;margin-left: 3%;margin-top:15px;">
					<input type="text" class="form-control" id ="orderNumber"  placeholder="输入订单编号查询" />
				</div>
				
				
				<div id="queryDiv" class="col-sm-1" style="text-align: left;padding-left: 0px;margin-top:15px;margin-bottom:40px;">
		            <button type="button" id="btn_query" class="inquiryComBtn">查询</button>
		        </div>
		        
		        <c:if test="${type eq '2' }">
		        	<table class="memberClassifiTab table-hover" style="margin-left:31px;margin-top:30px;">
		        		<tr>
		        			<td>订单量</td>
		        			<td>销售量</td>
		        			<td>销售额</td>
		        			<c:if test="${isShowAmount eq '1'}">
		        				<td>实收额</td>
		        			</c:if>
		        			
		        		</tr>
		        		<tr>
		        			<td class = "a"></td>
		        			<td class = "b"></td>
		        			<td class = "c"></td>
		        			<c:if test="${isShowAmount eq '1'}">
		        				<td class = "d"></td>
		        			</c:if>
		        		</tr>
		        	</table>
		        </c:if>
	        	
		        
		        <div id="queryDiv" class="col-sm-1" style="text-align: right;padding-left: 0px;margin-top:15px;float:right;">
		            <c:if test="${type eq '1' || type eq '2'}">
		           	   <button type="button" id="btn_down" class="greenBtn">导出</button>
		            </c:if>
		        </div>
		        
			</div>
		</form>
		<table id="tb_board"></table> 
		
	</div>
    <c:if test="${flag=='1'}">
        <p style="text-align:center;padding-top:30px;">
            <button type="button" class="yellowReturnRad" onclick="history.go(-1)">返回</button>
        </p>
    </c:if>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
    
	<script type="text/javascript">
	    var $table;
		$(function() {
			select_Init();
			initTable();
			
            // 搜索
			$('#btn_query').click(function(){
				$('#tb_board').bootstrapTable('getOptions').pageNumber=1;
				$('#tb_board').bootstrapTable('refresh');
			});
			
		});
		
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/headquarters/orderStatistics/doReadOrderList" />',
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
                queryParams: queryParams,
                showRefresh : true,
				striped : true,
				silent : true,
				sortable: true, 
				sortOrder: "asc",
                responseHandler: responseHandler,
                columns : [ 
                {
					field : 'createtime',
					title : '订单时间'
				}, {
					field : 'orderNumber',
					title : '订单号'
				},{
					field : 'storeName',
					title : '卖家编号'
				},{
					field : 'storeNumber',
					title : '卖家名称'
				},{
					field : 'consumerAccount',
					title : '消费者账户'
				},{
					field : 'money',
					title : '交易金额'
				},{
					field : 'isBonus',
					title : '是否抽成',
					formatter:isBonusFormatter
				},{
					field : 'bonus',
					title : '抽成金额'
				},{
					field : 'transactionMode',
					title : '交易方式',
					formatter:transactionModeFormatter
				},{
					field : 'status',
					title : '订单状态',
					formatter:statusFormatter
				},{
					field : 'id',
					title : '操作',
					align: 'center',
					formatter:openFormatter
				}],
                onLoadSuccess:function(){
                },
                formatLoadingMessage : function() {
									return "请稍等，正在加载中...";
								},
				formatNoMatches : function() { //没有匹配的结果
					return "无符合条件的记录";
				},
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });
        }
        
        
        //是否抽成 1:是 0：否 
		function isBonusFormatter(data,row){
			$(".a").html(row.orderCount);
			$(".b").html(row.salesCount);
			$(".c").html(row.salesMoney);
			$(".d").html(row.actualMoney); 
			
			if(data == "0"){
				return "否";
			}else if(data == "1"){
				return "是";
			}
		}
        
        // 交易方式 1：扫码支付 2：在线支付 
		function transactionModeFormatter(data){
			if(data == "1"){
				return "扫码支付";
			}else if(data == "2"){
				return "在线支付";
			}
		}
		
        // 订单状态 1待配送、2待收货、3已完成
		function statusFormatter(data){
			if(data == "1"){
				return "待配送";
			}else if(data == "2"){
				return "待收货";
			}else if(data == "3"){
				return "已完成";
			}else if(data == "4"){
				return "待付款";
			}
		}
		
		function openFormatter(data,row){
        	var type = ${type };
	        if(type == "2"){
	        	if(row.status == "1"){
        			var e = '<a href="#" class="blueText" mce_href="#" onclick="editStauts(\''+ data + '\')">配送</a> ';
       				return e; 
        		}
	        }
        }
        
        function editStauts(id){
        	layer.confirm('确认配送吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/orderStatistics/doEditOrder"/>',
					data:{
						id:id,
						status:"2"
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							$table.bootstrapTable('refresh', {url: '<spring:url value="/headquarters/orderStatistics/doReadOrderList" />'});
							layer.msg('操作成功!', {icon: 1});
						}
						else{
							layer.msg('操作失败!', {icon: 6});
						}
					}
				});
			}, function(){
				layer.msg('已取消', {icon: 1});
			});
        
        }
		
		
		//导出
        $('#btn_down').click(function () {
			$.ajax({
			    type: "POST",
			    url: "<spring:url value='/headquarters/orderStatistics/downExcel'/>",
			    dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
			    success: function(json){
			         var result = json.result;
			          if(result == "success")
			          {
			              window.location.href = 'http://'+json.filename;
			          }
			         else
			         {
			             alert("导出失败");
			         }
			    },
			     error: function(json){
			        return false;
			    }
			});
		});  
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
        
		// 查询参数设置  
		function queryParams(params) {
			var temp = {
				//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的    
				limit : params.limit, //页面大小    
				offset : params.offset, //页码   
				order : params.order,
				orderName : params.sort,
				transactionMode : $("#transactionMode").find("option:selected").val(),
				status : $("#status").find("option:selected").val(),
				startTime: $("#startDate").val(),
				endTime: $("#endDate").val(),
				orderNumber: $("#orderNumber").val(),
				province : $("#sel_province").find("option:selected").val(),
				city : $("#sel_city").find("option:selected").val(),
				area : $("#sel_area").find("option:selected").val()
			};
			return temp;
		};
		
		//被子页面调用
		function beCalled(data){
			$('#tb_board').bootstrapTable('refresh');
			if(data.body){
				top.layer.msg("更新成功!",{icon:1});
			}else{
				top.layer.msg("更新失败！",{icon:2});
			}
		}
		
		// json 数据处理
        function responseHandler(res) {
			if (res.body.total > 0) {
				return {
					"rows" : res.body.rows,
					"total" : res.body.total
				}
			} else {
				return {
					"rows" : [],
					"total" : 0
				}
			}
		}
        
	</script>

</body>
</html>
