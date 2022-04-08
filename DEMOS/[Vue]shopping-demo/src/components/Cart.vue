<template>
	<div id="cart">
		<div class="cart trans06" :class="{ hide: cartStatus }">
			<font-awesome-icon :icon="['fas', 'angle-right']" class="cancel-icon" size="2x" @click="toggleShoppingCart">
			</font-awesome-icon>
			<font-awesome-icon :icon="['fas', 'shopping-cart']" size="3x" class="cart-icon"></font-awesome-icon>
			<p class="cart-title">Shopping Cart</p>
			<div class="cart-items">
				<div class="empty-tip" v-if="cartEmpty">
					<p>Your cart is empty.</p>
					<p>Want some shopping?</p>
					<p><router-link to="/Home" class="link-home">Lets Go!</router-link></p>
					<font-awesome-icon :icon="['fas', 'smile']" size="3x"></font-awesome-icon>
				</div>
				<div class="cart-item" v-for="(item, index) in this.$store.state" :key="item.key">
					<div class="item-img">
						<img :src="item.imgUrl" />
						<font-awesome-icon :icon="['fas', 'trash-alt']" class="delete-icon"
							@click="deleteItemFromCart(index)"></font-awesome-icon>
					</div>
					<div class="item-info">
						<div class="item-name">{{ item.name }}</div>
						<div class="item-specs">{{ item.color }} | {{ item.capacity }}GB</div>
						<div class="quantity-price">
							<div class="item-quantity">
								<font-awesome-icon :icon='["fas", "minus"]' @click="decreaseAmount(index)"></font-awesome-icon>
								<input class="counter" type="number" min="1" max="100" v-model="item.amount" />
								<font-awesome-icon :icon='["fas", "plus"]' @click="increaseAmount(index)"></font-awesome-icon>
							</div>
							<div class="item-price">{{ item.price * item.amount }}￥</div>
						</div>
					</div>
				</div>
			</div>
			<div class="check">
				<router-link to="/ShoppingCart" class="to-cart trans03">View Cart</router-link>
				<div class="check-submit trans03">Check</div>
			</div>
		</div>
	</div>
</template>

<script>
	import bus from "../bus.js"

	export default {
		name: "cart",
		data() {
			return {
				cartStatus: true,
				cartEmpty: true,
			}
		},
		mounted() {
			var that = this;
			bus.$on("toggleShoppingCart", () => {
				that.cartStatus = !that.cartStatus
			})
			bus.$on("closeShoppingCart", () => {
				that.cartStatus = true
			})
		},
		methods: {
			decreaseAmount(index) {
				this.$store.dispatch('deAmount', index)
			},
			increaseAmount(index) {
				this.$store.dispatch('inAmount', index)
			},
			deleteItemFromCart: function(id) {
				this.$store.dispatch('spliceItem', id)
			},
			toggleShoppingCart: function() {
				this.cartStatus = !this.cartStatus
			}
		},
		computed: {
			storeLength() {
				return this.$store.state.length
			}
		},
		watch: {
			storeLength(curValue, oldValue) {
				if (curValue == 0) {
					this.cartEmpty = true
				} else {
					this.cartEmpty = false
				}
			}
		},
	};
</script>

<style scoped>
	.cart {
		display: flex;
		align-content: space-between;
		flex-wrap: wrap;
		padding: 45px 35px;
		box-sizing: border-box;
		z-index: 18;
		top: 0;
		right: 0;
		position: fixed;
		height: 100vh;
		width: 360px;
		background: var(--theme-main);
		box-shadow: 0 0 4px 4px var(--color-smoke);
	}

	.cart.hide {
		right: -390px;
	}

	.cancel-icon {
		cursor: pointer;
		position: absolute;
		top: calc(50% - 46px);
		left: -25px;
		padding: 30px 5px;
		border-top-left-radius: 5px;
		border-bottom-left-radius: 5px;
		color: var(--color-light);
		background: var(--theme-main);
	}

	.cart-icon {
		position: absolute;
		top: -1rem;
		right: 0;
		margin: 8px;
		color: var(--color-light);
		opacity: 0.2;
		font-size: 10rem;
		z-index: -1;
	}

	.cart-title {
		box-sizing: border-box;
		border-bottom: var(--theme-sub) 1px solid;
		border-left: var(--theme-sub) 10px solid;
		padding-left: 30px;
		padding-bottom: 5px;
		margin: 0;
		color: var(--color-light);
		font-size: 28px;
		font-family: "times new roman", "宋体";
		width: 100%;
		height: 40px;
	}

	.cart-items {
		width: 100%;
		overflow-y: auto;
		overflow-x: hidden;
		box-sizing: border-box;
		margin: 30px 0;
		height: calc(100% - 140px);
	}

	.empty-tip {
		color: var(--color-light);
		text-align: center;
	}

	.empty-tip p {
		margin: 0;
		padding: 0;
	}

	.empty-tip p:last-of-type {
		margin-bottom: 20px;
	}

	.empty-tip a {
		color: var(--color-light);
	}

	.cart-item {
		display: flex;
		align-items: flex-start;
		margin-bottom: 30px;
	}

	.cart-item:last-of-type {
		margin-bottom: 0;
	}

	.item-img {
		position: relative;
		display: inline-block;
		border-radius: 4px;
		border: 5px var(--theme-sub) solid;
		background: var(--color-light);
		width: 30%;
		height: auto;
		margin-right: 20px;
	}

	.item-img img {
		width: 100%;
	}

	.delete-icon {
		position: absolute;
		bottom: 0;
		left: 0;
		padding: 2px;
		color: var(--color-red);
		cursor: pointer;
		background: #ccc;
		border-top-right-radius: 75%;
	}

	.item-info {
		display: inline-block;
		width: 69%;
		color: var(--color-light);
	}

	.item-name {
		font-weight: bolder;
		margin: 0 0 5px;
		font-size: 1.2rem;
	}

	.item-specs {
		text-transform: capitalize;
	}

	.quantity-price {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: flex-end;
		margin-top: 10px;
	}

	.item-quantity {
		width: 68%;
		background: transparent;
		font-family: "impact";
		font-size: 1.4rem;
		display: flex;
		align-items: center;
	}

	.counter {
		font-size: 18px;
		width: 85%;
		background: transparent;
		color: var(--color-light);
		outline-style: none;
		border: none;
		border: 2px solid var(--color-light);
		margin: 0 10px;
		text-align: center;
	}
	
	.counter::-webkit-outer-spin-button,
	.counter::-webkit-inner-spin-button {
		-webkit-appearance: none;
	}

	.item-price {
		text-decoration: underline;
		font-family: "impact";
		font-size: 1.8rem;
	}

	.discount {
		color: orangered;
		text-decoration: line-through underline;
	}

	.check {
		height: 40px;
		width: 100%;
		display: flex;
	}
	
	.to-cart {
		margin-right: 1rem;
	}

	.check-submit,
	.to-cart {
		width: 100%;
		border: var(--theme-sub) 2px solid;
		padding: 8px;
		text-align: center;
		color: var(--color-light);
		text-decoration: none;
	}

	.check-submit:hover,
	.to-cart:hover {
		background: var(--color-light);
		color: var(--theme-sub);
		cursor: pointer;
		user-select: none;
	}
</style>
