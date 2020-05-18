import Vue from 'vue'
import Vuex from 'vuex'
import chart from './modules/chart.js'
import account from './modules/account.js'
import search from './modules/search.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    chart,
    account,
    search
  }
})
