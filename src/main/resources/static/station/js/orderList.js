tyre.orderList = {

    init:function () {
        pageLoadingFrame("show");
        tyre.orderList.doOrderList();
        //Ä£Äâ¼ÓÔØ
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