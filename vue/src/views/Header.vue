<template>
  <div class="background">
    <div class="header">
      <ul class="acount">
        <li class="personalinfo">
          User Name：{{ account[0].username }}
        </li>
        <li class="personalinfo">
          Authority:{{ account[0].role }}
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
      account: [{
        username: '',
        role: ''
      }]
    }
  },
  async mounted () {
    // ロード時にactionsにdispatchする
    await this.$store.dispatch('account/fetchAccount')
    // storeから情報を取得するメソッド
    this.setAccount()
  },
  methods: {
    // dataのaccountにaccount.jsのstateの情報をsetする
    setAccount () {
      const stateAccount = this.$store.state.account
      this.account[0].username = stateAccount.account[0].username
      const role = stateAccount.account[0].name
      // ROLE_USERだったら一般ユーザとヘッダーに表示される
      if (role === 'ROLE_USER') {
        this.account[0].role = '一般ユーザ'
      }
    },
    // ログアウトボタンが押された時のメソッド
    logout () {
      // authのstateのtokenを消す
      this.$store.commit('auth/deleteToken')
      // tokenが消されたあとログイン画面に遷移する
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.background {
  max-width: 100%;
  height: auto;
}
.header{
  width: 100%;
  height: 100px;
  background-color: #B2EBF2;
  background-image: url("../assets/header.png");
  background-size: contain;
}

.acount{
  text-align: right;
}

.acount li{
  line-height: 30px;
  margin-right: 35px;
  margin-top: 30px;
  padding: 8px;
  width: 115px;
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
