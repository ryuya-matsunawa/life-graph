import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    contents: [],
    loaded: false,
    update: false
  },
  mutations: {
    setGraph (state, payload) {
      state.contents = payload
      state.loaded = !state.loaded
    },
    resetState (state) {
      state.contents = []
    },
    updateGraph (state) {
      state.update = !state.update
    }
  },
  actions: {
    fetchGraph ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.get(url).then(res => commit('setGraph', res.data))
        .catch(err => err)
    },
    deleteItem ({ commit }, id) {
      const url = '/api/life-graphs/delete/' + id
      axios.post(url).then(res => commit('updateGraph', res.data))
        .catch(err => err)
    },
    register ({ commit }, data) {
      const url = '/api/life-graphs'
      axios.post(url, data).then(res => commit('updateGraph', res.data))
        .catch(err => err)
    }
  }
}
