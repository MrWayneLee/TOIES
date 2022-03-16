/* Coded by Wayne */

// import "./jquery-3.4.1"

$(document).ready(function () {
    var $banner = document.querySelector(".banner");
    var $trigger = $(".trigger");
    var $cancel = $(".cancel");
    var $imglist = $(".imglist");
    var $left = $(".left");
    var $right = $(".right");
    var imgLength = $(".imglist").children("li").length;
    var $mainWarpperTitle = $(".main-warpper-title")
    var $mainWarpperInfo = $(".main-warpper-info")
    var $switch = $(".switch")
    var $switcher = $(".switcher")
    var $dot = $(".dot")
    var $bar = $(".bar")
    var $star = $(".star")
    var $rateMsg = $(".rate-msg")
    var $addTag = $(".add-tag")
    var curIndex = 0;
    var offsetTop = $banner.offsetTop;

    window.addEventListener('scroll', function () {
        var scrollTop = this.scrollY || this.scrollTop;
        if (scrollTop > offsetTop) {
            $banner.classList.add('fixed');
        } else {
            $banner.classList.remove('fixed');
        }
    });

    $trigger.on('click', () => {
        // $(".banner a").toggleClass('show');
        $trigger.toggleClass('active');
        $(".side-banner").toggleClass('show');
    });

    $cancel.on('click', () => {
        $(".side-banner").toggleClass('show');
        $trigger.toggleClass('active');
    });

    //初始化
    imgShow(0);

    //轮播图部分
    $left.on('click', () => {
        imgPrev(imgLength - 1);
    });

    $right.on('click', () => {
        imgNext(imgLength - 1);
    });

    function imgPrev(length) {
        curIndex == 0 ? curIndex = length : curIndex--;
        imgShow(curIndex);
    }

    function imgNext(length) {
        curIndex == length ? curIndex = 0 : curIndex++;
        imgShow(curIndex);
    }

    function imgShow(index) {
        $imglist.children("li").eq(index).addClass("show").siblings().removeClass("show");
    }

    //主页信息
    $mainWarpperTitle.on('click', function () {
        $(this).addClass("active").siblings().removeClass("active")
        $mainWarpperInfo.eq($(this).index()).addClass("active").siblings().removeClass("active")
    })

    //开关
    $switch.on('click', function () {
        $switcher.toggleClass("on")
        $(this).toggleClass("on")
    })

    //评分
    var ratestatus = false
    var msg = ['失望','较差','一般','良好','优秀']
    $star.on('mouseover', function () {
        var start = 0
        $star.removeClass("on")
        while(start <= $(this).index()){
            $star.eq(start).addClass('on');
            start++
        }
        $rateMsg.html(msg[$(this).index()])
    })
    $star.on('mousedown', function () {
        var start = 0
        $star.removeClass("on")
        while(start <= $(this).index()){
            $star.eq(start).addClass('on');
            start++
        }
        $rateMsg.html(msg[$(this).index()])
        ratestatus = true
        retelevel = $(this).index()
    })
    $star.on('mouseleave', function () {
        if(ratestatus){
            rated(retelevel)
        } else {
            $rateMsg.html("")
            $star.removeClass("on")
        }
    })
    function rated(index) {
        $star.removeClass("on")
        var start = 0
        while(start <= index){
            $star.eq(start).addClass('on');
            start++
        }
        $rateMsg.html(msg[index])
    }
})