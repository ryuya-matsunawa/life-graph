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
    },
    loadDone (state, payload) {
      state.loaded = payload.loading
    }
  },
  actions: {
    fetchGraph ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.get(url).then(res => commit('setGraph', res.data))
        .catch(err => err)
    },
    load ({ commit }) {
      const loading = true
      commit('loadDone', { loading })
    }
  }
}
