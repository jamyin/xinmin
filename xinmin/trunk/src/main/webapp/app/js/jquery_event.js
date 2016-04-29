/// <reference path="../login.html" />
/// <reference path="../login.html" />
/// <reference path="../login.html" />
/// <reference path="../login.html" />
/// <reference path="jquery-2.1.3.min.js" />
/// <reference path="layer/layer.min.js" />
$(function(){
	$(document).keydown(function (e) {
        if (e.keyCode == 8) {
            return false;
        }
    });
});
var $$ = new Object();

//js用正则表达式获取地址栏信息的方法
//name表示：要获得值的变量名
$$.GetQueryString = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //window.location.search;获得地址栏比如http://localhost:1214/web/BoKeHomePage.aspx?name=1
    //返回"?name=1";
    //substr(index,index);截取字符串从第几个开始截取,截取多少个，不写代表一直到最后
    //match();返回指定数值在指定数组区域中的位置
    var r = window.parent.location.search.substr(1).match(reg);
    if (r != null) return r[2]; return null;
}

//主页右侧li点击事件
$$.Fn_rightli_click= function (c, t) {
    var s = $("#" + c + " li");
    $(s).each(function () {
        $(this).css("color", "rgb(53, 52, 52)");
        $(".centre1_rigth_li", $(this)).css({ "display": "none" });
    });
    $(t).css("color", "rgb(207,0,57)");
    $(".centre1_rigth_li", $(t)).css({ "display": "block" });
}

$$.Fn_Event_click = function (c,t){
	var dv = $(t).attr("data-value");
	window.location.href = "/stadium/"+dv+".do";
};

//个人主页左侧li悬停和悬浮事件
$$.Fn_leftli_seleave = function (t) {
    $(t).css("background-color", "rgb(99,106,124)");
    $(".check", t).css("display", "block");
}

$$.Fn_leftli_seout = function (t) {
    var check= $(t).attr("check");
    if (check == "0") {
        $(t).removeAttr("style", "background-color");
        $(".check", t).css("display", "none");
    }
   
}


$$.Fn_leftli_click = function (t, id,src) {
    //处理选项
    $("#left_ul li").each(function () {
        $(this).removeAttr("style", "background-color");
        $(".check", this).css("display", "none");
        $(this).attr("check","0");
    });
    $("#" + t).css("background-color", "rgb(99,106,124)");
    $(".check", $("#"+t)).css("display", "block");
    $("#"+t).attr("check", "1");
    
    //处理图片
    $(".tag_img").each(function () {
        if ($(this).attr("check") == 1) {//获取上次选中的div
            $("img", this).attr("src","images/"+ $(this).attr("src")+".jpg"); //设置上次选中div里的img标签为原始图
            $(this).attr("check",0)
        }
        $("#" + id).attr("check", 1);//设置当前选中的div选中状态
        $("#" + id+" img").attr("src", "images/" + id + ".jpg");//设置当前选中的div选中状态
        
    });
    $("#site_ifr").attr("src", src);
    
    
    
}

$$.Fn_tab = function (t,id) {
    //
    $("#date_info_tab tr td").each(function () {
        $(this).removeClass("td_check");
        $("span", this).removeClass("span_check").addClass("span");
    });
    $(t).addClass("td_check");
    $("span", $(t)).removeClass("span").addClass("span_check");
    $(".content_info_tab").each(function () {
        $(this).css("display","none");
    });
    $("#" + id).css("display","");
}


//img悬停和离开事件
//onmouseover
//onmouseout

$$.img_over=function (t) {
    $("div", t).css("display", "block");
}
$$.img_out=function (t) {
    $("div", t).css("display", "none");
}


$$.open = function (url, width, height) {
        $.layer({
            type: 1,
            title: false, //不显示默认标题栏
            shade: [0.5, '#000'], //不显示遮罩
            shift: 'top', //从头动画弹出
            area: [width, height], 
            page: { url: url }
        }); 
}