/**
 * Created by leihang on 2015/9/15.
 * 公共的js文件
 */


function downTemplate2(templateName) {
    window.location.href = '${basePath}/common/templateDown?templateFileName=' + templateName;
}

/**
 * 文件导出公用方法
 * @param {} url 导出功能对应的后台url请求地址
 * @param {} data 必须为json格式
 * @param {} method post/get ，默认为post
 * @param {} templateFileName 模块文件路径下，导出模板名称
 */
function downFile(url, data, method, templateFileName) {    // 获得url和data
    if (url && data) {
        var inputs = '';
        for (var key in data) {
            inputs += "<input type='hidden' name=" + key + " value='" + data[key] + "' />'"
        }
        if (templateFileName) {
            inputs += "<input type='hidden' name='templateFileName' value='" + templateFileName + "' />'"
        }
        // request发送请求
        $('<form action="' + url + '" method="' + (method || 'post') + '">' + inputs + '</form>')
            .appendTo('body').submit().remove();
    }
    ;
};


//将表form对象转换为json，用于查询页面提交查询条件。
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


