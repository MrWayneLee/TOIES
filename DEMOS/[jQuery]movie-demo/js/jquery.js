$(document).ready(function(){
    //导航栏部分
    $(".nav > ul > li").hover(function(){
        $(this).children("ul").stop().slideDown()
    }).mouseleave(function(){
        $(this).children("ul").stop().slideUp()
    })
    //广告栏部分
    adswitch = 0
    adactiontime = 1000
    $(".adswitch").stop().animate({bottom:"520px"},adactiontime)
    $(".ad").stop().animate({bottom:"10px"},adactiontime)
    $(".adswitch").click(function(){
        if(adswitch == 0){
            $(this).stop().animate({bottom:"10px"},adactiontime).children("img").attr("src","images/arrows/arrow_up_black.png")
            $(".ad").stop().animate({bottom:"-510px"},adactiontime)
            adswitch = 1
        }else{
            $(this).stop().animate({bottom:"520px"},adactiontime).children("img").attr("src","images/arrows/arrow_down_black.png")
            $(".ad").stop().animate({bottom:"10px"},adactiontime)
            adswitch = 0
        }
    })
    //轮播图部分
    imgNum = $(".imglist img").length
    for(i = 0; i < imgNum; i ++){
        $(".controllist ul").append("<li></li>")
        $(".controlimglist ul").append("<li><a><img src='" + $(".imglist img").eq(i).attr("src") + "'/></a></li>")
    }
    $(".controlimglist li").css({
        "width" : $(".controlimglist ul").width() / imgNum
    })
    $(".controllist ul li").first().addClass("current")
    $(".controlimglist ul li").first().addClass("current")
    $(".controllist").css({
        "margin-left": $(".controllist li").width() / 2 - imgNum * $(".controllist li").width()
    })
    $(".imglist a").first().addClass("current")
    $(".imglist img").css({
        "height": $(".slider").css("height"),
    })
    imgIndex = 1
    cycletime = 4000
    play = setInterval(start,cycletime)
    function start(){
        if(imgIndex > imgNum - 1){
            imgIndex = 0
        }
        iconControl(imgIndex)
        imgIndex ++
    }
    function iconControl(index){
        $(".imglist a").removeClass("current").eq(index).addClass("current")
        $(".controllist li").removeClass("current").eq(index).addClass("current")
        $(".controlimglist li").removeClass("current").eq(index).addClass("current")
    }
    $(".slider").mouseenter(function(){
        clearInterval(play)
    }).mouseleave(function(){
        play = setInterval(start,cycletime)
    })
    $(".controlimglist li").mouseenter(function(){
        clearInterval(play)
        imgIndex = $(this).index() + 1
        iconControl($(this).index())
    }).mouseleave(function(){
        play = setInterval(start,cycletime)
    })
    $(".right").click(function(){
        if(imgIndex > imgNum - 1){
            imgIndex = 0
        }
        iconControl(imgIndex);
        imgIndex ++
    }).hover(function(){
        $(this).toggleClass("hover")
    })
    $(".left").click(function(){
        imgIndex -= 2
        if(imgIndex < 0){
            imgIndex = imgNum - 1
        }
        iconControl(imgIndex);
        imgIndex ++
    }).hover(function(){
        $(this).toggleClass("hover")
    })
    //手风琴菜单部分
    $(".subnav > ul").css("display","block")
    $(".subnav > h4").click(function(){
        $(this).toggleClass("open").next("ul").stop().slideToggle();
    }).hover(function(){
        $(this).first().css("border-radius","5px")
    })
    //榜单滑动效果部分
    var rankhead1imgwidth = $(".rank-head1 li").width()
    var rankhead1imgmaxwidth = $(".rank-head1 li").width() * 1.2
    var rankhead1imgmaxheight = $(".rank-head1 li").height() * 1.2 + "px"
    var rankhead1imgminwidth = ($(".rank-head1 ul").width() - rankhead1imgmaxwidth) / 4
    var rankhead2imgwidth = $(".rank-head2 li").width()
    var rankhead2imgmaxwidth = $(".rank-head2 li").width() * 1.2
    var rankhead2imgmaxheight = $(".rank-head2 li").height() * 1.2 + "px"
    var rankhead2imgminwidth = ($(".rank-head2 ul").width() - rankhead2imgmaxwidth) / 4
    actspeed = 100
    //console.log($(".rank-head li").height())
    //console.log($(".rank-head li").height() * 1.2)
    $(".rank-detail1 .col-md-4 p").eq(0).show().siblings().hide()
    $(".rank-detail1 .col-md-8 p").eq(0).show().siblings().hide()
    $(".rank-head1 li").css({"line-height":rankhead1imgmaxheight,"max-height":rankhead1imgmaxheight}).mouseenter(function(){
        $(this).stop().animate({
            "width": rankhead1imgmaxwidth,
        },actspeed).siblings().stop().animate({
            "width": rankhead1imgminwidth,
        },actspeed)
        $(".rank-detail1 .col-md-8 p").eq($(this).index()).show().siblings().hide()
        $(".rank-detail1 .col-md-4 p").eq($(this).index()).show().siblings().hide()
    }).mouseleave(function(){
        $(this).stop().animate({
            "width" : rankhead1imgwidth
        },actspeed).siblings().stop().animate({
            "width" : rankhead1imgwidth
        },actspeed)
    })
    $(".rank-detail2 .col-md-4 p").eq(0).show().siblings().hide()
    $(".rank-detail2 .col-md-8 p").eq(0).show().siblings().hide()
    $(".rank-head2 li").css({"line-height":rankhead2imgmaxheight,"max-height":rankhead2imgmaxheight}).mouseenter(function(){
        $(this).stop().animate({
            "width": rankhead2imgmaxwidth,
        },actspeed).siblings().stop().animate({
            "width": rankhead2imgminwidth,
        },actspeed)
        $(".rank-detail2 .col-md-8 p").eq($(this).index()).show().siblings().hide()
        $(".rank-detail2 .col-md-4 p").eq($(this).index()).show().siblings().hide()
    }).mouseleave(function(){
        $(this).stop().animate({
            "width" : rankhead2imgwidth
        },actspeed).siblings().stop().animate({
            "width" : rankhead2imgwidth
        },actspeed)
    })
    $(".rank-head1").css("max-height", rankhead1imgmaxheight)
    $(".rank-head2").css("max-height", rankhead2imgmaxheight)
    //登录注册部分
    function generateCheckCode(){
        checkCode = ""
        words = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        for(t = 0;t < 6;t ++){
            checkCode += words.charAt(Math.floor(Math.random() * words.length))
        }
        return checkCode
    }
    $(".toregist").click(function(){
        $(".login").hide()
        $(".regist").show()
    })
    $(".tologin").click(function(){
        $(".login").show();
        $(".regist").hide()
    })
    $(".text:last").focus(function(){
        $(this).attr("value",generateCheckCode())
    })
    $(".modelbox").click(function(){
        $(this).fadeOut(500)
        $(".modelcontent").fadeOut(500)
    })
})