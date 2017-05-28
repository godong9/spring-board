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
    <div class="confirm-update-wrapper" v-bind:class="classObject" v-on:click="complete"><button>회원가입 완료</button></div>
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
    computed: {
      classObject: function classObject() {
        return {
          active: this.email && this.nickname && this.company
                  && this.password && this.passwordConfirm,
        };
      },
    },
    methods: {
      complete: function complete() {
        const self = this;
        if (!self.classObject.active) {
          return;
        }

        if (!self.validateData()) {
          return;
        }

        /*eslint-disable */
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
          self.errorHandler(response);
        });
        /*eslint-enable */
      },
      validateData: function validateData() {
        const self = this;

        if (self.password !== self.passwordConfirm) {
          alert('패스워드를 확인해주세요!');
          return false;
        }
        return true;
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
