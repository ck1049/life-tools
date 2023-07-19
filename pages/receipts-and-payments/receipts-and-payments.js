// pages/receipts-and-payments/receipts-and-payments.js
const api = require("../../config/api.js");
const base64 = require('../../utils/base64').Base64;

Page({

    /**
     * 页面的初始数据
     */
    data: {
        qrTxt: 'https://github.com/liuxdi/wx-qr',
        qrCodeUrl: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let url = api.qrCode.generate;
        console.log(url);
        wx.showLoading({
            title: '二维码生成中！',
        })
        wx.request({
            url,
            method: 'POST',
            header: {
                'tokenId': 'c89291aee03cf335103cd08ea9f1c4f0'
            },
            data: {
                'width': '100',
                'height': '100',
                'ratio': 4,
                'content': 'https://www.loafer.online/loaferCore/doc.html'
            },
            success: res => {
                // 将arraybuffer转换成Base64格式
                // const base64Data = wx.arrayBufferToBase64(res.data);
                var base64Data = base64.encode(res.data);
                let filePath = `${wx.env.USER_DATA_PATH}/qrCode.png`;
                console.log(filePath);
                console.log(base64Data);
                // 将Base64格式的图片数据转换成临时文件路径
                wx.getFileSystemManager().writeFile({
                    filePath,
                    data: base64Data,
                    encoding: 'base64',
                    success: (res) => {
                        console.log(res);
                        //在前端使用image标签展示图片
                        this.setData({
                            imageUrl: filePath
                        });
                    },
                    fail: (error) => {
                        console.error('转换图片失败：', error);
                    }
                });
                console.log('base64Data===', this.data.qrCodeUrl);
            },
            fail: res => console.log("=====请求失败！====="),
            complete: res => wx.hideLoading()
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