// var wayneCarousel = {
//     ele: "#carousel-main",
//     curIndex: 0,
//     init: function(element) {
//         this.ele = element || this.ele
//         let self = this
//         jQuery(function() {
//             self.initDOM()
//             self.initEvent()
//         })
//         return self
//     },
//     initDOM: function() {
//         var frameWidth = $(this.ele).width()
//         var pageLengthVal = $(this.ele).children(".carousel-list").children(".carousel-item").length
//         $(this.ele).children(".carousel-list").css({ "width" : frameWidth * pageLengthVal })
//         $(this.ele).children(".carousel-list").children(".carousel-item").css({ "width" : frameWidth })
//     },
//     initEvent: function() {
//         let self = this
//         $(this.ele).children(".controller-prev").on("click",function() {
//             self.curIndex --
//             self.switchCarousel(self.curIndex)
//         })
//         $(this.ele).children(".controller-next").on("click",function() {
//             self.curIndex ++
//             self.switchCarousel(self.curIndex)
//         })
//     },
//     switchCarousel: function(index) {
//         if(index <= 0) {
//             this.curIndex = 0
//         } else if (index >= $(this.ele).children(".carousel-list").children(".carousel-item").length - 1) {
//             this.curIndex = $(this.ele).children(".carousel-list").children(".carousel-item").length - 1
//         } else {
//             this.curIndex = index
//         }
//         $(this.ele).children(".carousel-list").css({ "transform" : "translateX(-" + this.curIndex * $(this.ele).children(".carousel-list").children(".carousel-item").width() + "px)" })
//     }
// }

function WayneCarousel() {
    this.ele
    this.curIndex = 0
}

WayneCarousel.prototype = {
    init: function(element) {
        this.ele = element || this.ele
        let self = this
        jQuery(function() {
            self.initDOM()
            self.initEvent()
        })
        return self
    },
    initDOM: function() {
        var frameWidth = $(this.ele).width()
        var pageLengthVal = $(this.ele).children(".carousel-list").children(".carousel-item").length
        $(this.ele).children(".carousel-list").css({ "width" : frameWidth * pageLengthVal })
        $(this.ele).children(".carousel-list").children(".carousel-item").css({ "width" : frameWidth })
    },
    initEvent: function() {
        let self = this
        $(this.ele).children(".controller-prev").on("click",function() {
            self.curIndex --
            self.switchCarousel(self.curIndex)
        })
        $(this.ele).children(".controller-next").on("click",function() {
            self.curIndex ++
            self.switchCarousel(self.curIndex)
        })
    },
    switchCarousel: function(index) {
        if(index <= 0) {
            this.curIndex = 0
        } else if (index >= $(this.ele).children(".carousel-list").children(".carousel-item").length - 1) {
            this.curIndex = $(this.ele).children(".carousel-list").children(".carousel-item").length - 1
        } else {
            this.curIndex = index
        }
        $(this.ele).children(".carousel-list").css({ "transform" : "translateX(-" + this.curIndex * $(this.ele).children(".carousel-list").children(".carousel-item").width() + "px)" })
    }
}