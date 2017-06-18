<template>
  <div class="cancel">
    <div class="title-label">‘이용권 해지’를 하시면 이미 구매한 기간까지만<br>
      이용 가능하고 이후에는 재결제되지 않습니다. </div>
    <div class="cancel-button-wrapper"><button>이용권 해지하기</button></div>
  </div>
</template>
<script>
  export default {
    name: 'cancel',
    data() {
      return {
        email: this.email,
      };
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
</style>
