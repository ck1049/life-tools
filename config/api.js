const rootUrl = 'https://www.loafer.online';
// const rootUrl = 'http://127.0.0.1:8888';
// const rootUrl = 'http://127.0.0.1:8888';

module.exports = {
    rootUrl,
    lotto: {
        index: '/lottery/lotto/index/{pageNum}/{pageSize}'
    },
    lottery: {
        lotteryInformation: '/lottery/lottery/lotteryInformation',
        historicalInformation: '/lottery/lottery/historicalInformation/{enName}/{minIssueNumber}/{pageSize}',
        trendUrl: '/lottery/lottery/trend?enName={enName}'
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