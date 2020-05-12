<template>
  <div class="header">
    <div class="btnWrap">
      <button type="button" class="btn">ユーザー名</button>
      <button type="button" class="btn">権限名</button>
      <button type="button" class="btn">ログアウト</button>
    </div>
  </div>
</template>
<script>

</script>
<style scoped>
    .header{
    margin-top: 20px;
    top: 0;
    left: 0;
    width: 100%;
    height: 70px;
    background-color:lemonchiffon
  }
  div.btnWrap{
    text-align: right;
    line-height: 70px;
  }
  .btn{
    margin-right: 10px;
    display: inline-block;
    font-size: 16px;
    position: relative;
    background-color: #f7d034;
    border-radius: 4px;
    color: #fff;
    line-height: 30px;
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
