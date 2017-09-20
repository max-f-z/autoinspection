tyre.motorList = {

    init:function () {
        pageLoadingFrame("show");
        tyre.motorList.doMotorList();
        setTimeout(function(){
            pageLoadingFrame("hide");
        },1000);
    },

    binEvent:function () {

    },

    doMotorList:function () {

    }
};

$(function(){
    tyre.motorList.init();
});