import Vue from "vue"
import Vuex from 'vuex'
import axios from 'axios'
Vue.use(Vuex)

export default new Vuex.Store({
	state: [],
	actions: {
		spliceItem: function(state, index) {
			this.state.splice(index, 1)
		},
		addItem: function(state, item) {
			let stateItem = this.state
			if(stateItem.length > 0) {
				for(let i in stateItem) {
					if(stateItem[i].Id == item.Id && stateItem[i].capacity == item.capacity && stateItem[i].color == item.color) {
						stateItem[i].amount ++
						stateItem[i].itemTotal = stateItem[i].amount * stateItem[i].price
						return
					}
				}
				this.state.push(item)
			}
			else {
				this.state.push(item)
			}
		},
		inAmount: function(state, index) {
			let stateItem = this.state
			this.state[index].amount ++
			stateItem[index].itemTotal = stateItem[index].amount * stateItem[index].price
		},
		deAmount: function(state, index) {
			let stateItem = this.state
			if(this.state[index].amount == 1) {
				stateItem[index].itemTotal = stateItem[index].amount * stateItem[index].price
				return
			}
			this.state[index].amount --
			stateItem[index].itemTotal = stateItem[index].amount * stateItem[index].price
		}
	},
	mutations: {
		
	}
})
