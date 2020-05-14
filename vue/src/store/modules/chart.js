import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 一番知っていそうなパターン
// export default new Vuex.Store({
//   state: {
//     age: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
//     lifeScores: [0, 20, 30, 40, -20, 50, 50, 60, 70, 0, 0, 2, 20, 40, 22, 0, 8, 0, 2, 2, 70, 70, 70, 70, 0]
//   }
// })

// 公式が参考にしてって言ってたパターン
// https://github.com/vuejs/vuex/blob/dev/examples/shopping-cart/store/modules/cart.js
// const state = () => ({
//   age: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
//   lifeScores: [0, 20, 30, 40, -20, 50, 50, 60, 70, 0, 0, 2, 20, 40, 22, 0, 8, 0, 2, 2, 70, 70, 70, 70, 0]
// })

// export default {
//   namespaced: true,
//   state
// }

// キッタパターン
// https://qiita.com/tsubo/items/771d163a3c6b807a6977
export default {
  namespaced: true,
  state: {
    age: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
    lifeScores: [0, 20, 30, 40, -20, 50, 50, 60, 70, 0, 0, 2, 20, 40, 22, 0, 8, 0, 2, 2, 70, 70, 70, 70, 0],
    load: false,
    loaded: false
  },
  mutations: {
    loadDone (state, payload) {
      state.loaded = payload.loading
      state.loaded = true
    }
  },
  actions: {
    load ({ commit }) {
      const loading = ['DONE']
      commit('loadDone', { loading })
    }
  }
}
