/**
 * Created by leihang on 2015/9/22.
 */

function goto(requestUrl,menuId){
    //更新当前菜单ID
    $.ajax({
        url: basePath+"/menu/setActiveMenuId",
        async: false,
        type: "POST",
        data: {
            menuId: menuId
        },
        success: function (data) {
            window.location.href = requestUrl;
        }
    });
}