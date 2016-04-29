$(function() {
	loadSportTypeList();
	$("#team_info").html("");
	$("#currPage").val(1);
	findTeams();

	$("#findTeam").click(function() {
		$("#team_info").html("");
		$("#currPage").val(1);
		findTeams();
	});
	// var teamType = 1;
	
});

function loadSportTypeList() {
	var a = "<option style=\"color:#000000;\" value=\'-1\'>请选择</option>";

	jQuery.ajax({
		url: '/xinmin/address/list.do',
		type: 'POST',
		dataType: 'json',
		data: {
			level: 3,
			parentId: 310100
		},
		success: function(data) {
			var myObject = data.data;
			for (var i = 0; i < myObject.length; i++) {
				//alert(myObject[i].id+"   "+myObject[i].name);
				a += "<option  style=\"color:#000000;\" value=\'" + myObject[i].id + "\'>" + myObject[i].name + "</option>";
			}
			$("#regionId").html(a);
		},
		error: function() {
			//alert('error');
		}
	});
}

function findTeams(flag) {
	var pageSize = $("#pageSize").val();
	var currPage = $("#currPage").val();
	var totals = $("#total").val();
	if(!flag) {
		if (totals != 0) {
			//alert('pageSize='+pageSize+',currPage='+currPage+',totals='+totals);
			if (parseInt(totals % pageSize) == 0) {
				tempCurr = parseInt(totals / pageSize);
			} else {
				tempCurr = parseInt(totals / pageSize + 1);
			}
			//alert('tempCurr='+tempCurr);
			if (tempCurr < currPage) {
				layer.msg("已经是最后一条了！");
				return false;
			}
		}
	}
	var teamName = $("#teamName").val();
	var regionId = $("#regionId").find("option:selected").val();
	var regionFlag = $("#regionId").val();
	/*var teamTypeFlag = $("#team_info").attr("check");
	if (teamTypeFlag == 0) {
		teamType = 1;
	} else {
		teamType = 2;
	}*/
	if (regionFlag == '' || regionFlag == null) {
		regionId = '-1';
	}
	if (teamName == '' || teamName == null) {
		teamName = '-1';
	}

	//var teamType = 1;

	$.ajax({
		url: '/xinmin/team/teamList.do',
		async: false, //--false即为 同步
		type: 'POST',
		dataType: 'json',
		//data : dataParams,
		data: {
			teamName: teamName,
			regionId: regionId,
			teamType: teamType,
			pageSize: pageSize,
			currPage: currPage
		},
		success: function(data) {
			//console.log(data);
			//if(data.)
			//alert(jsonData.length);
			if (data.status == 200) {
				var jsonData = data.data.results;
				var objHtml = "";
				for (var i = 0; i < jsonData.length; i++) {
					// objHtml += "<div class='team_info_block'>";
					// objHtml += "<div style=\"width:150px; height:165px;    padding-right:2%;  padding-left:5%; margin-top:5%; float:left;\">";
					//objHtml += "<div style=\"width:30%;  padding-left:10%; margin-top:5%; float:left;\">";

					//objHtml += "<img src='"
					//	+ jsonData[i].teamLogo
					//		+ " 'style=\"height: auto;width: auto\9;width: 80%;\" />";

					// objHtml += "<a href=\"/xinmin/user.html?id=" + jsonData[i].id + "\"><img src='" + jsonData[i].teamLogo + " 'class=\"team_img\"  />";
					// objHtml += "</a>";
					// objHtml += "</div>";
					// objHtml += "<div style=\"float:left; width:55%;   margin-top:5%;\">";
					// objHtml += "<p style=\"color:#d2fc31; font-size:28px; margin-top:10px; \">";
					// objHtml += jsonData[i].teamName;
					// objHtml += "</p>";
					// objHtml += "<p style=\"color:#fff; font-size:22px;  margin-top:10px;\">";

					objHtml += '<div class="row">';
                	objHtml += '<div class="col-xs-4" style="margin:5px 0;">';
                    objHtml += '<a href="/xinmin/user.html?id='+ jsonData[i].id +'\\"><img src="'+ jsonData[i].teamLogo +'" class="team_img" /></a>';
                	objHtml += '</div>';
                	objHtml += '<div class="col-xs-8" style="text-align:center;">';
                    objHtml += '<p style="font-size:20px; font-weight:bold; color:rgb(210,252,48);">'+ jsonData[i].teamName +'</p>';
                    objHtml += '<p style="font-size:20px; color:#fff;">';


					//alert(typeof (jsonData[i].regionId));
					switch (jsonData[i].regionId) {
						case '310101':
							objHtml += "黄浦区&nbsp;&nbsp;";
							break;
						case '310104':
							objHtml += "徐汇区&nbsp;&nbsp;";
							break;
						case '310105':
							objHtml += "长宁区&nbsp;&nbsp;";
							break;
						case '310106':
							objHtml += "静安区&nbsp;&nbsp;";
							break;
						case '310107':
							objHtml += "普陀区&nbsp;&nbsp;";
							break;
						case '310108':
							objHtml += "闸北区&nbsp;&nbsp;";
							break;
						case '310109':
							objHtml += "虹口区&nbsp;&nbsp;";
							break;
						case '310110':
							objHtml += "杨浦区&nbsp;&nbsp;";
							break;
						case '310112':
							objHtml += "闵行区&nbsp;&nbsp;";
							break;
						case '310113':
							objHtml += "宝山区&nbsp;&nbsp;";
							break;
						case '310114':
							objHtml += "嘉定区&nbsp;&nbsp;";
							break;
						case '310115':
							objHtml += "浦东新区&nbsp;&nbsp;";
							break;
						case '310116':
							objHtml += "金山区&nbsp;&nbsp;";
							break;
						case '310117':
							objHtml += "松江区&nbsp;&nbsp;";
							break;
						case '310118':
							objHtml += "青浦区&nbsp;&nbsp;";
							break;
						case '310107':
							objHtml += "普陀区&nbsp;&nbsp;";
							break;
						case '310120':
							objHtml += "奉贤区&nbsp;&nbsp;";
							break;
						case 310230:
							objHtml += "崇明县&nbsp;&nbsp;";
							break;
						default:
							;
					}
                    
					objHtml += jsonData[i].schoolName;
					objHtml += "</p>";
					objHtml += '<div class="join">';
					objHtml += "<a href=\"/xinmin/user.html?id=" + jsonData[i].id + "\">";
					objHtml += "加入";
					objHtml += "</a>";
					objHtml += '</div>';
                	objHtml += '</div>';
            		objHtml += '</div>';
				}
				if (flag) {
					$("#team_info").remove('*');
				};
				$("#team_info").append(objHtml);
				$("#currPage").val(parseInt(data.data.currPage) + 1);
				$("#total").val(data.data.total);
			} else if (data.status == 500) {
				//layer.msg(data.message);
				//layer.tips(data.message, '#team_info');
				$("#team_info").html("<div style='width:200px;margin-left:auto;margin-right:auto;text-align:center;color: #ff6a00;font-size:20px;'>" + data.message + "</div>");
			}

		}
	});
}