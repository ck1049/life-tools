// pages/lottery-information/lottery-information.js
// 获取应用实例
const app = getApp()
const api = require('../../config/api.js')

Page({
    data: {
        overviewList: [{
                icon: '../../images/twoColorBallIcon.png',
                name: '双色球',
                enName: 'twoColorBall',
                issueNumber: '2023079',
                awardDate: '07.11',
                week: '昨天',
                bonusPool: 20.47,
                redBalls: ['05', '06', '09', '12', '21', '22'],
                blueBalls: ['07'],
                trendIcon: '../../images/twoColorBallIcon.png',
                awardsToolIcon: '../../images/twoColorBallIcon.png'
            },
            {
                icon: '../../images/twoColorBallIcon.png',
                name: '超级大乐透',
                enName: 'lotto',
                issueNumber: '23078',
                awardDate: '07.10',
                week: '周一',
                bonusPool: 8.46,
                redBalls: ['02', '03', '06', '26', '27'],
                blueBalls: ['07', '08'],
                trendIcon: '../../images/twoColorBallIcon.png',
                awardsToolIcon: '../../images/twoColorBallIcon.png'
            },
            {
                icon: '../../images/twoColorBallIcon.png',
                name: '福彩3D',
                enName: 'luckyColor3D',
                issueNumber: '2023183',
                awardDate: '07.12',
                week: '今天',
                redBalls: ['0', '6', '6'],
                trendIcon: '../../images/twoColorBallIcon.png'
            }
        ]
    },
    forward(e) {
        var enName = e.currentTarget.dataset.enName;
        wx.navigateTo({
            url: '/pages/historical-information/historical-information'
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

        wx.request({
          url: api.lottery.lotteryInformation,
          method: 'GET',
          success: res => {
              if (lambda) {
                  lambda();
              } else {
                  this.setData({
                    overviewList: res.data.map(item => {
                        item.trendIcon = "../../images/twoColorBallIcon.png";
                        item.awardsToolIcon = "../../images/twoColorBallIcon.png";
                        return item
                      })
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