function slide() {
    // 指定元素
    this.ele = null
    // 指定子元素
    this.childEle = null
    // 指定每页子元素的个数
    this.items = 4
    // 子元素的间隔
    this.space = 20
    // 上一页-按钮
    this.prevEle = null
    // 下一页-按钮
    this.nextEle = null
    // 响应式
    this.responsive = {
        xs: 1,
        sm: 2,
        md: 3,
        lg: 4
    }
}

slide.prototype = {
    init: function(config) {
        this.ele = $(config.element) || this.ele
        this.childEle = $(config.element).children(config.childEle) || this.childEle
        this.items = config.items || this.items
        this.space = config.space || this.space
        this.prevEle = $(config.prevEle) || this.prevEle
        this.nextEle = $(config.nextEle) || this.nextEle
        this.responsive = config.responsive || this.responsive
        let self = this
        jQuery(function(){
            $(window).resize(function() {
                self.sizeAdjust()
                self.initDOM()
            })
            self.sizeAdjust()
            self.initDOM()
            self.initEvent()
        })
        return self
    },
    initDOM: function() {
        if(!this.ele.children(".wayne-slide").length) {
            this.ele.append("<div class='wayne-slide' style='width:max-content;transition:ease 0.5s'></div>").children(".wayne-slide").append(this.childEle)
        }
        this.prevEle.hide()
        this.nextEle.hide()
        if(this.childEle.length > this.items) {
            this.prevEle.show()
            this.nextEle.show()
        }
        this.childEle.css({
            "width" : (this.ele.width() - ((this.items - 1) * this.space)) / this.items ,
            "margin-right": this.space
        }).removeClass("active").filter(":lt(" + this.items + ")").addClass("active")
    },
    initEvent: function() {
        let self = this
        this.prevEle.on("click", function() {
            if(!self.childEle.filter(".active").first().prev().length){
                return
            }
            self.childEle.filter(".active").first().prev().addClass("active")
            self.childEle.filter(".active").last().removeClass("active")
            self.slide(self.childEle.filter(".active").first().prevAll().length)
        })
        this.nextEle.on("click", function() {
            if(!self.childEle.filter(".active").last().next().length){
                return
            }
            self.childEle.filter(".active").last().next().addClass("active")
            self.childEle.filter(".active").first().removeClass("active")
            self.slide(self.childEle.filter(".active").first().prevAll().length)
        })
    },
    slide: function(offset) {
        this.ele.children(".wayne-slide").css({
            "transform" : "translateX(-" + offset * (this.childEle.outerWidth() + this.space) + "px)"
        })
    },
    sizeAdjust: function() {
        if(window.outerWidth < 414) {
            this.items = this.responsive.xs
        } else if(window.outerWidth >= 414 && window.outerWidth < 784) {
            this.items = this.responsive.sm
        } else if(window.outerWidth >= 784 && window.outerWidth < 1216) {
            this.items = this.responsive.md
        } else {
            this.items = this.responsive.lg
        }
    }
}