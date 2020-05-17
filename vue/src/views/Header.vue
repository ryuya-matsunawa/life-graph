<template>
  <div class="header">
    <ul class="acount">
      <li class="personalinfo">
        ユーザー名：manato
      </li>
      <li class="personalinfo">
        権限名:一般ユーザー
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
  methods: {
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
