const rootUrl = 'https://www.loafer.online';
// const rootUrl = 'http://127.0.0.1:8888';
// const rootUrl = 'http://127.0.0.1:8888';

module.exports = {
    rootUrl,
    lotto: {
        index: '/api/lotto/index/{pageNum}/{pageSize}'
    },
    lottery: {
        lotteryInformation: '/api/lottery/lotteryInformation',
        historicalInformation: '/api/lottery/historicalInformation/{enName}/{minIssueNumber}/{pageSize}'
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