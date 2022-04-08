<template>
	<div id="account">
		<editor
			api-key="axvah145v3ivuuaobxc61114v17e7abn3tttel65v9dnhokr"
			v-model="editorValue"
			class="editor"
		/>
		<div class="preview-area">
			<p>Preview: </p>
			<div class="preview" v-html="editorValue"></div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				editorValue: ""
			}
		}
	}
</script>

<style>
	#account {
		width: 100%;
	}
	
	.editor {
		height: 50vh;
	}
	
	.preview-area {
		background-color: #f4f4f4;
		box-sizing: border-box;
		border-radius: 10px;
		padding: 20px;
		margin-top: 20px;
	}
	
	.preview-area p {
		margin: 0;
		margin-bottom: 20px;
	}
	
	.preview {
		box-sizing: border-box;
		background-color: #fff;
		height: 50vh;
		padding: 1rem;
		overflow-x: scroll;
		overflow-y: scroll;
	}
</style>
