function check_teamName() {
	var teamName = $("#teamName").val();
	if(teamName == null ||teamName == ''){
		layer.msg('请输入球队名称！');	
		return false;
	}
	if(teamName.length > 12){
		layer.msg('球队名称超过12个字！');	
		return false;
	}else{
		$.ajax({
	        url: '/xinmin/team/checkTeamName.do',
	        async: false,       		
	        type: 'POST',
	        dataType: 'json',
	        //data : dataParams,
	        data : {teamName:teamName},
	        success: function (data) {
	        if(data.status=='200'){
	       		 //layer.alert(data.message, {icon: 1});  //icon: 1 显示对勾
	        	layer.msg(data.message);	
	        }else  if(data.status=='500'){
	        	//layer.alert(data.message, {icon: 2});  //icon: 1 显示对勾	
	        	layer.msg(data.message);
	        	return false;
	        }else{
	        	layer.alert("系统错误!", {icon: 2});	
	        	return false;
	        }
	        }
	    });
		return true;
	}
		
}
//判断学校是否为空
function check_schoolName() {
	var schoolName = $("#schoolName").val();
	if(schoolName == null ||schoolName == ''){
		layer.msg('请输入学校！');	
		return false;
	}if(schoolName.length>20){
		layer.msg('学校名称大于20个字！');	
		return false;
	}else{
		return true;
	}
	
}
//判断领队名称是否为空
function check_captainName() {
	var captainName = $("#captainName").val();
	if(captainName == null ||captainName == ''){
		layer.msg('请输入领队名称！');	
		return false;
	}if(captainName.length>20){
		layer.msg('领队名称大于20个字！');	
		return false;
	}else{
		return true;
	}
}

//判断领队手机是否为空
function check_captainPhone() {
	var phone_reg = /^(((13[0-9]{1})|147|(15[0-3]{1})|(15[5-9]{1})|(18[0-3]{1})|(18[5-9]{1}))+\d{8})$/;	
	var captainPhone = $("#captainPhone").val();
	if(captainPhone == null || captainPhone == ''){
		layer.msg('请输入领队手机！');	
		return false;
	}
	if (!phone_reg.test(captainPhone)) {		
		layer.msg('手机号码格式不对！');	
		return false;
	}else{
		return true;
	}

}
//判断球队介绍是否为空
function check_teamDesc() {
	var teamDesc = $("#teamDesc").val();
	if(teamDesc == null ||teamDesc == ''){
		layer.msg('请输入球队介绍！');	
		return false;
	}if(teamDesc.length>255){
		layer.msg('球队介绍大于255个字！');	
		return false;
	}else{
		return true;
	}
}

	$(function(){
	    loadSportTypeList();
	    checkTextAeaLen();
	    
		$("#saveTeamBtn").click(function(){	
			//var dataParams =  $("#createTeam").serialize();
			var teamName =$("#teamName").val();
			var regionId=$("#regionId").val();
			var schoolName = $("#schoolName").val();
			//var teamType = $("#teamType").attr();
			//获取单选按钮的值
			var teamType = $('input[name="teamType"]:checked').val();
			var captainName=$("#captainName").val();
			var captainPhone = $("#captainPhone").val();
			var teamDesc=$("#teamDesc").val();
			//console.log("teamType0"+teamType);
			var teamLogo = $("#hfThumbnail").val();
			//return false;
			//提交时校验
			//alert(check_teamName() && check_schoolName() && check_captainName() &&  check_captainPhone() && check_teamDesc());
			if(!(check_teamName() && check_schoolName() && check_captainName() &&  check_captainPhone() && check_teamDesc())){
				return false; 
			}
			
			//没有上传球队LOGO则创建战队不成功
			//var userImageS =$("#hfThumbnail").val();
			var userImageS =$("#userImageS").attr("src"); 
			//alert(userImageS);
			if(userImageS.length==0){
			//if(userImageS == null ||userImageS ==''){
				layer.msg("请上传球队LOGO！");
				//layer.tips('手机号码格式不对！', '#captainPhone');	
				return false; 
			}
	    $.ajax({
	        url: '/xinmin/team/save.do',
	        async: false,       		//--false即为 同步
	        type: 'POST',
	        dataType: 'json',
	        //data : dataParams,
	        data : {teamName:teamName,regionId:regionId,schoolName:schoolName,teamType:teamType,captainName:captainName,captainPhone:captainPhone,teamDesc:teamDesc,teamLogo:teamLogo},
	        success: function (data) {
            //console.log(data.data);
            if(data.status=='200'){
            //layer.alert('创建球队成功！',icon: 1);
           		//layer.alert(data.message, {icon: 1});  //icon: 1 显示对勾
           		 /* layer.confirm(data.message, {icon: 1},function(){
					//var user_ifr = parent.window.document.getElementById("user_ifr");
					//$(user_ifr).attr("src","myJoinCrops.html");
				    //layer.close(index);
           			 window.location.href='index.html';
				}); */
            	layer.msg("创建球队成功！");
				setTimeout(function(){location.href="index.html";}, 3000 );
           		
            }if(data.status=='500' && data.message =='球队名已经存在!'){
            	layer.alert(data.message, {icon: 2});  //icon: 1 显示对勾	
            }
           /*  else{
            	 layer.alert("创建球队失败!", {icon: 2});	
            } */
	        }
	    });
	    });
		
		
			 $("#upload").upload({
		         uploadData: { id: "12233" },
		         successFn: "success",
		         deleteData: { id: function () { return "asdfasdf" } }
		     });
	});
	
	function loadSportTypeList(){
		//var a="<option value=\'-1\'>请选择</option>";
		var a= '';
		  jQuery.ajax({
			url: '/xinmin/address/list.do',  
		    type: 'POST',  
		    dataType: 'json', 
		    data : {level:3,parentId:310100},
		    success: function(data){
		    	var myObject =data.data;
	            for (var i = 0; i < myObject.length; i++) {
	            	//alert(myObject[i].id+"   "+myObject[i].name);
					 a+="<option value=\'"+myObject[i].id+"\'>"+myObject[i].name+"</option>";
	            }
				$("#regionId").html(a);
				},error :function(){
					//alert('error');
				} 
			});
	}
	
	function checkTextAeaLen(){
	 var limitNum = 255;    //数据库为varchar(255)
	    var pattern = '还可以输入' + limitNum + '字符';
	    $('#contentwordage').html(pattern);
	    var teamDescLen = $("#teamDesc").val().length;
	    if(teamDescLen>255){
	    	 $('#contentwordage').html("字数超过限制！");
	    }else{
	    	var result = limitNum - teamDescLen;
            pattern = '还可以输入' + result + '字符';
            $('#contentwordage').html(pattern);
	    }
	     /* $('#teamDesc').keyup(function(){
	        var remain = $(this).val().length;
	        if(remain > 255){
	                pattern = "字数超过限制！";
	            }else{
	                var result = limitNum - remain;
	                pattern = '还可以输入' + result + '字符';
	            }
	            $('#contentwordage').html(pattern);
	        });  */
	    $('#teamDesc').on('propertychange input', function() {
	    	var remain = $(this).val().length;
	        if(remain > 255){
	                pattern = "字数超过限制！";
	            }else{
	                var result = limitNum - remain;
	                pattern = '还可以输入' + result + '字符';
	            }
	            $('#contentwordage').html(pattern);
	    });
	    
	}