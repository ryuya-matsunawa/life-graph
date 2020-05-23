import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
// import rootState from '@/store'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    account: {},
    date: {}
  },
  mutations: {
    setAccount (state, payload) {
      state.account = payload
    },
    deleteAccount (state) {
      state.account = ''
    },
    setDate (state, payload) {
      state.date = payload
    }
  },
  actions: {
    fetchAccount ({ commit }, userId) {
      const url = '/api/auth/accounts/' + userId
      axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    },
    fetchDate ({ commit }, userId) {
      console.log(userId)
      const url = '/api/life-graphs/date/' + userId
      axios.get(url).then(res => commit('setDate', res.data))
        .catch(err => err)
    }
  }
}
