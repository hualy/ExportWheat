 // 基于准备好的dom，初始化echarts实例
 var myChart = echarts.init(document.getElementById('content'),'macarons');
var colors = ['#5793f3', '#d14a61', '#675bba'];



 $(function(){
     function GetQueryString(name)
     {
         var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
         var r = window.location.search.substr(1).match(reg);
         if(r!=null)return  unescape(r[2]); return null;
     }
     // alert(GetQueryString("site"));
     getDetail = function(option){
         $.post("getDetail",{"site":GetQueryString("site"),"option":option},function(returnedData,status){
             // app.title = '坐标轴刻度与标签对齐';
             option = {
                 title:{
                     show:true,
                     text:returnedData.site+":"+returnedData.name,
                     left:"center"
                 },
                 color: ['#3398DB'],
                 tooltip : {
                     trigger: 'axis',
                     axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                         type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                     }
                 },
                 toolbox: {
                     feature: {
                         dataView: {show: true, readOnly: true},
                         restore: {show: true},
                         magicType: {
                             show:true,
                             type: ['line', 'bar']
                         },
                         saveAsImage: {show: true}
                     }
                 },
                 grid: {
                     left: '3%',
                     right: '4%',
                     bottom: '3%',
                     containLabel: true
                 },
                 xAxis : [
                     {
                         type : 'category',
                         data : returnedData.years,
                         axisTick: {
                             alignWithLabel: true
                         }
                     }
                 ],
                 yAxis: [
                     {
                         type: 'value',
                         name: 'value/('+returnedData.unit+")",
                         min: returnedData.min,
                         max: returnedData.max,
                         // interval: 50,
                         axisLabel: {
                             formatter: '{value}'
                         }
                     }
                 ],
                 series : [
                         {
                             name:returnedData.name,
                             type:'line',
                             barWidth: '60%',
                             data:returnedData.values
                         }
                    ]
             };
             //     yAxis : [
             //         {
             //             type : 'value'
             //         }
             //     ],
             //     series : [
             //         {
             //             name:returnedData.name,
             //             type:'line',
             //             barWidth: '60%',
             //             data:returnedData.values
             //         }
             //     ]
             // };
             myChart.setOption(option);
         });
     }

     getDetail(1);
     // alert($("li[role]").length);

 })

// option = {
//     color: colors,
//
//     tooltip: {
//         trigger: 'axis'
//     },
//     grid: {
//         right: '20%'
//     },
//     toolbox: {
//         feature: {
//             dataView: {show: true, readOnly: false},
//             restore: {show: true},
//             saveAsImage: {show: true}
//         }
//     },
//     legend: {
//         data:['蒸发量','降水量','平均温度']
//     },
//     xAxis: [
//         {
//             type: 'category',
//             axisTick: {
//                 alignWithLabel: true
//             },
//             data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
//         }
//     ],
//     yAxis: [
//         {
//             type: 'value',
//             name: '蒸发量',
//             min: 0,
//             max: 250,
//             position: 'right',
//             axisLine: {
//                 lineStyle: {
//                     color: colors[0]
//                 }
//             },
//             axisLabel: {
//                 formatter: '{value} ml'
//             }
//         },
//         {
//             type: 'value',
//             name: '降水量',
//             min: 0,
//             max: 250,
//             position: 'right',
//             offset: 80,
//             axisLine: {
//                 lineStyle: {
//                     color: colors[1]
//                 }
//             },
//             axisLabel: {
//                 formatter: '{value} ml'
//             }
//         },
//         {
//             type: 'value',
//             name: '温度',
//             min: 0,
//             max: 25,
//             position: 'left',
//             axisLine: {
//                 lineStyle: {
//                     color: colors[2]
//                 }
//             },
//             axisLabel: {
//                 formatter: '{value} °C'
//             }
//         }
//     ],
//     series: [
//         {
//             name:'蒸发量',
//             type:'bar',
//             data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
//         },
//         {
//             name:'降水量',
//             type:'bar',
//             yAxisIndex: 1,
//             data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
//         },
//         {
//             name:'平均温度',
//             type:'line',
//             yAxisIndex: 2,
//             data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
//         }
//     ]
// };
//
//
//  // 使用刚指定的配置项和数据显示图表。
//  myChart.setOption(option);