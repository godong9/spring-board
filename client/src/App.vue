<template>
  <div id="app">
    <router-view name="nav"></router-view>
    <router-view></router-view>
    <router-view name="footer"></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
};
</script>

<style>
  html,
  body {
    margin: 0;
    height: 100%;
    width: 100%;
    background-color: #151921;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    background-color: #151921;
    height: 100%;
    position:relative;
  }
</style>
