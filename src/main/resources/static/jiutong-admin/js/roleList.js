tyre.roleList = {

    init:function () {
        pageLoadingFrame("show");
        tyre.roleList.doRoleList();
        //Ä£Äâ¼ÓÔØ
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