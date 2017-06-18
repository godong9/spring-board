<template>
  <div class="email-success">
    <img class="mail" src="../assets/sending-mail-ic.png">
    <div class="title-label">인증 메일 발송 완료!</div>
    <div class="message-wrapper">
      <span class="email">{{ email }}</span> 으로<br> 인증 메일이 발송되었습니다.
      <br>메일에 있는 <span class="verify">'인증하기'</span> 버튼 혹은<br> <a v-bind:href="verifyLink"  class="link">링크를 클릭</a>해주세요.
    </div>
    <div class="spam-check-message">메일을 받지 못하신 경우 스팸메일함을 확인해주세요.</div>
  </div>
</template>

<script>
  export default {
    name: 'email-success',
    data() {
      this.email = this.$route.query.email;
      this.verifyLink = this.$route.query.verifyLink;
      return {
        email: this.email,
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
  }
  .title-label {
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
  }
  .mail {
    margin: 62px 0 14px 0;
    width: 52px;
    height: 70px;
    object-fit: contain;
  }
  .message-wrapper {
    margin: 30px 30px 10px 30px;
    padding: 23px 0 23px 0;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.6);
    border: solid 1px #3b4251;
    font-size: 14px;
    color: #d5d5d5;
  }
  .email{
    font-weight: bold;
    color: #ff595f;
  }
  .message-wrapper .link,
  .message-wrapper .verify {
    font-weight: bold;
    text-decoration: none;
    color: #f6f6f6;
  }
  .spam-check-message {
    font-size: 14px;
    color: #636b7d;
  }
</style>
