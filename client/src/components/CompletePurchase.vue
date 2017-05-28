<template>
  <div class="need-purchase">
    <div class="dim">
      <div class="modal">
        <div class="modal-content">
          <div class="purchase-img-wrapper"><img class="purchase-img" src="../assets/logo.png"></div>
          <div class="notice">이용권 구매완료!</div>
          <div class="describe">구매 이용권으로<br><span class="duration">2017.05.31 까지</span> 입장 가능하며 <br>매달 1일에 자동결제 됩니다.</div>
          <div class="purchase-btn-wrapper">
            <button>입장하기</button>
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
  .duration {
    color: red;
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
