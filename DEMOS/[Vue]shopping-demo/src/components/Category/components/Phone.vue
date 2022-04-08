<template>
	<div id="phone">
		<div class="nav">
			<font-awesome-icon :icon="['fas', 'angle-double-right']" size="lg"></font-awesome-icon>
			Phone
		</div>
		<div class="category-list">
			<div class="category-item trans03">
				<font-awesome-icon :icon="['fab', 'apple']" size="2x" class="category-icon"></font-awesome-icon>
				<p>Apple</p>
			</div>
			<div class="category-item trans03">
				<font-awesome-icon :icon="['fas', 'mobile-alt']" size="2x" class="category-icon"></font-awesome-icon>
				<p>OnePlus</p>
			</div>
			<div class="category-item trans03">
				<font-awesome-icon :icon="['fas', 'mobile-alt']" size="2x" class="category-icon"></font-awesome-icon>
				<p>XiaoMi</p>
			</div>
			<div class="category-item trans03">
				<font-awesome-icon :icon="['fas', 'mobile-alt']" size="2x" class="category-icon"></font-awesome-icon>
				<p>Huawei</p>
			</div>
		</div>
		<div class="items">
			<div class="items-row" v-for="(page, index) of this.pages" :key="index">
				<div class="item trans03" v-for="item of page" :key="item.id">
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
		<div class="pagination trans03">
			<div class="pagination-prev trans03 disable">Prev</div>
			<ul>
				<li class="trans03" v-for="(item, index) in this.itemList.length">
					<router-link :to="'/Category/Phone/' + index">{{ index + 1 }}</router-link>
				</li>
			</ul>
			<div class="pagination-next trans03">Next</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	export default {
		name: "phone",
		data() {
			return {
				itemList: [],
				pages: []
			};
		},
		methods: {
			getData: function() {
				axios({
					method: "get",
					url: "https://localhost:44344/product/getallproduct"
				}).then(response => {
					console.log(response)
				})
				// axios.get("/static/js/index.json").then(response => {
				// 	this.getDataSucc(response)
				// })
			},
			getDataSucc: function(res) {
				let phoneData
				res.data.data.forEach(item => {
					switch(item.type) {
						case "Phone":
							phoneData = item.res
							break
						default:
							break
					}
				})
				this.itemList = phoneData
				//this.pagination(phoneData)
				// if(this.$route.name.length>0) {
				// 	phoneData.forEach(item => {
				// 		console.log(item)
				// 		if(item.category == this.$route.name) {
				// 			this.itemList.push(item)
				// 		}
				// 	})
				// }
				this.loadItem()
			},
			pagination(data) {
				let mainPages = [], itemNum = 9
				// data.forEach((item, index) => {
				// 	let mainPage = Math.floor(index / itemNum)
				// 	if (!this.itemList[mainPage]) {
				// 		this.itemList[mainPage] = []
				// 	}
				// 	this.itemList[mainPage].push(item)
				// })
				data.forEach((item, index) => {
					let mainPage = Math.floor(index / itemNum)
					if (!mainPages[mainPage]) {
						mainPages[mainPage] = []
					}
					mainPages[mainPage].push(item)
				})
				this.itemList = mainPages
				this.loadItem(mainPages)
			},
			loadItem(data) {
				this.itemList.forEach((item, index) => {
					let page = Math.floor(index / 3)
					if (!this.pages[page]) {
						this.pages[page] = []
					}
					this.pages[page].push(item)
				})
				this.$forceUpdate()
			}
		},
		mounted() {
			this.getData()
		}
	};
</script>

<style scoped>
	#phone {
		box-sizing: border-box;
		padding-left: 1rem;
		width: 100%;
	}

	.nav {
		font-size: 18px;
		padding: 20px 20px;
		background: var(--color-gray);
		border-radius: 5px;
		color: var(--color-dark);
	}

	.category-list {
		margin: 1rem 0;
		display: flex;
	}

	.category-item {
		text-decoration: none;
		color: var(--color-dark);
		cursor: pointer;
		display: flex;
		justify-content: center;
		align-items: center;
		border: 1px solid var(--color-gray);
		box-sizing: border-box;
		width: calc(25% - 12px);
		background: var(--color-gray);
		border-radius: 5px;
		margin-right: 1rem;
	}

	.category-item:hover {
		background: var(--color-light);
	}

	.category-item:last-of-type {
		margin-right: 0;
	}

	.category-icon {
		text-align: center;
		margin: 0.5rem 1rem 0.5rem 0;
	}

	.category-item p {
		user-select: none;
		margin: 0.5rem 0;
		text-align: center;
	}

	.items {
		margin: 0;
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
		width: calc(33.3333% - 10px);
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
		height: 250px;
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
	}
	
	.item-img a {
		height: 100%;
	}

	.item-img img {
		margin: 0 auto;
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
		margin: 0.5rem 0;
	}

	.item-desc p {
		margin: 0;
		font-size: 14px;
		height: 2rem;
		color: var(--color-smoke);
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
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
	
	.pagination {
		width: 100%;
		padding: 1rem 0;
		margin-top: 1rem;
		display: flex;
		justify-content: center;
	}
	
	.pagination-next,
	.pagination-prev {
		height: 3rem;
		display: flex;
		align-items: center;
		justify-content: center;
		user-select: none;
		cursor: pointer;
		border: 1px solid var(--color-gray);
		border-radius: 5px;
		box-sizing: border-box;
		padding: 0 2rem;
	}
	
	.pagination ul {
		list-style: none;
		margin: 0;
		padding: 0 2rem;
	}
	
	.pagination li {
		display: flex;
		align-items: center;
		justify-content: center;
		user-select: none;
		cursor: pointer;
		border: 1px solid var(--color-gray);
		border-radius: 5px;
		box-sizing: border-box;
		float: left;
		width: 3rem;
		height: 3rem;
		margin-right: 1rem;
	}
	
	.pagination li.active {
		border: 1px solid var(--color-dark);
		font-weight: bold;
	}
	
	.pagination li a{
		color: var(--color-dark);
		text-decoration: none;
	}
	
	.pagination li:last-of-type{
		margin: 0;
	}
	
	.pagination-next:hover,
	.pagination-prev:hover,
	.pagination li:hover {
		border: 1px solid var(--color-dark);
	}
	
	.pagination-next.disable,
	.pagination-prev.disable {
		cursor: not-allowed;
		border: 1px solid var(--color-gray);
		color: var(--color-gray);
	}
	
	@media (max-width: 375px) {
		#phone {
			padding: 0;
		}
		
		.category-list {
			flex-wrap: wrap;
		}
		
		.category-item {
			width: 50%;
			margin: 0;
			border-radius: 0;
			border: 1px solid var(--color-light);
		}
		
		.items-row {
			flex-direction: column;
		}
		
		.item {
			width: 100%;
			margin: 0;
			margin-bottom: 1rem;
		}
	}
	
	@media (min-width: 376px) and (max-width: 568px) {
		#phone {
			padding: 0;
		}
		
		.category-list {
			flex-wrap: wrap;
		}
		
		.category-item {
			width: 50%;
			margin: 0;
			border-radius: 0;
			border: 1px solid var(--color-light);
		}
		
		.items-row {
			flex-direction: column;
		}
		
		.item {
			width: 100%;
			margin: 0;
			margin-bottom: 1rem;
		}
	}
	
	@media (min-width: 569px) and (max-width: 812px) {
		#phone {
			padding: 0;
		}
	}
</style>
