<template>
  <div class="reset-password">
    <div class="title-label">
      비밀번호 초기화
    </div>
    <div class="user-email-input">
      <input type="text" v-model="email" placeholder="회사 이메일을 입력하세요">
    </div>
    <div class="confirm-reset-wrapper" v-bind:class="classObject" v-on:click="confirm"><button>비밀번호 초기화 메일받기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'reset-password',
    data() {
      return {
        email: '',
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.email.length > 6,
        };
      },
    },
    methods: {
      confirm: function confirm() {
        const self = this;
        if (!self.validateEmail(self.email)) {
          alert('올바른 이메일을 입력해주세요.');
          return;
        }
        this.$http.post(self.getServerPath('/users/find/password'), { email: this.email }).then(() => {
          self.$router.push('/');
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
