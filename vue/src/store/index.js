import Vue from 'vue'
import Vuex from 'vuex'
import chart from './modules/chart.js'
import account from './modules/account.js'
import createPersistedState from 'vuex-persistedstate'

import auth from '@/store/modules/auth'
import http from '@/store/modules/http'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    http,
    chart,
    account
  },
  // localStorageにtokenを残し続ける
  plugins: [createPersistedState({
    key: 'example',
    storage: window.sessionStorage
  })]
})
