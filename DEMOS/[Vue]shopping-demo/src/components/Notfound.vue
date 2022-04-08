<template>
	<div id="notfound">
		<font-awesome-icon :icon="['fas', 'times']" class="icon-error"></font-awesome-icon>
		<div class="tip"><span>404 NOT FOUND!</span></div>
		<p>The page you required is not found on this server.</p>
		<p>Please check your URL or contact our customer service.</p>
	</div>
</template>

<script>
	export default {
		name: "notfound",
	};
</script>

<style scoped>
	#notfound {
		position: relative;
		margin: 10.3rem auto;
		width: 68.75rem;
		text-align: center;
	}

	.tip {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.icon-error {
		font-size: 25rem;
		top: calc(50% - 200px);
		left: calc(50% - 137.5px);
		position: absolute;
		z-index: -1;
		opacity: 0.05;
	}

	span {
		color: var(--theme-main);
		font-style: italic;
		font-weight: bold;
		font-size: 52px;
	}

	p {
		color: var(--theme-sub);
	}
</style>
