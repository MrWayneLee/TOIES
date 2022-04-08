<template>
	<div id="app">
		<indexheader></indexheader>
		<router-view></router-view>
		<indexfooter></indexfooter>
		<cart></cart>
		<navmenu></navmenu>
	</div>
</template>

<script>
	import indexheader from "./components/Header.vue";
	import indexfooter from "./components/Footer.vue";
	import cart from "./components/Cart.vue";
	import navmenu from "./components/Menu.vue";

	export default {
		name: "app",
		data() {
			return {};
		},
		components: {
			indexheader,
			indexfooter,
			cart,
			navmenu
		},
	};
</script>

<style>
	@import url("./assets/roots.css");

	html {
		filter: grayscale(0%);
		font-family: Helvetica Neue,Helvetica,Arial,sans-serif
	}
	
	body {
		margin: 0;
		overflow-x: hidden;
	}
</style>
