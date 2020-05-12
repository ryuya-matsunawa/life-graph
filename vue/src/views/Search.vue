<template>
  <div class="form-container container" id="app">
  <form>
    <div class="form-group">
      <label for="exampleInputEmail1">ユーザー名</label>
      <div class="form-row">
        <div class="col">
          <input v-model="form.lastName" type="text" class="form-control" placeholder="名前">
          <small v-bind:class="{'is-hide':validation.lastName}"  class="form-text text-info">{{ errorMessage.lastName }}</small>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label for="exampleInputEmail1">更新日時</label>
      <input v-model="form.tel" type="tel" class="form-control" id="exampleInputEmail1" placeholder="電話番号">
      <small v-bind:class="{'is-hide':validation.tel}" class="form-text text-info">{{ errorMessage.tel }}</small>
    </div>
    <div class="form-group">
      <label for="exampleInputEmail1">表示順</label>
      <input v-model="form.email" type="email" class="form-control" id="exampleInputEmail1" placeholder="メールアドレス">
      <small v-bind:class="{'is-hide':validation.email}" class="form-text text-info">{{ errorMessage.email }}</small>
    </div>
    <div class="btn-container"><button type="submit" v-bind:disabled="!isValid" class="btn btn-primary">参照</button></div>
  </form>
</div>
</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      search: ''
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
// <style scoped lang="scss">
// .scene1{
//   font-size: 1.8rem;
//   text-align: center;
//   a div p{
//     display: block;
//     margin: 0 auto;
//     height:30pt;
//   }
// }
// .input{
//   width: 130pt;
//   height:30pt;
// }
// .button {
//   display: block;
//   position: relative;
//   margin: 0 auto;
//   width: 70pt;
//   border: solid 1px silver;
//   border-radius: 0.5rem 0.5rem;
//   padding: 0.5rem 1.5rem;
//   margin-top: 1rem;
//   text-decoration: none;
// }
// </style>
