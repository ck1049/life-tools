let echarts = require('../ec-canvas/echarts.min.js');

function initEcharts(lazyComponent, xAxis, yAxis, series, title) {
    lazyComponent.init((canvas, width, height, dpr) => {
        const chart = echarts.init(canvas, null, {
            width: width,
            height: height,
            devicePixelRatio: dpr // new
        });
        canvas.setChart(chart);

        var option = {
            title: {
                top: 0,
                text: title,
                left: 'center'
            },
            legend: {
                data: series.map(item => item.name),
                top: 20,
                left: 'center',
                backgroundColor: 'rgba(0, 0, 0, 0)',
                z: 10
            },
            grid: {
                y2: '10rpx',
                containLabel: true
            },
            tooltip: {
                show: true,
                trigger: 'axis'
            },
            xAxis,
            yAxis,
            series
        };
        
        chart.setOption(option);
        return chart;
    });
}

function initChart(dataList, that) {
    let xAxis = {
        type: 'category',
        name: '期次',
        boundaryGap: false,
        data: dataList.map(item => item.issueNumber),
        show: true
    }

    let amountSeries = [{
        name: '单注奖金',
        type: 'line',
        data: dataList.map(item => item.detailList[0].singleBetBonus / 10000),
        label: {
            show: true,
            position: "top",
            formatter: param => param.value + '万',
            fontSize: 10
        }
    }, {
        name: '中奖注数',
        type: 'line',
        yAxisIndex: 1,
        data: dataList.map(item => item.detailList[0].winningBetsNum),
        label: {
            show: true,
            position: "top",
            formatter: param => param.value + '注',
            fontSize: 10
        }
    }];
    var amountYAxisIndex0Max = Math.max(Math.max(...amountSeries[0].data.concat(1000)));
    var amountYAxisIndex1Max = Math.max(Math.max(...amountSeries[1].data.concat(15)));
    let amountYAxis = [{
            type: 'value',
            min: 500,
            max: amountYAxisIndex0Max,
            interval: (amountYAxisIndex0Max - 500) / 5,
            splitLine: {
                show: false
            },
            axisLabel: {
                show: false,
                formatter: '{value} 万'
            }
        },
        {
            type: 'value',
            min: 0,
            max: amountYAxisIndex1Max,
            interval: amountYAxisIndex1Max / 5,
            splitLine: {
                show: false
            },
            axisLabel: {
                show: false,
                formatter: '{value} 注'
            }
        }
    ];

    let bonusPoolSeries = [{
        name: '奖池走势',
        type: 'line',
        color: 'red',
        data: dataList.map(item => item.accumulatedPrizePool),
        show: true,
        label: {
            show: true,
            position: "top",
            formatter: param => param.value + '亿',
            fontSize: 10
        },
    }];
    var bonusPoolYAxisIndex0Max = Math.max(Math.max(...bonusPoolSeries[0].data.concat(10)));
    var bonusPoolYAxis = {
        type: 'value',
        min: 0,
        max: bonusPoolYAxisIndex0Max,
        interval: bonusPoolYAxisIndex0Max / 5,
        splitLine: {
            show: false
        },
        axisLabel: {
            show: false,
            formatter: '{value} 亿'
        }
    };

    // 延迟渲染，解决this.selectComponent因渲染不及时返回null的问题
    initEcharts(that.selectComponent('#amount-trend-line'), xAxis, amountYAxis, amountSeries, '奖金走势');
    initEcharts(that.selectComponent('#bonus-pool-trend-line'), xAxis, bonusPoolYAxis, bonusPoolSeries, '奖池走势');
}

module.exports = {
    initChart
}