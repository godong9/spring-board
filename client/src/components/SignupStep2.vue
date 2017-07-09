<template>
  <div class="signup-step2">
    <div class="title-label">이메일 인증 완료</div>
    <div class="description"> 닉네임과 비밀번호를 설정해주세요</div>
    <div class="input-email-wrapper">
      <input type="text" v-model="email" disabled>
    </div>
    <div class="input-first">
      <div class="input-nickname-wrapper"><input type="text" v-model="nickname" placeholder="닉네임 입력"></div>
      <div class="select-company-wrapper">
        <select class="select-company" v-model="company">
          <option disabled value="">회사 선택</option>
          <option v-for="option in companyOptions" v-bind:value="option.value">
            {{ option.text }}
          </option>
        </select>
      </div>
    </div>
    <div class="input-password-wrapper">
      <input type="password" v-model="password" placeholder="비밀번호 입력">
    </div>
    <div class="input-password-wrapper">
      <input type="password" v-model="passwordConfirm" placeholder="비밀번호 재입력">
    </div>
    <div class="warning">* 닉네임과 회사는 가입 후 변경할 수 없습니다.</div>
    <div class="signup-wrapper" v-bind:class="classObject" v-on:click="complete"><button>회원가입 완료</button></div>
  </div>
</template>

<script>
  export default {
    name: 'signup-step2',
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
        const params = {
          id: self.userId,
          uuid: self.uuid,
          name: self.nickname,
          password: self.password,
          email: self.email,
          company_id: self.company
        };
        self.$http.put(self.getServerPath('/users/data'), params).then(() => {
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
  .title-label {
    font-size: 18px;
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
