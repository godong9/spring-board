<template>
  <div class="need-purchase">
    <div class="dim"></div>
    <div class="modal">
      <div class="modal-content">
        <img class="purchase-img" src="../assets/ticket-complete.png">
        <div class="notice">이용권 구매완료!</div>
        <div class="describe">구매 이용권으로<br><span class="duration">2017.05.31 까지</span> 입장 가능하며 <br>매달 1일에 자동결제 됩니다.</div>
        <div class="purchase-btn-wrapper">
          <button v-on:click="submit">입장하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'need-purchase',
    methods: {
      submit: function submit() {
        this.$router.push('/posts');
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .need-purchase {
    text-align: center;
  }
  .dim {
    background-color: rgba(0, 0, 0, 0.5);
    border: solid 1px #979797;
    position: absolute;
    top:0;
    left:0;
    right:0;
    bottom:0;
  }
  .modal {
    text-align: center;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width:100%;
  }
  .modal .modal-content {
    margin: 0 27px 0 27px;
    background-color: #ffffff;
    opacity: 1;
    border: 1px solid;
    border-radius: 5px;
  }

  .purchase-img {
    margin: 40px 73px 14px 73px;
    width: 174px;
    height: 124px;
  }
  .notice {
    font-size: 20px;
    font-weight: bold;
    color: #3b4251;
  }
  .describe {
    margin:17px 54px 50px 54px;
    font-size: 16px;
    text-align: center;
    color: #6f7782;
  }
  .duration {
    font-weight: bold;
    color: #ff595f;
  }
  .purchase-btn-wrapper {
    background-color: #ff595f;
    border-radius: 4px;
    height: 60px;
  }
  .purchase-btn-wrapper button{
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
    border: none;
    margin:0;
    padding:0;
    height: 60px;
    background-color: #ff595f;
  }
</style>
