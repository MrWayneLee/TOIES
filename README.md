# TOIES

> 随便编写的小玩意

### DEMOS

> #### movie-demo [jQuery]

使用jQuery的API编写的一个简易电影网站样式

> #### mc-server-demo [jQuery][Node.js]

使用jQuery的API编写的一个服务器案例，服务端使用了node.js作为练习

### TOIES

> #### clock-1.html

一个简易的时钟样式

> #### demo-1.html

一个JavaScript, CSS练习

> #### demo-2.html

从URL获取参数并打印

> #### demo-vue-1.html

Vue的TODO List练习，使用了组件间通信

> #### five-in-a-row[jQuery].html

使用jQuery的API编写的五子棋游戏

> #### five-in-a-row[Vue].html

使用Vue编写的五子棋游戏

> #### full-carousel.html

使用jQuery的API编写的全屏滚动轮播图

> #### loading.html

使用CSS一些加载动画

> #### postcard-1.html

明信片样式图1

> #### postcard-2.html

明信片样式图2

> #### carousel.wayne.html

练习组件的打包方法
	
引用CSS：

    .carousel {
        position: relative;
        overflow: hidden;
        width: 600px;
        height: 500px;
    }
    .carousel-list, .carousel-item {
        font-size: 0;
        height: 100%;
    }
    .carousel-list {
        transition: ease 1s;
    }
    .carousel-item {
        font-size: 1rem;
        display: inline-block;
    }
    .controller-prev, .controller-next {
        position: absolute;
        background: lightgrey;
        top: calc(50% - 15px);
        height: 30px;
        width: 30px;
        cursor: pointer;
        user-select: none;
        text-align: center;
        line-height: 30px;
    }
    .controller-prev {
        left: 0;
    }
    .controller-next {
        right: 0;
    }

引用JavaScript：
		
    // 需要放在在jQuery之后
    <script src="./js/jquery-3.4.1.js"></script>
    <script src="./js/carousel.wayne.js"></script>

设置HTML标签：

    <div class="carousel" id="newCarousel">
        <div class="carousel-list">
            <div class="carousel-item">
                <!-- 轮播内容放在此处 -->
            </div>
            <div class="carousel-item">
                <!-- 轮播内容放在此处 -->
            </div>
            <div class="carousel-item">
                <!-- 轮播内容放在此处 -->
            </div>
        </div>
        <div class="controller-prev">&lt;</div>
        <div class="controller-next">&gt;</div>
    </div>

调用组件：

    var carousel = new WayneCarousel();
    carousel.init("#newCarousel")

> #### slide.wayne.html

练习组件的打包方法

- 组件的使用：

    引用CSS：

        ?

    引用JavaScript：

        ?

    设置HTML标签：

        ? 

    调用组件：

        ?
    
- 设置：

    > ele

    类型：`string`

    指定需要slide的元素。必须值。

    > childEle

    类型：`string`

    指定需要slide的子元素。必须值。
	    
    > items

    类型：`number`

    默认值：3

    指定每页所显示的子元素个数。

    > space

    类型：`number`

    默认值：20

    每个子元素横向之间的间隔，单位为像素。

    > prevEle

    类型：`string`

    控制滑动到上一页的按钮元素。必须值。

    > nextEle

    类型：`string`

    控制滑动到下一页的按钮元素。必须值。

    > responsive

    类型：`object`

    默认值：`{ xs: 1, sm: 2, md: 3, lg: 4 }`

    响应式显示，在不同宽度的设备上显示的子元素的个数。
    
    xs：`window.outerWidth < 414`

    sm：`window.outerWidth >= 414 && window.outerWidth < 784`

    md：`window.outerWidth >= 784 && window.outerWidth < 1216`

    lg：`window.outerWidth >= 1216`

	
		
		
