<template>
	<div id="register">
		<div class="login-form">
			<h1>Register</h1>
			<div class="form">
				<p>E-mail:</p>
				<input type="text" name="email" id="email" class="form-input trans03" v-model="email"/>
				<p>Username:</p>
				<input type="text" name="username" id="username" class="form-input trans03" v-model="username"/>
				<p>Password:</p>
				<input type="password" name="password" id="password" class="form-input trans03" v-model="password"/>
				<p>Name:</p>
				<input type="text" name="name" id="name" class="form-input trans03" v-model="name"/>
				<p>Gender:</p>
				<input type="radio" name="gender" value="male" class="trans03" v-model="gender"/>Male
				<input type="radio" name="gender" value="female" class="trans03" v-model="gender"/>Female
				<p class="tips">{{ tips }}</p>
				<input type="submit" id="" class="form-input trans03 submit" value="Submit" @click="commitRegister"/>
				<a href="/Login" class="center">Back to Login</a>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	export default{
		name: "register",
		data() {
			return {
				email: "",
				password: "",
				username: "",
				name: "",
				gender: "",
				tips: ""
			}
		},
		methods: {
			commitRegister: function() {
				if(!this.email.length || !this.password.length) {
					this.tips = "Invaild params."
					return
				}
				this.tips = ""
				axios({
					method: "post",
					url: "https://localhost:44344/home/addUsername",
					data: {
						Email: this.email,
						Password: this.password,
						Username: this.username,
						Name: this.name,
						Gender: this.gender,
						Region: "China",
						Location: "Wuhan",
						Description: ""
					}
				}).then((response) => {
					console.log(response)
					if(response.data){
						this.$router.push('/User/Profile')
					}
				})
			}
		}
	}
</script>

<style scoped>
	#register {
		width: 88.75rem;
		margin: 0rem auto;
	}
	
	h1 {
		text-align: center;
		margin: 0;
		color: var(--theme-main);
		margin-bottom: 2rem;
	}
	
	.login-form {
		margin: 0 auto;
		width: 45%;
		border-radius: 6px;
		box-sizing: border-box;
		padding: 4rem 5rem;
	}
		
	.login-form p {
		margin: 0;
		margin-bottom: 10px;
	}
	
	.form {
		width: 70%;
		margin: 0 auto;
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
