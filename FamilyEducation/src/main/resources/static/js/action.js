
$(document).ready(function () {

    //head
    $("#submit").click(function () {
        $("#logout").submit;
    });

    //取消应聘
    $("#cancelTheApplication").click(function () {
        var node = this.parentNode;
        var id = node.children[0].getAttribute("id");
        $(id).submit;
    });

    //删除消息
    $("#deleteInformation").click(function () {
        var node = this.parentNode;
        var id = node.children[0].getAttribute("id");
        $(id).submit;
    });

    //已读消息
    $("#readInformation").click(function () {
        var node = this.parentNode;
        var id = node.children[3].getAttribute("id");
        $(id).submit;
    });

    //websocket
    $("#response").on("click", ".toast", function () {
        $(this).fadeOut(100);
        $(this).attr("class", "toast fade hide");
    });

    //家长接受
    $("#enterTheInterview").click(function () {
        var node = this.parentNode;
        var id = node.children[0].getAttribute("id");
        $(id).submit;
    });

    //家长拒绝
    $("#refuseEntry").click(function () {
        var node = this.parentNode;
        var id = node.children[1].getAttribute("id");
        $(id).submit;
    });

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

//省市区
//获取csrf变量的token值放在_csrf变量中,此变量要作为所有请求的参数
var token = $("#token").val();
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
