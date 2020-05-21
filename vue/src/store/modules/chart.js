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
    // ここでaxiosを使ってAPIと連携する。
    // その時引数で渡すことができる
    fetchGraph ({ commit }, userid) {
      const url = '/api/life-graphs/' + userid
      axios.get(url).then(res => commit('setGraph', res.data))
        .catch(err => err)
    },
    load ({ commit }) {
      const loading = true
      commit('loadDone', { loading })
    }
  }
}
