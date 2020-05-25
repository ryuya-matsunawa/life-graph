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
    fetchGraph ({ commit, rootState }, userId) {
      const url = '/api/life-graphs/' + userId
      axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('setGraph', res.data))
        .catch(err => err)
    },
    deleteItem ({ commit, rootState }, id) {
      const url = '/api/life-graphs/delete/' + id
      axios.delete(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('updateGraph', res.data))
        .catch(err => err)
    },
    register ({ commit, rootState }, data) {
      const url = '/api/life-graphs'
      axios.post(url, data, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      }).then(res => commit('updateGraph', res.data))
        .catch(err => err)
    }
  }
}
