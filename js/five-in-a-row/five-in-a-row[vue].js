var app = new Vue({
    el: "#app",
    data: {
        unit: 20,                       // 棋盘
        steps: 0,                       // 步数
        player: ["black", "white"],     // 玩家：黑，白
        winner: "",                     // 赢家
        step: [],                       // 棋步
    },
    mounted() {
        this.$refs.frame.style.top = "95px"
        this.$refs.result.style.top = "95px"
        this.restart()
    },
    methods: {
        // 重置游戏
        restart() {
            this.steps = 0
            this.step = []
            for(var i = 0; i < this.$refs.col.length; i++) {
                this.$refs.col[i].classList.remove("active")
                this.$refs.col[i].children[0].classList.remove("black")
                this.$refs.col[i].children[0].classList.remove("white")
            }
        },
        // 放置棋子
        setPieces(X, Y) {
            let ele = this.$refs.row[X].children[Y]
            if(!ele.classList.value.match("active")) {
                ele.classList.add("active")
                if(!ele.children[0].classList.length) {
                    ele.children[0].classList.add(this.player[this.steps % 2])
                }
                this.steps ++
                this.step.push(ele)
                this.isWin(X, Y)
            }
        },
        // 判赢
        isWin(X, Y) {
            if(this.topBottom(X, Y) || this.leftRight(X, Y) || this.leftBottomRightTop(X, Y) || this.leftTopRightBottom(X, Y)){
                console.log(this.player[(this.steps + 1) % 2] + " WIN!")
            }
        },
        // 悔棋
        undo() {
            let ele = this.step.pop()
            ele.children[0].classList.value = ""
            ele.classList.remove("active")
            this.steps --
        },
        topBottom(X, Y) {
            var total = this.getCount(X, Y, 1) + this.getCount(X, Y, 5) - 1
            if(total >= 5) return true
        },
        leftRight(X, Y) {
            var total = this.getCount(X, Y, 7) + this.getCount(X, Y, 3) - 1
            if(total >= 5) return true
        },
        leftBottomRightTop(X, Y) {
            var total = this.getCount(X, Y, 6) + this.getCount(X, Y, 2) - 1
            if(total >= 5) return true
        },
        leftTopRightBottom(X, Y) {
            var total = this.getCount(X, Y, 8) + this.getCount(X, Y, 4) - 1
            if(total >= 5) return true
        },
        getCount(X, Y, methods) {
            var count = 1
            let ele
            for(var index = 1; index <= 4; index ++) {
                switch(methods) {
                    case 1: // 上
                        if(!this.$refs.row[X - index]) return count
                        ele = this.$refs.row[X - index].children[Y]; break
                    case 2: // 右上
                        if(!this.$refs.row[X].children[Y + index] || !this.$refs.row[X - index]) return count
                        ele = this.$refs.row[X - index].children[Y + index]; break
                    case 3: // 右
                        if(!this.$refs.row[X].children[Y + index]) return count
                        ele = this.$refs.row[X].children[Y + index]; break
                    case 4: // 右下
                        if(!this.$refs.row[X].children[Y + index] || !this.$refs.row[X + index]) return count
                        ele = this.$refs.row[X + index].children[Y + index]; break
                    case 5: // 下
                        if(!this.$refs.row[X + index]) return count
                        ele = this.$refs.row[X + index].children[Y]; break
                    case 6: // 左下
                        if(!this.$refs.row[X].children[Y - index] || !this.$refs.row[X + index]) return count
                        ele = this.$refs.row[X + index].children[Y - index]; break
                    case 7: // 左
                        if(!this.$refs.row[X].children[Y - index]) return count
                        ele = this.$refs.row[X].children[Y - index]; break
                    case 8: // 左上
                        if(!this.$refs.row[X].children[Y - index] || !this.$refs.row[X - index]) return count
                        ele = this.$refs.row[X - index].children[Y - index]; break
                    default: return
                }
                if(ele.children[0].classList.value == this.player[(this.steps + 1) % 2]) count ++
            }
            return count
        }
    }
})