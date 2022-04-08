<template>
	<div id="hot">
		<div class="imgCarousel" ref="imgCarousel">
			<div class="imgList" ref="imgList">
				<div class="imgItem trans05" v-for="(item, index) in imgList" ref="imgItem"
					:class="{active: activeStatus[index]}">
					<img :src="item">
				</div>
			</div>
			<div class="imgController trans03">
				<div class="dots" v-for="(item, index) in imgList" @click="shiftImg(index)"
					:class="{active: activeStatus[index]}">
				</div>
			</div>
			<div class="carouselNext trans03" @click='nextPage()'>
				<font-awesome-icon :icon='["fas", "angle-right"]'></font-awesome-icon>
			</div>
			<div class="carouselPre trans03" @click='prePage()'>
				<font-awesome-icon :icon='["fas", "angle-left"]'></font-awesome-icon>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"

	export default {
		name: "hot",
		data() {
			return {
				imgList: [],
				imgListIndex: 0,
				activeStatus: []
			}
		},
		mounted() {
			this.getData()
		},
		methods: {
			getData() {
				axios.get("static/js/index.json").then(response => {
					if (response.status == 200 || response.statusText == "OK") {
						this.getDataSucc(response)
					}
				})
			},
			getDataSucc(res) {
				let carouselData, phoneData
				res.data.data.forEach(item => {
					switch (item.type) {
						case "carouselImg":
							carouselData = item.res
							break
						case "Phone":
							phoneData = item.res
							break
						default:
							break
					}
				})
				carouselData.forEach(item => {
					this.imgList.push(item)
					this.activeStatus.push(false)
				})
				this.initCarousel()
				this.initStatus(0)
			},
			initCarousel() {
				this.$nextTick(function() {
					var carouselWidth = this.$refs.imgCarousel.clientWidth
					this.$refs.imgItem.forEach(item => {
						item.style.width = carouselWidth + "px"
					})
				})
			},
			prePage() {
				if (this.imgListIndex == 0) {
					return
				}
				this.imgListIndex -= 1
				this.initStatus(this.imgListIndex)
			},
			nextPage() {
				if (this.imgListIndex == this.imgList.length - 1) {
					return
				}
				this.imgListIndex += 1
				this.initStatus(this.imgListIndex)
			},
			shiftImg(index) {
				this.imgListIndex = index
				this.initStatus(index)
			},
			initStatus(index) {
				for (let i in this.activeStatus) {
					this.activeStatus[i] = false
				}
				this.activeStatus[index] = true
				this.$forceUpdate()
			}
		}
	};
</script>

<style scoped>
	#hot {
		margin: 1rem auto;
		width: 68.75rem;
	}

	.imgCarousel {
		box-sizing: border-box;
		width: 100%;
		height: 550px;
		overflow: hidden;
		position: relative;
	}

	.imgCarousel:hover .imgList {
		transform: translateX(0)
	}

	.imgCarousel:hover .carouselNext,
	.imgCarousel:hover .carouselPre,
	.imgCarousel:hover .imgController {
		opacity: 1;
	}

	.imgController {
		position: absolute;
		display: flex;
		justify-content: center;
		width: 100%;
		margin: 1.1rem 0;
		bottom: 0;
		opacity: 0;
	}

	.dots {
		cursor: pointer;
		height: 14px;
		width: 14px;
		border-radius: 50%;
		background: var(--color-gray);
		margin-right: 1.2rem;
	}

	.dots.active {
		background: var(--color-smoke);
	}

	.dots:last-of-type {
		margin-right: 0;
	}

	.carouselNext,
	.carouselPre {
		height: 76px;
		width: 76px;
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		position: absolute;
		bottom: calc(50% - 38px);
		box-sizing: border-box;
		opacity: 0;
	}

	.carouselNext {
		font-size: 3rem;
		right: 0;
	}

	.carouselNext:hover,
	.carouselPre:hover {
		background: var(--color-gray);
	}

	.carouselPre {
		font-size: 3rem;
		left: 0;
	}

	.imgList {
		width: max-content;
		position: relative;
		height: 550px;
	}

	.imgList:active {
		cursor: grab;
	}

	.imgItem {
		position: absolute;
		top: 0;
		user-select: none;
		display: flex;
		height: inherit;
		z-index: -1;
		opacity: 0;
	}

	.imgItem.active {
		z-index: 1;
		opacity: 1;
	}

	.imgItem img {
		margin: 0 auto;
		height: inherit;
	}
</style>
