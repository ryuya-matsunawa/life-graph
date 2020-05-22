import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    items: []
  },
  mutations: {
    setItems (state, payload) {
      state.items = payload
    },
    deleteUser (state) {
      state.items = ''
    }
  },
  actions: {
    fetchItems ({ commit }) {
      const url = '/api/life-graphs'
      axios.get(url).then(res => commit('setItems', res.data))
    },
    deleteGraphData ({ commit }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.delete(url).then(res => commit('deleteUser', res.data))
    }
  }
}
