// pages/lottery-information/lottery-information.js
// 获取应用实例
const app = getApp()

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
    }]
  },
  forward(e) {
      var enName = e.currentTarget.dataset.enName;
      wx.navigateTo({url: '/pages/logs/logs'})
  },
  onLoad() {
  }
})
