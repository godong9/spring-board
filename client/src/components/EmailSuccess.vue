<template>
  <div class="email-success">
    <div class="title-label">
      인증 메일 발송 완료
    </div>
    <div class="mail-wrapper"><img class="mail" src="../assets/logo.png"></div>
    <div class="check-mail-text">이메일을 확인 해주세요.</div>
    <div class="mail-success-text"><span class="email">jangpyo.kwon@sktechx.com</span> 으로 인증 메일이 발송되었습니다.</div>
    <div class="verify-message">
      메일에 있는 <span class="verify">'인증하기' 버튼</span> 혹은<br> <span class="link">링크</span>를 클릭해 주세요.
    </div>
    <div class="spam-check-message">메일을 받지 못하신 경우 스팸메일함을 확인해주세요.</div>
  </div>
</template>

<script>
  export default {
    name: 'email-success',
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
  .email-success {
    text-align: center;
    padding: 0 10px 0 10px;
  }
  .title-label {
    font-size: 4vw;
    font-weight: bold;
    margin: 10vw 0 0 0;
  }
  .mail-wrapper {
    margin: 25px 0 25px 0
  }
  .mail-wrapper .mail {
    max-width : 30%;
  }
  .check-mail-text {
    font-size: 5vw;
    font-weight: bold;
  }
  .mail-success-text {
    margin: 4vw 0 0 0;
  }
  .mail-success-text .email{
    color: red;
  }
  .verify-message {
    margin: 10vw 0 0 0;
    background-color: lightgray;
    padding: 4vw;
    font-size: 4vw;
  }
  .verify-message .link,
  .verify-message .verify {
    font-weight: bold;
  }
  .spam-check-message {
    margin: 10vw 0 0 0;
    font-size: 3vw;
    color: gray;
  }
</style>
