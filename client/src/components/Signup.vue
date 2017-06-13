<template>
  <div class="sign-up">
    <div class="title-label">
      회원가입
    </div>
    <div class="input-sign-up-wrapper">
      <input type="text" v-model="email" placeholder="회사 이메일을 입력">
    </div>
    <div class="sign-up-description">
      상장사 직원임을 판별 할 수있는 최소한의 정보로 <br>회사 이메일을 활용합니다.
    </div>
    <div class="confirm-email-wrapper" v-bind:class="classObject" v-on:click="signup"><button>인증 메일 받기</button></div>
    <div class="sign-up-assistant"> - 회원가입이 안되시나요? <a href="mailto:stockblind.kr@gmail.com">문의하기</a></div>

  </div>
</template>

<script>
  export default {
    name: 'signup',
    data() {
      return {
        email: '',
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.email,
        };
      },
    },
    methods: {
      signup: function signup() {
        const self = this;
        if (!self.classObject.active) {
          return;
        }

        if (!self.validateEmail(self.email)) {
          alert('올바른 이메일을 입력해주세요.');
          return;
        }
        this.$http.post(self.getServerPath('/users/email'), { email: this.email }).then(() => {
          self.$router.push('email-success?email=' + self.email);
        }, (response) => {
          self.errorHandler(response);
        });
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .sign-up {

  }
  .title-label {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
    margin: 53px 0 30px 0;
  }

  .input-sign-up-wrapper {
    margin: 11px 0 0 0;
    padding:0 25px 0 25px;
  }

  .input-sign-up-wrapper input {
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
  .sign-up-description {
    font-size: 14px;
    color: #636b7d;
    margin: 10px 0 0 0;
    padding: 0 25px 0 25px;
  }
  .sign-up-assistant {
    font-size: 14px;
    color: #c2c7cb;
    text-align: center;
  }
  .sign-up-assistant a {
    color: #ff595f;
  }
  .confirm-email-wrapper {
    margin:85px 25px 20px 25px
  }
  .confirm-email-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
    font-weight: bold;
  }
</style>
