queryUser();
function  decrypt(val){
    //alert(val);
        $.ajax({
            async: true,
            type: "post",
            url: "/System/OrderDecrypt",
            dataType: "json",
            data: {deCrypt:val},
            cache: false,
            success: function (data) {
                //解密成功后,返回值
                alert("111");
                alert(data.decrypt);
            },
            error:function (e) {
                alert("错误编码:"+e.errorCode);
            }
        });
};
function confirm(val){
    $.ajax({
        async: true,
        type: "post",
        url: "/System/OrderConfirm",
        dataType: "json",
        data: {deCrypt:val},
        cache: false,
        success: function (data) {
            //解密成功后,返回值
            alert(data);
            alert("订单确认成功");
        }
    });
};
$(document).ready(function(){

    // $("#decrypt").click(function () {
    //     alert("订单解密");

    // });
    $("#confirm").click(function () {
        alert("区块生成");
    });
    $("#broadcast").click(function () {
        alert("区块广播");
    });
});
function queryUser(){
    let pageNum = "1";
    let html = "";
    $.ajax({
        async: true,
        type: "post",
        url: "/order/queryAllNodeInfo",//向后台发送请求，后台为stuts2框架
        dataType: "json",
        data: {page:pageNum},
        cache: false,
        success: function(data) {
            let totalPages = data.totalPages;
            let currentPage = data.currentPage;
            let map = data.allNodeInfo;
            let htmlInfo = "";
            if(data.flag==0)
            {
                $('#allNodeInfo').html(htmlInfo);

            }else{
                for(let key in map){
                    html += "<tr>" +
                        "<th scope=\"row\"><b>"+map[key].nodeId+"</b></th>";
                    if(map[key].nodeState == 0)
                    {
                    	html +=	"<td><div class=\"tm-status-circle moving\"></div>正常</td>";
                    }
                    else if(map[key].nodeState == 1)
                    {
                    	html += "<td><div class=\"tm-status-circle pending\"></div>活跃</td>";
                    }
                    else if(map[key].nodeState == 2)
                    {
                    	html += "<td><div class=\"tm-status-circle cancelled\"></div>冻结</td>";
                    }
                    html += "<td><b>"+map[key].nodeName+"</b></td>"+
                        "<td><b>"+map[key].nodeSex+"</b></td>"+
                        "<td>"+map[key].nodeEmail+"</td>";
                    if(map[key].nodeCompetence == 0)    
                    {
                    	html +=	"<td>超级节点</td>";
                    }
                    else if(map[key].nodeCompetence == 1)
                    {
                    	html +=	"<td>交易节点</td>";
                    }
                    html +=	  "</tr>";
                }
                $('#allNodeInfo').html(html);
                let count = "共"+data.flag+"条数据";
                $('#totalPageSize').text(count);
            }
//            $('.pagination').bootstrapPaginator({
//                currentPage: currentPage,
//                totalPages: totalPages,
//                size:"normal",
//                bootstrapMajorVersion: 3,//bootstrap的版本要求。
//                alignment:"right",
//                numberOfPages:5,
//                itemTexts: function (type, page, current) {
//                    switch (type) {
//                        case "first": return "首页";
//                        case "prev": return "上一页";
//                        case "next": return "下一页";
//                        case "last": return "末页";
//                        case "page": return page;
//                    }
//                },
//                onPageClicked:function (e,originalEvent,type,page) {
//                    html="";
//                    $.ajax({
//                        async: true,
//                        type: "post",
//                        url: "/order/queryAllNodeInfo",//向后台发送请求，后台为stuts2框架
//                        dataType: "json",
//                        data: {page:page},
//                        cache: false,
//                        success: function(data) {
//                            let totalPages = data.totalPages;
//                            let currentPage = data.currentPage;
//                            let map = data.allNodeInfo;
//                            let htmlInfo = "";
//                            if(data.flag==0)
//                            {
//                                $('#allNodeInfo').html(htmlInfo);
//
//                            }else{
//                                for(let key in map){
//                                	html += "<tr>" +
//                                    "<th scope=\"row\"><b>"+map[key].nodeId+"</b></th>"+
//                                    "<td><div class=\"tm-status-circle moving\"></div>Moving<b>"+map[key].nodeState+"</b</td>"+
//                                    "<td><b>"+map[key].nodeName+"</b></td>"+
//                                    "<td><b>"+map[key].nodeSex+"</b></td>"+
//                                    "<td>"+map[key].nodeEmail+"</td>"+
//                                    "<td>"+map[key].nodeCompetence+"</td>"+
//                                    "</tr>";
//                                }
//                                $('#allNodeInfo').html(html);
//                                let count = "共"+data.flag+"条数据";
//                                $('#totalPageSize').text(count);
//                            }
//                        }
//                    });
//                }
//            });
        }
    });
}





