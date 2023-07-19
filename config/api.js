const rootUrl = 'https://www.loafer.online/api';

module.exports = {
    lotto: {
        index: rootUrl + '/lotto/index/{pageNum}/{pageSize}'
    },
    lottery: {
        lotteryInformation:  rootUrl + '/lottery/lotteryInformation',
        historicalInformation: rootUrl + '/lottery/historicalInformation/{enName}/{minIssueNumber}/{pageSize}'
    },
    twoColorBall: {

    },
    qrCode: {
        // generate: rootUrl.replace('api', 'loaferCore') + '/qrCode/generate'
        generate: 'http://localhost:8888/loaferCore/qrCode/generate'
    }
}