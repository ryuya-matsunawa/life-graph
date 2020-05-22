import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/reset.css'
import { ValidationProvider, extend, ValidationObserver, localize } from 'vee-validate'
import { required, between } from 'vee-validate/dist/rules'
import ja from 'vee-validate/dist/locale/ja.json'
Vue.component('ValidationProvider', ValidationProvider)
Vue.component('ValidationObserver', ValidationObserver)

extend('required', required)
extend('between', between)

localize('ja', ja)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
