// pages/home/home.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        miniApps: [{
            id: 1,
            name: '彩票',
            icon: "../../images/lottery-avatar.png",
            url: "../../pages/lottery-information/lottery-information"
        }]
    },
    goToLottery() {
        wx.navigateTo({
          url: '/pages/lottery-information/lottery-information'
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var _miniAppList = this.data.miniApps;
        for (var i = 0; i < 13; i++) {
            _miniAppList.push(_miniAppList[0]);
        }
        _miniAppList.push({
            id: 1,
            name: '更多',
            icon: "../../images/lottery-avatar.png",
            url: "../../pages/lottery-information/lottery-information"
        });
        this.setData({
            'miniApps': _miniAppList
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