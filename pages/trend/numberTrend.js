const groupingBy = require('../../utils/util').groupingBy;

// 将trend接口返回的数据转成表格数据格式
function numberListToIssueList(resList, color) {
    const backgroundColor = {
        red: 'rgb(250, 98, 98)',
        blue: 'dodgerblue'
    };
    // 返回map<issue, [item]>
    let issueListMap = groupingBy(resList.filter(item => item.color == color), 'issue');

    // 红球趋势表头
    let ballHeaders = [{
        title: '期次',
        key: 'issue'
    }];

    let numberList = issueListMap[Object.keys(issueListMap)[0]].map(item => ({
        'title': item.number,
        'key': item.number
    }));

    ballHeaders = [...ballHeaders, ...numberList];
    // 表格数据内容
    let data = [];

    Object.keys(issueListMap).forEach(key => {
        const itemList = issueListMap[key];
        const dataItem = {};
        dataItem['issue'] = {
            value: key.substring(2) + '期',
            style: null
        };
        for (let i = 0; i < itemList.length; i++) {
            const numObj = itemList[i];
            let intervalCount = numObj.intervalCount || numObj.number;
            let style = numObj.intervalCount ? null : "color: white;background-color: " + backgroundColor[color];
            dataItem[numObj.number] = {
                value: intervalCount,
                style: style
            }
        }
        data.push(dataItem);
    });

    return {
        'headers': ballHeaders,
        'data': data
    };
}

function initTable(dataList, that) {
    let redBallTrend = numberListToIssueList(dataList, 'red');
    let blueBallTrend = numberListToIssueList(dataList, 'blue');
    that.setData({
        ['redBallsTrend.headers']: redBallTrend.headers,
        ['redBallsTrend.data']: redBallTrend.data,
        ['blueBallsTrend.headers']: blueBallTrend.headers,
        ['blueBallsTrend.data']: blueBallTrend.data
    });
}

module.exports = {
    initTable,
    numberListToIssueList
}