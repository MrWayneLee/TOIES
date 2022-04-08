<template>
	<div id="indexfooter">
		<div clas="logo">
			<img src="../assets/logo.png" />
		</div>
		<div class="copyright">
			<p>Copyright @MrWayneLee All Right Reserved</p>
		</div>
	</div>
</template>

<script>
	export default {
		name: "indexfooter",
	};
</script>

<style scoped>
	#indexfooter {
		display: flex;
		justify-content: space-evenly;
		flex-direction: column;
		align-items: center;
		background: darkturquoise;
		box-sizing: border-box;
	}

	.logo {
		width: 3rem;
	}
	
	.copyright {
		box-sizing: border-box;
		background: var(--theme-main);
		padding: 1rem;
		width: 100%;
	}
	
	.copyright p {
		margin: 0;
		color: var(--color-light);
		text-align: center;
	}
</style>
