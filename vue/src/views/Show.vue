<template>
  <div class="show">
    <h1>User Name</h1>
    <router-link to="/Top">
      <button class="toTop">
        TOPへ
      </button>
    </router-link>
  </div>
</template>

<script>
// import Header from "../views/Header"
// import Chart from "../views/";

export default {
  name: 'Show',
  components: {
    // Header
    // Chart
  }
}
</script>

<style scoped>
.toTop {
  background: #f16272;
  border: none;
  color: #fff;
}
</style>
