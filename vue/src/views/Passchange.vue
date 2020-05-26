<template>
  <div id="passchange">
    <div class="file">
      <div class="formOut">
        <li class="newpass">
          新しいパスワード
          <input v-model="newPassword" type="text">
        </li>
        <button class="createButton" @click="passwordChange()">
          変更
        </button>
        <p v-if="successMessage" class="successMessage">
          パスワードが変更されました！
        </p>
        <router-link class="createButton" tag="button" to="/top">
          Topに戻る
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      newPassword: '',
      successMessage: false
    }
  },
  computed: {
    success () {
      return this.$store.state.auth.success
    }
  },
  watch: {
    success () {
      this.successMessage = true
    }
  },
  methods: {
    passwordChange () {
      this.$store.dispatch('auth/passChange',
        {
          userId: this.$store.state.auth.userId,
          newPassword: this.newPassword
        })
    }
  }
}
</script>

<style scoped>
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
  text-align: center;
  width: 250px;
  font-weight: bold;
  font-size: 18px;
  font-family: 'Noto Serif JP', serif;
  list-style-type: none;
}

.formOut input {
  margin: 20px 10px;
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
  margin-left: 180px;
  margin-top: 10px;
  text-align: center;
}

.form-item input:focus {
  border-bottom: 2px solid #c0c0c0;
  outline: none;
}

.successMessage {
  width: 280px;
  margin: 15px auto;
  font-size: 14px;
}

</style>
