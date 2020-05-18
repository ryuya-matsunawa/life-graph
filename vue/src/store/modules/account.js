import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    account: []
  },
  mutations: {
    setAccount (state, payload) {
      // APIから取得したアカウント情報の中からauth.jsのstateのuserIDと一致しているものだけを抽出したい
      const filterAccount = payload.filter((account) => account.id === 2)
      state.account = filterAccount
    }
  },
  actions: {
    async fetchAccount ({ commit }) {
      // const authId = rootState.auth.userId 他のmoduleからstateを取得したいがうまく行かない、、
      const url = '/api/auth/account'
      await axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    }
  // },
  // getters: {
  //   getAccount: (state) => {
  //     return state.account.length > 0
  //   }
  }
}
