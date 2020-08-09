$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/shopadmin/getshopmanagementinfo?shopId=' + shopId;
    $.getJSON(shopInfoUrl,function(data){
        if(data.redirect){  //有shop则返回true需要重定向到店铺列表页面
            window.location.href = data.url;
        } else {
            if(data.shopId != undefined && data.shopId != null){
                shopId = data.shopId;
            }
            // 商店信息管理
            $('#shopInfo').attr('href','/shopadmin/shopoperation?shopId=' + shopId);
        }
    });

});