tyre.roleList = {

    init:function () {
        pageLoadingFrame("show");
        tyre.roleList.doRoleList();
        setTimeout(function(){
            pageLoadingFrame("hide");
        },1000);
    },

    binEvent:function () {

    },

    doRoleList:function () {

    }
};

$(function(){
    tyre.roleList.init();
});