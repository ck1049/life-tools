const api = require("./api.js");
const app = getApp();
const loginInterceptor = {
    config: {
      baseURL: api.rootUrl, // 接口的基础路径
      header: {
        // 'Content-Type': 'application/json',
        'tokenId': app.globalData.tokenId
      }
    },
    request: (req) => {
        console.log("====loginInterceptor url:=====", req.url);
      // 拦截请求并修改配置
    //   req.url += '?token=12345'
      return req
    },
    response: (res) => {
      // 拦截响应并处理结果
      if (res.statusCode === 40100) {
        wx.navigateTo({url: '../pages/home/home'})
      }
      if (res.header.tokenId) {
        app.globalData.tokenId = res.header.tokenId;
        wx.setStorageSync('tokenId', res.header.tokenId)
      }
      return res
    }
  }

  module.exports = {
    loginInterceptor: loginInterceptor
  }