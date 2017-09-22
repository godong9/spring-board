<template>
  <div class="main">
    <div class="logo">
      <div>
        <img class="logo-big" src="../assets/jutu-logo-big.png">
      </div>
      <div>
        <img class="logo-big-text" src="../assets/jutu-text-logo-big.png">
      </div>
    </div>
    <div class="title">
      상장사 직원들의 익명 주식 투자 커뮤니티
    </div>
    <div class="signup-wrapper">
      <div class="signup"><router-link to="signup" tag="button">회사 이메일로 가입하기</router-link></div>
      <div class="login-description">
        기존 사용자이신가요? <router-link to="login" class="login-text">로그인</router-link>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'main',
    created() {
      this.$store.dispatch('getMe', () => {})
        .then(() => {
          this.$router.push('/posts');
        });
    },
    computed: {
      ...mapGetters([
        'me',
      ]),
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .logo {
    margin: 79px 0 0 0;
    width: 100%;
    text-align: center;
  }
  .logo .logo-big {
    width: 56px;
  }
  .logo .logo-big-text {
    width: 112px;
    object-fit: contain;
  }
  .title {
    text-align: center;
    font-size: 16px;
    color: #ededed;
  }
  .signup-wrapper {
    background: url("../assets/bg-wave.png") center no-repeat;
    background-size: contain;
    height: 276px;
    width: 100%;
    margin: 85px 0 0 0;
    display: inline-block;
  }
  .signup{
    margin:135px 25px 20px 25px
  }
  .signup button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
    font-weight: bold;
  }
  .login-description {
    text-align: center;
    font-size: 14px;
    color: #c2c7cb;
  }

  .login-description .login-text {
    font-weight: bold;
    cursor: pointer;
    text-decoration: underline;
    color: #ff595f;
  }
</style>
