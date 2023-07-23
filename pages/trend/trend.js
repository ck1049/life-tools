let echarts = require('../ec-canvas/echarts.min.js');

const app = getApp();
const api = require('../../config/api.js')
const groupingBy = require('../../utils/util').groupingBy;

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

// 将trend接口返回的数据转成表格数据格式
function numberListToIssueList(resList, color) {
    const backgroundColor = {
        red: 'rgb(250, 98, 98)',
        blue: 'dodgerblue'
    };
    // 返回map<issue, [item]>
    let issueListMap = groupingBy(resList.filter(item => item.color == color), 'issue');

    // 红球趋势表头
    let ballHeaders = [{
        title: '期次',
        key: 'issue'
    }];
    let numberList = issueListMap[Object.keys(issueListMap)[0]].map(item => ({
        'title': item.number,
        'key': item.number
    }));

    ballHeaders = [...ballHeaders, ...numberList];
    // 表格数据内容
    let data = [];


    Object.keys(issueListMap).forEach(key => {
        const itemList = issueListMap[key];
        const dataItem = {};
        dataItem['issue'] = {
            value: key.substring(2) + '期',
            style: null
        };
        for (let i = 0; i < itemList.length; i++) {
            const numObj = itemList[i];
            let intervalCount = numObj.intervalCount || numObj.number;
            let style = numObj.intervalCount ? null : "color: white;background-color: " + backgroundColor[color];
            dataItem[numObj.number] = {
                value: intervalCount,
                style: style
            }
        }
        data.push(dataItem);
    });
    // console.log(data);

    return {
        'headers': ballHeaders,
        'data': data
    };
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
        tabs: [{
            title: '奖金走势',
            key: 'amountTrend'
        }, {
            title: '红球走势',
            key: 'redBallTrend'
        }, {
            title: '蓝球走势',
            key: 'blueBallTrend'
        }, {
            title: '红球冷热',
            key: 'redBallHeat'
        }, {
            title: '蓝球冷热',
            key: 'blueBallHeat'
        }, {
            title: '大小',
            key: 'sizeTrend'
        }, {
            title: '奇偶',
            key: 'parityTrend'
        }, {
            title: '质合',
            key: 'primeCompositeTrend'
        }],
        redBallsTrend: {
            headers: [],
            data: []
        },
        blueBallsTrend: {
            headers: [],
            data: []
        },
        selectedTab: 'amountTrend',
        redBallHeat: [],
        blueBallHeat: []
    },
    switchTab(e) {
        this.setData({
            selectedTab: e.currentTarget.dataset.selectedTab
        });
        console.log(this.data.selectedTab);
    },
    onOuterSwiperTouchMove(event) {
        // 阻止外部swiper的滑动事件冒泡到内部swiper
        // event.stopPropagation();
    },
    /**
     * 请求数据
     */
    requestTrendData(url, lambda, title) {
        wx.showLoading({
            title: title || '加载中',
            mask: true
        })

        console.log(url);
        app.authRequest({
            url,
            method: 'GET',
            success: res => {
                lambda(res);
            },
            complete: res => {
                wx.hideLoading();
                wx.stopPullDownRefresh();
            }
        })
    },
    async renderChartAsync() {
        return new Promise((resolve, reject) => {
            // 异步处理渲染任务
            // ...
            resolve(); // 渲染完成后调用resolve
        });
    },
    onLoad(option) {
        this.setData({
            enName: option.enName || 'LOTTO',
        })
    },
    onReady() {
        // 奖金走势图
        let amountTrendUrl = api.lottery.historicalInformation.replace('{enName}', this.data.enName)
            .replace('{minIssueNumber}', '9999999').replace('{pageSize}', '7');
        this.requestTrendData(amountTrendUrl, res => {
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

            // 延迟渲染，解决this.selectComponent因渲染不及时返回null的问题
            initChart(this.selectComponent('#amount-trend-line'), xAxis, amountYAxis, amountSeries, '奖金走势');
            initChart(this.selectComponent('#bonus-pool-trend-line'), xAxis, bonusPoolYAxis, bonusPoolSeries, '奖池走势');
            // this.renderChartAsync().then(() => {
            //     // 延迟渲染，解决this.selectComponent因渲染不及时返回null的问题
            //     initChart(this.selectComponent('#amount-trend-line'), xAxis, amountYAxis, amountSeries, '奖金走势');
            //     initChart(this.selectComponent('#bonus-pool-trend-line'), xAxis, bonusPoolYAxis, bonusPoolSeries, '奖池走势');
            // }, 1000);
            // setTimeout(() => {
            //     // 延迟渲染，解决this.selectComponent因渲染不及时返回null的问题
            //     initChart(this.selectComponent('#amount-trend-line'), xAxis, amountYAxis, amountSeries, '奖金走势');
            //     initChart(this.selectComponent('#bonus-pool-trend-line'), xAxis, bonusPoolYAxis, bonusPoolSeries, '奖池走势');
            // }, 1000);
        }, '奖金走势加载中！');


        let dataList = [];
        var redBallTrend = {};
        var blueBallTrend = {};
        // 彩票号码走势
        let lotteryTrendUrl = api.lottery.trendUrl.replace('{enName}', this.data.enName);
        this.requestTrendData(lotteryTrendUrl, res => {
            dataList = res.data;
            redBallTrend = numberListToIssueList(dataList, 'red');
            blueBallTrend = numberListToIssueList(dataList, 'blue');
            console.log("===redBallTrend1===", JSON.stringify(redBallTrend));
            this.setData({
                ['redBallsTrend.headers']: redBallTrend.headers,
                ['redBallsTrend.data']: redBallTrend.data,
                ['blueBallsTrend.headers']: blueBallTrend.headers,
                ['blueBallsTrend.data']: blueBallTrend.data
            });
        }, '红球/蓝球走势加载中！');
        console.log("===redBallTrend2===", JSON.stringify(redBallTrend));


        // 红球冷热
        let redHeatDataList = this.data.redBallsTrend.headers.filter(item => item.key != 'issue').map(item => ({
            number: item.number
        }));
        console.log("===redHeatDataList===", JSON.stringify(redBallTrend));

        // 按照号码分组map<number, [item]>
        let numberListMap = groupingBy(dataList.filter(item => item.color == 'red'), 'number');
        redHeatDataList.forEach(heatData => {
            let number = heatData.number;
            let numberList = numberListMap[number];
            let numberHitList = numberList.filter(item => item.hit);
            // 近50期出现次数
            heatData.nearCount = numberHitList.size();
            // 当前遗漏
            heatData.currentOmission = numberList[numberList.size() - 1].issue - numberHitList[heatData.nearCount].issue;
            // 平均遗漏
            heatData.avgOmission = Math.round((50 - heatData.nearCount) * 1.0 / heatData.nearCount);
        });

        this.setData({
            redBallHeat: redHeatDataList
        })
        console.log("===redBallHeat===", JSON.stringify(this.data.redBallHeat));

    }
});