<template>
  <div class="login">
    <video id="video" src="../assets/login.mp4" autoplay loop muted />
    <div class="comment">
      あの頃も、思い返せば綺麗だった。
    </div>
    <div class="form-item">
      <label for="username" />
      <input v-model="username" type="username" placeholder="UserName">
      <label for="password" />
      <input v-model="password" type="password" required="required" placeholder="Password">
    </div>
    <button class="button1" @click="login()">
      ログイン
    </button>
    <div class="form-footer">
      <p><a href="#">Create an account</a></p>
      <p><a href="#">Forgot password?</a></p>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      password: '',
      isValid: false
    }
  },
  computed: {
    // isValidated () {
    //   return Object.keys(this.fields).every(k => this.fields[k].validated) &&
    //     Object.keys(this.fields).every(k => this.fields[k].valid)
    // },
    token () {
      return this.$store.state.auth.token
    },
    account () {
      return this.$store.state.account.account
    }
  },
  watch: {
    // tokenの状態を監視して、tokenが更新されたらtop画面に遷移する
    token (newToken) {
      const userId = this.$store.state.auth.userId
      // ロード時にactionsにdispatchする
      this.$store.dispatch('account/fetchAccount', userId)
    },
    account (newAccount) {
      this.$router.push('/top')
    }
  },
  methods: {
    login () {
      // ログイン画面で入力したusrnameとpasswordをAPIに渡す
      this.$store.dispatch(
        'auth/create',
        {
          username: this.username,
          password: this.password
        }
      )
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  position: relative;
}

#video {
  position: relative;
}

.comment {
  position: absolute;
  color: #fff;
  font-size: 40px;
  top: 300px;
  left: 350px;
  z-index: 1;
  /* eslint-disable no-unused-vars */
  font-family: "Hannari";
  animation-name: fadeIn;
  animation-duration: 6s;
  animation-timing-function: ease-out;
  animation-delay: 0s;
  animation-iteration-count: 1;
  animation-direction: normal;
  animation-fill-mode: forwards;
}

@keyframes fadeIn { /*animetion-nameで設定した値を書く*/
  0% {opacity: 0} /*アニメーション開始時は不透明度0%*/
  100% {opacity: 1} /*アニメーション終了時は不透明度100%*/

}

.form-item {
  position: absolute;
  display: block;
  margin-top: 5px;
}

.form-item input {
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #8c8c8c;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 1em;
  height: 50px;
  transition: border-color 0.3s;
  width: 280px;
  display: block;
  margin-left: 100px;
  margin-top: 10px;
  text-align: center;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

.button1 {
  display: block;
  top: 80px;
  left: 500px;
  width: 160px;
  text-align: center;
  color: #1B1B1B;
  background: #fff;
  border-radius: 30px;
  border:1px solid #1B1B1B;
  cursor: pointer;
  /* eslint-disable no-unused-vars */
  font-family: "Hannari"
}

.button:hover{
  -webkit-animation: button 0.4s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
  -moz-animation: button 0.4s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
  animation: button .4s cubic-bezier(0.250, 0.460, 0.450, 0.940) both;
}

@-webkit-keyframes button {
  0% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
  }
  100% {
    -webkit-transform: translateY(-5px);
            transform: translateY(-5px);
  }
}

@-moz-keyframes button {
  0% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
  }
  100% {
    -webkit-transform: translateY(-5px);
            transform: translateY(-5px);
  }
}

@keyframes button {
  0% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
  }
  100% {
    -webkit-transform: translateY(-5px);
            transform: translateY(-5px);
  }
}

.form-footer {
  background-color: transparent;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  height: 50px;
  transition: border-color 0.3s;
  width: 280px;
  display: block;
  margin-left: 440px;
  margin-top: 110px;
  text-align: center;
  line-height: 190%;
}

.form-footer a {
  color: #8c8c8c;
  text-decoration: none;
  transition: border-color 0.3s;
}
.form-footer a:hover {
  border-bottom: 1px dotted #8c8c8c;
}
</style>
