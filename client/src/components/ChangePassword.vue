<template>
  <div class="reset-password">
    <div class="title-label">
      새로운 비밀번호 설정
    </div>
    <div class="user-password-input">
      <input type="password" v-model="password" placeholder="새로운 비밀번호를 입력하세요">
    </div>
    <div class="user-password-confirm-input">
      <input type="password" v-model="passwordConfirm" placeholder="비밀번호 다시 입력하세요">
    </div>
    <div class="confirm-update-wrapper" v-bind:class="classObject" v-on:click="complete"><button>비밀번호 변경</button></div>
  </div>
</template>

<script>
  export default {
    name: 'change-password',
    data() {
      const self = this;
      self.userId = this.$route.query.id;
      self.uuid = this.$route.query.uuid;

      return {
        password: '',
        passwordConfirm: '',
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.password && this.passwordConfirm,
        };
      },
    },
    methods: {
      complete: function complete() {
        const self = this;
        if (self.password !== self.passwordConfirm) {
          alert('패스워드를 확인해주세요!');
          return;
        }
        const params = {
          id: self.userId,
          uuid: self.uuid,
          password: self.password,
        };
        self.$http.put(self.getServerPath('/users/password'), params).then(() => {
          alert('비밀번호가 변경되었습니다. 로그인 해주세요.');
          self.$router.push('/login');
        }, (response) => {
          self.errorHandler(response);
        });
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
