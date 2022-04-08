<template>
	<div id="accessories">
		<div class="nav">
			<font-awesome-icon :icon="['fas', 'angle-double-right']" size="lg"></font-awesome-icon>
			Accessories
		</div>
	</div>
</template>

<script>
	export default {
		name: "accessories"
	}
</script>

<style scoped>
	#accessories {
		box-sizing: border-box;
		padding: 0 1rem;
		width: 100%;
	}
	
	.nav {
		font-size: 18px;
		padding: 20px 20px;
		background: var(--color-gray);
		border-radius: 5px;
		color: var(--color-dark);
	}

	.category-list {
		margin: 1rem 0;
		display: flex;
	}

	.category-item {
		text-decoration: none;
		color: var(--color-dark);
		cursor: pointer;
		display: flex;
		justify-content: center;
		align-items: center;
		border: 1px solid var(--color-gray);
		box-sizing: border-box;
		width: calc(25% - 12px);
		background: var(--color-gray);
		border-radius: 5px;
		margin-right: 1rem;
	}

	.category-item:hover {
		background: var(--color-light);
	}

	.category-item:last-of-type {
		margin-right: 0;
	}

	.category-icon {
		text-align: center;
		margin: 0.5rem 1rem 0.5rem 0;
	}

	.category-item p {
		user-select: none;
		margin: 0.5rem 0;
		text-align: center;
	}
</style>
