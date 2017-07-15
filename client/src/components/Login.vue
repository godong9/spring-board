<template>
  <div class="login">
    <div class="title-label">
      로그인
    </div>
    <div class="user-email-input">
      <input type="email" v-model="email" placeholder="이메일을 입력하세요">
    </div>
    <div class="user-password-input">
      <input type="password" v-model="password" placeholder="비밀번호를 입력하세요">
    </div>
    <div class="login-description">
      <router-link to="reset-password">비밀번호를 잊어버리셨나요?</router-link>
    </div>
    <div class="confirm-login-wrapper" v-bind:class="classObject" v-on:click="login"><button>로그인</button></div>
    <div class="signup-description">
      아직 회원이 아니신가요? <router-link to="signup" class="signup-text">회원가입</router-link>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      return {
        email: '',
        password: '',
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.email && this.password,
        };
      },
    },
    methods: {
      login: function login() {
        const self = this;
        if (!self.classObject.active) {
          return;
        }
        this.$store.dispatch('login', {
          email: self.email, password: self.password,
        }).then(() => {
          this.$router.push('/posts');
        });
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .login {
    background-color: #151921;
  }
  .login .title-label {
    margin: 30px 0 19px 0;
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
  }
  .user-password-input,
  .user-email-input {
    margin: 11px 0 0 0;
    padding:0 25px 0 25px;
  }
  .user-password-input input,
  .user-email-input input {
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
  .login-description {
    font-size: 12px;
    color: #636b7d;
    float:right;
    margin:5px 25px 25px 0;
  }
  .login-description a {
    text-decoration: none;
    color:#636b7d;
  }
  .confirm-login-wrapper{
    margin:0px 25px 20px 25px
  }
  .confirm-login-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
    font-weight: bold;
  }
  .signup-description {
    text-align: center;
    font-size: 14px;
    color: #c2c7cb;
  }

  .signup-description .signup-text {
    font-weight: bold;
    cursor: pointer;
    text-decoration: underline;
    color: #ff595f;
  }

</style>
