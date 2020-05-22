import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    contents: [],
    loaded: false
  },
  mutations: {
    setGraph (state, payload) {
      state.contents = payload
      state.loaded = true
    },
    resetState (state) {
      state.contents = []
      state.loaded = false
    }
  },
  actions: {
    fetchGraph ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.get(url).then(res => commit('setGraph', res.data))
        .catch(err => err)
    }
  }
}
