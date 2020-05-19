<template>
  <div id="app">
    <router-view />
    <head>
      <meta name="viewport" content="width=device-width,initial-scale=1.0">
    </head>
  </div>
</template>

<style>
@media screen and (min-width:1024px) {
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif, Tahoma;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
  }

  .chart{
    width: 600px;
    margin: 0 auto;
    margin-top: 30px;
    border: 1px solid;
    padding: 30px;
    border-radius: 5px;
    border-bottom: solid 6px #c0c0c0;
    box-shadow: 0 3px 5px gray;
    border-radius: 8px;
    border-color: #c0c0c0;
  }
}
@media only screen and (min-width:768px) and (max-width:1024px) {
 #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
  }

  .chart{
    width: 600px;
    margin: 0 auto;
    margin-top: 30px;
    border: 1px solid;
    padding: 30px;
    border-radius: 5px;
    border-bottom: solid 6px #c0c0c0;
    box-shadow: 0 3px 5px gray;
    border-radius: 8px;
    border-color: #c0c0c0;
  }
}

</style>
