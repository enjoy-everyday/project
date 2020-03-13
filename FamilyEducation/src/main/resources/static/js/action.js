
//获取csrf变量的token值放在_csrf变量中,此变量要作为所有请求的参数
var token = $("#token").val();

$(document).ready(function () {

    //head
    $("#submit").click(function () {
        $("#logout").submit();
    });

    // //取消应聘
    // $("#cancelTheApplication").click(function () {
    //     var node = this.parentNode;
    //     var id = node.children[0].getAttribute("id");
    //     $(id).submit();
    // });

    // //删除消息
    // $("#deleteInformation").click(function () {
    //     var node = this.parentNode;
    //     var id = node.children[0].getAttribute("id");
    //     $(id).submit();
    // });

    // //已读消息
    // $("#readInformation").click(function () {
    //     var node = this.parentNode;
    //     var id = node.children[3].getAttribute("id");
    //     $(id).submit();
    // });

    //websocket
    $("#response").on("click", ".toast", function () {
        $(this).fadeOut(100);
        $(this).attr("class", "toast fade hide");
    });

    // //家长接受
    // $("#enterTheInterview").click(function () {
    //     var node = this.parentNode;
    //     var id = node.children[0].getAttribute("id");
    //     $(id).submit();
    // });

    // //家长拒绝
    // $("#refuseEntry").click(function () {
    //     var node = this.parentNode;
    //     var id = node.children[1].getAttribute("id");
    //     $(id).submit();
    // });

    //发布家教信息
    $("#release").click(function () {
        var json =
            "gender:" + $("input[name='gender']:checked").val() + "," +
            "qualification:" + $("#qualification option:selected").text() + "," +
            "experience:" + $("#experience option:selected").text() + "," +
            "otherRequirement:" + $("#otherRequirement").val() + "," +
            // "grade:" + $("#grade option:selected").text() + "," +
            // "subject:" + $("#subject option:selected").text() + "," +
            "place:" + $("input[name='place']:checked").val() + "," +
            "price:" + $("input[name='price']:checked").val() + "," +
            "otherPlace:" + $("#otherPlace").val()
        ;
        // "qualification": $("#qualification option:selected").text(),
        // "experience": $("#experience option:selected").text(),
        // "other": $("#other").text(),
        // "grade": $("#grade option:selected").text(),
        // "subject": $("#subject option:selected").text(),
        // "teachingTime": $("#teachingTime").text(),
        // "place": $("#place").text()
        // ";

        $.ajax({
            url: "/releaseDetails",
            type: "post",
            data: {json: json, _csrf: token},
            success: function(result){
                if (result === "success"){
                    alert("发布成功");
                    window.location.replace("/findAllPublish");
                }
                else if (result === "error"){
                    alert("请先完善个人资料");
                }
                else {
                    alert("请重新提交");
                }
            }
        });

    });

    //修改资料
    $("#change").click(function () {
        var information;
        var role = $("#role").val();
        if (role == "学生"){
            information = "username:" + $("#username").val() + "," +
                "name:" + $("#name").val() + "," +
                "age:" + $("#age").val() + "," +
                "gender:" + $("input[name='gender']:checked").val() + "," +
                "qualification:" + $("#qualification option:selected").text() + "," +
                "province:" + $("#province option:selected").text() + "," +
                "city:" + $("#city option:selected").text() + "," +
                "area:" + $("#area option:selected").text() + "," +
                "detailedAddress:" + $("#detailedAddress").val()
            ;
        }
        else {
            information = "username:" + $("#username").val() + "," +
                "name:" + $("#name").val() + "," +
                "age:" + $("#age").val() + "," +
                "gender:" + $("input[name='gender']:checked").val() + "," +
                "province:" + $("#province option:selected").text() + "," +
                "city:" + $("#city option:selected").text() + "," +
                "area:" + $("#area option:selected").text() + "," +
                "detailedAddress:" + $("#detailedAddress").val()
            ;
        }

        $.ajax({
            url: "/changeInformation",
            type: "post",
            data: {information: information, _csrf: token},
            success: function (result) {
                if (result === "success"){
                    alert("修改成功");
                    window.location.replace("/personalCenter");
                }
                else {
                    alert("请重新提交");
                }
            }
        })
    });

    //注册
    $("#register").click(function () {
       $("#registerForm").submit();
    });

    //应聘
    // $("#applyForTutor").on("click",function () {
    //     var node = this.parentNode;
    //     var id = node.children[0].getAttribute("id");
    //     alert($(id).val());
    //     $.ajax({
    //         url: "/applyForTutor",
    //         type: "post",
    //         data: {detail_id: $(id).val(),  _csrf: token},
    //         success: function (result) {
    //             if (result.eq("success")){
    //                 alert("成功发送，等待家长处理");
    //             }
    //             else {
    //                 alert("请先完善个人资料");
    //             }
    //             window.location.replace("/findAllApplicants");
    //         }
    //     });
    // });

    //搜素年级、科目
    // $("#screen").click(function () {
    //     var grade = $("#grade option:selected").text();
    //     var subject = $("#subject option:selected").text();
    //     if (grade.eq("所有科目")){
    //         $("#inputGrade").attr("value", "null");
    //     }
    //     else {
    //         $("#inputGrade").attr("value", grade);
    //     }
    //     if (subject.eq("所有科目")){
    //         $("#inputSubject").attr("value", "null");
    //     }
    //     else {
    //         $("#inputSubject").attr("value", subject);
    //     }
    //     $("#searchGradesAndSubjects").submit;
    // })

});

