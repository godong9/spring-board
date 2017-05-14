<template>
  <div class="payment-sign-up">
    <div class="title-label">
      닉네임과 비밀번호 설정
    </div>
    <div class="input-email-wrapper">
      <input type="text" placeholder="회사 이메일을 입력 해주세요.">
    </div>
    <div class="input-first">
      <input type="text" placeholder="닉네임">
      <select>
        <option>카카오</option>
        <option>네이버</option>
        <option>삼성</option>
      </select>
    </div>
    <div>
      서비스 이용료: 10,000/월
    </div>
    <div class="input-second">
      <input type="password" placeholder="비밀번호">
      <input type="password" placeholder="비밀번호 재입력">
    </div>
    <div class="payment-signup-wrapper"><button>가입완료</button></div>
  </div>
</template>

<script>
  export default {
    name: 'payment-signup',
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
  .payment-sign-up {
    text-align: center;
    padding: 0 10px 0 10px;
  }
  .title-label {
    font-size: 4vw;
    font-weight: bold;
    margin: 10vw 0 0 0;
  }
  .payment-signup-wrapper {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 10px;
  }
  .payment-signup-wrapper button{
    font-size: 5vw;
    line-height: 8vh;
    width: 100%;
    color:white;
    background-color: red;
    max-width: 500px;
  }
</style>
