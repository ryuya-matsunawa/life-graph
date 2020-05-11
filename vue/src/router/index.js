import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Search from '../views/Search.vue'
import Edit from '../views/Edit.vue'
import Show from '../views/Show.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
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

export default router
