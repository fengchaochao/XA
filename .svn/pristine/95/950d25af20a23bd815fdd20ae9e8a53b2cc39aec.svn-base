var fileManager = {fileList:[]};

fileManager.add = function(obj){
	this.fileList.push(obj);
};

fileManager.clean = function(){
	this.fileList = [];
};

fileManager.del = function(paramAddfilePath, paramType, paramClass){
	try
	{
		var index;
		$.each(this.fileList, function(i, n){
			if(n.addfilePath == paramAddfilePath){
				index = i;
				return false;
			}
		});
		if(!isNaN(index)){
			this.fileList.splice(index, 1);
			fileManager.show({
				addfileType: paramType,
				itemClass: paramClass||""
			});
		}
	}
	catch(e)
	{
		alert(e);
	}
};


function getFilePath(addfileServer,addfilePath)
{
	var fullFilePath;
    if((addfileServer.substr(addfileServer.length-1,1))=="/"){
    	fullFilePath = addfileServer.substr(0,addfileServer.length-1) + addfilePath;
    }
    else{
    	fullFilePath = addfileServer + addfilePath;
    }
    return fullFilePath;
}

fileManager.show = function(paramSettings){

	var imgTypeReg = /\.jpg|\.bmp|\.png|\.gif|\.jpeg$/i;

	var options = $.extend({},{
		addfileType: "",
		paramTmpl: '<div class="showFileCssStyle">\
			            <div class="strong">{addfileName}</div>\
						<div class="itemTit">\
			               <a class="open" href="javascript:;" onclick="window.open(\'{filePath}\');" >&#25171;&#24320;</a>\
			               <a class="del" href="javascript:;" onclick="fileManager.del(\'{addfilePath}\',\'{fileType}\',\'{itemClass}\');">&#21024;&#38500;</a>\
			            </div>\
						<div><img src="{preview}" onclick="window.open(\'{filePath}\');" /></div>\
					</div>',
		itemClass: ''
	}, paramSettings);

	//var tempFilePath = (this.filePath != "" && this.filePath != "/" && this.filePath) || (location.protocol + "//" + location.hostname + ":" +location.port + "");

	var tempImgs = [];

	$.each(this.fileList, function(i, n){
		if(n.addfileType == options.addfileType && n.filePath !=""){
			tempImgs.push(options.paramTmpl
					    .replace(/{fileType}/gi, options.addfileType)
						.replace(/{addfilePath}/gi, n.addfilePath)
						.replace(/{addfileName}/gi, n.addfileName)
			            .replace(/{filePath}/gi, getFilePath(n.addfileServer,n.addfilePath))
			            .replace(/{preview}/gi, imgTypeReg.test(n.addfilePath)? getFilePath(n.addfileServer,n.addfilePath) : n.addfileServer+"/upload/download.jpg")
			);
		}
	});

	$("#"+options.addfileType).html(tempImgs.join(""));

};