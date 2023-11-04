// components/table/table.js
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
        headerTap(e) {
            var detail = { selectedHeader: e.currentTarget.dataset.key };
            var options = {
                bubbles: false, // 事件是否冒泡
                composed: false, // 事件是否可以穿越组件边界，为false时，事件将只能在引用组件的节点树上触发，不进入其他任何组件内部
                capturePhase: false // 事件是否拥有捕获阶段
            }
            this.triggerEvent('headerTapEvent', detail, options);
        }
    }
})