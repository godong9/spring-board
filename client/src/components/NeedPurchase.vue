<template>
  <div class="need-purchase">
    <div class="dim">
      <div class="modal">
        <div class="modal-content">
          <div class="purchase-img-wrapper"><img class="purchase-img" src="../assets/logo.png"></div>
          <div class="notice">이용권이 없습니다.</div>
          <div class="describe">서비스를 계속 이용하시려면<br>이용권을 구매해주세요.</div>
          <div class="fee">(서비스 이용료:10,000/월)</div>
          <div class="purchase-btn-wrapper">
            <button>Paypal로 결제하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'need-purchase',
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
  .need-purchase {
    text-align: center;
  }
  .dim {
    background-color: rgba(0,0,0,0.75);
    top:0;
    width: 100%;
    height: 100%;
    z-index: 999;
    position: absolute;
    display: table;
  }
  .modal {
    text-align: center;
    display:table-cell;
    vertical-align: middle;
    z-index: 100;
  }
  .modal .modal-content {
    margin: 0 30px 0 30px;
    background-color: white;
    opacity: 1;
    border: 1px solid rgba(0,0,0,.2);
    border-radius: 6px;
  }
  .purchase-img-wrapper {
    width: 20vw;
    height: 20vw;
    display: inline-block;
  }
  .purchase-img-wrapper .purchase-img {
    max-width: 100%;
  }
  .notice {
    font-size: 5vw;
    font-weight: bold;
  }
  .describe {
    margin: 10px 0 10px 0;
    color: lightgray;
    font-size: 3vw;
  }
  .fee {
    margin: 10px 0 10px 0;
    color:deeppink;
    font-size:2.5vw;
  }
  .purchase-btn-wrapper {
    background-color: red;
    height: 40px;
    border: 1px solid rgba(0,0,0,.2);
    border-radius: 6px;
  }
  .purchase-btn-wrapper button{
    height: 100%;
    color:white;
    font-size: 3vw;
  }
</style>
