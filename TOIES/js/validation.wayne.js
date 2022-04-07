function WayneValidation() {
    this.eles = null
    this.submitEle = null
}

WayneValidation.prototype = {
    init: function(config) {
        this.eles = config.eles || this.eles
        this.submitEle = config.submitEle || this.submitEle
        let self = this
        jQuery(function() {
            self.initDOM()
            self.initEvent()
        })
    },
    initDOM: function() {
        $(this.eles).children("label").after("<span></span>")
    },
    initEvent: function() {
        let self = this
        $(this.eles).children("input").on("focus", function() {
            $(this).bind('input propertychange', function() {
                self.match($(this))
            })
        })
        $(this.submitEle).on("click", function() {
            var count = 0
            for(var i = 0 ; i < $(self.eles).length ; i++) {
                if($(self.eles).eq(i).children("input").val().length == 0) {
                    count++
                }
            }
            !count ? null : alert(count + " unfilled forms!")
        })
    },
    match: function(ele) {
        ele.hasClass("_required") ? this.isEmpty(ele) : null
        ele.hasClass("_number") ? this.isNum(ele) : null
        ele.hasClass("_email") ? this.isMail(ele) : null
    },
    isEmpty: function(ele) {
        console.log(ele)
    },
    isNum: function(ele) {
        console.log(ele)
        
    },
    isMail: function(ele) {
        console.log(ele)
        
    },
    isReg: function(ele) {
        console.log(ele)

    }
}

var validate = new WayneValidation()
validate.init({
    eles: ".form-group",
    submitEle: "#submit"
})

$(this).children("input").css({
    "border": "1px solid #f00"
})

// $("._required").on('focus',function() {
//     $(this).bind('input propertychange', function() {
//         matchFormat($(this))
//     })
// })
// $(".form-submit").on("click", function() {
//     let count = 0
//     for(let index = 0; index < $("._required").length; index++) {
//         let ele = $("._required").eq(index)
//         matchFormat(ele)
//         if(ele.hasClass("warn")) count += 1
//     }
//     !count ? $(".checkout-form").submit() : alert(count + " unfilled forms!")
// })
// function removeTip(el) {
//     el.removeClass("warn").addClass("succ")
//     $(el).siblings("p").children("label").text("")
// }
// function showTip(el, msg) {
//     el.addClass("warn").removeClass("succ")
//     $(el).siblings("p").children("label").text(msg)
// }
// function matchFormat(el) {
//     var matchEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
//     var matchNumber = /^[0-9]*$/
//     if(el.hasClass('_email')) {
//         (matchEmail.test(el.val()) && el.val().length) ? removeTip(el) : showTip(el, "Wrong Format!")
//     } else if(el.hasClass('_number')) {
//         (matchNumber.test(el.val()) && el.val().length) ? removeTip(el) : showTip(el, "Wrong Format!")
//     } else if (el.hasClass('_zipcode')) {
//         (matchNumber.test(el.val()) && el.val().length) ? removeTip(el) : showTip(el, "Wrong Format!")
//     } else {
//         !el.val().length ? showTip(el, "Required!") : removeTip(el)
//     }
// }
