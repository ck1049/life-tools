// components/table2/table2.js
Component({
     /**
     * 组件的属性列表
     */
    options: {
        styleIsolation: 'apply-shared'
    },
    properties: {
        headers: {
            type: Array,
            value: []
        },
        data: {
            type: Array,
            value: []
        },
        headerTap: { // header的点击事件
            type: Function,
            value: null
        }
    },

    /**
     * 组件的初始数据
     */
    data: {
    },
    /**
     * 组件的生命周期函数
     */
    lifetimes: {
        attached() {
            // 在组件渲染前执行的操作
            console.log('组件被附加到页面节点树，执行初始化操作');
            // 可以在这里调用其他方法或进行其他初始化操作
            console.log(this.properties);
        },

        // 其他生命周期函数（可选）
        created() {},
        ready() {
        },
        detached() {},
    },

    /**
     * 组件的方法列表
     */
    methods: {

    }
})
