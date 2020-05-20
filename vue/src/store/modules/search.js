import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    items: [
      {
        id: 5,
        name: 'Yamada',
        day: '2020-05-14'
      },
      {
        id: 2,
        name: 'Suzuki',
        day: '2020-05-15'
      },
      {
        id: 3,
        name: 'Tanaka',
        day: '2020-05-16'
      }
    ]
  },
  mutations: {
  },
  actions: {
    fetchSearch ({ commit }) {
      const url = '/api/life-graphs/search'
      axios.get(url).then(res => commit('setSearch', res.data))
    }
  }
}
