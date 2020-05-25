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
    deleteGraphData (state) {
      state.items = ''
    }
  },
  actions: {
    fetchItems ({ commit, rootState }) {
      const url = '/api/life-graphs'
      axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('setItems', res.data))
    },
    deleteGraphData ({ commit, rootState }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.delete(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('deleteGraphData', res.data))
    }
  }
}
