<template>
  <div class="post">
    <h1>{{ msg }}</h1>
    <h2>id: {{ $route.params.id }} Post Page</h2>
  </div>
</template>

<script>
  export default {
    name: 'post',
    data() {
      return {
        msg: 'Post page Message',
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
