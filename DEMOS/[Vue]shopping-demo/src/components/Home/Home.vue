<template>
	<div id="home">
		<div class="img-carousel" ref="imgCarousel">
			<div class="img-list trans08" ref="imgList" :style="listShift">
				<div class="img-item" v-for="item in imgList" ref="imgItem">
					<img :src="item">
				</div>
			</div>
			<div class="img-controller">
				<div class="dots" v-for="(item, index) in imgList" @click="shiftImg(index)"
					:class="{active: activeStatus[index]}">
				</div>
			</div>
			<div class="carousel-next trans03" @click='nextPage()'>
				<font-awesome-icon :icon='["fas", "angle-right"]'></font-awesome-icon>
			</div>
			<div class="carousel-pre trans03" @click='prePage()'>
				<font-awesome-icon :icon='["fas", "angle-left"]'></font-awesome-icon>
			</div>
		</div>
		<div class="services">
			<div class="service">
				<font-awesome-icon :icon='["fas", "truck"]' size='2x'></font-awesome-icon>
				<p>Free Shipping</p>
			</div>
			<div class="service">
				<font-awesome-icon :icon='["fas", "reply"]' size='2x'></font-awesome-icon>
				<p>7 Days Return</p>
			</div>
			<div class="service">
				<font-awesome-icon :icon='["fas", "shield-alt"]' size='2x'></font-awesome-icon>
				<p>Quality Guarantee</p>
			</div>
		</div>
		<div class="category-nav">
			<h1 class="category-title">Category</h1>
			<div class="category-list">
				<router-link :to="'/Category/Phone'" class="category-item trans03">
					<font-awesome-icon :icon='["fas", "mobile-alt"]' size='3x'></font-awesome-icon>
					<p>Phone</p>
				</router-link>
				<router-link to="/Category/Laptop" class="category-item trans03">
					<font-awesome-icon :icon='["fas", "laptop"]' size='3x'></font-awesome-icon>
					<p>Laptop</p>
				</router-link>
				<router-link to="/" class="category-item trans03">
					<font-awesome-icon :icon='["fas", "tablet-alt"]' size='3x'></font-awesome-icon>
					<p>Tablet</p>
				</router-link>
				<router-link to="/" class="category-item trans03">
					<font-awesome-icon :icon='["fas", "gamepad"]' size='3x'></font-awesome-icon>
					<p>Gaming</p>
				</router-link>
				<router-link to="/" class="category-item trans03">
					<font-awesome-icon :icon='["fas", "keyboard"]' size='3x'></font-awesome-icon>
					<p>Accessories</p>
				</router-link>
			</div>
		</div>
		<div class="display-template">
			<div class="display-title">
				<h1>Phone</h1>
				<router-link to="/Category/Phone" class="">More>></router-link>
			</div>
			<div class="display-items">
				<div class="items-row" v-for="page of pages">
					<div class="item trans03" v-for="item of page">
						<div class="item-img">
							<router-link :to="'/Product/'+ item.id" :key='item.id' class="trans03">
								<img :src="item.imgURL" />
							</router-link>
							<font-awesome-icon :icon="['fas', 'shopping-cart']" class="cart-icon trans03">
							</font-awesome-icon>
						</div>
						<div class="item-info">
							<div class="item-title">
								<h2 :title="item.name">
									<router-link :to="'/Product/'+ item.id" :key='item.id' class="trans03">
										{{ item.name }}
									</router-link>
								</h2>
							</div>
							<div class="item-desc">
								<p>{{ item.desc }}</p>
							</div>
							<div class="item-price">
								<span>￥{{ item.price }}</span>
								<router-link :to="'/Product/'+ item.id" :key='item.id' class="trans03">Purchase
								</router-link>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="display-template">
			<div class="display-title">
				<h1>Tablet</h1>
				<router-link to="/Category/Laptop" class="">More>></router-link>
			</div>
			<div class="display-items">
				<div class="items-row">
					<div class="item trans03">
						<div class="item-img">
							<img src="../../assets/logo.png" />
							<font-awesome-icon :icon="['fas', 'shopping-cart']" class="cart-icon trans03">
							</font-awesome-icon>
						</div>
						<div class="item-info">
							<div class="item-title">
								<h2>名称</h2>
							</div>
							<div class="item-desc">
								<p>描述</p>
							</div>
							<div class="item-price">
								<span>￥1000</span>
								<router-link to="/Home" :key='' class="trans03">Purchase
								</router-link>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	import bus from "../../bus.js"
	export default {
		name: "home",
		data() {
			return {
				imgList: [], //存放轮播图数据
				imgListIndex: 0, //记录跳转到某张播图的索引
				imgShift: 0, //记录轮播列表的位移[通过位移将图片滑动到可视区域]
				activeStatus: [], //记录每张轮播图的状态
				itemList: []
			}
		},
		methods: {
			getData() {
				//使用axios请求本地的json数据
				axios.get("static/js/index.json").then(response => {
					//将请求回的数据传递给getDataSucc函数
					this.getDataSucc(response)
				})
			},
			getDataSucc(res) {
				//在数据中抽取轮播图数据
				let carouselData, phoneData
				res.data.data.forEach(item => {
					switch (item.type) {
						case "carouselImg":
							carouselData = item.res
							break
						case "Phone":
							phoneData = item.res
							break
						default:
							break
					}
				})
				//遍历轮播图数据
				carouselData.forEach(item => {
					//将每次遍历的数据存放到imgList中
					this.imgList.push(item)
					//将每张轮播图的状态设置为关闭
					this.activeStatus.push(false)
				})
				//构建轮播列表
				this.initCarousel()
				//初始化轮播图，设置轮播图索引为0
				this.initStatus(0)
				this.itemList = phoneData
			},
			//将每一张轮播图设置成父元素的宽度
			initCarousel() {
				this.$nextTick(function() {
					var carouselWidth = this.$refs.imgCarousel.clientWidth
					this.$refs.imgItem.forEach(item => {
						item.style.width = carouselWidth + "px"
					})
				})
			},
			//下一张轮播图
			nextPage() {
				this.$nextTick(function() {
					//判断当前跳转的轮播图的索引是否为轮播图总数一致
					if (this.imgListIndex == this.imgList.length - 1) {
						//是则为最后一张轮播图，不进行任何操作
						return
					}
					//不是则将播图的索引加1，即跳转到下一张轮播图
					this.imgListIndex += 1
					//相应的轮播列表的位移增加一个轮播图的单位
					this.imgShift += this.$refs.imgCarousel.clientWidth
					//修改索引的轮播图状态
					this.initStatus(this.imgListIndex)
				})
			},
			//上一张轮播图
			prePage() {
				this.$nextTick(function() {
					//判断当前跳转的轮播图的索引是否为0
					if (this.imgListIndex == 0) {
						//是则为第一张轮播图，不进行任何操作
						return
					}
					//不是则将轮播图的索引减1，即跳转到上一张轮播图
					this.imgListIndex -= 1
					//相应的轮播列表的位移减少一个轮播图的单位
					this.imgShift -= this.$refs.imgCarousel.clientWidth
					//修改索引的轮播图状态
					this.initStatus(this.imgListIndex)
				})
			},
			//根据索引跳转到相应轮播图
			shiftImg(index) {
				this.$nextTick(function() {
					//索引值乘以单个轮播图的宽度
					this.imgShift = index * this.$refs.imgCarousel.clientWidth
					//设置图片索引为当前轮播图
					this.imgListIndex = index
					//修改索引的轮播图状态
					this.initStatus(this.imgListIndex)
				})
			},
			//拖动轮播图效果
			dragImg(e) {
				//let odiv = e.target
				let mouseDownX = e.clientX - e.path[2].offsetLeft + this.imgShift
				//let mouseDownY = e.clientY - e.path[2].offsetTop
				document.onmousemove = e => {
					let mouseUpX = e.clientX - e.path[2].offsetLeft + this.imgListIndex * this.$refs.imgCarousel.clientWidth
					e.path[1].style.transform = "translateX(-" +  (mouseDownX - mouseUpX) + "px)"
					console.log(mouseDownX - mouseUpX)
				}
				document.onmouseup = e => {
					document.onmousemove = null
					document.onmouseup = null
				}
			},
			//设置轮播图的状态
			initStatus(index) {
				//初始化轮播图状态
				for (let i in this.activeStatus) {
					this.activeStatus[i] = false
				}
				//将索引的轮播图状态设置为激活
				this.activeStatus[index] = true
			}
		},
		//在实例完成挂载之后，HTML已经完成了渲染
		mounted() {
			//调用getData函数
			this.getData()
		},
		computed: {
			listShift() {
				return 'transform : translateX(-' + this.imgShift + 'px)'
			},
			pages() {
				const pages = []
				this.itemList.forEach((item, index) => {
					const page = Math.floor(index / 5)
					if (page >= 2) {
						return
					}
					if (!pages[page]) {
						pages[page] = []
					}
					pages[page].push(item)
				})
				return pages
			}
		}
	};
