let echarts = require('../ec-canvas/echarts.min.js');

const app = getApp();
const api = require('../../config/api.js')

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
            headers: [{
                    title: 'Name',
                    key: 'name',
                    // width: 100,
                    fixed: 'left'
                },
                {
                    title: 'Age',
                    key: 'age',
                    // width: 100
                },
                {
                    title: 'Address',
                    key: 'address',
                    // width: 200
                },
                {
                    title: 'Province',
                    key: 'province',
                    // width: 100
                },
                {
                    title: 'City',
                    key: 'city',
                    // width: 100
                },
                {
                    title: 'Postcode',
                    key: 'zip',
                    // width: 100
                }
            ],
            data: [{
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    address: 'Washington, D.C. No. 1 Lake Park',
                    province: 'America',
                    city: 'Washington, D.C.',
                    zip: 100000
                },
                {
                    name: 'Joe Black',
                    age: 30,
                    address: 'Sydney No. 1 Lake Park',
                    province: 'Australian',
                    city: 'Sydney',
                    zip: 100000
                },
                {
                    name: 'Jon Snow',
                    age: 26,
                    address: 'Ottawa No. 2 Lake Park',
                    province: 'Canada',
                    city: 'Ottawa',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    address: 'Washington, D.C. No. 1 Lake Park',
                    province: 'America',
                    city: 'Washington, D.C.',
                    zip: 100000
                },
                {
                    name: 'Joe Black',
                    age: 30,
                    address: 'Sydney No. 1 Lake Park',
                    province: 'Australian',
                    city: 'Sydney',
                    zip: 100000
                },
                {
                    name: 'Jon Snow',
                    age: 26,
                    address: 'Ottawa No. 2 Lake Park',
                    province: 'Canada',
                    city: 'Ottawa',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    address: 'Washington, D.C. No. 1 Lake Park',
                    province: 'America',
                    city: 'Washington, D.C.',
                    zip: 100000
                },
                {
                    name: 'Joe Black',
                    age: 30,
                    address: 'Sydney No. 1 Lake Park',
                    province: 'Australian',
                    city: 'Sydney',
                    zip: 100000
                },
                {
                    name: 'Jon Snow',
                    age: 26,
                    address: 'Ottawa No. 2 Lake Park',
                    province: 'Canada',
                    city: 'Ottawa',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    address: 'Washington, D.C. No. 1 Lake Park',
                    province: 'America',
                    city: 'Washington, D.C.',
                    zip: 100000
                },
                {
                    name: 'Joe Black',
                    age: 30,
                    address: 'Sydney No. 1 Lake Park',
                    province: 'Australian',
                    city: 'Sydney',
                    zip: 100000
                },
                {
                    name: 'Jon Snow',
                    age: 26,
                    address: 'Ottawa No. 2 Lake Park',
                    province: 'Canada',
                    city: 'Ottawa',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                }
            ]
        },
        selectedTab: 'amountTrend'
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
     * 刷新页面数据
     */
    requestAmountTrend(url, lambda, title) {
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
        this.requestAmountTrend(amountTrendUrl, res => {
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


        // 彩票号码走势
        let lotteryTrendUrl = '';
        // 红球冷热走势
        
    }
});