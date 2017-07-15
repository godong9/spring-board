<template>
  <div class="nav">
    <div class="blind-logo"></div>
    <div class="title">StockBlind</div>
    <div v-if="showButton" class="button-wrapper">
      <a class="btn write"><i class="write"></i>글쓰기</a>
      <a class="btn"><i class="person"></i>My</a>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'nav',
    created() {
      const self = this;
      // TODO: 수정 필요
      this.$http.get(self.getServerPath('/users/me'), {}).then((me) => {
        console.log(me);
      }, (response) => {
        self.errorHandler(response);
      });
    },
    computed: {
      showButton() {
        return this.$store.getters.showHeaderButton;
      },
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
    width: 23px;
    height: 23px;
    background-color: #d8d8d8;
    display: inline-block;
    vertical-align: middle;
    margin: 0 0 5px  10px;
  }
  .title {
    display: inline-block;
    font-size: 18px;
    font-weight: bold;
    line-height: 50px;
  }
  .button-wrapper {
    float: right;
    line-height: 50px;
    margin: 0 8px 0 0;
  }
  .btn {
    font-size: 12px;
    background-color:#ffffff;
    -moz-border-radius:6px;
    -webkit-border-radius:6px;
    border-radius:6px;
    border:1px solid #ff595f;
    display:inline-block;
    cursor:pointer;
    color:#ff595f;
    font-weight:bold;
    line-height: 28px;
    padding: 0 8px 0 8px;
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
  .btn i {
    width: 15px;
    height: 15px;
    margin: 0 5px 2px 0;
    line-height: 28px;
    vertical-align: middle;
    display: inline-block;
  }
  .btn.write {

  }
  i.write {
    background: url(../assets/write-ic.png) no-repeat;
    background-size: contain;
  }
  i.person {
    background: url(../assets/mypage-ic.png) no-repeat;
    background-size: contain;
  }

</style>
