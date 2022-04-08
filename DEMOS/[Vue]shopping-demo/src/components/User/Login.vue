<template>
	<div id="login">
		<div class="login-form">
			<h1>Login</h1>
			<div class="form">
				<p>E-mail:</p>
				<input type="text" name="username" id="username" class="form-input trans03" v-model="email"/>
				<p>Password:</p>
				<input type="password" name="password" id="password" class="form-input trans03" v-model="password"/>
				<p class="tips">{{ tips }}</p>
				<input type="submit" id="" class="form-input trans03 submit" value="Sign In" @click="commitLogin"/>
				<a href="/Register" class="center">Don't have account?</a>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	
	export default {
		name: "login",
		data() {
			return {
				email: "",
				password: "",
				tips: ""
			}
		},
		methods:{
			commitLogin: function() {
				//用户名密码不允许为空
				if(!this.email.length || !this.password.length) {
					this.tips = "Invaild params."
					return
				}
				this.tips = "";
				axios({
					method: "post",
					url: "https://localhost:44344/home/getuserinfobyemail",
					//url: "http://localhost:8088/admin/login",
					data: {
						email: this.email,
						//username: this.email,
						password: this.password
					}
				}).then((response) => {
					console.log(response)
					// if(response.data.code != 200){
					// 	this.tips = "Wrong password"
					// 	return
					// }
					// window.localStorage.setItem('token', response.data.data.api_token)
					// this.$router.push('/Profile')
				})
			}
		}
	}
</script>

<style scoped>
	#login {
		width: 68.75rem;
		margin: 6rem auto;
	}
	
	h1 {
		text-align: center;
		margin: 0;
		color: var(--theme-main);
		margin-bottom: 3rem;
	}
	
	.login-form {
		margin: 0 auto;
		width: 45%;
		border: 1px solid var(--color-gray);
		border-radius: 6px;
		box-sizing: border-box;
		padding: 4rem 5rem;
	}
		
	.login-form p {
		margin: 0;
		margin-bottom: 10px;
	}
		
	.form-input {
		width: 100%;
		text-align: center;
		box-sizing: border-box;	
		border: 1px var(--color-smoke) solid;
		border-radius: 40px;
		height: 40px;
		outline: none;
		padding: 4px 10px;
		margin-bottom: 1rem;
		font-size: 16px;
	}
	
	.form-input:focus {
		border: 1px var(--theme-sub) solid;
		box-shadow: 0px 0px 1px 1px var(--theme-sub);
	}
	
	.tips {
		text-align: center;
	}
	
	.submit {
		color: var(--theme-sub);
		border: 1px var(--theme-sub) solid;
		background: var(--color-light);
		margin-top: 1rem;
	}
	
	.submit:hover {
		color: var(--color-light);
		background: var(--theme-sub);
	}
	
	.center {
		display: block;
		margin: 0 auto;
		width: fit-content;
	}
</style>
