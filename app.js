// app.js
const api = require("config/api.js");
App({
    onLaunch() {
        // const that = this;
        //     // 获取系统信息
        //     const systemInfo = wx.getSystemInfoSync();
        //     // 胶囊按钮位置信息
        //     const menuButtonInfo = wx.getMenuButtonBoundingClientRect();
        //     // 导航栏高度 = 状态栏高度 + 44
        //     that.globalData.navBarHeight = systemInfo.statusBarHeight + 44;
        //     that.globalData.menuRight = systemInfo.screenWidth - menuButtonInfo.right;
        //     that.globalData.menuBotton = menuButtonInfo.top - systemInfo.statusBarHeight;
        //     that.globalData.menuHeight = menuButtonInfo.height;
        var tokenId = wx.getStorageSync('tokenId')
        if (tokenId) {
            this.globalData.tokenId = tokenId;
        } else {
            let url = api.rootUrl + api.users.login;
            console.log("=====url:=====", url);
            wx.request({
                url,
                method: 'POST',
                data: {
                    'username': 'Admin',
                    'password': 'Admin'
                },
                success: res => {
                    console.log("=====登录成功!=====");
                    this.globalData.tokenId = res.header.tokenId;
                },
                fail: res => console.log("=====登录失败!====="),
                complete: res => console.log("=====登录complete!=====")
            })
        }
    },
    authRequest: function (options) {
        let header = options.header || {};
        header.tokenId = this.globalData.tokenId;
        wx.request({
            url: api.rootUrl + options.url,
            method: options.method,
            header: header,
            data: options.data,
            // that: options.that,
            success: res => {
                options.success(res);
                if (res.statusCode === 40100) {
                    // 未登录
                    wx.navigateTo({
                        url: '../pages/home/home'
                    })
                }
                if (res.header.tokenId) {
                    this.globalData.tokenId = res.header.tokenId;
                    wx.setStorageSync('tokenId', res.header.tokenId)
                }
            },
            fail: res => options.fail(res),
            complete: res => options.complete(res)
        })
    },
    authDownloadFile: function (options) {
        let header = options.header || {};
        header.tokenId = this.globalData.tokenId;
        let downloadUrl = api.rootUrl + options.url;
        if (options.data) {
            downloadUrl += "?";
            for (let key in options.data) {
                downloadUrl += key + "=" + options.data[key] + "&"
            }
            downloadUrl = downloadUrl.substring(0, downloadUrl.length - 1);
        }
        
        console.log("downloadUrl:" + downloadUrl);
        wx.downloadFile({
            url: downloadUrl,
            header: header,
            timeout: options.timeout,
            filePath: options.filePath,
            that: options.that,
            success: res => {
                options.success(res);
                if (res.statusCode === 40100) {
                    // 未登录
                    wx.navigateTo({
                        url: '../pages/home/home'
                    })
                }
                if (res.header.tokenId) {
                    this.globalData.tokenId = res.header.tokenId;
                    wx.setStorageSync('tokenId', res.header.tokenId)
                }
            },
            fail: res => options.fail(res),
            complete: res => options.complete(res)
        })
    },
    globalData: {
        tokenId: ''
    }
})