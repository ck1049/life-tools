// components/table/table.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        headerFontSize: { // 标题行字体大小
            type: Number,
            value: 30 // 默认30rpx
        },
        columnFontSize: { // 内容单元格字体大小
            type: Number,
            value: 25 // 默认30rpx
        },
        headers: {
            type: Array,
            value: []
        },
        data: {
            type: Array,
            value: []
        },
        width: {
            type: Number,
            value: 0
        },
        height: {
            type: Number,
            value: 0
        },
        border: {
            type: Boolean,
            value: false
        }
    },

    /**
     * 组件的初始数据
     */
    data: {
        scrollX: false,
        scrollY: false,
        tdWidths: {}
    },
    /**
     * 组件的生命周期函数
     */
    lifetimes: {
        attached() {
            // 在组件渲染前执行的操作
            console.log('组件被附加到页面节点树，执行初始化操作');
            // 可以在这里调用其他方法或进行其他初始化操作
        },

        // 其他生命周期函数（可选）
        created() {},
        ready() {
            this.calculateTdWidths();
        },
        detached() {},
    },

    /**
     * 组件的方法列表
     */
    methods: {
        calculateTdWidths() {
            // 计算每列的宽度
            // 这里需要根据实际数据计算宽度，可以通过遍历数据获取每列的最大宽度
            let tdWidths = {};
            const headersLength = this.properties.headers.length;
            for (var i = 0; i < headersLength; i++) {
                var headerInfo = this.properties.headers[i];
                if (headerInfo.width) {
                    // 列信息设置了width
                    tdWidths[headerInfo.key] = headerInfo.width;
                    continue;
                }
                // 根据内容自适应
                // 标题单元格宽度
                var headerWidth = this.properties.headers[i].title.length * this.properties.headerFontSize;
                // 该列所有单元格宽度
                var contentLengthArray = this.properties.data.map(item => ('' + item[headerInfo.key]).length);
                console.log("====contentLengthArray====" + contentLengthArray);
                // 内容单元格宽度
                var contentWidth = Math.max(...contentLengthArray) * this.properties.columnFontSize;
                tdWidths[headerInfo.key] = Math.max(headerWidth, contentWidth);
            }
            console.log("====列宽====", tdWidths);

            this.setData({
                tdWidths,
                scrollX: true, // 设置为可水平滚动
                scrollY: true, // 设置为可垂直滚动
            });
        }
    }
})