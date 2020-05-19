<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
#app {
  color: #2c3e50;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

.chart{
  width: 600px;
  margin: 0 auto;
}

.toTop {
  background: #f16272;
  border: none;
  color: #fff;
  float: left;
  margin: 50px;
  padding: 5px 10px 5px 10px !important;
}

button:focus {
  outline:0;
}

</style>
