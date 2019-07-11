queryUser();
queryOnlineNode();
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
        url: "/order/queryOrder",//向后台发送请求，后台为stuts2框架
        dataType: "json",
        data: {page:pageNum},
        cache: false,
        success: function(data) {
            let totalPages = data.totalPages;
            let currentPage = data.currentPage;
            let map = data.allOrderInfo;
            let htmlInfo = "";
            if(data.flag==0)
            {
                $('#allOrderInfo').html(htmlInfo);
                return;
            }else{
                for(let key in map){
                	let send = eval("("+map[key].orderInfoSend+")");
                	let receive = eval("("+map[key].orderInfoRecevie+")");
                    html += "<tr>" +
                        "<th scope=\"row\"><input type=\"checkbox\" /></th>"+
                        "<td class=\"tm-product-name\">"+map[key].orderId+"</td>"+
                        "<td>"+map[key].sendId+"</td>"+
                        "<td>"+send.sendNodeProduct+"</td>"+
                        "<td>"+receive.receiveNodeName+"</td>"+
                        "<td>"+receive.recevieNodeAddress+"</td>"+
                        "<td>"+map[key].orderInfoTime+"</td>"+
                        "<td>"+
                        "<a href='/order/addProduct?id="+map[key].orderId+"&sId="+map[key].sendId+"' class=\"tm-product-delete-link\">"+
                        "<i class=\"far fa-trash-alt tm-product-delete-icon\"></i>"+
                        "</a></td>"+
                        "</tr>";
                }
                $('#allOrderInfo').html(html);
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
//                        url: "/order/queryOrder",//向后台发送请求，后台为stuts2框架
//                        dataType: "json",
//                        data: {page:page},
//                        cache: false,
//                        success: function(data) {
//                            let totalPages = data.totalPages;
//                            let currentPage = data.currentPage;
//                            let map = data.allOrderInfo;
//                            let htmlInfo = "";
//                            if(data.flag==0)
//                            {
//                                $('#allOrderInfo').html(htmlInfo);
//                                return;
//
//                            }else{
//                                for(let key in map){
//                                	let send = eval("("+map[key].orderInfoSend+")");
//                                	let receive = eval("("+map[key].orderInfoRecevie+")");
//                                    html += "<tr>" +
//                                        "<th scope=\"row\"><input type=\"checkbox\" /></th>"+
//                                        "<td class=\"tm-product-name\">"+map[key].orderId+"</td>"+
//                                        "<td>"+map[key].sendId+"</td>"+
//                                        "<td>"+send.sendNodeProduct+"</td>"+
//                                        "<td>"+receive.receiveNodeName+"</td>"+
//                                        "<td>"+receive.recevieNodeAddress+"</td>"+
//                                        "<td>"+map[key].orderInfoTime+"</td>"+
//                                        "<td>"+
//                                        "<a th:href=\"@{/order/addProduct?id="+map[key].orderId+"}\" class=\"tm-product-delete-link\">"+
//                                        "<i class=\"far fa-trash-alt tm-product-delete-icon\"></i>"+
//                                        "</a></td>"+
//                                        "</tr>";
//                                }
//                                $('#allOrderInfo').html(html);
//                                let count = "共"+data.flag+"条数据";
//                                $('#totalPageSize').text(count);
//                            }
//                        }
//                    });
//                }
//            });
        }
    });
};

function queryOnlineNode(){
	let html = "";
	$.ajax({
        async: true,
        type: "post",
        url: "/order/getOnlineNode",//向后台发送请求，后台为stuts2框架
        dataType: "json",
        cache: false,
        success: function(data){
        	 for(let key in data){
                 html += "<tr>" +
                     "<td class=\"tm-product-name\">"+data[key].nodeId+"</td>"+
                     "<td class=\"tm-product-name\">"+data[key].nodeName+"</td>"+
                     "<td class=\"text-center\">"+
                     "<a href=\"#\" class=\"tm-product-delete-link\">"+
                         "<i class=\"far fa-trash-alt tm-product-delete-icon\"></i>"+
                     "</a>"+
                     "</td>"+
                     "</tr>";
             }
        	 $('#allNodeOnline').html(html);
        }
        });
}





