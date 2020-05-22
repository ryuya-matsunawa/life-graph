import axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: ''
  },
  mutations: {
    create (state, data) {
      console.log(data)
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
    }
  },
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      axios.post(url, data).then(res => commit('create', res.data))
        .catch(err => err).then(res => {
          const userId = this.$store.state.auth.userId
          this.$store.dispatch('account/fetchAccount', userId)
        })
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
