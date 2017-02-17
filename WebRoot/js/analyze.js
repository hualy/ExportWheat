var count = 3;
var length = 2;
var addFunction = function(button){
    if(length<count){
            var divc = $('<div class="col-md-3"></div>');
            var div = $('<div class="form-group form-flag"></div>');
            var select1 = $('<select class="form-control" onchange="ChangeSelect1(this)"></select>');
            var option0 = $('<option></option>').text('安徽');
            var option1 = $('<option></option>').text('河北');
            var option2 = $('<option></option>').text('河南');
            var option3 = $('<option></option>').text('江苏');
            var option4 = $('<option></option>').text('山东');
            var option5 = $('<option></option>').text('天津');
            var select2 = $('<select class="form-control"></select>');
            var option00 = $('<option></option>').text('毫州');
            var option11 = $('<option></option>').text('砀山');
            var option22 = $('<option></option>').text('蚌埠');
            var option33 = $('<option></option>').text('阜阳');
            select1.append(option0,option1,option2,option3,option4,option5);
            select2.append(option00,option11,option22,option33);
            div.append(select1,select2);
            var button11 = $('<button type="button" class="removeBtn" onclick="deleteFunction(this);"></button>').text('删除');
            divc.append(div,button11);
            $('#addBtn').before(divc);
            length++;
            $('button.removeBtn').removeAttr('disabled');
            $('button.removeBtn').css({
                'color': '#FF8300',
                'border-color': '#FF8300'
            });
        }else{
            alert("站点过多");
            $('#addBtn').attr('disabled', 'disabled');
        }
}
var deleteFunction = function(button) {
    if(length<=2){
        $('button.removeBtn').attr('disabled', 'disabled');
        $('button.removeBtn').css({
            'color': '#d7d7d7',
            'border-color': '#d7d7d7'
        });
    }else{
        $(button).parent().fadeOut();
        $(button).parent().remove();
        $('#addBtn').removeAttr('disabled');
        length--;
        if(length==2){
            $('button.removeBtn').css({
            'color': '#d7d7d7',
            'border-color': '#d7d7d7'
            });
        }
    }
};
//---------------------------------------charts-----------------------------------------------------
$(function(){


    var ltppsconvertData = function (returnedData) {
        var res = [];
        for (var i = 0; i < returnedData.names.length; i++) {
            // var geoCoord = geoCoordMap[data[i].name];
            var temp = {};
            temp.name = returnedData.names[i];
            temp.type = "bar";
            temp.data = returnedData.datas[i].ltpps;
            // temp.add("name",returnedData.names[i]);
            // temp.add("type",bar);
            // temp.add("data",returnedData.datas[i].ltpps);
            res.push(temp);
        }
        return res;
    };

    var cppsconvertData = function (returnedData) {
        var res = [];
        for (var i = 0; i < returnedData.names.length; i++) {
            // var geoCoord = geoCoordMap[data[i].name];
            var temp = {};
            temp.name = returnedData.names[i];
            temp.type = "bar";
            temp.data = returnedData.datas[i].cpps;
            // temp.add("name",returnedData.names[i]);
            // temp.add("type",bar);
            // temp.add("data",returnedData.datas[i].cpps);
            res.push(temp);
        }
        return res;
    };

    var rainfallsconvertData = function (returnedData) {
        var res = [];
        for (var i = 0; i < returnedData.names.length; i++) {
            // var geoCoord = geoCoordMap[data[i].name];
            var temp = {};
            temp.name = returnedData.names[i];
            temp.type = "bar";
            temp.data = returnedData.datas[i].rainfalls;
            // temp.add("name",returnedData.names[i]);
            // temp.add("type",bar);
            // temp.add("data",returnedData.datas[i].cpps);
            res.push(temp);
        }
        return res;
    };

    var paniclesesconvertData = function (returnedData) {
        var res = [];
        for (var i = 0; i < returnedData.names.length; i++) {
            // var geoCoord = geoCoordMap[data[i].name];
            var temp = {};
            temp.name = returnedData.names[i];
            temp.type = "bar";
            temp.data = returnedData.datas[i].panicleses;
            // temp.add("name",returnedData.names[i]);
            // temp.add("type",bar);
            // temp.add("data",returnedData.datas[i].cpps);
            res.push(temp);
        }
        return res;
    };

    var postMessage = function(dataSites,startYear,endYear){

        $.post("getMoreMSM",{"sites":dataSites,"startYear2":startYear,"endYear2":endYear},function(returnedData,status){
            var chart1 = echarts.init(document.getElementById('chart1'),'vintage');
            var chart2 = echarts.init(document.getElementById('chart2'),'macarons');
            var chart3 = echarts.init(document.getElementById('chart3'),'walden');
            var chart4 = echarts.init(document.getElementById('chart4'),'dark');



            var ltppoption = {
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:returnedData.names
                },
                xAxis: [
                    {
                        type: 'category',
                        data: returnedData.datas[0].years
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '光温生产潜力/(kg*hm^-2/hr)',
                        min: returnedData.ltppMin,
                        max: returnedData.ltppMax,
                        // interval: 50,
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: ltppsconvertData(returnedData)
            };

            chart1.setOption(ltppoption);

            var cppoption = {
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:returnedData.names
                },
                xAxis: [
                    {
                        type: 'category',
                        data: returnedData.datas[0].years
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '气候生产潜力/(t/hm^2*year)',
                        min: returnedData.cppMin,
                        max: returnedData.cppMax,
                        // interval: 50,
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: cppsconvertData(returnedData)
            };

            chart2.setOption(cppoption);

            var paniclesoption = {
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:returnedData.names
                },
                xAxis: [
                    {
                        type: 'category',
                        data: returnedData.datas[0].years
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '生育期内需水量/ml',
                        min: returnedData.paniclesesMin,
                        max: returnedData.painclesesMax,
                        // interval: 50,
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: paniclesesconvertData(returnedData)
            };

            chart3.setOption(paniclesoption);

            var rainfalloption = {
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data:returnedData.names
                },
                xAxis: [
                    {
                        type: 'category',
                        data: returnedData.datas[0].years
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '降水盈亏量/ml',
                        min: returnedData.rainfallMin,
                        max: returnedData.rainfallMax,
                        // interval: 50,
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: rainfallsconvertData(returnedData)
            };

            chart4.setOption(rainfalloption);

        });
    }

    getMessage = function(){
        // alert($(".form-flag").length);
        // alert($(".form-flag").eq(0).children().eq(1).find("option:selected").text());
        // alert($(".form-flag").find("option:selected").text());
        // var dataSites = "[";
        var dataSites = [];
        for(var i=0;i<$(".form-flag").length;i++){
            var tempStr = $(".form-flag").eq(i).children().eq(1).find("option:selected").text();
            if(dataSites.toString().indexOf(tempStr)!=-1){
                alert("请不要输入相同站点");
                return ;
            }
            dataSites.push("\""+tempStr+"\"");
        }
        // dataSites = dataSites.substr(0,dataSites.length-1);
        // dataSites+="]";

        // alert(dataSites.toString());
        var startYear = document.getElementById("beginTime").value;
        var endYear = document.getElementById("endTime").value;
        // alert(startYear);
        // alert(2);
        if(isNaN(startYear)||isNaN(endYear)){
            alert("请输入数字形式");
            return false;
        }

        if(endYear>2013||startYear<1961||endYear<startYear){
            alert("请输入正确的数据（1961-2013）");
            return false;
        }

        postMessage("["+dataSites+"]",startYear,endYear);


    };

    postMessage();

});