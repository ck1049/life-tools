// pages/historical-information/historical-information.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        lotteryType: "双色球",
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
            },{
                level: '二等奖',
                winningBetsNum: 219,
                singleBetBonus: 35238
            },{
                level: '三等奖',
                winningBetsNum: 3330,
                singleBetBonus: 3000
            },{
                level: '四等奖',
                winningBetsNum: 135991,
                singleBetBonus: 200
            },{
                level: '五等奖',
                winningBetsNum: 2080932,
                singleBetBonus: 10
            },{
                level: '六等奖',
                winningBetsNum: 16810255,
                singleBetBonus: 5
            }]
        },
        {
            issueNumber: '2023079',
            awardDate: '07.11',
            week: '昨天',
            bonusPool: 20.47,
            redBalls: ['05', '06', '09', '12', '21', '22'],
            blueBalls: ['07'],
            saleVolume: 3.52,
            accumulatedPrizePool: 20.47,
            expand: false,
            detailList: [{
                level: '一等奖',
                winningBetsNum: 44,
                singleBetBonus: 5140313
            },{
                level: '二等奖',
                winningBetsNum: 219,
                singleBetBonus: 35238
            },{
                level: '三等奖',
                winningBetsNum: 3330,
                singleBetBonus: 3000
            },{
                level: '四等奖',
                winningBetsNum: 135991,
                singleBetBonus: 200
            },{
                level: '五等奖',
                winningBetsNum: 2080932,
                singleBetBonus: 10
            },{
                level: '六等奖',
                winningBetsNum: 16810255,
                singleBetBonus: 5
            }]
        }]
    },
    expand(e) {
        var index = e.currentTarget.dataset.index;/*  */
        var expand = !this.data.infoList[index].expand;
        var itemExpand = "infoList[" + index + "].expand";
        this.setData({
            [itemExpand]: expand
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        wx.setNavigationBarTitle({
          title: this.data.lotteryType,
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
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

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

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})