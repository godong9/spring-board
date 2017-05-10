
<template>
  <div class="sign-up">
    <div class="title">상장사 직원들의 익명 주식 커뮤니티</div>
    <div class="service-name">스탁 블라인드</div>
    <div class="logo-wrapper"><img class="logo" src="../assets/logo.png"></div>
    <div class="description">코스피, 코스닥에 상장된 회사(계열사 포함)에 다니고 있는 개인 투자자만 가입할 수 있는 비밀 주식 커뮤니티입니다.<br>(서비스 이용료: 10,000/월) </div>
    <div class="login-assistant">기존 사용자세요? <a href="/">로그인</a></div>
    <div class="button-sign-up"><button>회사 이메일로 가입하기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'signup',
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
  .sign-up {
    padding: 5% 5% 0 5%;
  }
  .title {
    font-weight: bold;
    font-size: 2.5vh;
  }
  .service-name {
    font-size : 6vh;
    color : red;
    font-weight: bold;
    margin: 2% 0 0 0;
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
    font-size: 2.2vh;
  }
  .login-assistant {
    position: absolute;
    width: 90%;
    bottom: 15%;
  }
  .button-sign-up {
    position: absolute;
    width:90%;
    bottom: 10px;
  }
  .button-sign-up button{
    font-size: 3vh;
    line-height: 8vh;
    width: 100%;
    color:white;
    background-color: red;
    max-width: 500px;
  }
</style>
