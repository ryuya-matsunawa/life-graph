<template>
  <div class="background">
    <div class="paper">
      <div class="lines">
        <img id="title" src="../assets/signin.png">
        <img id="l2" src="../assets/h2.png">
        <div class="form-item">
          <label for="username" />
          <input v-model="username" type="username" placeholder="UserName">
          <span v-if="isValid">ユーザー名を入力してください</span>
          <br>
          <label for="password" />
          <input v-model="password" type="password" required="required" placeholder="Password">
        </div>
        <button class="button-panel button" @click="login()">
          Signin
        </button>
        <div class="form-footer">
          <p><a href="#">Create an account</a></p>
          <p><a href="#">Forgot password?</a></p>
        </div>
        <div class="holes hole-top" />
        <div class="holes hole-middle" />
        <div class="holes hole-bottom" />
      </div>
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
/*Font*/
@import url(//weloveiconfonts.com/api/?family=fontawesome);
[class*="fontawesome-"]:before {
  font-family: 'FontAwesome', sans-serif;
}
/* Simple Reset */
* {
  margin: 0;
  padding: 0;
  position: relative;
}

.background {
  background-image: url("../assets/team20.png");
  background-size: cover;
  width: 100%;
  height: 700px;
  background-position: right;
}

.paper {
  height: 450px;
  width: 570px;
  background: rgba(255,255,255,0.9);
  box-shadow: 0px 0px 5px 0px #888;
  left: 50px;
  top: 100px;
}

.paper::before {
  content: '';
  position: absolute;
  left: 45px;
  height: 450px;
  width: 2px;
  background: rgba(255,0,0,0.4);
}

.lines {
  margin-top: 40px;
  width: 570px;
  background-image: repeating-linear-gradient(white 0px, white 24px, steelblue 25px);
}

#title {
  width: 260px;
}

#l2 {
  width: 33px;
  right: 330px;
  bottom: 180px;
}

.form-item {
  width: 280px;
  height: 130px;
  padding: 20px 20px;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 19px;
  line-height: 1.8;
  white-space: pre-wrap;
  text-overflow: ellipsis;
  left: 40px;
}

.form-item input {
  background-color: transparent;
  border: none;
  border-bottom: 2px solid #e9e9e9;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 1em;
  height: 50px;
  transition: border-color 0.3s;
  width: 70%;
  display: block;
  margin-left: 10px;
  text-align: center;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

button {
  margin: 2em 0 0;
  margin-top: 40px;
  cursor: pointer
}

.button {
  position:absolute;
  top: 250px;
  right: 10px;
  width: 12rem;
  height: 4rem;
  padding-left: 3rem;
  padding-top: 1rem;
  overflow: hidden;
  color: #fff;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 10%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(30, 100%, 85%, 1), hsla(30, 100%, 85%, 1));
  font-size: 2rem;
  line-height: 1.5;
  transition: all 1s;
  -webkit-perspective: 800px;
  -moz-perspective: 800px;
  -o-perspective: 800px;
  -ms-perspective: 800px;
  perspective: 800px;
}

.button:hover {
  transition-delay: 50ms;
  transform: rotate3d(0,0,1,-13deg);
}

.form-footer {
  position: absolute;
  left: 75%;
  top: 10%;
  text-align: left;
  width: 12rem;
  height: 4rem;
  padding-left: 3rem;
  padding-top: 1rem;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1rem;
  line-height: 1.5;
}

.form-footer a {
  color: #8c8c8c;
  text-decoration: none;
  transition: border-color 0.3s;
}

.form-footer a:hover {
  border-bottom: 1px dotted #8c8c8c;
}

.holes {
  position: absolute;
  left: 10px;
  height: 25px;
  width: 25px;
  background: #fff;
  border-radius: 50%;
  box-shadow: inset 0px 0px 2px 0px #888;
}
.hole-top {
  top: 10%;
}
.hole-middle {
  top: 43%;
}
.hole-bottom {
  bottom: 10%;
}
</style>
