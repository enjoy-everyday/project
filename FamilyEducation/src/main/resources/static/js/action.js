
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
        var otherRequirement = "";
        var otherPlace = "";
        if ($("#otherRequirement").val() == ""){
            otherRequirement = "null";
        }
        else {
            otherRequirement = $("#otherRequirement").val();
        }
        if ($("input[name='place']:checked").val() == "address"){
            otherPlace = "null";
        }
        else {
            otherPlace = $("#otherDetailedAddress").val();
        }
        var json =
            "gender," + $("input[name='gender']:checked").val() + ";" +
            "qualification," + $("#qualification option:selected").text() + ";" +
            "experience," + $("#experience option:selected").text() + ";" +
            "otherRequirement," + otherRequirement + ";" +
            "teachingGradeAndSubject," + $("#teachingGradeAndSubjectSelected p:eq(0)").text() + ";" +
            "teachingTime," + $("#teachingTimeSelected p:eq(0)").text() + ";" +
            "calculation," + $("input[name='price']:checked").val() + ";" +
            "price," + $("#price").val() + ";" +
            "otherPlace," + otherPlace
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
        var detailedAddress;
        var role = $("#role").val();
        if ($("#detailedAddress").val() == ""){
            detailedAddress = "null";
        }
        else {
            detailedAddress = $("#detailedAddress").val();
        }
        if (role == "学生"){
            information = "username," + $("#username").val() + ";" +
                "name," + $("#name").val() + ";" +
                "age," + $("#age").val() + ";" +
                "gender," + $("input[name='gender']:checked").val() + ";" +
                "qualification," + $("#qualification option:selected").val() + ";" +
                "province," + $("#province option:selected").text() + ";" +
                "city," + $("#city option:selected").text() + ";" +
                "area," + $("#area option:selected").text() + ";" +
                "goodAtSubjects," + $("#gradeAndSubjectSelected").val() + ";" +
                "freeTime," + $("#timeSelected").val() + ";" +
                "detailedAddress," + detailedAddress
            ;
        }
        else {
            information = "username," + $("#username").val() + ";" +
                "name," + $("#name").val() + ";" +
                "age," + $("#age").val() + ";" +
                "gender," + $("input[name='gender']:checked").val() + ";" +
                "province," + $("#province option:selected").text() + ";" +
                "city," + $("#city option:selected").text() + ";" +
                "area," + $("#area option:selected").text() + ";" +
                "achievements," + $("#achievements option:selected").text() + ";" +
                "detailedAddress," + detailedAddress
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

    //发布，点击显示详细地址文本域
    $("#others").click(function(){
        $("#otherDetailedAddress").append('<textarea style="width: 100%;  min-height: 50px;" placeholder="详细地址"  name="otherPlace" id="otherPlace"></textarea>')
    });

    //发布，点击隐藏详细地址文本域
    $("#address").click(function(){
        $("#otherDetailedAddress").empty();
    });

    //显示星星数
    var starNumber = parseInt($("#score").text()) - 1;
    if (starNumber !== -1){
        $("input[name='star']").eq(starNumber).attr("checked", true);
    }

    //显示进度条
    $("#taskNumberBar").css("width", ($("#taskNumberText").text() / $("#allDetailNumber").val() * 100) + '%');
    $("#cancelTimeBar").css("width", $("#cancelTimeText").text());
    $("#refuseTimeBar").css("width", $("#refuseTimeText").text());
    $("#acceptTimeBar").css("width", $("#acceptTimeText").text());
    $("#successTimeBar").css("width", $("#successTimeText").text());
    
    //排行榜星星
    var rankingListNumber = $("form[name='rankingList']").length;
    for (var i = 0; i < rankingListNumber; i++){
        var rankingListStarNumber = parseInt($("#score" + (i + 1)).val()) - 1;
        if (rankingListStarNumber !== -1){
            $("div[name='rankingListStars']").eq(i).children().eq(rankingListStarNumber).attr("checked", true);
        }
    }

    // //家长筛选学生
    // $("#screenStudents").click(function () {
    //     var qualification = $("#selectQualification option:selected").val();
    //     var score = $("#selectScore option:selected").val();
    //     $.ajax({
    //         url: "/searchQualificationAndScore",
    //         type: "post",
    //         data: {qualification: qualification, score: score, _csrf: token},
    //         error: function () {
    //             alert("错误");
    //         }
    //     })
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
                window.location.replace("/findApplied");
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
                window.location.replace("/findApplied");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//删除发布
function deleteDetail(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/deleteDetail",
        type: "post",
        data: {detail_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("删除成功");
                window.location.replace("/findAllPublish");
            }
            else {
                alert("请重新操作");
            }
        }
    });
}

