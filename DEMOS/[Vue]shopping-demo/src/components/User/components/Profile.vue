<template>
	<div id="profile">
		<div class="user-profile">
			<h2>
				Information
				<p v-if='status.userEdit' @click="userInfoEdit">[edit]</p>
				<p v-if='status.userInfo' @click="userInfoSave">[save]</p>
			</h2>
			<div class="info-list">
				<div class="form-group user-info">
					<div class="info-title">Username:</div>
					<div class="info-content">MrWayneLee</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">Name:</div>
					<input v-if='status.userInfo' type="text" ref="name" v-model="userInfo.name" />
					<div v-if='status.userEdit' class="info-content">{{userInfo.name}}</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">E-mail:</div>
					<input v-if='status.userInfo' type="text" ref="email" v-model="userInfo.email" />
					<div v-if='status.userEdit' class="info-content">{{userInfo.email}}</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">Gender:</div>
					<div v-if='status.userInfo'>
						<select ref="gender" v-model="userInfo.gender">
							<option value="male">Male</option>
							<option value="female">Female</option>
						</select>
					</div>
					<div v-if='status.userEdit' class="info-content">{{userInfo.gender}}</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">Birth:</div>
					<input v-if='status.userInfo' type="date" ref="birth" v-model="userInfo.birth" />
					<div v-if='status.userEdit' class="info-content">{{userInfo.birth}}</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">Region:</div>
					<input v-if='status.userInfo' type="text" ref="region" v-model="userInfo.region" />
					<div v-if='status.userEdit' class="info-content">{{userInfo.region}}</div>
				</div>
				<div class="form-group user-info">
					<div class="info-title">Location:</div>
					<input v-if='status.userInfo' type="text" ref="location" v-model="userInfo.location" />
					<div v-if='status.userEdit' class="info-content">{{userInfo.location}}</div>
				</div>
				<div class="form-group user-info desc">
					<div class="info-title">Description:</div>
					<textarea v-if='status.userInfo' rows="3" ref="description"
						v-model="userInfo.description"></textarea>
					<div v-if='status.userEdit' class="info-content">{{userInfo.description}}</div>
				</div>
			</div>
			<h2>
				Delivery Address
				<p v-if='status.addressListEdit' @click="addressListEdit">[edit]</p>
				<p v-if='status.addressListInfo' @click="addressListSave">[save]</p>
			</h2>
			<div class="address-list">
				<div class="form-group address-info" v-for="(item, index) in this.addressInfo">
					<p class="w-50">
						<label>Location:</label><span :ref="'addrInfo' + index">{{ item.loaction }}</span>
						<input class="mg-l-0" type="text" v-model="item.loaction" :ref="'addrInfo' + index"
							name="location" />
					</p>
					<p class="w-50">
						<label>Zip Code:</label><span :ref="'addrInfo' + index">{{ item.zipCode }}</span>
						<input class="mg-l-0" type="text" v-model="item.zipCode" :ref="'addrInfo' + index"
							name="zipCode" />
					</p>
					<p class="w-100">
						<label>Address:</label><span :ref="'addrInfo' + index">{{ item.address }}</span>
						<input class="mg-l-0 w-75" type="text" v-model="item.address" :ref="'addrInfo' + index"
							name="address" />
					<p class="w-50">
						<label>Receiver:</label><span :ref="'addrInfo' + index">{{ item.recevier }}</span>
						<input class="mg-l-0" type="text" v-model="item.recevier" :ref="'addrInfo' + index"
							name="recevier" />
					</p>
					<p class="w-50">
						<label>TEL:</label><span :ref="'addrInfo' + index">{{ item.tel }}</span>
						<input class="mg-l-0" type="text" v-model="item.tel" :ref="'addrInfo' + index" name="tel" />
					</p>
					<div class="option trans03" :class='{ show:status.addressListInfo }'>
						<font-awesome-icon :icon="['fa', 'trash']" size="lg" @click="deleteAddressItem(index)">
						</font-awesome-icon>
						<div class="editOption trans03" :class='{ editMode: addressEditStatus[index] }'>
							<font-awesome-icon :icon="['fa', 'edit']" size="lg" color="#41b883"
								@click="editAddressItem(index)"></font-awesome-icon>
							<font-awesome-icon :icon="['fa', 'check']" size="lg" color="#41b883"
								@click="saveAddressItem(index)"></font-awesome-icon>
						</div>
					</div>
				</div>
			</div>
			<div class="add trans03" @click="addAddress()">Add adderess</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios"
	export default {
		data() {
			return {
				userInfo: {},
				addressInfo: [],
				addressEditStatus: [],
				status: {
					userEdit: true,
					userInfo: false,
					addressListEdit: true,
					addressListInfo: false,
				}
			}
		},
		mounted() {
			// axios({
			// 	method: "get",
			// 	url: "https://loacalhost:44344/home/getuserinfo",
			// 	data: {
			// 		Username: this.username
			// 	}
			// }).then(response => {
			// 	console.log(response)
			// 	this.getUserInfo(response)
			// })
		},
		methods: {
			getUserInfo: function(res) {
				res.data.data.forEach(item => {
					switch (item.type) {
						case "userInformation":
							this.initData(item.res)
							break
						default:
							break
					}
				})
			},
			initData(data) {
				this.userInfo = data.profile
				this.addressInfo = data.addressList
				data.addressList.forEach(() => {
					this.addressEditStatus.push(false)
				})
			},
			userInfoEdit: function() {
				this.status.userEdit = false
				this.status.userInfo = true
			},
			userInfoSave: function() {
				this.status.userEdit = true
				this.status.userInfo = false
			},
			addressListEdit: function() {
				this.status.addressListInfo = true
				this.status.addressListEdit = false
			},
			addressListSave: function() {
				this.status.addressListInfo = false
				this.status.addressListEdit = true
			},
			deleteAddressItem: function(index) {
				this.addressInfo.splice(index, 1)
			},
			editAddressItem: function(index) {
				this.addressEditStatus[index] = true
				this.$refs["addrInfo" + index].forEach(item => {
					if(item.tagName == "INPUT") {
						item.style.display = "block"
					} else {
						item.style.display = "none"
					}
				})
				this.$forceUpdate()
			},
			saveAddressItem: function(index) {
				this.addressEditStatus[index] = false
				this.$refs["addrInfo" + index].forEach(item => {
					if(item.tagName == "INPUT") {
						item.style.display = "none"
					} else {
						item.style.display = "block"
					}
				})
				this.$forceUpdate()
			}
		}
	}
