<template>
  <div class="sign-up">
    <div class="title-label">
      회원가입
    </div>
    <div class="input-sign-up-wrapper">
      <input type="text" v-model="email" placeholder="회사 이메일을 입력 해주세요.">
    </div>
    <div class="sign-up-description">
      상장사 직원임을 판별 할 수있는 최소한의 정보로 회사이 메일을 활용합니다.
    </div>
    <div class="sign-up-assistant"> - 회원가입이 안되시나요? <a href="/">문의하기</a></div>
    <div class="confirm-email-wrapper" v-on:click="signup"><button>인증 메일 받기</button></div>
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
    methods: {
      signup: function signup() {
        this.$http.post('http://localhost:9700/users/email', { email: this.email }).then((response) => {
          // get body data
          this.bodyData = response.body;
          console.log(this.bodyData);
        }, (response) => {
          this.errorData = response.body;
          // error callback
        });
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .sign-up {
    text-align: center;
    padding: 0 10px 0 10px;
  }
  .title-label {
    font-size: 4vw;
    font-weight: bold;
    margin: 10vw 0 0 0;
  }
  .input-sign-up-wrapper {

  }
  .input-sign-up-wrapper input::-webkit-input-placeholder {
    font-size: 4vw;
  }
  .input-sign-up-wrapper input::-moz-placeholder {
    font-size: 4vw;
  } /* firefox 19+ */
  .input-sign-up-wrapper input:-ms-input-placeholder {
    font-size: 4vw;
  } /* ie */

  .input-sign-up-wrapper input {
    width:100%;
    border: none;
    border-bottom: 1px solid;
    margin: 10vw 0 0 0;
    line-height: 8vw;
  }
  .sign-up-description {
    margin: 2vw 0 0 0;
  }
  .sign-up-assistant {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 12vh;
  }

  .confirm-email-wrapper {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 10px;
  }
  .confirm-email-wrapper button{
    font-size: 5vw;
    line-height: 8vh;
    width: 100%;
    color:white;
    background-color: red;
    max-width: 500px;
  }
</style>