//删除应聘
function deleteTheApplication(element) {
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/deleteTheApplication",
        type: "post",
        data: {task_id: value,  _csrf: token},
        success: function (result) {
            if (result === "success"){
                alert("删除成功");
                window.location.replace("/viewAll");
            }
            else if (result === "error"){
                alert("此单还未完成，不能删除");
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

//查看学生个人详情
function personalParticulars(element){
    var node = element.parentNode;
    var value = node.children[0].getAttribute("value");
    $.ajax({
        url: "/introductionPage",
        type: "post",
        data: {id: value,  _csrf: token},
        error: function () {
            alert("错误");
        }
    });
}

$(document).ready(function(){

    //选择空闲时间
    var clock = 10;
    var weeklyTimeLength = 0;
    var choiceWeekAndTime = [[], [], [], [], [], [], []];
    var weekChoice = 0;
    var time = ["08:00-08:30", "08:30-09:00", "09:00-09:30", "09:30-10:00"];
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
                time[i] = clock + ":30-" + (clock + 1) + ":00";
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
        if(choiceWeekAndTime[$(this).index()].length == 0){
            $("div[name='weeklyTime']").attr("class", "weeklyTime");
        }
        else{
            for(var i = 0; i < weeklyTimeLength; i++){
                if(choiceWeekAndTime[$(this).index()][i] != null){
                    $("div[name='weeklyTime']").eq(i).attr("class", "weeklyTime choiceTime");
                }
                else{
                    $("div[name='weeklyTime']").eq(i).attr("class", "weeklyTime");
                }
            }
        }
        $(".choiceWeek").attr("class", "week");
        $(this).attr("class", "week choiceWeek");
        weekChoice = $(this).index();
    });

    $(".weeklyTime").on("click", function(){
        if($(this).index() >= weeklyTimeLength){
            weeklyTimeLength = $(this).index() + 1;
        }
        if($(this).attr("class") == "weeklyTime"){
            $(this).attr("class", "weeklyTime choiceTime");
            choiceWeekAndTime[weekChoice][$(this).index()] = time[$(this).index()];
        }
        else {
            $(this).attr("class", "weeklyTime");
            choiceWeekAndTime[weekChoice][$(this).index()] = null;
        }
    });

    $("#chooseFreeTime").click(function () {
        $.ajax({
            url: "/chooseFreeTime",
            type: "post",
            data: {array: choiceWeekAndTime,  _csrf: token},
            traditional: true,
            success: function (result) {
                $("#timeSelected").empty();
                $("#timeSelected").append("<p>" + result + "</p>");
                alert("成功");
            },
            error: function () {
              alert("操作错误，请重新选择");
            }
        });
    });

    //选择擅长年级及科目
    var subjectLength = 0;
    var choiceGradeAndSubject = [];
    var subjectChoice = 0;
    var subject = ["语文", "数学", "英语", "政治", "历史", "地理", "物理", "化学", "生物"];
    var grade = ["小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级", "小学六年级", "初一", "初二", "初三", "高一", "高二", "高三"];

    for(var i = 0; i < 13; i++){
        choiceGradeAndSubject[i] = [];
    }

    for(var i = 1; i < grade.length; i++){
        if(grade[i] == "小学六年级"){
            $("#grade").append('<div class="grade"><p style="margin: 8px;">' + grade[i] + '</p></div><br>');
        }
        else{
            $("#grade").append('<div class="grade"><p style="margin: 8px;">' + grade[i] + '</p></div>');
        }
    }

    for(var i = 0; i < 3; i++){
        $("#subject").append('<div class="subject" name="subject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
    }

    $(".grade").on("click", function(){
        subjectChoice = $(this).index();
        if(subjectChoice < 6){
            $("#subject").empty();
            for(var i = 0; i < 3; i++){
                $("#subject").append('<div class="subject" name="subject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
            }
        }
        else{
            $("#subject").empty();
            for(var i = 0; i < subject.length; i++){
                $("#subject").append('<div class="subject" name="subject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
            }
        }
        if(choiceGradeAndSubject[$(this).index()].length == 0){
            $("div[name='subject']").attr("class", "subject");
        }
        else{
            for(var i = 0; i < subjectLength; i++){
                if(choiceGradeAndSubject[$(this).index()][i] != null){
                    $("div[name='subject']").eq(i).attr("class", "subject choiceSubject");
                }
                else{
                    $("div[name='subject']").eq(i).attr("class", "subject");
                }
            }
        }
        $(".choiceGrade").attr("class", "grade");
        $(this).attr("class", "grade choiceGrade");
    });

    $(document).on("click", ".subject", function(){
        if($(this).index() >= subjectLength){
            subjectLength = $(this).index() + 1;
        }
        if($(this).attr("class") == "subject"){
            $(this).attr("class", "subject choiceSubject");
            choiceGradeAndSubject[subjectChoice][$(this).index()] = subject[$(this).index()];
        }
        else {
            $(this).attr("class", "subject");
            choiceGradeAndSubject[subjectChoice][$(this).index()] = null;
        }
    });

    $("#chooseGradeAndSubject").click(function () {
        $.ajax({
            url: "/chooseGradeAndSubject",
            type: "post",
            data: {array: choiceGradeAndSubject,  _csrf: token},
            traditional: true,
            success: function (result) {
                $("#gradeAndSubjectSelected").empty();
                $("#gradeAndSubjectSelected").append("<p>" + result + "</p>");
                alert("成功");
            },
            error: function () {
                alert("操作错误，请重新选择");
            }
        });
    });

    //家长选择任教年级和科目
    var choiceTeachingSubjects = [];
    var teachingGradeChoice = 0;
    for(var i = 1; i < grade.length; i++){
        if(grade[i] == "小学六年级"){
            $("#teachingGrade").append('<div class="teachingGrade"><p style="margin: 8px;">' + grade[i] + '</p></div><br>');
        }
        else{
            $("#teachingGrade").append('<div class="teachingGrade"><p style="margin: 8px;">' + grade[i] + '</p></div>');
        }
    }

    for(var i = 0; i < 3; i++){
        $("#teachingSubject").append('<div class="teachingSubject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
    }

    $(".teachingGrade").on("click", function(){
        choiceTeachingSubjects = [];
        teachingGradeChoice = $(this).index();
        if(teachingGradeChoice < 6){
            $("#teachingSubject").empty();
            for(var i = 0; i < 3; i++){
                $("#teachingSubject").append('<div class="teachingSubject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
            }
        }
        else{
            $("#teachingSubject").empty();
            for(var i = 0; i < subject.length; i++){
                $("#teachingSubject").append('<div class="teachingSubject"><p style="margin: 8px;">' + subject[i] + '</p></div>');
            }
        }

        $(".choiceTeachingGrade").attr("class", "teachingGrade");
        $(this).attr("class", "teachingGrade choiceTeachingGrade");
    });

    $(document).on("click", ".teachingSubject", function(){
        if($(this).attr("class") == "teachingSubject"){
            $(this).attr("class", "teachingSubject choiceTeachingSubject");
            choiceTeachingSubjects[$(this).index()] = subject[$(this).index()];
        }
        else {
            $(this).attr("class", "teachingSubject");
            choiceTeachingSubjects[$(this).index()] = null;
        }
    });

    $("#confirmTeachingGradeAndSubject").click(function () {
        $.ajax({
            url: "/choiceTeachingGradeAndSubject",
            type: "post",
            data: {array: choiceTeachingSubjects, gradeNumber: teachingGradeChoice,  _csrf: token},
            traditional: true,
            success: function (result) {
                if (result == "null"){
                    alert("没有选择科目，请重新选择");
                }
                else {
                    $("#teachingGradeAndSubjectSelected").empty();
                    $("#teachingGradeAndSubjectSelected").append("<p>" + result + "</p>");
                }
                alert("成功");
            },
            error: function () {
                alert("操作错误，请重新选择");
            }
        });
    });

    //选择任教时间
    $("#chooseTeachingTime").click(function () {
        $.ajax({
            url: "/chooseFreeTime",
            type: "post",
            data: {array: choiceWeekAndTime,  _csrf: token},
            traditional: true,
            success: function (result) {
                $("#teachingTimeSelected").empty();
                $("#teachingTimeSelected").append("<p>" + result + "</p>");
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
