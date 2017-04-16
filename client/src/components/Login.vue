<template>
  <div class="login">
    <h1>{{ msg }}</h1>
    <h2>Login Page</h2>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      return {
        msg: 'Login page Message',
      };
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
    font-size: 50px;
  }

</style>
