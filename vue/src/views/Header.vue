<template>
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
    .header{
    top: 0;
    left: 0;
    width: 100%;
    height: 70px;
    position:relative;
    border-bottom: thin solid #AEAC9E;
    background-image: url(../assets/header.png);
  }
  .acount{
    text-align: right;
  }
  .acount li{
    display:inline-block;
    line-height: 70px;
    margin-right: 35px;
  }
  .personalinfo{
    color:black
  }

  .btn{
    margin-right: 10px;
    padding: 8px;
    width: 115px;
    font-size: 18px;
    background-color: #EEFF41;
    color: #AEAC9E;
    border-color: transparent;
  }
  .btn:hover {
  background-color: #ff8f00;
  color: #fff;
}
</style>
