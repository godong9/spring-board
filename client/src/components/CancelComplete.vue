<template>
  <div class="cancel-complete">
    <div class="title-label">자동결제가 정상적으로 해지됐습니다. <br>
      현재 보유한 이용권으로 <br>
      <span class="duration">2017년 6월 30일까지 </span>
      이용 가능합니다. </div>
    <div class="cancel-button-wrapper"><button>이용권 재구매하기</button></div>
  </div>
</template>
<script>
  export default {
    name: 'cancel-complete',
    created() {
      this.$store.dispatch('setTitle', '이용권 해지하기');
    },
    methods: {
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .title-label {
    font-size: 16px;
    text-align: center;
    color: #c2c7cb;
    margin:100px 27px 0 27px;
  }
  .cancel-button-wrapper{
    margin:52px 27px 20px 27px
  }
  .cancel-button-wrapper button{
    font-size: 16px;
    font-weight: bold;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
  }
  .duration {
    color: #ff595f;
  }
</style>
