import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Top from '../views/Top.vue'
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
    component: Top
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
router.beforeEach((to, from, next) => {
// ユーザー一覧ページへアクセスした時に/topへリダイレクトする例
  if (to.path !== '/login' && Store.state.auth.token === '') {
    next('/login')
  } else {
    next()
  }
})

export default router
