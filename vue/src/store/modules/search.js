import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'あとで使う！！

Vue.use(Vuex)

export default {
  state: {
    items: [
      {
        id: 1,
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
  }
  // mutations: {
  // },
  // actions: {
  // }
}
