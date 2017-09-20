tyre.authList = {
    
    init:function () {
        tyre.authList.initTree();

    },
    
    bindEvent:function () {
        $('#jstree_demo_div')
    },

    initTree:function () {
        $('#jstree_demo_div').jstree({
            plugins : ["contextmenu","checkbox","types","themes","state", ],
            "types": {
                "default" : {
                    "icon" : false  // ɾ��Ĭ��ͼ��
                },
            },
            "themes" : { "stripes" : true },  // ��������
            'core' : {
                'data' : {
                    "url" : "../json/authList.json",
                    "dataType" : "json"
                }
            },
            "contextmenu":{
                "items":{
                    "create":null,
                    "rename":null,
                    "remove":null,
                    "ccp":null,
                    "新建":{
                        "label":"新建",
                        "action":function(data){
                            var inst = jQuery.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            dialog.show({"title":"新建"+obj.text+"的子菜单",url:"/accountmanage/createMenu?id="+obj.id,height:280,width:400});
                        }
                    },
                    "删除":{
                        "label":"删除",
                        "action":function(data){
                            var inst = jQuery.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            if(confirm("确定要删除此菜单？删除后不可恢复。")){
                                jQuery.get("/accountmanage/deleteMenu?id="+obj.id,function(dat){
                                    if(dat == 1){
                                        alert("删除菜单成功！");
                                        jQuery("#"+treeid).jstree("refresh");
                                    }else{
                                        alert("删除菜单失败！");
                                    }
                                });
                            }
                        }
                    },
                    "编辑":{
                        "label":"编辑",
                        "action":function(data){
                            var inst = jQuery.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            dialog.show({"title":"编辑“"+obj.text+"”菜单",url:"/accountmanage/editMenu?id="+obj.id,height:280,width:400});
                        }
                    }
                }
            }

        });
    }

};

$(function () {
    tyre.authList.init();
})