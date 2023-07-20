// pages/historical-information/historical-information.js
const api = require("../../config/api.js")
const interceptor = require("../../config/interceptor.js").loginInterceptor;
const app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        pageNum: 1,
        pageSize: 10,
        name: "双色球",
        enName: "TWO_COLOR_BALL",
        minIssueNumber: 99999999,
        infoList: [{
            issueNumber: '2023079',
            awardDate: '07.11',
            week: '昨天',
            bonusPool: 20.47,
            redBalls: ['05', '06', '09', '12', '21', '22'],
            blueBalls: ['07'],
            saleVolume: 3.52,
            accumulatedPrizePool: 20.47,
            expand: true,
            detailList: [{
                level: '一等奖',
                winningBetsNum: 44,
                singleBetBonus: 5140313
            }, {
                level: '二等奖',
                winningBetsNum: 219,
                singleBetBonus: 35238
            }, {
                level: '三等奖',
                winningBetsNum: 3330,
                singleBetBonus: 3000
            }, {
                level: '四等奖',
                winningBetsNum: 135991,
                singleBetBonus: 200
            }, {
                level: '五等奖',
                winningBetsNum: 2080932,
                singleBetBonus: 10
            }, {
                level: '六等奖',
                winningBetsNum: 16810255,
                singleBetBonus: 5
            }]
        }]
    },
    expand(e) {
        var index = e.currentTarget.dataset.index; /*  */
        var expand = !this.data.infoList[index].expand;
        var itemExpand = "infoList[" + index + "].expand";
        this.setData({
            [itemExpand]: expand
        })
    },

    /**
     * 刷新页面数据
     */
    refresh(lambda, title) {
        wx.showLoading({
            title: title || '加载中',
            mask: true
        })

        app.authRequest({
            url: api.lottery.historicalInformation.replace('{enName}', this.data.enName)
            .replace('{minIssueNumber}', this.data.minIssueNumber).replace('{pageSize}', this.data.pageSize),
            method: 'GET',
            interceptor: interceptor,
            success: res => {
                lambda(res);
                this.setData({
                    minIssueNumber: this.data.infoList[this.data.infoList.length - 1].issueNumber
                })
            },
            complete: res => {
                wx.hideLoading();
                wx.stopPullDownRefresh();
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.setData({
            ['name']: options.name,
            ['enName']: options.enName
        })
        wx.setNavigationBarTitle({
            title: options.name,
        })

        this.refresh(res => {
            var _infoList = res.data.map(item => {
                item['expand'] = false;
                return item;
            })
            _infoList[0].expand = true;
            this.setData({
                infoList: _infoList
            })
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {
        this.refresh(res => {
            var _infoList = res.data.map(item => {
                item['expand'] = false;
                return item;
            })
            this.setData({
                infoList: this.data.infoList.concat(_infoList)
            })
        });
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})