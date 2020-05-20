<template>
  <div class="background">
    <div class="login">
      <img id="title" src="../assets/signin.png">
      <div class="form-item">
        <label for="username" />
        <input v-model="username" type="username" placeholder="UserName">
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
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      password: ''
    }
  },
  computed: {
    // isValidated () {
    //   return Object.keys(this.fields).every(k => this.fields[k].validated) &&
    //     Object.keys(this.fields).every(k => this.fields[k].valid)
    // },
    token () {
      return this.$store.state.auth.token
    }
  },
  watch: {
    // tokenの状態を監視して、tokenが更新されたらtop画面に遷移する
    token (newToken) {
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
}

.background {
  background-image: url("../assets/team20.png");
  background-size: cover;
  width: 100%;
  height: 700px;
  background-position: right;
  position: relative;
}

.login {
  width: 35%;
  padding: 30px;
  margin-left: 50px;
  background: #fff;
  background-image:
    linear-gradient(
      90deg,
      rgba(0,0,0,0) 0%,
      rgba(0,0,0,0) 50%,
      #ffff 50%,
      #ffff 50%
    ),
    linear-gradient(
      180deg,
      rgba(0,0,0,0) 0%,
      rgba(0,0,0,0) 97%,
      #999 97%,
      #999 100%
    );
  background-size:
    8px 80%,
    80% 2em;
  line-height:1.5;
  box-shadow: 0 0 10px rgba(0,0,0,0.3);
}

#title {
  width: 50%;
  margin-top: 15px;
}

.form-item {
  width: 18rem;
  height: 8rem;
  padding: 1em 1.5em;
  overflow: hidden;
  box-shadow: .25rem 0 .25rem hsla(0, 0%, 0%, .1);
  background-image:
    linear-gradient(90deg, hsla(0, 0%, 45%, .1) 2rem, hsla(0, 100%, 100%, 0) 2.5rem)
  , linear-gradient(90deg, hsla(60, 100%, 85%, 1), hsla(60, 100%, 85%, 1));
  font-size: 1.125rem;
  line-height: 1.8;
  white-space: nowrap;
  text-overflow: ellipsis;
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
  z-index: -1;
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
</style>
