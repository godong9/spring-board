<template>
  <div class="board">
    <h1>{{ msg }}</h1>
    <h2>id: {{ $route.params.id }} Board Page</h2>
  </div>
</template>

<script>
  export default {
    name: 'board',
    data() {
      return {
        msg: 'Board page Message',
      };
    },
    beforeCreated() {
      // 참고: https://github.com/pagekit/vue-resource/blob/develop/docs/recipes.md
      this.$http.get('https://httpbin.org/get').then((response) => {
        // get body data
        this.bodyData = response.body;
        console.log(this.bodyData);
      }, (response) => {
        this.errorData = response.body;
        // error callback
      });
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
