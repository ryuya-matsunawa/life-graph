<template>
  <div class="header">
    <ul class="acount">
      <li class="personalinfo">
        ユーザー名：{{ account[0].username }}
      </li>
      <li class="personalinfo">
        権限名:{{ account[0].role }}
      </li>
      <li>
        <span tag="button" class="btn" @click="logout()">
          ログアウト
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
    await this.$store.dispatch('account/fetchAccount')
    this.setAccount()
  },
  methods: {
    setAccount () {
      const Account = this.$store.state.account
      this.account[0].username = Account.account[0].username
      const role = Account.account[0].name
      if (role === 'ROLE_USER') {
        this.account[0].role = '一般ユーザ'
      }
    },
    logout () {
      this.$store.commit('auth/deleteToken')
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
    background-color:lemonchiffon;
    position:fixed;
    opacity: 0.7;
  }
  .acount{
    text-align: right;
  }
  .acount li{
    display:inline-block;
    line-height: 70px;
    margin-right: 10px;
  }
  .personalinfo{
    color:black
  }

  .btn{
    margin-right: 10px;
    font-size: 16px;
    position: relative;
    background-color: #f7d034;
    border-radius: 4px;
    color: #fff;
    -webkit-transition: none;
    transition: none;
    box-shadow: 0 3px 0 #f7ba59;
    text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
  }
  .btn:hover {
  top: -4px;
  box-shadow: 0 7px 0 #f7ba59;
}
.btn:active {
  top: 3px;
  box-shadow: none;
}
</style>
