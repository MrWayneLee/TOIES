<template>
	<div id="nav-menu">
		<div class="menu trans06" :class="{ hide: menuStatus }">
			<font-awesome-icon :icon="['fas', 'angle-right']" class="cancel-icon" size="2x" @click="toggleMenu">
			</font-awesome-icon>
			<p class="menu-title">Menu</p>
			<div class="menu-list">
				<ul>
					<li><router-link class="trans03" to='/Home'>Home</router-link></li>
					<li><router-link class="trans03" to="/Category">Category</router-link></li>
					<li><router-link class="trans03" to="/Hot">Hot</router-link></li>
					<li><router-link class="trans03" to="/New">New</router-link></li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script>
	import bus from "../bus.js"

	export default {
		name: "nav-menu",
		data() {
			return {
				menuStatus: true
			}
		},
		mounted() {
			var that = this
			bus.$on("toggleMenu", () => {
				that.menuStatus = !that.menuStatus
			})
		},
		methods: {
			toggleMenu: function() {
				this.menuStatus = !this.menuStatus
			}
		}
	}
</script>

<style scoped>
	.menu {
		display: none;
	}

	@media (max-width: 1024px) {
		.menu {
			display: block;
			z-index: 15;
			display: flex;
			align-content: flex-start;
			flex-wrap: wrap;
			padding: 45px 35px;
			box-sizing: border-box;
			top: 0;
			right: 0;
			position: fixed;
			height: 100vh;
			width: 280px;
			background: var(--theme-main);
			box-shadow: 0 0 4px 4px var(--color-smoke);
		}

		.menu.hide {
			right: -310px;
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

		.menu-title {
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

		.menu-list {
			margin: 30px 0 0;
			width: 100%;
		}

		ul {
			width: inherit;
			list-style: none;
			margin: 0;
			padding: 0;
		}

		li {
			width: inherit;
			margin-bottom: 1rem;
		}

		li:last-of-type {
			margin: 0;
		}

		li a {
			display: block;
			padding: 0.5rem 1rem;
			box-sizing: border-box;
			text-align: center;
			border: 5px solid transparent;
			color: var(--color-light);
			text-decoration: none;
		}

		li a:hover {
			border-left: 5px solid var(--color-light);
			border-right: 5px solid var(--color-light);
		}
	}
</style>
