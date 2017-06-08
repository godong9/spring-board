<template>
  <div class="reset-password">
    <div class="title-label">
      비밀번호 찾기
    </div>
    <div class="user-email-input">
      <input type="text" v-model="email" placeholder="회사 이메일 입력">
    </div>
    <div class="confirm-reset-wrapper" v-bind:class="classObject" v-on:click="confirm"><button>비밀번호 초기화 메일받기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'reset-password',
    data() {
      return {
        email: '',
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.email.length > 6,
        };
      },
    },
    methods: {
      confirm: function confirm() {
        const self = this;
        if (!self.validateEmail(self.email)) {
          alert('올바른 이메일을 입력해주세요.');
          return;
        }
        this.$http.post(self.getServerPath('/users/find/password'), { email: this.email }).then(() => {
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
  .title-label {
    margin: 53px 0 0 0;
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
  }
  .user-email-input {
    margin: 30px 0 0 0;
    padding:0 25px 0 25px;
  }
  .user-email-input input {
    font-size:14px;
    width: 100%;
    box-sizing: border-box;
    height: 50px;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    padding: 0 14px 0 14px;
    color:#f6f6f6;
  }
  .confirm-reset-wrapper{
    margin:85px 25px 20px 25px
  }
  .confirm-reset-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
  }
</style>
