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
    contents: [
      {
      age: 1,
      lifeScores: 0,
      comment: 'コメント１'
      },

      {
      age: 2,
      lifeScores: 20,
      comment: 'コメント２'
      },

      {
      age: 3,
      lifeScores: 30,
      comment: 'コメント3'
      },

      {
      age: 4,
      lifeScores: 40,
      comment: 'コメント4'
      },

      {
      age: 5,
      lifeScores: -20,
      comment: 'コメント5'
      },

      {
      age: 6,
      lifeScores: 50,
      comment: 'コメント6'
      },

      {
      age: 7,
      lifeScores: 50,
      comment: 'コメント7'
      },

      {
      age: 8,
      lifeScores: 60,
      comment: 'コメント8'
      },

      {
      age: 9,
      lifeScores: 70,
      comment: 'コメント9'
      },

      {
      age: 10,
      lifeScores: 0,
      comment: 'コメント10'
      },

      {
      age: 11,
      lifeScores: 0,
      comment: null
      }
      ],    load: false,
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
