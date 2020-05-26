<template>
  <div>
    <div v-if="loginDialog" class="loginSection">
      <video id="video" src="../assets/login.mp4" autoplay loop muted />
      <div class="comment">
        あの頃も、思い返せば綺麗だった。
      </div>
      <div class="form-item">
        <span v-if="!errMessage" class="loginNoAlert" />
        <span v-if="errMessage" class="loginAlert">メールアドレスまたはパスワードが間違っています</span>
        <label for="email" />
        <input v-model="email" type="email" placeholder="Email" @keypress.enter="login">
        <span v-if="!isErrorEmail" class="loginNoAlert" />
        <span v-if="isErrorEmail" class="loginAlert">emailアドレスを入力してください</span>
        <label for="password" />
        <input v-model="password" type="password" required="required" placeholder="Password" @keypress.enter="login">
        <span v-if="!isErrorEmail" class="loginNoAlert" />
        <span v-if="isErrorPassword" class="loginAlert">パスワードを入力してください</span>
      </div>
      <button class="login" @click="login()">
        ログイン
      </button>
      <div class="form-footer">
        <p @click="signup()">
          Create an account
        </p>
      </div>
    </div>
    <div v-if="signupDialog" class="file">
      <div class="formOut">
        <label for="name" />
        ユーザ名
        <input v-model="username" type="text" placeholder="UserName">
        <span v-if="!isErrorUsername" class="loginNoAlert" />
        <span v-if="isErrorUsername" class="loginAlert">ユーザー名を入力してください</span>
        <label for="email" />
        メールアドレス
        <input v-model="email" type="email" placeholder="Email">
        <span v-if="!isErrorUsername" class="loginNoAlert" />
        <span v-if="isErrorEmail" class="loginAlert">メールアドレスを入力してきださい</span>
        <label for="password" />
        パスワード
        <input v-model="password" type="password" required="required" placeholder="Password">
        <span v-if="!isErrorUsername" class="loginNoAlert" />
        <span v-if="isErrorPassword" class="loginAlert">パスワードを入力してください</span>
        <span v-if="errMessage" class="loginAlert">ユーザ名またはメールアドレスはすでに使われています。</span>
        <button
          class="createButton"
          @click="loginChange()"
        >
          Create an account
        </button>
        <button
          class="returnButton"
          @click="returnLogin()"
        >
          Return
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      email: '',
      password: '',
      errMessage: false,
      isValid: false,
      loginDialog: true,
      signupDialog: false,
      // バリデーションエラー
      isErrorUsername: false,
      isErrorPassword: false,
      isErrorEmail: false,
      // バリデーション用の正規表現
      usernameValidation: /^.{1,}$/,
      emailValidation: /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/,
      passwordValidation: /^[A-Za-z0-9]{1,}$/
    }
  },
  computed: {
    token () {
      return this.$store.state.auth.token
    },
    account () {
      return this.$store.state.account.account
    },
    err () {
      return this.$store.state.auth.message
    },
    success () {
      return this.$store.state.auth.success
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
    },
    err () {
      this.errMessage = true
    },
    success () {
      this.loginDialog = true
      this.signupDialog = false
      this.errMessage = false
    }
  },
  methods: {
    activeSingup () {
      // 全部のバリデーションが正常に動いているかチェックするため
      // trueの数を数えるvalidationChech
      let singupValidationCheck = 0

      if (this.usernameValidation.test(this.username)) {
        // 大丈夫なら+1
        singupValidationCheck++
        // エラーの表示off
        this.isErrorUsername = false
      } else {
        // エラーの表示on
        this.isErrorUsername = true
      }
      /**
       * "@""."を挟んだ文字列である
       * 決められた範囲ならtrue
       */
      if (this.emailValidation.test(this.email)) {
        // 大丈夫なら+1
        singupValidationCheck++
        // エラーの表示off
        this.isErrorEmail = false
      } else {
        // エラーの表示on
        this.isErrorEmail = true
      }
      /**
       * 英数１文字以上入っているか
       * 決められた範囲ならtrue
       */
      if (this.passwordValidation.test(this.password)) {
        // 大丈夫なら+1
        singupValidationCheck++
        this.isErrorPassword = false
      } else {
        // エラーの表示on
        this.isErrorPassword = true
      }
      /**
       * コメントが200字以下であるかの判定
       * 決められた範囲ならtrue
       */
      return singupValidationCheck
    },
    activeLogin () {
      // 全部のバリデーションが正常に動いているかチェックするため
      // trueの数を数えるvalidationChech
      let loginValidationCheck = 0
      /**
       * "@"を挟んだ文字列である
       * 決められた範囲ならtrue
       */
      if (this.emailValidation.test(this.email)) {
        // 大丈夫なら+1
        loginValidationCheck++
        // エラーの表示off
        this.isErrorEmail = false
      } else {
        // エラーの表示on
        this.isErrorEmail = true
      }
      /**
       * editAgeが+or-の,100もしくは0~99であるかどうかの判定
       * 決められた範囲ならtrue
       */
      if (this.passwordValidation.test(this.password)) {
        // 大丈夫なら+1
        loginValidationCheck++
        // エラーの表示off
        this.isErrorPassword = false
      } else {
        // エラーの表示on
        this.isErrorPassword = true
      }
      /**
       * コメントが200字以下であるかの判定
       * 決められた範囲ならtrue
       */
      return loginValidationCheck
    },
    login () {
      this.errMessage = false
      if (this.activeLogin() === 2) {
      // ログイン画面で入力したusrnameとpasswordをAPIに渡す
        this.$store.dispatch(
          'auth/create',
          {
            email: this.email,
            password: this.password
          }
        )
      }
    },
    signup () {
      this.loginDialog = false
      this.signupDialog = true
      this.isErrorUsername = false
      this.isErrorPassword = false
      this.isErrorEmail = false
    },
    loginChange () {
      if (this.activeSingup() === 3) {
        this.$store.dispatch(
          'auth/signup',
          {
            username: this.username,
            email: this.email,
            password: this.password,
            role: ['user']
          }
        )
      }
    },
    returnLogin () {
      this.loginDialog = true
      this.signupDialog = false
<<<<<<< HEAD
=======
      this.isErrorEmail = false
      this.isErrorPassword = false
>>>>>>> develop
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
  width: 640px;
  top: 300px;
  left: 350px;
  z-index: 1;
  font-family: "Hannari", serif;
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

.file {
  width: 600px;
  margin: 0 auto;
  margin-top: 30px;
  border: 1px solid;
  padding: 30px;
  border-radius: 8px;
  border-color: #c0c0c0;
  width: 50%;
  background-color:  #efeeee;
}

.formOut{
  display: inline-block;
  width: 300px;
  margin: 5px 0px 5px 0px;
  font-weight: bold;
  font-size: 18px;
  font-family: 'Noto Serif JP', serif;
}

.formOut label{
  width: 30px;
  height: 30px;
  margin: 20px 0px 5px 0px;
  vertical-align: middle;
  display:inline-block;
}

.formOut input {
  width: 100px;
  margin: 0px 0px 5px 0px;
  display:inline-block;
  vertical-align: middle;
}

.createButton {
  padding: 10px 20px;
  color:#353434;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:10px;
  cursor: pointer;
  font-family: 'Hannari', serif;
  border-radius: 8px;
}

.createButton:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.returnButton {
  padding: 10px 20px;
  color:#353434;
  border-color:#a39d9d;
  max-width:960px;
  text-align:center;
  position:relative;
  margin-top:10px;
  margin-left: 10px;
  cursor: pointer;
  font-family: 'Hannari', serif;
  border-radius: 8px;
}

.returnButton:hover{
  color:#fff;
  background-color:#a39d9d;
  border-color:#a39d9d;
}

.createform {
  display: flex;
}

.form-item {
  position: absolute;
  display: block;
  margin-top: 5px;
  margin-left: 180px;
}

.form-item input {
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #8c8c8c;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 16px;
  height: 50px;
  transition: border-color 0.3s;
  width: 280px;
  display: block;
  margin-left: 30px;
  text-align: center;
}

.loginNoAlert{
  display: block;
  text-align: center;
  margin: 0px 0px 0px 0px;
  height: 16px;
}

.loginAlert{
  background-color: #FADBDA;
  border: none;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 10px;
  height: 16px;
  transition: border-color 0.3s;
  width: 280px;
  display: inline-block;
  text-align: center;
  padding: 4px 0px 0px 0px;
  border-radius:0px;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

.alert {
  font-size: 10px;
}

.login {
  display: block;
  top: 50px;
  left: 580px;
  width: 160px;
  font-size: 12px;
  text-align: center;
  color: #1B1B1B;
  background: #fff;
  border-radius: 30px;
  border:1px solid #1B1B1B;
  cursor: pointer;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: "Hannari", serif;
}

.login:hover{
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
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  height: 50px;
  transition: border-color 0.3s;
  width: 280px;
  display: block;
  margin-left: 520px;
  margin-top: 97px;
  text-align: center;
  line-height: 190%;
}

.form-footer p {
  cursor: pointer;
  color: #8c8c8c;
  text-decoration: none;
  transition: border-color 0.3s;
}
.form-footer p:hover {
  border-bottom: 1px dotted #8c8c8c;
}
</style>
