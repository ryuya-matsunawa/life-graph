import axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: '',
    message: true,
    success: true
  },
  mutations: {
    create (state, data) {
      state.token = data.token
      state.userId = data.id
    },
    destroy (state) {
      state.userId = ''
      state.token = ''
    },
    deleteToken (state) {
      state.token = ''
      state.userId = ''
    },
    errMessage (state) {
      state.message = !state.message
    },
    accountCreate (state) {
      state.success = !state.success
    },
    passChange (state) {
      state.success = !state.success
    }
  },
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      axios.post(url, data).then(res => commit('create', res.data))
        .catch(err => commit('errMessage', err))
    },
    signup ({ commit }, data) {
      const url = '/api/auth/signup'
      axios.post(url, data).then(res => commit('accountCreate', res.data))
        .catch(err => commit('errMessage', err))
    },
    passChange ({ commit }, data) {
      const url = '/api/auth/pass-change'
      axios.post(url, data).then(res => commit('passChange', res.data))
    }
    // destroy ({ commit, dispatch }, data) {
    //   dispatch(
    //     'http/delete',
    //     { url: '/auth', data },
    //     { root: true }
    //   ).then(res => commit('create', res.data))
    //     .catch(err => err)
    //     // logout anyway ...
    //     .finally(res => commit('destroy'))
    // }
  }
}
