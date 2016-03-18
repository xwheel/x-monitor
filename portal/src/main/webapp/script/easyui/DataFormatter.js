/**
 * Created by leihang on 2015/11/23.
 */
//EasyUI通用格式化
var DataFormmatter = {

    //时间格式化：
    TimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        if (value == undefined) {
            return "";
        }
        return getTime(value, 'hh:mm:ss');
    },
    //时间格式化：
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        return getTime(value, 'yyyy-MM-dd hh:mm:ss');
    },

    //时间格式化：
    DateFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        return getTime(value, 'yyyy-MM-dd');
    }

};

function getTime(time, format) {
    var ts = time || 0;
    var t, y, m, d, h, i, s;
    t = ts ? new Date(ts) : new Date();
    y = t.getFullYear();
    m = t.getMonth() + 1;
    d = t.getDate();
    h = t.getHours();
    i = t.getMinutes();
    s = t.getSeconds();
    // 可根据需要在这里定义时间格式
    var returnValue = '';
    switch (format) {
        case 'yyyy-MM-dd':
        {
            returnValue = y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
            break;
        }
        case 'yyyy-MM-dd hh:mm:ss':
        {
            returnValue = y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
            break;
        }
        case 'hh:mm:ss':
        {
            returnValue = (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
            break;
        }
    }
    return returnValue;
}