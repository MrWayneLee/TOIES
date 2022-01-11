$(document).ready(function(){
    let ele_carouselMain = $("#carousel-main")
    var carouselMainHeight = ele_carouselMain.height()
    var carouselMainWidth = ele_carouselMain.width()
    
    var carouselMainIndex = 0
    let ele_carouselMainList = $(".carousel-main-list")
    let ele_carouselMainItem = $(".carousel-main-item")
    let ele_carouselSubList = $(".carousel-sub-list")
    let ele_carouselSubItem = $(".carousel-sub-item")
    
    for(var index of ele_carouselMainItem) {
        index.style.height = carouselMainHeight + "px"
    }
    for(var index of ele_carouselSubItem) {
        index.style.width = carouselMainWidth + "px"
    }
    for(var index of ele_carouselSubList) {
        console.log($(index));
        $(index).children().eq(0).addClass("active").siblings().removeClass("active")
    }
    ele_carouselMainItem.eq(carouselMainIndex).addClass("active").siblings().removeClass("active")
    ele_carouselSubList.children("li:nth(0)")
    ele_carouselMainList.after('<i class="bi bi-arrow-up btn-up"></i><i class="bi bi-arrow-down btn-down"></i>')
    ele_carouselSubList.after('<i class="bi bi-arrow-left btn-left"></i><i class="bi bi-arrow-right btn-right"></i>')
    
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
        if(e.wheelDelta > 0) {
            prevPage()
        } else if(e.wheelDelta < 0) {
            nextPage()
        }
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
        if(!_this.siblings("ul").children(".active").prev().length) {
            return
        }
        _this.siblings("ul").css("transform","translateX(-" + (carouselMainWidth + parseInt(_this.siblings("ul").css("transform").substring(6).split(",")[4])) + "px)")
        _this.siblings("ul").children(".active").prev().addClass("active").siblings().removeClass("active")
    }

    function subNextPage(_this) {
        if(!_this.siblings("ul").children(".active").next().length) {
            return
        }
        _this.siblings("ul").css("transform","translateX(-" + (carouselMainWidth - parseInt(_this.siblings("ul").css("transform").substring(6).split(",")[4])) + "px)")
        _this.siblings("ul").children(".active").next().addClass("active").siblings().removeClass("active")
    }

});

