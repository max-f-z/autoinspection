tyre.orderList = {

    init:function () {
        pageLoadingFrame("show");
        tyre.orderList.doOrderList();
        //ģ�����
        setTimeout(function(){
            pageLoadingFrame("hide");
        },1000);
    },

    bindEvent:function () {

    },
    
    doOrderList:function () {

    }

};

$(function(){
    tyre.orderList.init();
});