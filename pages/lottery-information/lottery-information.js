// pages/lottery-information/lottery-information.js
// 获取应用实例
const app = getApp()
const api = require('../../config/api.js')

Page({
    data: {
        overviewList: []
    },
    forward(e) {
        var name = e.currentTarget.dataset.name;
        var enName = e.currentTarget.dataset.enName;
        wx.navigateTo({
            url: '/pages/historical-information/historical-information?name=' + name + "&enName=" + enName
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

        console.log(api.lottery.lotteryInformation);
        wx.request({
          url: api.lottery.lotteryInformation,
          method: 'GET',
          success: res => {
              if (lambda) {
                  console.log("lambda<>null");
                  lambda();
              } else {
                var _overviewList = res.data.map(item => {
                    item.trendIcon = "../../images/twoColorBallIcon.png";
                    item.awardsToolIcon = "../../images/twoColorBallIcon.png";
                    return item
                  });
                  this.setData({
                    overviewList: _overviewList
                  })
              }
              
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
        this.refresh();
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
        this.refresh();
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