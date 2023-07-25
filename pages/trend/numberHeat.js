let echarts = require('../ec-canvas/echarts.min.js');
const numberListToIssueList = require('./numberTrend.js').numberListToIssueList;

const groupingBy = require('../../utils/util').groupingBy;

function initChart(dataList, that) {
    const backgroundColor = {
        red: 'rgb(250, 98, 98)',
        blue: 'dodgerblue'
    };

    for (let color of ['red', 'blue']) {
        let ballTrend = numberListToIssueList(dataList, color);

        // 红球/蓝球冷热
        let heatDataList = ballTrend.headers.filter(item => item.key != 'issue').map(item => ({ 'number': item.key }));

        // 按照号码分组map<number, [item]>
        let numberListMap = groupingBy(dataList.filter(item => item.color == color), 'number');
        heatDataList.forEach(heatData => {
            let number = heatData.number;
            let numberList = numberListMap[number];
            let numberHitList = numberList.filter(item => item.hit);

            // 近50期出现次数
            heatData.nearCount = numberHitList.length;
            // 当前遗漏
            heatData.currentOmission = numberList[numberList.length - 1].issue - numberHitList[heatData.nearCount - 1].issue;
            // 平均遗漏
            heatData.avgOmission = Math.round((50 - heatData.nearCount) * 1.0 / heatData.nearCount);
            heatData.ec = { lazyLoad: true }
        });

        if (color == 'red') {
            that.setData({ redBallHeat: heatDataList });
        } else {
            that.setData({ blueBallHeat: heatDataList });
        }
        

        let max = Math.max(...heatDataList.map(item => item.nearCount));
        heatDataList.forEach(heatData => {
            let nearCount = heatData.nearCount;
            heatData.width = Math.round(nearCount / max * 0.9 * 100) + '%'; // canvas的 style：width
            // 近50期出现次数的横向柱状图
            let xAxis = [{
                type: 'value',
                splitLine: {
                    show: false
                },
                axisLine: {
                    "show": false
                }, // x轴坐标轴，false为隐藏，true为显示
                axisLabel: {
                    show: false
                }, // 隐藏x轴坐标轴的数值
                axisTick: {
                    show: false
                }, // 隐藏x轴坐标轴刻度线
                boundaryGap: false,
                min: 0,
                max: nearCount,
                interval: 1
            }];

            let yAxis = [{
                type: 'category',
                data: ['01'],
                splitLine: {
                    show: false
                },
                axisLine: {
                    "show": false
                },
                axisLabel: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }];
            let series = [{
                name: 'series',
                type: 'bar',
                color: backgroundColor[color],
                data: [nearCount],
                itemStyle: {
                    normal: {
                        barBorderRadius: [0, 10, 10, 0]
                    }
                } // 设置顶部左右两侧的圆角半径为5，使其呈现弧形效果
            }];
            let grid = {
                containLabel: true,
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            };
            let options = {
                xAxis,
                yAxis,
                series,
                grid
            };

            that.selectComponent("#ec-canvas-" + color + "-" + heatData.number).init((canvas, width, height, dpr) => {
                // console.log(canvas, width, height, dpr);
                let barWidth = Math.round(height * 0.8);
                options.series.barWidth = barWidth; // 柱状图宽度
                let chart = echarts.init(canvas, null, {
                    width,
                    height,
                    devicePixelRatio: dpr
                });
                canvas.setChart(chart);
                chart.setOption(options);
                return chart;
            });

        });

        if (color == 'red') {
            that.setData({ redBallHeat: heatDataList });
        } else {
            that.setData({ blueBallHeat: heatDataList });
        }
    }
}

module.exports = {
    initChart
}