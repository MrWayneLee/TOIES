import Vue from 'vue'
import Router from "vue-router"
//import Home from '@/components/Home/Home.vue'
// import Category from '@/components/Category/Category.vue'
// import CategoryPhone from '@/components/Category/components/Phone.vue'
// import CategoryLaptop from '@/components/Category/components/Laptop.vue'
// import CategoryTablet from '@/components/Category/components/Tablet.vue'
// import CategoryGaming from '@/components/Category/components/Gaming.vue'
// import CategoryAccessories from '@/components/Category/components/Accessories.vue'
// import Hot from '@/components/Hot/Hot.vue'
// import New from '@/components/New/New.vue'
// import Product from '@/components/Product/Product.vue'
// import ShoppingCart from '@/components/ShoppingCart/ShoppingCart.vue'
// import Login from '@/components/User/Login.vue'
// import User from '@/components/User/User.vue'
// import UserProfile from '@/components/User/components/Profile.vue'
// import UserOrder from '@/components/User/components/Order.vue'
// import UserAccount from '@/components/User/components/Account.vue'
// import ERROR from '@/components/Notfound.vue'

Vue.use(Router)

export default new Router({
	routes: [{
		path: "*",
		component: () => import ('@/components/Notfound.vue'),
		meta: {
			title: "404 not found"
		}
	}, {
		path: "/",
		redirect: "/Home",
		meta: {
			title: "Home"
		}
	}, {
		path: "/Home",
		name: 'Home',
		component: () => import ('@/components/Home/Home.vue'),
		meta: {
			title: "Home"
		}
	}, {
		path: "/Category",
		component: () => import ('@/components/Category/Category.vue'),
		children: [{
			path: "",
			redirect: "Phone"
		}, {
			path: "Phone",
			name: 'Phone',
			component: () => import ('@/components/Category/components/Phone.vue'),
			meta: {
				title: "Phone"
			}
		}, {
			path: "Phone/:page",
			component: () => import ('@/components/Category/components/Phone.vue'),
			meta: {
				title: "Phone"
			}
		}, {
			path: "Laptop",
			name: 'Laptop',
			component: () => import ('@/components/Category/components/Laptop.vue'),
			meta: {
				title: "Laptop"
			}
		}, {
			path: "Tablet",
			name: 'Tablet',
			component: () => import ('@/components/Category/components/Tablet.vue'),
			meta: {
				title: "Tablet"
			}
		}, {
			path: "Gaming",
			name: 'Gaming',
			component: () => import ('@/components/Category/components/Gaming.vue'),
			meta: {
				title: "Gaming"
			}
		}, {
			path: "Accessories",
			name: 'Accessories',
			component: () => import ('@/components/Category/components/Accessories.vue'),
			meta: {
				title: "Accessories"
			}
		}]
	}, {
		path: "/Hot",
		name: 'Hot',
		component: () => import ('@/components/Hot/Hot.vue'),
		meta: {
			title: "Hot"
		}
	}, {
		path: "/New",
		name: 'New',
		component: () => import ('@/components/New/New.vue'),
		meta: {
			title: "New"
		}
	}, {
		path: "/Product/:id",
		name: 'Product',
		component: () => import ('@/components/Product/Product.vue'),
		meta: {
			title: "Product"
		}
	}, {
		path: "/ShoppingCart",
		name: 'ShoppingCart',
		component: () => import ('@/components/ShoppingCart/ShoppingCart.vue'),
		meta: {
			title: "ShoppingCart"
		}
	}, {
		path: "/Login",
		name: 'Login',
		component: () => import ('@/components/User/Login.vue'),
		meta: {
			title: "Login"
		}
	}, {
		path: "/User",
		component: () => import ('@/components/User/User.vue'),
		meta: {
			title: "User"
		},
		children: [{
			path: "",
			redirect: "Profile"
		}, {
			path: "Profile",
			name: 'Profile',
			component: () => import ('@/components/User/components/Profile.vue'),
			meta: {
				title: "Profile"
			}
		}, {
			path: "Order",
			name: 'Order',
			component: () => import ('@/components/User/components/Order.vue'),
			meta: {
				title: "Order"
			}
		}, {
			path: "Account",
			name: 'Account',
			component: () => import ('@/components/User/components/Account.vue'),
			meta: {
				title: "Account"
			}
		}]
	}, {
		path: "/Register",
		name:'Register',
		component: () => import ('@/components/User/Register.vue'),
		meta: {
			title: "Register"
		}
	}],
	mode: 'history'
})

// router.beforeEach((to, from, next) => {
// 	if (to.path === '/Account') {
// 		next();
// 	} else {
// 		let token = localStorage.getItem('Authorization');

// 		if (token === null || token === '') {
// 			next('/Login');
// 		} else {
// 			next();
// 		}
// 	}
// });

