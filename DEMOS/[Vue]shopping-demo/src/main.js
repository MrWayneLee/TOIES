// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import axios from 'axios'
import router from './router'
import store from './store/vueX.js'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import Editor from '@tinymce/tinymce-vue';

library.add(fas, far, fab)

Vue.component('editor', Editor)
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

//每次发送请求时带上token
axios.interceptors.request.use(config => {
	config.headers.Authorization = window.localStorage.getItem('token')
	return config
})
Vue.prototype.$http = axios

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	store,
	components: { App },
	template: '<App/>'
})

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})