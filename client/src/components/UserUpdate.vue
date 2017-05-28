<template>
  <div class="user-update">
    <div class="title-label">
      개인정보 입력
    </div>
    <div class="user-email-text">
      {{ email }}
    </div>
    <div class="input-user-nickname">
      <input type="text" v-model="nickname" placeholder="닉네임 입력">
    </div>
    <select class="select-user-company" v-model="company">
      <option disabled value="">회사 선택</option>
      <option v-for="option in companyOptions" v-bind:value="option.value">
        {{ option.text }}
      </option>
    </select>
    <div class="user-password-input">
      <input type="password" v-model="password" placeholder="비밀번호 입력">
    </div>
    <div class="user-password-confirm-input">
      <input type="password" v-model="passwordConfirm" placeholder="비밀번호 재입력">
    </div>
    <div class="info-message">
      닉네임과 회사는 가입 후 변경할 수 없습니다.
    </div>
    <div class="confirm-update-wrapper" v-on:click="complete"><button>회원가입 완료</button></div>
  </div>
</template>

<script>

  export default {
    name: 'user-update',
    data() {
      const self = this;
      self.email = this.$route.query.email;
      self.userId = this.$route.query.id;
      self.uuid = this.$route.query.uuid;

      /*eslint-disable */
      if (self.email) {
        const url = self.getServerPath('/companies?email=' + self.email);
        this.$http.get(url).then((response) => {
          let data = response.body.data;
          self.companyOptions = data.map(function(obj) {
            let rObj = {};
            rObj['text'] = obj.company_name;
            rObj['value'] = obj.id;
            return rObj;
          });
        });
      }
      /*eslint-enable */

      return {
        email: self.email,
        companyOptions: self.companyOptions,
        nickname: '',
        company: '',
        password: '',
        passwordConfirm: '',
      };
    },
    methods: {
      complete: function complete() {
        const self = this;
        /*eslint-disable */
        if (!self.validateData()) {
            return;
        }

        let params = {
          id: self.userId,
          uuid: self.uuid,
          name: self.nickname,
          password: self.password,
          email: self.email,
          company_id: self.company
        };
        self.$http.put(self.getServerPath('/users/data'), params).then(() => {
          // get body data
          alert('회원정보가 변경되었습니다.');
          self.$router.push('need-purchase');
        }, (response) => {
          if (response.body && response.body.error) {
            alert(response.body.error.message);
          }
        });
        /*eslint-enable */
      },
      validateData: function validateData() {
        const self = this;
        /*eslint-disable */
        if (!self.nickname) {
          alert('닉네임을 입력해주세요!');
          return false;
        }
        if (!self.company) {
          alert('회사를 선택해주세요!');
          return false;
        }
        if (!self.password || !self.passwordConfirm) {
          alert('패스워드를 입력해주세요!');
          return false;
        }
        if (self.password !== self.passwordConfirm) {
          alert('패스워드를 확인해주세요!');
          return false;
        }
        return true;
        /*eslint-enable */
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
