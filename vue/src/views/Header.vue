<template>
  <div class="background">
    <div class="header">
      <div class="title">
        Life graph
      </div>
      <ul class="acount">
        <li class="personalinfo">
          User Name：{{ username }}
        </li>
        <li class="personalinfo">
          Authority:{{ role }}
        </li>
      </ul>
      <div>
        <router-link to="/pass-change" class="changepass">
          パスワード変更
        </router-link>
        <span tag="button" class="logout" @click="logout()">
          ログアウト
        </span>
      </div>
      <div>
        <router-link to="/top" class="toTop">
          TOPへ
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      role: ''
    }
  },
  computed: {
    account () {
      return this.$store.state.account.account
    }
  },
  watch: {
    account (newAccount) {
      this.setAccount()
    }
  },
  mounted () {
    this.setAccount()
  },
  methods: {
    // dataのaccountにaccount.jsのstateの情報をsetする
    setAccount () {
      const stateAccount = this.$store.state.account
      this.username = stateAccount.account.username
      const role = stateAccount.account.name
      // ROLE_USERだったら一般ユーザとヘッダーに表示される
      if (role === 'ROLE_OWNER') {
        this.role = 'オーナー'
      } else if (role === 'ROLE_ADMIN') {
        this.role = '管理者'
      } else {
        this.role = '一般ユーザ'
      }
    },
    // ログアウトボタンが押された時のメソッド
    logout () {
      // authのstateのtokenを消す
      this.$store.commit('auth/deleteToken')
      this.$store.commit('account/deleteAccount')
      // tokenが消されたあとログイン画面に遷移する
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.background {
  width: 100%;
  height: 80px;
  position: fixed;
  top: 0;
  background-color: #a39d9d;
  z-index: 1;
}

.title {
  color: #fff;
  font-size: 30px;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Playfair Display', serif;
}

.acount{
  text-align: right;
}

.acount li{
  line-height: 26px;
  margin-right: 35px;
  margin-top: 10px;
  padding: 8px;
  width: auto;
  font-size: 18px;
  font-family: "Hannari", serif;
  display: inline-block;
  text-decoration: none;
  text-align: center;
}

.personalinfo{
  color: #fff;
  font-family: 'Playfair Display', serif;
}

.changepass {
  position: fixed;
  bottom: 210px;
  right: 30px;
  padding: 20px 15px;
  border-radius: 8px;
  width: 100px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a39d9d;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Playfair Display', serif;
}

.logout {
  width: 95px;
  position: fixed;
  bottom: 130px;
  right: 30px;
  padding: 20px 15px;
  border-radius: 8px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a39d9d;
  font-family: 'Playfair Display', serif;
}

.toTop {
  position: fixed;
  bottom: 50px;
  right: 30px;
  padding: 20px 15px;
  border-radius: 8px;
  width: 90px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a39d9d;
  /* eslint-disable-next-line to ignore the next line. */
  font-family: 'Playfair Display', serif;
}
</style>
