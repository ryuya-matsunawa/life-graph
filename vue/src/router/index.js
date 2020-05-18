import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Search from '../views/Search.vue'
import Edit from '../views/Edit.vue'
import Show from '../views/Show.vue'

// 作業する時は下のコメントアウトしとくと良い、あと一番下のも
// import Store from '@/store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/top',
    name: 'Top',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/Top.vue')
    }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit
  },
  {
    path: '/show',
    name: 'Show',
    component: Show
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// authのstateのtokenが’’だったらログイン画面に遷移させる
// 作業する時は下全部コメントアウトしてStoreをインポートしているとこもコメントアウトして
// router.beforeEach((to, from, next) => {
//   // ユーザー一覧ページへアクセスした時に/topへリダイレクトする例
//   if (to.path === '/top' && Store.state.auth.token === '') {
//     next('/login')
//   } else if (to.path === '/' && Store.state.auth.token === '') {
//     next('/login')
//   } else if (to.path === '/edit' && Store.state.auth.token === '') {
//     next('/login')
//   } else if (to.path === '/show' && Store.state.auth.token === '') {
//     next('/login')
//   } else if (to.path === '/search' && Store.state.auth.token === '') {
//     next('/login')
//   } else {
//     next()
//   }
// })

export default router
