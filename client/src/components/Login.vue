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
      <a v-on:click="findPassword">비밀번호를 잊어버리셨나요?</a>
    </div>
    <div class="confirm-login-wrapper" v-bind:class="classObject" v-on:click="login"><button>로그인</button></div>
    <div class="signup-description">
      아직 회원이 아니신가요? <span class="signup-text" v-on:click="signup">회원가입</span>
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
        self.$http.post(self.getServerPath('/users/login'), { email: self.email, password: self.password }).then(() => {
          self.$router.push('board');
        }, (response) => {
          self.errorHandler(response);
        });
      },
      findPassword: function findPassword() {
        this.$router.push('reset-password');
      },
      signup: function signup() {
        this.$router.push('signup');
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
    font-size: 50px;
  }

</style>
