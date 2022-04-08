<template>
	<div id="indexheader">
		<div class="head">
			<div class="logo">
				<img src="../assets/logo.png" />
				<p>Wayne.</p>
			</div>
			<div class="nav">
				<router-link to="/Home">Home</router-link>
				<router-link to="/Category">Category</router-link>
				<router-link to="/Hot">Hot</router-link>
				<router-link to="/New">New</router-link>
			</div>
			<div class="icon">
				<a class="user-menu-trigger" :class="{ active: this.qqq }" @mouseover="toggleUserMenu(true)" @mouseout="toggleUserMenu(false)" title='个人中心'><font-awesome-icon :icon="['fas', 'user']" size="lg"/></a>
				<div class="user-menu trans03" @mouseover="toggleUserMenu(true)" @mouseout="toggleUserMenu(false)">
					<ul>
						<li><router-link to="/Login">Login</router-link></li>
						<li><router-link to="/User">Account</router-link></li>
						<li><router-link to="/Register">Register</router-link></li>
					</ul>
				</div>
				<font-awesome-icon :icon="['fas', 'shopping-bag']" size="lg" @click="toggleShoppingBag"/>
				<font-awesome-icon :icon="['fas', 'search']" size="lg" @click="toggleSearchBar"/>
			</div>
			<div class="menu">
				<font-awesome-icon :icon='["fas", "bars"]' size="2x" @click="toggleMenu"></font-awesome-icon>
			</div>
		</div>
		<div class="search trans05" :class="{ hide: this.ttt }">
			<input type="text" name="key" id="key" value="" placeholder="Enter key word(s)" />
		</div>
	</div>
</template>

<script>
	import bus from "../bus.js";

	export default {
		name: "indexheader",
		data() {
			return {
				ttt: true,
				qqq: false
			};
		},
		methods: {
			toggleSearchBar: function() {
				this.ttt = !this.ttt
			},
			toggleUserMenu: function(a) {
				this.qqq = a
			},
			toggleShoppingBag: function() {
				bus.$emit("toggleShoppingCart");
			},
			toggleMenu: function() {
				bus.$emit("closeShoppingCart");
				bus.$emit("toggleMenu");
			}
		}
	}
</script>

<style scoped>
	#indexheader {
		background: darkturquoise;
	}

	.head,
	.nav {
		height: 5rem;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.logo {
		display: flex;
		align-items: center;
		margin-right: 10rem;
	}

	.logo img {
		height: 3rem;
	}

	.logo p {
		user-select: none;
		margin: 0;
		padding: 0;
		display: inline-block;
		font-family: helvetica;
		font-weight: bolder;
		font-size: 1.5rem;
		text-transform: capitalize;
		transition: 0.2s ease;
	}

	.logo p:hover {
		color: var(--color-light);
	}

	.nav {
		width: auto;
	}

	.nav a {
		width: 120px;
		text-align: center;
		user-select: none;
		font-weight: bolder;
		text-transform: uppercase;
		line-height: 5rem;
		padding: 0 1rem;
		text-decoration: none;
		color: #fff;
		box-sizing: border-box;
		transition: 0.2s ease;
	}

	.nav a:hover {
		background: darkcyan;
	}

	.nav a::after {
		display: block;
		content: "";
		height: 2px;
		width: 100%;
		margin-top: -2px;
		background: var(--color-light);
		transition: 0.2s ease;
	}

	.nav a:hover::after {
		height: 4px;
		margin-top: -4px;
		background: var(--color-light);
	}
	
	.icon {
		width: 131px;
		display: flex;
		justify-content: flex-end;
		margin-left: 10rem;
		position: relative;
	}

	.icon svg,
	.menu svg,
	.icon a {
		cursor: pointer;
		margin-right: 10px;
		color: var(--color-light);
		transition: 0.2s ease;
	}

	.icon svg:last-of-type,
	.menu svg:last-of-type {
		margin: 0;
	}

	.icon > svg:hover,
	.menu > svg:hover,
	.icon a > svg:hover {
		color: var(--color-dark);
	}
	
	.icon .user-menu-trigger.active + .user-menu {
		opacity: 1;
		top: 30px;
		z-index: 1;
	} 
	
	.user-menu {
		z-index: -1;
		opacity: 0;
		left: -20%;
		top: 20px;
		width: 140%;
		background: #fff;
		position: absolute;
		border-radius: 5px;
		border: 1px solid var(--color-gray);
		box-sizing: border-box;
		overflow: hidden;
	}
	
	.user-menu ul  {
		list-style: none;
		padding: 0;
		margin: 0;
	}
	
	.user-menu ul li {
		margin: 0
	}
	
	.user-menu ul li a {
		width: 100%;
		display: block;
		box-sizing: border-box;
		padding: 10px 1rem;
		color: var(--color-dark);
		text-decoration: none;
	}
	
	.user-menu ul li a:hover {
		background: var(--theme-main);
		color: var(--color-light);
	}

	.search {
		height: 64px;
		display: flex;
		overflow: hidden;
	}

	.search.hide {
		height: 0;
	}
	
	.menu {
		display: none;
	}

	#key {
		position: relative;
		border: none;
		background: transparent;
		border-bottom: var(--color-light) 1px solid;
		color: var(--color-light);
		outline-style: none;
		text-align: center;
		font-size: 20px;
		padding: 5px;
		margin: 20px auto 10px;
	}

	#key:focus {
		border-bottom: var(--color-light) 1px solid;
	}

	#key::placeholder {
		color: var(--color-light);
	}
	
	/* 4 | 5 | SE */
	@media (max-width:375px) {
		#indexheader{
			width: 100%;
		}
		.head {
			width: 100%;
			margin: 0 auto;
			justify-content: space-between;
		}
		.logo,
		.icon {
			margin: 0;
		}
		.head {
			width: 90%;
		}
		.nav,
		.icon {
			display: none;
		}
		.menu {
			display: block;
		}
	}
	
	/* 6 | 7 | 8 | X | Plus | Max */
	@media (min-width: 376px) and (max-width: 568px) {
		#indexheader{
			width: 100%;
		}
		.head {
			width: 90%;
			margin: 0 auto;
			justify-content: space-between;
		}
		.logo,
		.icon {
			margin: 0;
		}
		.nav,
		.icon {
			display: none;
		}
		.menu {
			display: block;
		}
	}
	
	/* iPad | iPad Pro */
	@media (min-width: 569px) and (max-width: 1023px) {
		#indexheader{
			width: 100%;
		}
		.head {
			width: 96%;
			margin: 0 auto;
			justify-content: space-between;
		}
		.nav,
		.icon {
			display: none;
		}
		.menu {
			display: block;
		}
	}
	
	@media (min-width: 1024px) and (max-width: 1366px) {
		.head {
			width: 96%;
			margin: 0 auto;
			justify-content: space-between;
		}
		.logo,
		.icon {
			margin: 0;
		}
	}
	
</style>
