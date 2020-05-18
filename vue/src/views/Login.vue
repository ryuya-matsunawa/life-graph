<template>
  <div class="login">
    <h1>Sign In</h1>
    <div class="form-item">
      <label for="username" />
      <input v-model="username" type="username" name="username" placeholder="UserName">
      <label for="password" />
      <input v-model="password" type="password" name="password" placeholder="Password">
    </div>
    <button class="button-panel" @click="login()">
      Signin
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
  box-sizing: border-box;
}

/* body */
body {
  background: #e9e9e9;
  color: #5e5e5e;
  font: 400 87.5%/1.5em 'Open Sans', sans-serif;
  margin-left: auto;
  margin-right: auto;
}

/* Form Layout */
.form-wrapper {
  background: #fafafa;
  margin: 3em auto;
  padding: 0 1em;
  max-width: 370px;
  margin-left: auto;
  margin-right: auto;
}

h1 {
  text-align: center;
  padding: 1em 0;
}

form {
  padding: 0 1.5em;
  margin-left: auto;
  margin-right: auto;
}

.form-item {
  margin-bottom: 0.75em;
  width: 100%;
  margin-left: auto;
  margin-right: auto;
}

.form-item input {
  background: #fafafa;
  border: none;
  border-bottom: 2px solid #e9e9e9;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 1em;
  height: 50px;
  transition: border-color 0.3s;
  width: 55%;
  margin-left: auto;
  margin-right: auto;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
  margin-left: auto;
  margin-right: auto;
}

.button-panel {
  margin: 2em 0 0;
  margin-left: auto;
  margin-right: auto;
}

.button-panel .button {
  background: #f16272;
  border: none;
  color: #fff;
  cursor: pointer;
  height: 50px;
  font-family: 'Open Sans', sans-serif;
  font-size: 1.2em;
  letter-spacing: 0.05em;
  text-align: center;
  text-transform: uppercase;
  transition: background 0.3s ease-in-out;
  width: 30%;
  margin-left: auto;
  margin-right: auto;
}

.button:hover {
  background: #ee3e52;
}

.form-footer {
  font-size: 1em;
  padding: 2em 0;
  text-align: center;
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