//应聘
function applyForTutor(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/applyForTutor",
        type: "post",
        data: {detail_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("成功发送，等待家长处理");
                window.location.replace("/findAllApplicants");
            }
            else {
                alert("请先完善个人资料");
            }
        }
    });
}

//学生取消应聘
function cancelTheApplication(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/cancelTheApplication",
        type: "post",
        data: {task_id: value,  _csrf: token},
        success: function (result) {
            if (result === "accepted"){
                alert("取消成功");
                window.location.replace("/accepted");
            }
            else if (result === "untreated"){
                alert("取消成功");
                window.location.replace("/untreated");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//家长接受应聘，学生进入面试
function enterTheInterview(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/enterTheInterview",
        type: "post",
        data: {task_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("接受成功");
                window.location.replace("/viewCandidates");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//家长拒绝应聘，学生失败
function refuseEntry(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/refuseEntry",
        type: "post",
        data: {task_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("拒绝成功");
                window.location.replace("/viewCandidates");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//删除消息
function deleteInformation(element){
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/deleteInformation",
        type: "post",
        data: {message_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("删除成功");
                window.location.replace("/findInformation");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//已读消息
function readInformation(element){
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/readInformation",
        type: "post",
        data: {message_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("操作成功");
                window.location.replace("/findInformation");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//选择空闲时间
$(document).ready(function(){

    var clock = 10;
    var length = 0;
    var choice = [[], [], [], [], [], [], []];
    var weekChoice = 0;
    var time = ["08:00-08:30", "08:31-09:00", "09:00-09:30", "09:30-10:00"];
    var week = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];

    for(var i = 1; i < week.length; i++){
        $("#week").append('<div class="week"><p style="margin: 8px;">' + week[i] + '</p></div>');
    }

    for(var i = 0; i < 28; i++){
        if(i < 4){
            $("#weekTime").append('<div class="weeklyTime" name="weeklyTime"><p style="margin: 8px;">' + time[i] + '</p></div>');
        }
        else{
            if(i % 2 != 0){
                time[i] = clock + ":31-" + (clock + 1) + ":00";
                $("#weekTime").append('<div class="weeklyTime" name="weeklyTime"><p style="margin: 8px;">' + time[i] + '</p></div>');
                clock++;
            }
            else{
                time[i] = clock + ":00-" + clock + ":30";
                $("#weekTime").append('<div class="weeklyTime" name="weeklyTime"><p style="margin: 8px;">' + time[i] + '</p></div>');
            }
        }
    }

    $(".week").on("click", function(){
        if(choice[$(this).index()].length == 0){
            $("div[name='weeklyTime']").attr("class", "weeklyTime");
        }
        else{
            for(var i = 0; i < length; i++){
                if(choice[$(this).index()][i] != null){
                    $("div[name='weeklyTime']").eq(i).attr("class", "weeklyTime choiceTime");
                }
                else{
                    $("div[name='weeklyTime']").eq(i).attr("class", "weeklyTime");
                }
            }
        }
        $(".choice").attr("class", "week");
        $(this).attr("class", "week choice");
        weekChoice = $(this).index();
    });

    $(".weeklyTime").on("click", function(){
        if($(this).index() >= length){
            length = $(this).index() + 1;
        }
        if($(this).attr("class") == "weeklyTime"){
            $(this).attr("class", "weeklyTime choiceTime");
            choice[weekChoice][$(this).index()] = time[$(this).index()];
        }
        else {
            $(this).attr("class", "weeklyTime");
            choice[weekChoice][$(this).index()] = null;
        }
    });

    $("#chooseFreeTime").click(function () {
        $.ajax({
            url: "/chooseFreeTime",
            type: "post",
            data: {array: choice,  _csrf: token},
            traditional: true,
            success: function (result) {
                if ($("#timeSelected").children("p").length != 0){
                    $("#timeSelected").empty();
                }
                $("#timeSelected").append("<p>" + result + "</p>");
                alert("成功");
            },
            error: function () {
              alert("操作错误，请重新选择");
            }
        });
    })

});


//省市区
$(document).ready(function () {
    $.ajax({
        type: "post",
        url: "/getProvince",
        data: {_csrf: token},
        dataType: "json",
        success: function (result) {
            for (var i = 0; i < result.length; i++){
                $("#province").append("<option value='" + result[i].id + "'>" + result[i].province_name + "</option>");
            }
        }
    });

    $("#alter").click(function(){
        var province_name = $("#province option:selected").text();
        var city_name = $("#city option:selected").text();
        var area_name = $("#area option:selected").text();
        if ($("#province option:selected").val() == 0 || $("#city option:selected").val() == 0){
            alert("请选择省份以及城市");
        }
        else if ($("#area option:selected").val() == 0){
            $("#province_name").attr("value", province_name);
            $("#city_name").attr("value", city_name);
            $("#area_name").attr("value", "null");
            $("#position").submit();
        }
        else {
            $("#province_name").attr("value", province_name);
            $("#city_name").attr("value", city_name);
            $("#area_name").attr("value", area_name);
            $("#position").submit();
        }
    });

});

$("#province").change(function () {

    var province_code = $("#province option:selected").val();

    $("#city option:gt(0)").remove();
    $("#area option:gt(0)").remove();

    $.ajax({
        type: "post",
        url: "/getCities",
        data: {_csrf: token, province_code: province_code},
        dataType: "json",
        success: function (result) {
            for (var i = 0; i < result.length; i++){
                $("#city").append("<option value=" + result[i].id + ">" + result[i].city_name + "</option>")
            }
        }
    })
});

$("#city").change(function () {

    var city_code = $("#city option:selected").val();

    $("#area option:gt(0)").remove();

    $.ajax({
        type: "post",
        url: "/getAreas",
        data: {_csrf: token, city_code: city_code},
        dataType: "json",
        success: function (result) {
            for (var i = 0; i < result.length; i++){
                $("#area").append("<option value=" + result[i].id + ">" + result[i].area_name + "</option>")
            }
        }
    })
});


//websocket
// var phone = [[${session.authentication}]];
var phone = $("#authentication").val();

var stompClient = null;	    //加载完浏览器后  调用connect（），打开双通道
// var stompClient1 = null;	    //加载完浏览器后  调用connect（），打开双通道
$(function(){
    //打开双通道
    connect()
});
//强制关闭浏览器  调用websocket.close（）,进行正常关闭
window.onunload = function() {
    disconnect()
};

function connect(){
    var socket = new SockJS('/endpointOyzc');
    // var socket1 = new SockJS('/endpointOne');
    //连接SockJS的endpoint名称为"endpointOyzc"
    stompClient = Stomp.over(socket);//使用STMOP子协议的WebSocket客户端

    //     stompClient1 = Stomp.over(socket1);//使用STMOP子协议的WebSocket客户端
    // stompClient1.connect({},function(frame){//连接WebSocket服务端
    //     console.log('Connected:' + frame);//通过stompClient.subscribe订阅/topic/getResponse 目标(destination)发送的消息
    // stompClient1.subscribe('/topic/getResponse',function(response){
    //     showResponse(JSON.parse(response.body));
    // });
    // });


    stompClient.connect({},function(frame){//连接WebSocket服务端
        console.log('Connected:' + frame);            //通过stompClient.subscribe订阅/topic/getResponse 目标(destination)发送的消息
        stompClient.subscribe('/user/' + phone + '/queue/getResponse',function(response){
            showResponse(response.body)
        });
        stompClient.subscribe('/topic/getResponse',function(response){
            showResponse(JSON.parse(response.body));
        });
    });


}     //关闭双通道
function disconnect(){
    if(stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
function showResponse(message) {
    var response = $("#response");
    response.append('<div class="toast fade show" style="position: fixed; right: 0; bottom: 0; float: right; width: 100%;">' +
        '<div class="toast-header">' +
        '<img src="" class="rounded mr-2" alt="">' +
        '<strong class="mr-auto">消息</strong>' +
        '<small>刚刚</small>' +
        '<button type="button" class="ml-2 mb-1 close" data-dismiss="toast">' +
        '<span>×</span>' +
        '</button>' +
        '</div>' +
        '<div class="toast-body">' +
        message +
        '</div>' +
        '</div>');
}
