let echarts = require('../ec-canvas/echarts.min.js');

const app = getApp();
const api = require('../../config/api.js')
const interceptor = require("../../config/interceptor.js").loginInterceptor;

function initChart(lazyComponent, xAxis, yAxis, series, title) {
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
                // selectedMode: 'single',
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

Page({
    onShareAppMessage: function (res) {
        return {
            title: 'ECharts 可以在微信小程序中使用啦！',
            path: '/pages/index/index',
            success: function () {},
            fail: function () {}
        }
    },
    data: {
        enName: '',
        amountTrend: {
            lazyLoad: true
        },
        bonusPool: {
            lazyLoad: true
        },
        swiper: {
            background: ['demo-text-1', 'demo-text-2', 'demo-text-3'],
            indicatorDots: true,
            vertical: false,
            autoplay: false,
            interval: 2000,
            duration: 500
        }
    },

    /**
     * 刷新页面数据
     */
    refresh(lambda, title) {
        wx.showLoading({
            title: title || '加载中',
            mask: true
        })

        let url = api.lottery.historicalInformation.replace('{enName}', this.data.enName)
            .replace('{minIssueNumber}', '9999999').replace('{pageSize}', '7');
        console.log(url);
        app.authRequest({
            url,
            method: 'GET',
            interceptor: interceptor,
            success: res => {
                lambda(res);
            },
            complete: res => {
                wx.hideLoading();
                wx.stopPullDownRefresh();
            }
        })
    },

    onLoad(option) {
        console.log(option);
        this.setData({
            enName: option.enName || 'lotto'
        })
        this.refresh(res => {
            let dataList = res.data;

            let xAxis = {
                type: 'category',
                name: '期次',
                boundaryGap: false,
                data: dataList.map(item => item.issueNumber),
                show: true,
                nameTextStyle: {
                    // padding:[0,25]
                }
            }
            // var xAxis1 = JSON.parse(JSON.stringify(xAxis));
            // xAxis1.nameTextStyle = {};

            let amountSeries = [{
                name: '单注奖金',
                type: 'line',
                // smooth: true,
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
                // smooth: true,
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
                    // name: series[0].name,
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
                    // name: series[1].name,
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
                // smooth: true,
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
                // name: series[1].name,
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

            initChart(this.selectComponent('#amount-trend-line'), xAxis, amountYAxis, amountSeries, '奖金走势');
            initChart(this.selectComponent('#bonus-pool-trend-line'), xAxis, bonusPoolYAxis, bonusPoolSeries, '奖池走势');
        });
    },
    onReady() {}
});