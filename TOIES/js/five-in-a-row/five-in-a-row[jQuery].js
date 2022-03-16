console.log("Five in a row, also called Gomoku.")
console.log("- Require jQuery v3.4.1")
console.log("- Hit the title to restart the game.")
console.log("- Hit the player's name to regret the move.")
console.log("Coded by Wayne. => Github: https://github.com/MrWayneLee/")
console.log("上班摸鱼的好活动。")

$(function(){
    // 变量&DOM
    var stepCount = 1                   // 步数
    var player = ["black", "white"]     // 玩家：黑，白
    var stepList = []                   // 计步
    let titleEle = $("#title")
    let stepCountEle = $(".step")
    let playerEle = $(".player")
    let shessUnitEle = $(".chessunit")
    let resultEle = $(".result")
    let winnerEle = $(".winner")
    let restartEle = $(".restart")

    // 初始化游戏
    stepCountEle.text(stepCount)
    restart()
    
    // 添加棋步点击事件
    shessUnitEle.on("click", function () {
        // 判定棋步是否有棋子
        if(!$(this).hasClass("active")) {
            $(this).addClass("active")
            setStep(stepCount + 1)
            stepList.push($(this))
            if(stepCount % 2) {
                $(this).append("<div class='white'></div>")
            } else {
                $(this).append("<div class='black'></div>")
            }
            // 检查赢否
            isWin($(this))
        }
    })
    
    // 添加title点击事件
    titleEle.on("click", function() {
        restart()
    })

    // 添加player点击事件
    playerEle.on("click", function() {
        regret()
    })

    // 添加restart点击事件
    restartEle.on("click", function() {
        resultEle.removeClass('show')
        restart()
    })

    // 判赢
    function isWin(ele) {
        if(up2Down(ele) || left2Right(ele) || leftBottom2RightTop(ele) || leftTop2RightBottom(ele)) {
            resultEle.addClass('show')
            winnerEle.text(player[stepCount % 2])
        }
    }
    
    // 悔棋
    function regret() {
        setStep(stepCount - 1)
        stepList.pop().removeClass("active").empty()
        playerEle.text(player[(stepCount + 1) % 2])
    }

    // 重开
    function restart() {
        setStep(1)
        playerEle.text(player[(stepCount + 1) % 2])
        shessUnitEle.removeClass("active").empty()
    }

    // 设置步数
    function setStep(number) {
        if(number < 1) {
            setStep(1); return
        }
        stepCount = number
        stepCountEle.text(stepCount)
        playerEle.text(player[(number + 1) % 2])
    }

/* ------------------------------------------------------------------------ */
/*                                                                          */
/* ------------------------------------------------------------------------ */

    // 垂直判定
    function up2Down(ele) {
        var total = getCount(1, ele) + getCount(5, ele) - 1
        if (total >= 5) return true
    }

    // 水平判定
    function left2Right(ele) {
        var total = getCount(7, ele) + getCount(3, ele) - 1
        if (total >= 5) return true
    }

    // 左下右上判定
    function leftBottom2RightTop(ele) {
        var total = getCount(6, ele) + getCount(2, ele) - 1
        if (total >= 5) return true
    }

    // 左上右下判定
    function leftTop2RightBottom(ele) {
        var total = getCount(8, ele) + getCount(4, ele) - 1
        if (total >= 5) return true
    }

    // 公共遍历算法
    function getCount(methods, pieces) {
        var keyNum = 20, count = 1
        let _this
        for (let index = 0; index < 4; index++) {
            switch(methods) {
                case 1: // 上
                    _this = pieces.prevAll().eq(keyNum - 1); break
                case 2: // 右上
                    _this = pieces.prevAll().eq(keyNum - 2 - index); break
                case 3: // 右
                    _this = pieces.nextAll().eq(index); break
                case 4: // 右下
                    _this = pieces.nextAll().eq(keyNum + index); break
                case 5: // 下
                    _this = pieces.nextAll().eq(keyNum - 1); break
                case 6: // 左下
                    _this = pieces.nextAll().eq(keyNum - 2 - index); break
                case 7: // 左
                    _this = pieces.prevAll().eq(index); break
                case 8: // 左上
                    _this = pieces.prevAll().eq(keyNum + index); break
                default: return
            }
            if(_this.length && _this.hasClass("active")) {
                if(_this.children().hasClass(player[stepCount % 2])) {
                    count ++
                    keyNum += 20
                } else break
            } else break
        }
        return count
    }
});