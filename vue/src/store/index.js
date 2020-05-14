import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const config = {
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
}

export default new Vuex.Store({
  state: {
    account: {
      // TODO: delete because of test
      login: 'ng'
    },
    // lifeGraph配列の中に、上から年齢・人生スコア・コメントの配列が入っている
    lifeGraph: [
      [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],
      [0, null, null, null, -20, null, null, null, -80, null, -70, null, 20, null, null, null, 30, null, null, 70, null, null, 80, null, 80],
      ['コメント１', 'コメント２', 'コメント3', 'コメント4', 'コメント5', 'コメント6', 'コメント7', 'コメント8', 'コメント9', 'コメント10']
    ]
  },
  mutations: {
    // TODO: delete because of test
    setLogin (state, payload) {
      state.login = payload.login
    }
    // setGraphScores(state,payload) {
    //   state.age = payload;
    //   state.lifeScores = payload;
    //   // /* eslint-disable no-debugger */
    //   // debugger
    //   state.loaded = true;
    // }
  },
  actions: {
    // TODO: delete because of test
    fetchLogin ({ commit }) {
      const url = '/api/demo/login'
      axios.get(url, config).then((res) => {
        commit('setLogin', res.data)
      })
    }
  }
})
