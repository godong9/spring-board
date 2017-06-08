<template>
  <div class="reset-password">
    <div class="title-label">
      새로운 비밀번호 설정
    </div>
    <div class="user-password-input">
      <input type="password" v-model="password" placeholder="새로운 비밀번호 입력">
    </div>
    <div class="user-password-confirm-input">
      <input type="password" v-model="passwordConfirm" placeholder="비밀번호 재입력">
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
  .title-label {
    margin: 53px 0 0 0;
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
  }
  .user-password-confirm-input {
    margin: 10px 0 0 0;
    padding:0 25px 0 25px;
  }
  .user-password-input {
    margin: 30px 0 0 0;
    padding:0 25px 0 25px;
  }
  .user-password-confirm-input input,
  .user-password-input input {
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
  .confirm-update-wrapper{
    margin: 25px 25px 20px 25px
  }
  .confirm-update-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
  }
</style>
