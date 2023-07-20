// pages/receipts-and-payments/receipts-and-payments.js
const api = require("../../config/api.js");
// const interceptor = require("../../config/interceptor.js").loginInterceptor;
import {
    Base64
} from 'js-base64';
import {
    encode,
    decode
} from 'js-base64';
const app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        qrCodeUrl: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let url = api.qrCode.generate;
        wx.showLoading({
            title: '二维码生成中！'
        })
        app.authDownloadFile({
            url: url,
            that: this,
            data: {
                width: 400,
                height: 400,
                content: 'wxp://f2f0-aMBOGyCFEaYeuNojQLDbbx66-i2Kz6oXk9wJ_bjrog'
            },
            success: function(res) {
                console.log("this.url:" + JSON.stringify(this.that));
                this.that.setData({qrCodeUrl: res.tempFilePath});
                console.log("========" + JSON.stringify(this.that.data));
            },
            fail: res => console.log("===fail===" + JSON.stringify(res)),
            complete: res => wx.hideLoading()
        });

        console.log(api.rootUrl + url);
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
    onUnload() {},

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