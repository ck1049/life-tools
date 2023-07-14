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

    }
}