<template>
  <div class="nav">
    <div class="blind-logo">B</div>
    <div class="title">StockBlind</div>
    <div class="button-wrapper">
      <a class="btn"><i class="write"></i>글쓰기</a>
      <a class="btn"><i class="person"></i>My</a>
    </div>
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
    height: 50px;
    background-color: #ffffff;
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
    font-size: 18px;
    font-weight: bold;
  }
  .button-wrapper {
    font-size: 7vw;
    float: right;
    display: table-cell;
    vertical-align: middle;
    margin: 0 15px 0 0;
  }
  .btn {
    background-color:#ffffff;
    -moz-border-radius:6px;
    -webkit-border-radius:6px;
    border-radius:6px;
    border:1px solid #ff595f;
    display:inline-block;
    cursor:pointer;
    color:#ff595f;
    font-size:3vw;
    font-weight:bold;
    padding:6px 6px;
    text-decoration:none;
    text-shadow:0px 1px 0px #ffffff;
  }
  .btn:hover {
    background-color:#f6f6f6;
  }
  .btn:active {
    position:relative;
    top:1px;
  }
  i.write {
    width: 2.5vw;
    height: 2.5vw;
    margin: 0 5px 0 0;
    background: url(../assets/write-ic.png) no-repeat;
    background-size: contain;
    display: inline-block;
  }
  i.person {
    width: 2.5vw;
    height: 2.5vw;
    margin: 0 5px 0 0;
    background: url(../assets/mypage-ic.png) no-repeat;
    background-size: contain;
    display: inline-block;
  }

</style>
