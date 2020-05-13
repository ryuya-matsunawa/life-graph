import Vue from 'vue'
import Vuex from 'vuex'
import chart from './stores/chart.js'
import account from './stores/account.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    chart,
    account
  }
})
