$(document).ready(function(){
    var oneFlame
    var carouselMainIndex = 0
    
    let ele_carouselMain = $("#carousel-main")
    let ele_carouselMainList = $(".carousel-main-list")
    let ele_carouselMainItem = $(".carousel-main-item")
    let ele_carouselSubList = $(".carousel-sub-list")
    let ele_carouselSubItem = $(".carousel-sub-item")
    
    var carouselMainHeight = ele_carouselMain.height()
    var carouselMainWidth = ele_carouselMain.width()
    for(var index of ele_carouselSubList) {
        $(index).children().eq(0).addClass("active").siblings().removeClass("active")
    }
    ele_carouselMainItem.eq(carouselMainIndex).addClass("active").siblings().removeClass("active")
    ele_carouselMainList.after('<i class="bi bi-arrow-up btn-up"></i><i class="bi bi-arrow-down btn-down"></i>')
    ele_carouselSubList.after('<i class="bi bi-arrow-left btn-left"></i><i class="bi bi-arrow-right btn-right"></i>')
    ele_carouselSubList.css("transform", "translateX(0px)")
    windowResize()
    
    $(".btn-up").on("click", function() {
        prevPage()
    })
    $(".btn-down").on("click", function() {
        nextPage()
    })
    $(".btn-left").on("click", function() {
        if($(this).parents()[1].classList.value.indexOf("active") != -1) {
            subPrevPage($(this))
        };
    })
    $(".btn-right").on("click", function() {
        if($(this).parents()[1].classList.value.indexOf("active") != -1) {
            subNextPage($(this))
        };
    })

    window.onmousewheel = document.onmousewheel = (e) => {
        if(oneFlame) clearTimeout(oneFlame)
        oneFlame = setTimeout(function() {
            if(e.wheelDelta > 0) {          // 上滑
                prevPage()
            } else if(e.wheelDelta < 0) {   // 下滑
                nextPage()
            }
        }, 50)
    }

    window.onresize = () => {
        windowResize()
    }
    
    function windowResize() {
        carouselMainHeight = ele_carouselMain.height()
        carouselMainWidth = ele_carouselMain.width()
        for(var index of ele_carouselMainItem) {
            $(index).css("height", carouselMainHeight + "px")
        }
        for(var index of ele_carouselSubItem) {
            $(index).css("width", carouselMainWidth + "px")
        }
        for(var index of ele_carouselSubList) {
            var offset = parseInt($(index).css("transform").substring(6).split(",")[4]) / carouselMainWidth
            var offsetIndex = Math.abs(Math.round(offset))
            console.log(offsetIndex, carouselMainWidth ,offsetIndex * carouselMainWidth)
            $(index).css("transform", "translateX(-" + offsetIndex * carouselMainWidth + "px)")
        }
        ele_carouselMainList.css({"transform": "translateY(-" + carouselMainIndex * carouselMainHeight + "px)"})
    }

    function prevPage() {
        carouselMainIndex <= 0 ? null : carouselMainIndex --
        ele_carouselMainList.css({"transform": "translateY(-" + carouselMainIndex * carouselMainHeight + "px)"})
        ele_carouselMainItem.eq(carouselMainIndex).addClass("active").siblings().removeClass("active")
    }

    function nextPage() {
        carouselMainIndex >= ele_carouselMainItem.length - 1 ? null : carouselMainIndex ++
        ele_carouselMainList.css({"transform": "translateY(-" + carouselMainIndex * carouselMainHeight + "px)"})
        ele_carouselMainItem.eq(carouselMainIndex).addClass("active").siblings().removeClass("active")
    }

    function subPrevPage(_this) {
        if(!_this.siblings("ul").children(".active").prev().length)  return
        var offset = carouselMainWidth + parseInt(_this.siblings("ul").css("transform").substring(6).split(",")[4])
        _this.siblings("ul").css("transform", "translateX(" + offset + "px)")
        _this.siblings("ul").children(".active").prev().addClass("active").siblings().removeClass("active")
    }
    
    function subNextPage(_this) {
        if(!_this.siblings("ul").children(".active").next().length)  return
        var offset = carouselMainWidth - parseInt(_this.siblings("ul").css("transform").substring(6).split(",")[4])
        _this.siblings("ul").css("transform", "translateX(-" + offset + "px)")
        _this.siblings("ul").children(".active").next().addClass("active").siblings().removeClass("active")
    }

});
