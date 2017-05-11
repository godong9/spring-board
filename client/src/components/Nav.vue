<template>
  <div class="nav">
    <div class="blind-logo">B</div>
    <div class="title">스탁 블라인드</div>
    <div class="description">상장사 직원들의 익명 주식 커뮤니티</div>
  </div>
</template>

<script>
  export default {
    name: 'nav',
    data() {
      return {
        msg: 'Nav page Message',
      };
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .nav {
    text-align: left;
  }
  .blind-logo {
    margin: 10px;
    font-size: 6vw;
    width: 7vw;
    background: red;
    text-align: center;
    color: white;
    display: inline-block;
  }
  .title {
    display: inline-block;
    font-size: 6vw;
    font-weight: bold;
  }
  .description {
    width: 34vw;
    float: right;
    word-wrap: break-word;
    word-break: keep-all;
    margin: 5px 10px 0 0;
    text-align: right;
  }
</style>
