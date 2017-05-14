
<template>
  <div class="signup-intro">
    <div class="title">상장사 직원들의 익명 주식 커뮤니티</div>
    <div class="service-name">스탁 블라인드</div>
    <div class="logo-wrapper"><img class="logo" src="../assets/logo.png"></div>
    <div class="description">코스피, 코스닥에 상장된 회사(계열사 포함)에 다니고 있는 개인 투자자만 가입할 수 있는 비밀 주식 커뮤니티입니다.<br>(서비스 이용료: 10,000/월) </div>
    <div class="login-assistant">기존 사용자세요? <a href="/">로그인</a></div>
    <div class="button-sign-up-wrapper"><button>회사 이메일로 가입하기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'signup-intro',
    data() {
      return {
        msg: 'Signup page Message',
        name: '',
        email: '',
        password: '',
      };
    },
    methods: {
      clickSignup: function clickSignup() {
        this.$http.post('http://localhost:9000/users/signup', { name: this.name, email: this.email, password: this.password }).then((response) => {
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
  .signup-intro {
    padding: 10px 10px 0 10px;
    text-align: center;
  }
  .title {
    font-weight: bold;
    font-size: 4.5vw;
  }
  .service-name {
    font-size : 8vw;
    color : red;
    font-weight: bold;
    margin: 10px 0 0 0;
  }
  .logo-wrapper {
    margin: 25px 0 25px 0
  }
  .logo {
    max-width : 30%;
  }
  .description {
    word-break: keep-all;
    word-wrap: break-word;
    font-size: 4.2vw;
  }
  .login-assistant {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 12vh;
  }
  .button-sign-up-wrapper {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 10px;
  }
  .button-sign-up-wrapper button{
    font-size: 5vw;
    line-height: 8vh;
    width: 100%;
    color:white;
    background-color: red;
    max-width: 500px;
  }
</style>
