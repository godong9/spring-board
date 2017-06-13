<template>
  <div class="signup-step2">
    <div class="title-label">이메일 인증 완료</div>
    <div class="description"> 닉네임과 비밀번호를 설해주세요</div>
    <div class="input-email-wrapper">
      <input type="text" placeholder="회사 이메일을 입력 해주세요.">
    </div>
    <div class="input-first">
      <div class="input-nickname-wrapper"><input type="text" placeholder="닉네임 입력"></div>
      <div class="select-company-wrapper">
        <select class="select-company">
          <option>회사 선택</option>
          <option>카카오</option>
          <option>네이버</option>
          <option>삼성</option>
        </select>
      </div>
    </div>
    <div class="input-password-wrapper">
      <input type="password" placeholder="비밀번호 입력">
    </div>
    <div class="input-password-wrapper">
      <input type="password" placeholder="비밀번호 재입력">
    </div>
    <div class="warning">* 닉네임과 회사는 가입 후 변경할 수 없습니다.</div>
    <div class="signup-wrapper"><button>회원가입 완료</button></div>
  </div>
</template>

<script>
  export default {
    name: 'signup-step2',
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
  .title-label {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
    margin: 36px 0 4px 0;
  }
  .description {
    font-size: 14px;
    text-align: center;
    color: #969fa6;
    margin:0 0 36px 0;
  }
  .input-first {
    margin: 0 25px 0 25px;
  }
  .select-company-wrapper,
  .input-nickname-wrapper {
    margin: 11px 0 0 0;
    display: inline-block;
    width:49%;
  }
  .input-password-wrapper,
  .input-email-wrapper {
    margin: 11px 0 0 0;
    padding:0 25px 0 25px;
  }

  .input-password-wrapper input,
  .input-email-wrapper input {
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
  .select-company-wrapper select,
  .input-nickname-wrapper input {
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
  .warning {
    margin: 10px 0 0 25px;
    font-size: 12px;
    color: #6f7782;
  }
  .signup-wrapper {
    margin:40px 25px 20px 25px
  }
  .signup-wrapper button{
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
