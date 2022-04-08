<template>
	<div id="user">
		<div class="left">
			<div class="user-menu">
				<div class="user-info">
					<div class="user-header">
						<img src="../../assets/logo.png">
					</div>
					<p>Wayne Lee</p>
				</div>
				<ul>
					<li><router-link class="trans03" to="Profile">User Infomation</router-link></li>
					<li><router-link class="trans03" to="Order">My Order</router-link></li>
					<li><router-link class="trans03" to="Account">My Account</router-link></li>
				</ul>
				<p class="log-out">Log out</p>
			</div>
		</div>
		<div class="right">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	export default {
		name: "user",
		data() {
			return {

			}
		},
		mounted() {
			if (window.localStorage.token) {

			}
		},
		methods: {
			logout() {
				//window.localStorage.setItem('token', null)
				console.log(window.localStorage)
				console.log(window.localStorage.token)
			}
		}
	}
</script>

<style scoped>
	#user {
		margin: 1rem auto;
		width: 68.75rem;
		display: flex;
	}

	.left {
		width: 25%;
		box-sizing: border-box;
	}

	.user-info {
		display: flex;
		flex-direction: column;
		align-items: center;
		box-sizing: border-box;
		border-radius: 10px;
		padding: 2rem;
		border: 1px solid var(--color-gray);
	}

	.user-header {
		width: 200px;
		height: 200px;
		box-shadow: 0px 0px 3px 3px #ccc;
		border-radius: 50%;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: hidden;
	}
	
	.user-header img {
		max-height: 200px;
		max-width: 200px;
	}

	.user-info p {
		margin: 20px 0 0;
		font-size: 20px;
	}
	
	.user-menu ul {
		margin-top: 1rem;
		list-style: none;
		padding: 0;
		box-sizing: border-box;
		border-radius: 5px;
		border: 1px solid var(--color-gray);
		box-shadow: 0px 0px 3px 1px #eaeaea;
		overflow: hidden;
	}
	
	.user-menu ul li {
		margin: 0;
		border-bottom: 1px solid var(--color-gray);
		overflow: hidden;
	}
	
	.user-menu ul li a {
		text-decoration: none;
		color: var(--color-dark);
		display: block;
		padding: 14px;
	}

	.user-menu ul li a:hover {
		background: var(--theme-main);
		color: var(--color-light);
	}
	
	.log-out {
		text-decoration: underline;
		color: var(--color-red);
		text-align: center;
		margin: 2rem 0;
	}

	.right {
		width: 75%;
		box-sizing: border-box;
		padding: 0 1rem;
	}
</style>
