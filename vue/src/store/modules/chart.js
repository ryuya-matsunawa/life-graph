import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 一番知っていそうなパターン
// export default new Vuex.Store({
//   state: {
//     2, 20, 40, 22, 0, 8, 0, age: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
//     lifeScores: [0, 20, 30, 40, -20, 50, 50, 60, 70, 0, 0, 2, 2, 70, 70, 70, 70, 0]
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
  state: {
    contents: [
      {
        age: 1,
        lifeScores: 0,
        comment: 'コメント１'
      },

      {
        age: 2,
        lifeScores: null,
        comment: 'コメント２'
      },

      {
        age: 3,
        lifeScores: null,
        comment: 'コメント3'
      },

      {
        age: 4,
        lifeScores: null,
        comment: 'コメント4'
      },

      {
        age: 5,
        lifeScores: -20,
        comment: 'コメント5'
      },

      {
        age: 6,
        lifeScores: null,
        comment: 'コメント6'
      },

      {
        age: 7,
        lifeScores: null,
        comment: 'コメント7'
      },

      {
        age: 8,
        lifeScores: -60,
        comment: 'コメント8'
      },

      {
        age: 9,
        lifeScores: null,
        comment: 'コメント9'
      },

      {
        age: 10,
        lifeScores: null,
        comment: 'コメント10'
      },

      {
        age: 11,
        lifeScores: 0,
        comment: null
      },

      {
        age: 12,
        lifeScores: null,
        comment: 'コメント１2'
      },

      {
        age: 13,
        lifeScores: null,
        comment: 'コメント13'
      },

      {
        age: 14,
        lifeScores: 30,
        comment: 'コメント14'
      },

      {
        age: 15,
        lifeScores: null,
        comment: 'コメント15'
      },

      {
        age: 16,
        lifeScores: null,
        comment: 'コメント16'
      },

      {
        age: 17,
        lifeScores: 50,
        comment: 'コメント17'
      },

      {
        age: 18,
        lifeScores: null,
        comment: 'コメント18'
      },

      {
        age: 19,
        lifeScores: null,
        comment: 'コメント19'
      },

      {
        age: 20,
        lifeScores: 80,
        comment: 'コメント20'
      },

      {
        age: 21,
        lifeScores: null,
        comment: 'コメント21'
      },

      {
        age: 22,
        lifeScores: null,
        comment: 'コメント22'
      },

      {
        age: 23,
        lifeScores: null,
        comment: 'コメント23'
      },

      {
        age: 24,
        lifeScores: 80,
        comment: 'コメント24'
      },

      {
        age: 25,
        lifeScores: null,
        comment: 'コメント25'
      }
    ],
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
