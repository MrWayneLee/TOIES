<template>
	<div id="product">
		<div class="nav">
			<font-awesome-icon :icon="['fas', 'angle-double-right']" size="lg"></font-awesome-icon>
			<router-link :to="'/Category/' + this.productCategory">{{this.productCategory}}</router-link>
			<font-awesome-icon :icon='["fas","angle-right"]'></font-awesome-icon>
			<router-link :to="'/Category/' + this.productCategory + '/' + this.itemCategory">
				{{this.itemCategory}}</router-link>
		</div>
		<div class="info-container" v-for="product in productInfo">
			<div class="info-left">
				<div class="item-img">
					<img :src='imgUrl'>
				</div>
			</div>
			<div class="info-right">
				<div class="item-title">
					<p>{{product.name}}</p>
				</div>
				<div class="item-info">
					<p>Screen: <span>{{product.info.screen}}</span> inchs</p>
					<p>Processor: <span>{{product.info.processor}}</span></p>
					<p>Camera: <span>{{product.info.camera}}</span> magapixels</p>
					<p>RAM: <span>{{product.info.RAM}}</span> GB</p>
				</div>
				<div class="item-color-select">
					<p>Color:</p>
					<div class="color-item-row" v-for="page of spec('color')">
						<div class="color-item trans03" v-for="item of page" :class="{'active': colorData[item]}"
							@click="imgBind(item)">{{item}}</div>
					</div>
				</div>
				<div class="item-capacity-select">
					<p>Capacity:</p>
					<div class="capacity-item-row" v-for="page of spec('capacity')">
						<div class="capacity-item trans03" v-for="item of page" :class="{'active': capacityData[item]}"
							@click="priceBind(item)">{{item}}GB</div>
					</div>
				</div>
				<div class="item-price">
					<p><span>Price: </span>{{itemPrice}}<span>￥</span></p>
				</div>
				<div class="action-button">
					<button class="trans03 btn-purchase">Purchase</button>
					<button type="button" @click="cartAdd(product.id)" class="trans03 btn-cart">Add to cart</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	export default {
		name: "product",
		data() {
			return {
				productCategory: "",
				itemCategory: "",
				productInfo: [],
				colorData: {},
				imgData: {},
				capacityData: {},
				priceData: {},
				imgUrl: "",
				itemPrice: ""
			}
		},
		methods: {
			getData: function() {
				axios.get("/static/js/index.json").then(response => {
					this.getDataSucc(response)
				});
			},
			getDataSucc: function(res) {
				let phoneData
				res.data.data.forEach(item => {
					switch (item.type) {
						case "Phone":
							phoneData = item.res
							this.productCategory = item.type
							break
						default:
							break
					}
				})
				phoneData.forEach(item => {
					if (item.id == this.$route.params.id) {
						this.productInfo.push(item)
						this.imgUrl = item.imgURL
						this.itemPrice = item.price
						this.itemCategory = item.category
						item.selector.color.forEach((item_, index) => {
							this.colorData[item_] = false
							this.imgData[item_] = item.selector.carouselImg[index]
						})
						item.selector.capacity.forEach((item_, index) => {
							this.capacityData[item_] = false
							this.priceData[item_] = item.selector.price[index]
						})
					}
				})
			},
			cartAdd: function(itemId) {
				let selected = {}
				selected["name"] = this.productInfo[0].name
				selected["Id"] = itemId
				selected["type"] = this.productCategory
				for (let i in this.colorData) {
					if (this.colorData[i] == true) {
						selected["color"] = i
					}
				}
				for (let j in this.capacityData) {
					if (this.capacityData[j] == true) {
						selected["capacity"] = j
					}
				}
				selected["price"] = this.itemPrice
				selected["amount"] = 1
				selected["itemTotal"] = selected["price"] * selected["amount"]
				selected["imgUrl"] = this.imgUrl
				this.$store.dispatch("addItem", selected)
			},
			imgBind: function(item) {
				for (let i in this.colorData) {
					this.colorData[i] = false
				}
				this.colorData[item] = true
				this.imgUrl = this.imgData[item]
				// 如果在实例创建之后添加新的属性到实例上，它不会触发视图更新。
				// 强制实例重新渲染
				this.$forceUpdate()
			},
			priceBind: function(item) {
				for (let i in this.capacityData) {
					this.capacityData[i] = false
				}
				this.capacityData[item] = true
				this.itemPrice = this.priceData[item]
				this.$forceUpdate()
			}
		},
		mounted() {
			this.getData()
		},
		computed: {
			// capacity() {
			// 	const capacity = [];
			// 	this.productInfo[0].selector.capacity.forEach((item, index) => {
			// 		const page = Math.floor(index / 4);
			// 		if (!capacity[page]) {
			// 			capacity[page] = [];
			// 		}
			// 		capacity[page].push(item);
			// 	});
			// 	return capacity;
			// },
			// colors() {
			// 	const colors = [];
			// 	this.productInfo[0].selector.color.forEach((item, index) => {
			// 		const page = Math.floor(index / 4);
			// 		if (!colors[page]) {
			// 			colors[page] = [];
			// 		}
			// 		colors[page].push(item);
			// 	});
			// 	return colors;
			// },
			spec() {
				return function(key) {
					let array = ['color', 'capacity']
					let obj = {}
					for (let item in array) {
						const list = []
						this.productInfo[0].selector[array[item]].forEach((item, index) => {
							const page = Math.floor(index / 4)
							if (!list[page]) {
								list[page] = []
							}
							list[page].push(item)
						});
						obj[array[item]] = list
					}
					return obj[key]
				}
			}
		}
	};
