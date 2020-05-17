<template>
  <div class="header">
    <ul class="acount">
      <li class="personalinfo">
        User Name ： manato
      </li>
      <li class="personalinfo">
        Authority class : General
      </li>
      <li>
        <router-link to="/" tag="button" class="btn">
          Log Out
        </router-link>
      </li>
    </ul>
  </div>
</template>
<script>

</script>
<style scoped>
    .header{
    top: 0;
    left: 0;
    width: 100%;
    height: 70px;
    background-color: #fff;
    position:fixed;
    border-bottom: thin solid #AEAC9E;
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
