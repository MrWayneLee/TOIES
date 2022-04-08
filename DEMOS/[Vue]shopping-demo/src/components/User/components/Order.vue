<template>
	<div id="order">
		<div class="user-order">
			<h2>Orders</h2>
			<div class="order-list">
				<!-- <div class="input-range">
					<input type="range" v-model="rangeValue" step="0.01" min="0.01" max="100.01" @mousedown="setRandNum()">
					<div class="rangeValue">{{ rangeValue }}</div>
					<div class="rangeValue">{{ clickTime }}</div>
				</div> -->
				<div class="demo">
					<div class="demo-row" v-for="(item, index) in this.demoTitle">
						<div class="col-title">{{ item }}</div>
						<div class="col-val">
							<input :type="returnInputType(index)" v-for="lang in returnInputNum(index)"/>
							
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				rangeValue: "0.01",
				clickTime: "0",
				
				demoTitle: [
					"product_id",
					"product_model",
					"product_category",
					"product_name",
					"product_brand",
					"product_description",
					"product_color",
					"product_capacity",
					"product_ram"
				],
				demoInputType: [
					"text",
					"text",
					"text",
					"text",
					"text",
					"text",
					"radio",
					"radio",
					"radio"
				],
				demoInputNumber: [
					"1",
					"1",
					"1",
					"1",
					"1",
					"1",
					"1",
					["name", "age"],
					["male", "female"],
				]
			}
		},
		methods: {
			setRandNum: function() {
				this.clickTime ++
				var remain = Math.round(this.rangeValue % 1 * 1000) / 1000
				console.log(remain.toString().substr(2, 4).length)
				if(!remain.toString().substr(2, 4).length) {
					console.log("You Win!")
				}
			},
			returnInputType: function(index) {
				return this.demoInputType[index]
			},
			returnInputNum: function(index) {
				return this.demoInputNumber[index].length
			}
		},
		computed: {
			
		}
	}
</script>

<style scoped>
	#order {
		width: 100%;
	}
	
	.user-order {
		border: 1px solid var(--color-gray);
		padding: 1rem;
		border-radius: 5px;
	}
	
	.user-order h2 {
		margin: 0 1rem;
		padding: 1rem 0;
		border-bottom: 1px solid var(--color-smoke);
	}
	
	.order-list {
		margin: 1rem 0;
	}
	
	.input-range {
		width: 800px;
		display: flex;
		align-items: center;
	}
	
	.input-range input[type="range"] {
		display: inline-block;
		width: calc(100% - 136px);
		outline: none;
	}
	
	.rangeValue {
		width: 68px;
		display: inline-block;
		text-align: center;
	}
	
	.demo {
		padding: 0 1rem;
	}
	
	.demo-row {
		display: flex;
		padding: 0.5rem;
		margin-bottom: 1rem;
	}
	
	.demo-row:last-of-type {
		margin-bottom: 0rem;
	}
	
	
</style>
