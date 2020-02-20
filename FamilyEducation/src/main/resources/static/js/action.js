
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
            $("#area").attr("value", "null");
            $("#position").submit();
        }
        else {
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