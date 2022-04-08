<template>
	<div id="category">
		<div class="left">
			<p><font-awesome-icon :icon="['fas', 'bars']"></font-awesome-icon>Categories</p>
			<ul>
				<li class="trans03"><router-link to="/Category/Phone">Phone</router-link></li>
				<li class="trans03"><router-link to="/Category/Laptop">Laptop</router-link></li>
				<li class="trans03"><router-link to="/Category/Tablet">Tablet</router-link></li>
				<li class="trans03"><router-link to="/Category/Gaming">Gaming</router-link></li>
				<li class="trans03"><router-link to="/Category/Accessories">Accessories</router-link></li>
			</ul>
		</div>
		<div class="right">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	import axios from "axios";
	export default {
		name: "category",
		data() {
			return {
				allData: []
			}
		},
		methods: {
			getData: function(res) {
				this.allData = res.data.data
			},
			getDataSucc: function() {
				axios.get("/static/js/index.json").then(response => {
					this.getData(response)
				});
			}
		},
		mounted() {
			this.getDataSucc()
		},
	};
</script>

<style scoped>
	#category {
		box-sizing: border-box;
		margin: 20px auto;
		width: 68.75rem;
		display: flex;
		align-items: flex-start;
	}

	.left {
		box-sizing: border-box;
		background: var(--color-gray);
		width: 25%;
		padding: 1rem;
	}

	.left svg {
		margin-right: 10px;
	}

	.left p {
		background: var(--color-gray);
		user-select: none;
		font-size: 22px;
		margin: 0;
		padding: 0;
	}

	.left ul {
		list-style: none;
		margin: 20px 0 0 0;
		padding: 0;
	}

	.left ul li {
		box-sizing: border-box;
		margin-bottom: 10px;
		padding: 5px 20px;
		font-size: 1rem;
		border-left: var(--theme-sub) 8px solid;
		border-bottom: var(--theme-sub) 1px solid;
		border-right: var(--color-gray) 1px solid;
		border-top: var(--color-gray) 1px solid;
	}

	.left ul li:hover {
		border-right: var(--theme-sub) 1px solid;
		border-top: var(--theme-sub) 1px solid;
	}

	.left ul li:last-of-type {
		margin-bottom: 0;
	}

	.left ul a {
		display: block;
		text-decoration: none;
		color: var(--theme-main);
	}

	.right {
		width: 75%;
	}
	
	@media (max-width: 375px) {
		#category {
			width: 90%;
			flex-direction: column;
		}
		
		.left,
		.right {
			width: 100%;
		}
		
		.right {
			margin-top: 1rem;
		}
		
	}
	
	@media (min-width: 376px) and (max-width: 568px) {
		#category {
			width: 90%;
			flex-direction: column;
		}
		
		.left,
		.right {
			width: 100%;
		}
		
		.right {
			margin-top: 1rem;
		}
	}
	
	@media (min-width: 569px) and (max-width: 812px) {
		#category {
			width: 96%;
			flex-direction: column;
		}
		
		.left,
		.right {
			width: 100%;
		}
		
		.right {
			margin-top: 1rem;
		}
		
		.left ul li {
			float: left;
			border: var(--theme-sub) 1px solid;
			width: 50%;
			margin-bottom: 0;
			font-size: 1rem;
			padding: 0.5rem 20px;
			border-radius: 6px;
		}
	}
	
	@media (min-width: 813px) and (max-width: 1024px) {
		#category {
			width: 96%;
		}
	}
</style>