</script>

<style scoped>
	#profile {
		width: 100%;
	}

	.user-profile {
		border: 1px solid var(--color-gray);
		padding: 1rem;
		border-radius: 5px;
	}

	h2 {
		margin: 0 1rem;
		padding: 1rem 0;
		border-bottom: 1px solid var(--color-smoke);
		display: flex;
		align-items: flex-end;
		justify-content: space-between;
	}

	h2 p {
		color: var(--color-red);
		margin: 0;
		text-decoration: underline;
		font-size: 16px;
		font-style: normal;
		cursor: pointer;
		user-select: none;
	}

	.info-list,
	.address-list {
		margin: 1rem 0;
	}

	.form-group {
		box-sizing: border-box;
		padding: 1rem;
		display: flex;
		align-items: center;
	}

	.form-group.user-info {
		height: 60px;
	}

	.form-group.desc {
		height: fit-content;
		align-items: flex-start;
	}

	.info-title {
		width: 30%;
		padding: 0 1rem;
		text-align: right;
		box-sizing: border-box;
		color: var(--color-smoke);
	}

	.info-content {
		display: flex;
		align-items: center;
		width: 70%;
		padding: 0 1rem;
		box-sizing: border-box;
	}

	.edit,
	.info {
		display: none;
	}

	input,
	textarea,
	select {
		margin-left: 1rem;
		outline: none;
		padding: 10px;
		border-radius: 6px;
		border: 1px solid var(--color-smoke);
	}

	input:focus,
	textarea:focus {
		border: 1px solid var(--color-dark);
	}

	textarea {
		width: 64.5%;
		font-size: 16px;
	}

	.form-group.address-info {
		position: relative;
		padding: 0 1rem;
		margin: 1rem;
		height: fit-content;
		display: block;
		border-bottom: 1px dashed var(--color-smoke);
		overflow: hidden;
	}

	.form-group.address-info:last-of-type {
		border: none;
	}

	.form-group.address-info p {
		height: 50px;
		box-sizing: border-box;
		margin: auto 0;
		display: inline-flex;
		align-items: center;
	}

	.form-group.address-info p label {
		margin-right: 1rem;
		color: var(--color-smoke)
	}

	p.w-50 {
		width: 49%;
	}

	p.w-100 {
		width: 100%;
	}

	.option {
		opacity: 0;
		position: absolute;
		right: 0;
		top: calc(50% - 38px);
		color: var(--color-red);
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		height: 50%;
		width: 24px;
		overflow: hidden;
	}

	.option.show {
		right: 10px;
		opacity: 1;
	}

	.option svg {
		cursor: pointer;
	}

	.mg-l-0 {
		display: none;
		margin-left: 0;
	}

	.w-75 {
		width: 75%;
	}

	.editOption {
		width: 50px;
	}

	.editOption.editMode {
		transform: translateX(-24px);
	}

	.editOption svg {
		user-select: none;
		float: left;
	}
	
	.add {
		cursor: pointer;
		user-select: none;
		text-align: center;
		margin: 0 auto;
		width: 30%;
		border-radius: 8px;
		padding: 0.8rem 1rem;
		color: var(--color-light);
		background: var(--theme-main);
	}
	
	.add:hover {
		background: var(--color-dark);
	}

	/* 	input[type='radio'] {
		opacity: 0;
		margin: 0;
		height: 22px;
		width: 22px;
		margin-right: 5px;
	}
	
	input[type='radio'] + div {
		height: 22px;
		width: 22px;
		display: flex;
		justify-content: center;
		align-items: center;
		position: absolute;
		border-radius: 3px;
		opacity: 1;
		background: var(--color-dark);
		color: var(--color-light);
	} */
</style>
