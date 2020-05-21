<template>
  <div class="background">
    <head>
      <meta charset="UTF-8">
      <link href="https://fonts.googleapis.com/earlyaccess/hannari.css" rel="stylesheet">
      <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    </head>
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
      <span tag="button" class="button" @click="logout()">
        ログアウト
      </span>
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
  mounted () {
    const userId = this.$store.state.auth.userId
    // ロード時にactionsにdispatchする
    this.$store.dispatch('account/fetchAccount', userId)
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
  height: 80px;
  position: fixed;
  top: 0;
  background-color: #a39d9d;
  z-index: 1;
}

.title {
  color: #fff;
  font-size: 30px;
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
  font-family: "Hannari";
  display: inline-block;
  text-decoration: none;
  text-align: center;
}

.personalinfo{
  color: #fff;
  font-family: 'Playfair Display', serif;
}

.button {
  width: 95px;
  position: fixed;
  bottom: 190px;
  right: 30px;
  padding: .75rem 1.25rem;
  border-radius: 5px;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: .15rem;
  cursor: pointer;
  background-color: #a7d28d;
  font-family: 'Playfair Display', serif;
}
</style>
