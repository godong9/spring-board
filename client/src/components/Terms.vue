<template>
  <div class="terms">
    <div class="service-agreement">
      <div class="title">서비스 이용약관</div>
      <input type="checkbox">
    </div>
    <div class="service-agreement">
      <div class="title">개인정보 처리방침</div>
      <input type="checkbox">
    </div>
    <div class="age-check"></div>
  </div>
</template>

<script>
  export default {
    name: 'terms',
    data() {
      return {
        msg: 'Signup page Message',
      };
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .service-agreement {
    border: solid 1px lightgray;
  }
  .service-agreement .title{
    display: inline-block;
    font-size: 6vw;
  }
  .service-agreement input{
    float: right;
  }
</style>
