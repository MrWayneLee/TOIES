<template>
	<div id="shopping-cart">
		<div class="cart-title">
			<h1>Shopping Cart</h1>
		</div>
		<div class="cart-items">
			<div class="empty-tip" v-if="tipStatus">
				<p>Your cart is empty.>_<</p>
				<p>Want some shopping?</p>
				<router-link to="/Home">Let's Go!</router-link>
			</div>
			<div class="cart-item" v-for="(item, index) in this.$store.state">
				<div class="item-img">
					<img :src='item.imgUrl' />
				</div>
				<div class="item-desc">
					<div class="item-title">{{item.name}}</div>
					<div class="item-spec">{{item.color}}</div>
					<div class="item-spec">{{item.capacity}}GB</div>
				</div>
				<div class="item-price">
					<p>{{item.price}}￥</p>
				</div>
				<div class="item-amount">
					<div class="counter">
						<div class="counter-decrease trans03" @click="decreaseAmount(index)">
							<font-awesome-icon :icon='["fas", "minus"]'></font-awesome-icon>
						</div>
						<input class="counter-number" type="number" v-model="item.amount">
						<div class="counter-increase trans03" @click="increaseAmount(index)">
							<font-awesome-icon :icon='["fas", "plus"]'></font-awesome-icon>
						</div>
					</div>
				</div>
				<div class="item-total">
					<p>{{item.itemTotal}}￥</p>
					<div class="remove-item" @click="removeItem(index)">Remove</div>
				</div>
			</div>
		</div>
		<div class="submit">
			<div class="price-total">Total:{{total(this.$store.state)}}￥</div>
			<div class="submition">
				<div class="checkout">Proceed to checkout</div>
				<div class="paypal">Paypal</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	
	export default {
		name: "shopping-cart",
		data() {
			return {
				tipStatus: true,
			}
		},
		methods: {
			decreaseAmount(index) {
				this.$store.dispatch('deAmount', index)
			},
			increaseAmount(index) {
				this.$store.dispatch('inAmount', index)
			},
			removeItem(index) {
				this.$store.dispatch('spliceItem', index)
			}
		},
		computed: {
			total() {
				return function(list) {
					let total = 0
					list.forEach(item => {
						total += item.itemTotal
					})
					return total
				}
			},
			storeLength() {
				return this.$store.state.length
			}
		},
		watch: {
			storeLength(curValue, oldValue) {
				if (curValue == 0) {
					this.tipStatus = true
				} else {
					this.tipStatus = false
				}
			}
		}
	}
</script>

<style scoped>
	#shopping-cart {
		box-sizing: border-box;
		width: 68.75rem;
		margin: 5rem auto;
	}
	
	.cart-title {
		box-sizing: border-box;
		border-bottom: 1px solid var(--color-dark);
	}
	
	.cart-items {
		padding: 1rem 0;
		border-bottom: 1px solid var(--color-dark);
	}
	
	.cart-item {
		display: flex;
		align-items: flex-start;
		padding: 1rem 0;
	}
		
	.item-img {
		display: flex;
		justify-content: center;
		align-items: center;
		user-select: none;
		height: 12.5rem;
		width: 12.5rem;
		padding: 0.8rem;
		box-sizing:border-box;
		border: 1px solid var(--color-gray);
		border-radius: 8px;
	}
	
	.item-img img {
		max-height: 100%;
		max-width: 100%;
	}
	
	.item-desc {
		width: 20.85rem;
		margin: 0 2rem;
		padding-top: 1rem;
	}
	
	.item-title {
		color: var(--theme-main);
		font-size: 32px;
		font-weight: bolder;
		margin-bottom: 1rem;
	}
	
	.item-spec {
		color: var(--theme-sub);
		font-size: 22px;
		margin-bottom: 0.5rem;
		text-transform: capitalize;
	}
	
	.item-price {
		width: 5rem;
		padding-top: 1rem;
		margin-right: 2rem;
	}
	
	.item-price p {
		margin: 0;
		font-size: 22px;
	}
	
	.item-amount {
		width: 10.25rem;
	}
	
	.counter {
		display: flex;
		margin-top: 1rem;
		box-sizing: border-box;
		justify-content: center;
	}
	
	.counter-decrease,
	.counter-increase {
		user-select: none;
		box-sizing: border-box;
		width: 16%;
		text-align: center;
		cursor: pointer;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.counter-decrease:hover,
	.counter-increase:hover {
		color: var(--color-light);
		background: var(--color-dark);
	}
	
	.counter-number {
		width: 33.3%;
		text-align: center;
		outline: none;
		border: none;
		font-size: 20px;
		user-select: none;
	}
	
	.counter-number::-webkit-outer-spin-button,
	.counter-number::-webkit-inner-spin-button {
		-webkit-appearance: none;
	}
	
	.item-total {
		display: flex;
		align-items: flex-end;
		flex-direction: column;
		justify-content: space-between;
		width: 14.15rem;
	}
	
	.item-total p {
		margin-top: 1rem;
		margin-bottom: 0;
		color: var(--color-red);
		font-size: 38px;
		font-family: impact;
		text-align: center;
	}
	
	.remove-item {
		text-decoration: underline;
		font-size: 18px;
		margin-top: 7rem;
		margin-right: 1rem;
		cursor: pointer;
	}
	
	.submit {
		padding-top: 2rem;
		text-align: right;
	}
	
	.price-total {
		font-size: 38px;
	}
	
	.submition {
		margin-top: 2rem;
		display: flex;
		justify-content: flex-end;
	}
	
	.checkout,
	.paypal {
		margin-left: 1rem;
		user-select: none;
		padding: 1rem 2rem;
		border: 2px solid var(--color-dark);
		box-sizing: border-box;
	}
	
	.empty-tip {
		margin: 5rem 0;
		width: 100%;
		text-align: center;
	}
	
	.empty-tip * {
		color: var(--theme-sub);
		margin: 0;
		font-size: 22px;
	}
	
	.hidden {
		display: none;
	}
	
	
</style>
