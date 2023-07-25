const app = getApp();
const api = require('../../config/api.js')
const initAmountTrendChart = require('./amountTrend.js').initChart;
const initNumberTrendTable = require('./numberTrend.js').initTable;
const initNumberHeatChart = require('./numberHeat.js').initChart;

// 奖金走势
let amountTrendRes;
// 号码走势
let numberTrendRes;

/**
 * 请求数据
 */
function requestTrendData(url, title) {
    return new Promise((resolve, reject) => {
        wx.showLoading({
            title: title || '加载中',
            mask: true
        })
        console.log(url);
        app.authRequest({
            url,
            method: 'GET',
            success: res => {
                resolve(res);
            },
            fail: res => reject(res),
            complete: res => {
                wx.hideLoading();
                wx.stopPullDownRefresh();
            }
        })
    })
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
        let tabs = this.data.tabs.map(item => item.key);
        switch (e.currentTarget.dataset.selectedTab) {
            case tabs[0]:
                initAmountTrendChart(amountTrendRes, this)
                break;
            case tabs[1]:
                initNumberTrendTable(numberTrendRes, this)
                break;
            case tabs[2]:
                initNumberTrendTable(numberTrendRes, this)
                break;
            case tabs[3]:
                initNumberHeatChart(numberTrendRes, this)
                break;
            case tabs[4]:
                initNumberHeatChart(numberTrendRes, this)
                break;
            default:
                break;
        }
    },
    refreshData() {
        // 奖金走势图url
        let amountTrendUrl = api.lottery.historicalInformation.replace('{enName}', this.data.enName)
            .replace('{minIssueNumber}', '9999999').replace('{pageSize}', '7');
        // 彩票号码走势url
        let lotteryTrendUrl = api.lottery.trendUrl.replace('{enName}', this.data.enName).replace('{pageSize}', '50');

        // api请求数据，奖金走势结果赋值
        requestTrendData(amountTrendUrl)
            .then(res => amountTrendRes = res.data)
            .then(() => {
                // api请求数据，号码走势结果赋值
                requestTrendData(lotteryTrendUrl).then(res => numberTrendRes = res.data);
            })
            .then(() => {
                let e = {
                    currentTarget: {
                        dataset: {
                            selectedTab: this.data.selectedTab
                        }
                    }
                };
                this.switchTab(e);
            });
    },
    onLoad(option) {
        this.setData({
            enName: option.enName || 'LOTTO',
        })
    },
    onReady() {
        setTimeout(() => this.refreshData(), 100);
    },
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
        this.refreshData();
    },
});