const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

const groupingBy = (array, key) => {
    return array.reduce((result, item) => {
        let __key = item[key];
        if (!result[__key]) {
            result[__key] = [];
        }
        result[__key].push(item);
        return result;
    }, {})
}

module.exports = {
  formatTime,
  groupingBy
}
