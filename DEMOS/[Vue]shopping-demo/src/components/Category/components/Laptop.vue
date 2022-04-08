<template>
	<div id="laptop">
		<div class="nav">
			<font-awesome-icon :icon="['fas', 'angle-double-right']" size="lg"></font-awesome-icon>
			Laptop
		</div>
		<div class="category-list">
			<router-link to="/Category/Phone/Apple" class="category-item trans03">
				<font-awesome-icon :icon="['fab', 'apple']" size="2x" class="category-icon"></font-awesome-icon>
				<p>Apple</p>
			</router-link>
			<router-link to="/Category/Phone/XiaoMi" class="category-item trans03">
				<font-awesome-icon :icon="['fas', 'mobile-alt']" size="2x" class="category-icon"></font-awesome-icon>
				<p>XiaoMi</p>
			</router-link>
			<router-link to="/Category/Phone/Huawei" class="category-item trans03">
				<font-awesome-icon :icon="['fas', 'mobile-alt']" size="2x" class="category-icon"></font-awesome-icon>
				<p>Huawei</p>
			</router-link>
		</div>
		<div class="items">
			<div class="items-row" v-for="(page, index) of pages" :key="index">
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
				<li class="trans03">1</li>
				<li class="trans03">2</li>
				<li class="trans03">3</li>
			</ul>
			<div class="pagination-next trans03">Next</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	export default {
		name: "laptop",
		data() {
			return {
				itemList: []
			}
		},
		mounted() {
			this.getData()
		},
		methods: {
			getData: function() {
				axios.get("/static/js/index.json").then(response => {
					this.getDataSucc(response)
				})
			},
			getDataSucc: function(res) {
				let laptopData
				res.data.data.forEach(item => {
					switch(item.type) {
						case "Laptop":
							laptopData = item.res
							break
						default:
							break
					}
				})
				if(this.$route.params.category) {
					laptopData.forEach(item => {
						if(item.category == this.$route.params.category) {
							this.itemList.push(item)
						}
					})
				} else {
					this.itemList = laptopData
				}
			}
		},
		computed: {
			pages() {
				const pages = []
				this.itemList.forEach((item, index) => {
					const page = Math.floor(index / 3)
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
	#laptop {
		box-sizing: border-box;
		padding: 0 1rem;
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
		margin: 0.5rem 0;
	}

	.item-desc p {
		margin: 0;
		font-size: 14px;
		height: 38px;
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
	}
</style>
