const rootUrl = 'https://www.loafer.online'; // prod
// const rootUrl = 'http://127.0.0.1:8888'; // loafer-core
// const rootUrl = 'http://127.0.0.1:8090'; // lottery

module.exports = {
    rootUrl,
    lotto: {
        index: '/lottery/lotto/index/{pageNum}/{pageSize}'
    },
    lottery: {
        lotteryInformation: '/lottery/lottery/lotteryInformation',
        historicalInformation: '/lottery/lottery/historicalInformation/{enName}/{minIssueNumber}/{pageSize}',
        trendUrl: '/lottery/lottery/trend/{enName}/{pageSize}'
    },
    twoColorBall: {

    },
    users: {
        login: '/loaferCore/user/login'
    },
    qrCode: {
        // generate: rootUrl.replace('api', 'loaferCore') + '/qrCode/generate'
        generate: '/loaferCore/qrCode/generate'
    }
}