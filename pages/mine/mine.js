// pages/mine/mine.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        redBallsTrend: {
            headers: [{
                    title: 'Name',
                    key: 'name',
                    // width: 100,
                    fixed: 'left'
                },
                {
                    title: 'Age',
                    key: 'age',
                    // width: 100
                },
                {
                    title: 'Address',
                    key: 'address',
                    // width: 200
                },
                {
                    title: 'Province',
                    key: 'province',
                    // width: 100
                },
                {
                    title: 'City',
                    key: 'city',
                    // width: 100
                },
                {
                    title: 'Postcode',
                    key: 'zip',
                    // width: 100
                }
            ],
            data: [{
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'Jim Green',
                age: 24,
                address: 'Washington, D.C. No. 1 Lake Park',
                province: 'America',
                city: 'Washington, D.C.',
                zip: 100000
            },
            {
                name: 'Joe Black',
                age: 30,
                address: 'Sydney No. 1 Lake Park',
                province: 'Australian',
                city: 'Sydney',
                zip: 100000
            },
            {
                name: 'Jon Snow',
                age: 26,
                address: 'Ottawa No. 2 Lake Park',
                province: 'Canada',
                city: 'Ottawa',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'Jim Green',
                age: 24,
                address: 'Washington, D.C. No. 1 Lake Park',
                province: 'America',
                city: 'Washington, D.C.',
                zip: 100000
            },
            {
                name: 'Joe Black',
                age: 30,
                address: 'Sydney No. 1 Lake Park',
                province: 'Australian',
                city: 'Sydney',
                zip: 100000
            },
            {
                name: 'Jon Snow',
                age: 26,
                address: 'Ottawa No. 2 Lake Park',
                province: 'Canada',
                city: 'Ottawa',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'Jim Green',
                age: 24,
                address: 'Washington, D.C. No. 1 Lake Park',
                province: 'America',
                city: 'Washington, D.C.',
                zip: 100000
            },
            {
                name: 'Joe Black',
                age: 30,
                address: 'Sydney No. 1 Lake Park',
                province: 'Australian',
                city: 'Sydney',
                zip: 100000
            },
            {
                name: 'Jon Snow',
                age: 26,
                address: 'Ottawa No. 2 Lake Park',
                province: 'Canada',
                city: 'Ottawa',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                name: 'Jim Green',
                age: 24,
                address: 'Washington, D.C. No. 1 Lake Park',
                province: 'America',
                city: 'Washington, D.C.',
                zip: 100000
            },
            {
                name: 'Joe Black',
                age: 30,
                address: 'Sydney No. 1 Lake Park',
                province: 'Australian',
                city: 'Sydney',
                zip: 100000
            },
            {
                name: 'Jon Snow',
                age: 26,
                address: 'Ottawa No. 2 Lake Park',
                province: 'Canada',
                city: 'Ottawa',
                zip: 100000
            },
            {
                name: 'John Brown',
                age: 18,
                address: 'New York No. 1 Lake Park',
                province: 'America',
                city: 'New York',
                zip: 100000
            },
            {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    address: 'Washington, D.C. No. 1 Lake Park',
                    province: 'America',
                    city: 'Washington, D.C.',
                    zip: 100000
                },
                {
                    name: 'Joe Black',
                    age: 30,
                    address: 'Sydney No. 1 Lake Park',
                    province: 'Australian',
                    city: 'Sydney',
                    zip: 100000
                },
                {
                    name: 'Jon Snow',
                    age: 26,
                    address: 'Ottawa No. 2 Lake Park',
                    province: 'Canada',
                    city: 'Ottawa',
                    zip: 100000
                },
                {
                    name: 'John Brown',
                    age: 18,
                    address: 'New York No. 1 Lake Park',
                    province: 'America',
                    city: 'New York',
                    zip: 100000
                }
            ]
        },
        tableHeight: (20 + 1) * 96,
        tableWidth: 200 * 6 + 60,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

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