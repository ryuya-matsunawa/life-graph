<template>
  <div class="background">
    <div class="header">
      <ul class="acount">
        <li class="personalinfo">
          User Name：{{ username }}
        </li>
        <li class="personalinfo">
          Authority:{{ role }}
        </li>
        <li>
          <span tag="button" class="btn" @click="logout()">
            Log Out
          </span>
        </li>
      </ul>
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
  async mounted () {
    const userId = this.$store.state.auth.userId
    // ロード時にactionsにdispatchする
    await this.$store.dispatch('account/fetchAccount', userId)
    // storeから情報を取得するメソッド
    this.setAccount()
  },
  methods: {
    // dataのaccountにaccount.jsのstateの情報をsetする
    setAccount () {
      const stateAccount = this.$store.state.account
      this.username = stateAccount.account.username
      const role = stateAccount.account.name
      // ROLE_USERだったら一般ユーザとヘッダーに表示される
      if (role === 'ROLE_USER') {
        this.role = '一般ユーザ'
      } else if (role === 'ROLE_ADMIN') {
        this.role = '管理者'
      } else {
        this.role = 'オーナー'
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
  height: auto;
  position: fixed;
  top: 0;
  z-index: 10;
}
.header{
  width: 100%;
  background-color: #B2EBF2;
  background-image: url("../assets/header.png");
  background-size: contain;
  margin-top: -30px;
  padding-bottom: 5px;
}

.acount{
  text-align: right;
}

.acount li{
  line-height: 26px;
  margin-right: 35px;
  margin-top: 80px;
  padding: 8px;
  width: auto;
  font-size: 18px;
  display: inline-block;
  text-decoration: none;
  background-color: #6AAEA5;
  border-color: transparent;
  border-radius: 3px;/*角の丸み*/
  font-weight: bold;
  text-shadow: -1px -1px rgba(255, 255, 255, 0.44), 1px 1px rgba(0, 0, 0, 0.38);
  text-align: center;
}

.personalinfo{
  color: #fff;
}

.btn{
  color: #6AAEA5;
  cursor: pointer
}

.btn:hover {
  background-color: #6AAEA5;
  color: #fff;
  text-shadow: -1px -1px rgba(255, 255, 255, 0.44), 1px 1px rgba(0, 0, 0, 0.38);
}
</style>
