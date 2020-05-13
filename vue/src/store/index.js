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
    age: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
    lifeScores: [0, 30, 30, 40, -20, 50, 50, 60, 70, 0, 0, 2, 20, 40, 22, 0, 8, 0, 2, 2, 70, 70, 70, 70, 0]
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
