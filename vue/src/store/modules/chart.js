import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    contents: [],
    loaded: false,
    sample: []
  },
  mutations: {
    setGraph (state, payload) {
      state.contents = payload
      state.loaded = true
    },
    resetState (state) {
      state.contents = []
      state.loaded = false
    },
    sample (state) {
      state.sample = []
    }
  },
  actions: {
    fetchGraph ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.get(url).then(res => commit('setGraph', res.data))
        .catch(err => err)
    },
    register ({ commit }, data) {
      const url = '/api/life-graphs'
      axios.post(url, data).then(res => commit('sample', res.data))
        .catch(err => err)
    }
  }
}
