$(function(){
 geoCoorMap = "";
 converDataArray = "";
    $.ajax({url:"getMSMInfor",async: false,success:function(returnedData,status){
            geoCoorMap = returnedData.lals;
            converDataArray = returnedData.altitudes;
        }
    });

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoorMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    option = {
        backgroundColor: '#404a59',
        title: {
            text: '冬小麦种植地海拔高度分布',
            subtext: '点击站点进入站点详情 ',
            //sublink: 'http://www.pm25.in',
            x:'center',
            textStyle: {
                color: '#fff',
                fontWeight:'bold',
                fontSize:20
            }
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x:'right',
            data:['冬小麦种植地海拔高度分布'],
            textStyle: {
                color: '#fff'
            }
        },
        visualMap: {
            min: 100,
            max: 850,
            calculable: true,
            inRange: {
                color: ['#50a3ba', '#eac736', '#d94e5d']
            },
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            },
            center:[116.51,35.34],
            zoom:1.5,
            roam:true
        },
        series: [
            {
                name: '',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(converDataArray),
                symbolSize: 12,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                itemStyle: {
                    emphasis: {
                        borderColor: '#fff',
                        borderWidth: 1
                    }
                }
            }
        ]
    };
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'),'macarons');
    myChart.setOption(option);


    myChart.on("click", function(param){
        // alert(param.name);
        if(null==param.value)
            return ;
        window.location = "views/detail.jsp?site="+param.name;

    });
});