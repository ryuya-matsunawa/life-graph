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
    setItems (state, payload) {
      state.items = payload
    }
  },
  actions: {
    fetchItems ({ commit }) {
      const url = '/api/life-graphs'
      axios.get(url).then(res => commit('setItems', res.data))
    }
  }
}
