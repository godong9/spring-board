<template>
  <div class="need-purchase">
    <div class="dim"></div>
    <div class="modal">
      <div class="modal-content">
        <img class="purchase-img" src="../assets/ticket-ic.png">
        <div class="notice">이용권이 없습니다.</div>
        <div class="describe">서비스를 계속 이용하시려면<br>이용권을 구매해주세요.</div>
        <div class="fee">(서비스 이용료:10,000/월)</div>
        <div class="purchase-btn-wrapper">
          <button v-on:click="submit">이용권 구매</button>
        </div>
      </div>
    </div>
    <div class="logout" v-on:click="logout">로그아웃</div>
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
      submit: function submit() {
        this.$router.push('/payment');
      },
      logout: function logout() {
        const self = this;
        this.$http.post(self.getServerPath('/users/logout'), {}).then(() => {
          self.$router.push('/');
        }, (response) => {
          self.errorHandler(response);
        });
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
    margin:auto;
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
    margin:17px 0 0 0;
    font-size: 16px;
    text-align: center;
    color: #6f7782;
  }
  .fee {
    margin: 21px 0 31px 0;
    font-size: 12px;
    font-weight: bold;
    text-align: center;
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
  .logout {
    width:100%;
    position: absolute;
    bottom: 40px;
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    color: #f6f6f6;
  }
</style>