</script>

<style scoped>
	#product {
		width: 68.75rem;
		margin: 1rem auto;
	}

	.nav {
		background: var(--color-gray);
		padding: 20px;
		font-size: 1.125rem;
		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
	}

	.nav a {
		text-decoration: none;
		color: var(--color-dark);
	}

	.nav a:hover {
		text-decoration: underline;
		color: var(--color-dark);
	}

	.info-container {
		margin: 1rem 0;
		display: flex;
		align-items: flex-start;
	}

	.info-left {
		margin-right: 1rem;
		width: calc(40% - 8px);
	}

	.info-right {
		background: var(--color-light);
		width: 60%;
	}

	.item-img {
		width: 100%;
		padding: 0.5rem;
		box-sizing: border-box;
		border: 1px solid var(--color-gray);
		border-radius: 5px;
	}

	.item-img img {
		width: 100%;
	}

	.item-title {
		padding: 1rem;
	}

	.item-title p {
		width: fit-content;
		margin: 0;
		font-size: 1.75rem;
		font-weight: bold;
		color: var(--color-red);
	}

	.item-info,
	.item-color-select,
	.item-capacity-select,
	.item-price,
	.action-button {
		padding: 1rem;
		padding-top: 0;
	}

	.item-info p {
		margin: 0 0 0.25rem;
	}

	.item-info span {
		color: var(--color-smoke);
	}

	.item-info p:last-of-type {
		margin: 0;
	}

	.item-color-select p,
	.item-capacity-select p {
		margin: 0 0 1rem;
		padding-top: 0;
	}

	.color-item-row,
	.capacity-item-row {
		text-transform: capitalize;
		margin-bottom: 1rem;
		display: flex;
	}

	.color-item-row:last-of-type,
	.capacity-item-row:last-of-type {
		margin-bottom: 0;
	}

	.color-item,
	.capacity-item {
		user-select: none;
		cursor: pointer;
		border-radius: 10px;
		box-sizing: border-box;
		width: calc(25% - 12px);
		text-align: center;
		padding: 0.5rem;
		margin-right: 1rem;
		border: 1px solid var(--color-smoke);
	}

	.color-item:last-of-type,
	.capacity-item:last-of-type {
		margin-right: 0;
	}

	.color-item:hover,
	.capacity-item:hover {
		box-shadow: 0 0 2px 2px var(--theme-sub);
	}

	.color-item.active,
	.capacity-item.active {
		box-shadow: 0 0 2px 2px var(--theme-sub);
	}

	.item-price p {
		margin: 0;
		font-family: "impact";
		font-size: 44px;
		color: var(--color-red);
	}

	.item-price span {
		color: #000000;
		font-size: 18px;
		font-family: "微软雅黑";
	}

	.btn-purchase,
	.btn-cart {
		border: 3px solid var(--color-dark);
		background: var(--color-light);
		padding: 0.6rem 2.4rem;
		margin-right: 1rem;
		font-size: 18px;
	}

	.btn-purchase:hover,
	.btn-cart:hover {
		background: var(--color-dark);
		color: var(--color-light);
	}
	
	@media (max-width: 414px) {
		#product {
			width: 90%;
			margin: 1rem auto;
		}
		
		.info-container {
			flex-direction: column;
		}
		
		.info-left {
			width: 100%;
		}
		
		.info-right {
			margin-top: 1rem;
			width: 100%;
		}
		
		.color-item-row,
		.capacity-item-row {
			flex-wrap: wrap;
			margin-right: -1rem;
			margin-bottom: 0;
		}
		
		.item-color-select,
		.item-capacity-select {
			padding-bottom: 0;
		}
		
		.color-item,
		.capacity-item {
			width: calc(50% - 1rem);
			margin: 0 1rem 1rem 0;
		}
		
		.btn-purchase,
		.btn-cart {
			width: 100%;
		}
		
		.btn-purchase {
			margin-bottom: 1rem;
		}
	}
	
	@media (min-width: 415px) and (max-width: 812px) {
		#product {
			width: 96%;
			margin: 1rem auto;
		}
		
		.color-item-row,
		.capacity-item-row {
			flex-wrap: wrap;
			margin-right: -1rem;
			margin-bottom: 0;
		}
		
		.item-color-select,
		.item-capacity-select {
			padding-bottom: 0;
		}
		
		.color-item,
		.capacity-item {
			width: calc(50% - 1rem);
			margin: 0 1rem 1rem 0;
		}
		
		.btn-purchase,
		.btn-cart {
			width: 100%;
		}
		
		.btn-purchase {
			margin-bottom: 1rem;
		}
	}
	
	@media (min-width: 813px) and (max-width: 1366px) {
		#product {
			width: 96%;
			margin: 1rem auto;
		}
	}
</style>