</script>

<style scoped>
	#home {
		width: 68.75rem;
		margin: 1rem auto;
	}

	.img-carousel {
		box-sizing: border-box;
		width: 100%;
		height: 550px;
		overflow: hidden;
		position: relative;
	}

	.img-carousel:hover .img-list {
		transform: translateX(0)
	}

	.img-controller {
		position: absolute;
		display: flex;
		justify-content: center;
		width: 100%;
		margin: 1.1rem 0;
		bottom: 0;
	}

	.dots {
		cursor: pointer;
		height: 14px;
		width: 14px;
		border-radius: 50%;
		background: var(--color-gray);
		margin-right: 1.2rem;
	}

	.dots.active {
		background: var(--color-smoke);
	}

	.dots:last-of-type {
		margin-right: 0;
	}

	.carousel-next,
	.carousel-pre {
		height: 100%;
		display: flex;
		align-items: center;
		position: absolute;
		bottom: 0;
		padding: 2.5rem;
		box-sizing: border-box;
		font-size: 3rem;
	}

	.carousel-next {
		right: 0;
	}

	.carousel-next:hover,
	.carousel-pre:hover {
		padding: 4rem;
	}

	.carousel-pre {
		left: 0;
	}

	.img-list {
		width: max-content;
		height: 100%;
	}

	.img-list:active {
		cursor: grab;
	}

	.img-item {
		user-select: none;
		display: flex;
		float: left;
		height: inherit;
	}

	.img-item img {
		margin: 0 auto;
		height: inherit;
	}

	.services {
		display: flex;
		margin-top: 1.5rem;
	}

	.service {
		box-sizing: border-box;
		border: 2px solid var(--color-dark);
		padding: 2.2rem 4rem;
		width: 32.5%;
		margin-right: 1rem;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.service svg {
		font-size: 2rem;
	}

	.service p {
		font-size: 1rem;
		margin: 0 0 0 1.5rem;
	}

	.service:last-of-type {
		margin-right: 0;
	}

	.category-nav {
		margin: 2rem 0 0;
		width: 100%;
	}

	.category-title {
		margin: 0;
		padding-bottom: 1rem;
		border-bottom: 1px solid var(--theme-main);
		border-right: 10px solid var(--theme-main);
		box-sizing: border-box;
	}

	.category-list {
		margin-top: 1rem;
		display: flex;
	}

	.category-item {
		text-decoration: none;
		color: var(--color-dark);
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		box-sizing: border-box;
		padding: 2rem 0;
		width: 18.85%;
		margin-right: 1rem;
		text-align: center;
		border: 1px var(--theme-sub) dashed;
		border-radius: 6px;
		user-select: none;
		cursor: pointer;
	}

	.category-item p {
		margin-bottom: 0;
		font-size: 1rem;
	}

	.category-item:last-of-type {
		margin-right: 0;
	}

	.category-item:hover {
		box-shadow: 0 0 4px 1px var(--theme-sub);
		border: 1px var(--theme-sub) solid;
	}

	.display-template {
		margin-top: 2rem;
	}

	.display-title {
		margin: 0;
		border-bottom: 1px solid var(--theme-main);
		border-right: 10px solid var(--theme-main);
		box-sizing: border-box;
		padding: 1rem 0;
	}

	.display-items {
		margin-top: 1rem;
	}

	.display-title {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.display-title h1 {
		width: fit-content;
		margin: 0;
	}

	.display-title a {
		width: fit-content;
		margin-right: 2rem;
		text-decoration: none;
		color: var(--color-dark);
	}

	.items {
		margin: 1rem 0;
	}

	.items-row {
		display: flex;
		margin: 1rem 0;
	}

	.items-row:last-of-type {
		margin: 0;
	}

	.item {
		border: 1px solid var(--color-gray);
		margin-right: 1rem;
		padding: 1rem;
		width: calc(20% - 13px);
		overflow: hidden;
		box-sizing: border-box;
		display: flex;
		flex-direction: column;
	}

	.item:hover {
		border: 1px solid var(--color-dark);
		box-shadow: 0 0 5px 5px var(--color-gray);
	}

	.item:hover .cart-icon {
		top: 0;
	}

	.item:last-of-type {
		margin: 0;
	}

	.item-img {
		height: 210px;
		position: relative;
	}

	.item-img img {
		margin: 0 auto;
		width: 100%;
		height: 100%;
	}

	.cart-icon {
		position: absolute;
		top: -70px;
		left: calc(50% - 23px);
		padding: 10px;
		background: #fff;
		border: 1px solid var(--color-dark);
		font-size: 25px;
		height: 28px;
		width: 28px;
		cursor: pointer;
	}

	.cart-icon:hover {
		color: var(--color-light);
		background: var(--color-dark);
	}

	.item-info {
		padding-top: 1rem;
		box-sizing: border-box;
	}

	.item-title * {
		text-decoration: none;
		color: var(--color-red);
		font-size: 1.25rem;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		height: 26px;
		margin: 0;
		padding: 0;
		cursor: pointer;
	}

	.item-desc {
		display: none;
		margin: 0.5rem 0;
	}

	.item-desc p {
		margin: 0;
		font-size: 14px;
		color: var(--color-smoke);
		overflow: hidden;
	}

	.item-price {
		margin: 1rem 0 0;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.item-price span {
		font-size: 1rem;
	}

	.item-price a {
		text-decoration: none;
		border: var(--color-dark) 1px solid;
		padding: 5px 10px;
		color: var(--color-dark);
	}

	.item-price a:hover {
		border: var(--color-dark) 1px solid;
		color: var(--color-light);
		background: var(--color-dark);
	}
	
	@media (max-width: 375px) {
		#home {
			width: 90%;
		}
		
		.img-carousel {
			height: 300px;
		}
		
		.dots {
			height: 8px;
			width: 25px;
			border-radius: 0;
			margin-right: 1rem;
			background: var(--theme-sub);
		}
		
		.dots.active {
			background: var(--color-red);
		}
		
		.carousel-next,
		.carousel-pre {
			padding: 1rem;
			font-size: 2.5rem;
		}
		
		.carousel-next:hover,
		.carousel-pre:hover {
			padding: 1.8rem;
		}
		
		.services {
			width: 100%;
			flex-direction: column;
			box-sizing: border-box;
		}
		
		.service {
			width: 100%;
			margin-right: 0;
			padding: 1.2rem;
			margin-bottom: 1rem;
			box-sizing: border-box;
		}
		
		.service p {
			font-size: 16px;
		}
		
		.category-list {
			flex-direction: column;
		}
		
		.category-item {
			flex-direction: row;
			width: 100%;
			margin: 0 0 0.8rem 0;
			padding: 1rem 0;
		}
		
		.category-item p {
			margin-left: 1rem;
			font-size: 14px;
		}
		
		.category-item svg {
			font-size: 2rem;
		}
		
		.items-row {
			flex-wrap: wrap;
		}
		
		.item,
		.item:last-of-type {
			flex-direction: row;
			margin: 1rem 0 0;
			width: 100%;
			height: 220px;
			align-items: stretch;
		}
		
		.item:first-of-type {
			margin-top: 0;
		}
		
		.item-img {
			width: 130px;
			height: 100%;
		}
		
		.item-info {
			margin-left: 0.4rem;
			box-sizing: border-box;
			padding: 0;
			width: calc(100% - 130px);
			display: flex;
			flex-direction: column;
			justify-content: space-between;
		}
		
		.item-desc {
			display: block;
		}
		
		.item-price {
			flex-direction: column;
			align-items: flex-start;
		}
		
		.item-price a {
			margin-top: 1rem;
		}
		
		.display-title a {
			margin-right: 1rem;
		}
	}
	
	@media (min-width: 376px) and (max-width: 568px) {
		#home {
			width: 90%;
		}
		
		.img-carousel {
			height: 325px;
		}
		
		.dots {
			height: 8px;
			width: 30px;
			border-radius: 0;
			margin-right: 1rem;
			background: var(--theme-sub);
		}
		
		.dots.active {
			background: var(--color-red);
		}
		
		.carousel-next,
		.carousel-pre {
			padding: 1rem;
			font-size: 2.5rem;
		}
		
		.carousel-next:hover,
		.carousel-pre:hover {
			padding: 1.8rem;
		}
		
		.services {
			width: 100%;
			flex-direction: column;
			box-sizing: border-box;
		}
		
		.service {
			width: 100%;
			margin-right: 0;
			padding: 1.2rem;
			margin-bottom: 1rem;
			box-sizing: border-box;
		}
		
		.service p {
			font-size: 16px;
		}
		
		.service:last-of-type {
			margin-bottom: 0;
		}
		
		.service svg {
			font-size: 1.2rem;
		}
		
		.service p {
			margin: 0 0 0 1rem;
		}
				
		.category-list {
			flex-direction: column;
		}
		
		.category-item {
			flex-direction: row;
			width: 100%;
			margin: 0 0 0.8rem 0;
			padding: 1rem 0;
		}
				
		.category-item p {
			margin-left: 1rem;
			font-size: 14px;
		}
		
		.category-item svg {
			font-size: 2rem;
		}

		.items-row {
			flex-wrap: wrap;
		}
		
		.item,
		.item:last-of-type {
			flex-direction: row;
			margin: 1rem 0 0;
			width: 100%;
			height: 220px;
		}
		
		.item:first-of-type {
			margin-top: 0;
		}
		
		.item-img {
			width: 160px;
			height: 100%;
		}
		
		.item-info {
			margin-left: 1rem;
			box-sizing: border-box;
			padding: 1rem 0;
			width: calc(100% - 160px);
			display: flex;
			flex-direction: column;
			justify-content: space-between;
		}
		
		.item-desc {
			display: block;
		}
		
		.display-template {
			margin-top: 1rem;
		}
		
		.display-title a {
			margin-right: 1rem;
		}
	}
	
	@media (min-width: 569px) and (max-width: 812px) {
		#home {	
			width: 96%;
		}
		
		.img-carousel {
			height: 325px;
		}
		
		.dots {
			height: 8px;
			width: 30px;
			border-radius: 0;
			margin-right: 1rem;
			background: var(--theme-sub);
		}
		
		.dots.active {
			background: var(--color-red);
		}
		
		.carousel-next,
		.carousel-pre {
			padding: 1rem;
			font-size: 2.5rem;
		}
		
		.carousel-next:hover,
		.carousel-pre:hover {
			padding: 1.8rem;
		}
		
		.services {
			width: 100%;
			flex-direction: row;
			box-sizing: border-box;
		}
		
		.service {
			width: 100%;
			margin-right: 1rem;
			padding: 1.2rem;
			margin-bottom: 0;
			box-sizing: border-box;
		}
		
		.service p {
			font-size: 16px;
		}
				
		.service svg {
			font-size: 1.2rem;
		}
		
		.service p {
			margin: 0 0 0 1rem;
		}
				
		.category-list {
			flex-direction: row;
		}
		
		.category-item {
			flex-direction: column;
			width: 100%;
			margin: 0 1rem 0.8rem 0;
			padding: 1rem 0;
		}
				
		.category-item p {
			font-size: 14px;
		}
		
		.category-item svg {
			font-size: 2rem;
		}

		.items-row {
			flex-wrap: wrap;
		}
		
		.item,
		.item:last-of-type {
			flex-direction: row;
			margin: 1rem 0 0;
			width: 100%;
			height: 220px;
		}
		
		.item:first-of-type {
			margin-top: 0;
		}
		
		.item-img {
			width: 160px;
			height: 100%;
		}
		
		.item-info {
			margin-left: 1rem;
			box-sizing: border-box;
			padding: 1rem 0;
			width: calc(100% - 160px);
			display: flex;
			flex-direction: column;
			justify-content: space-between;
		}
		
		.item-desc {
			display: block;
		}
		
		.display-template {
			margin-top: 1rem;
		}
		
		.display-title a {
			margin-right: 1rem;
		}
	}
	
	@media (min-width: 813px) and (max-width: 1024px) {
		#home {	
			width: 96%;
		}
		
		.img-carousel {
			height: 402px;
		}
		
		.services {
			width: 100%;
			flex-direction: row;
			box-sizing: border-box;
		}
		
		.service {
			width: 100%;
			margin-right: 1rem;
			padding: 1.8rem 0;
			margin-bottom: 0;
			box-sizing: border-box;
		}
		
		.service p {
			font-size: 16px;
		}
				
		.service svg {
			font-size: 1.2rem;
		}
		
		.service p {
			margin: 0 0 0 1rem;
		}
		
		.item-price {
			flex-direction: column;
			align-items: flex-start;
		}
		
		.item-price span {
			text-align: left;
		}
		
		.item-price a {
			margin-top: 1rem;
		}
	}
	
	@media (min-width: 1024px) and (max-width: 1366px) {
		#home {
		}
	}
</style>
