<template>
  <div class="withdraw">
    <div class="title-label">'탈퇴'를 하시면 현재 사용하고 있는 계정을 더 이상 이용할 수 없게 됩니다.</div>
    <div class="warning">
      <span class="caution">[주의사항]</span><br>
      - 작성한 글과 댓글은 남아 있습니다. <br>
      &nbsp;&nbsp;지우고 싶은 내용은  탈퇴 전에 삭제해주세요. <br>
      - 탈퇴 후 재가입 시에는 재인증을 받아야 합니다.
    </div>
    <div class="withdraw-button-wrapper"><button>탈퇴하기</button></div>
  </div>
</template>
<script>
  export default {
    name: 'withdraw',
    data() {
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
  .title-label {
    font-size: 16px;
    text-align: center;
    color: #c2c7cb;
    margin:90px 40px 0 40px;
  }
  .warning {
    font-size: 14px;
    color: #6f7782;
    margin: 90px 27px 0 27px;
  }
  .caution {
    font-weight: bold;
    color :#ff595f;
  }
  .withdraw-button-wrapper{
    margin:52px 27px 20px 27px
  }
  .withdraw-button-wrapper button{
    font-size: 16px;
    font-weight: bold;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
  }
</style>
