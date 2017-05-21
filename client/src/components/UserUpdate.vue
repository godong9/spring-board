<template>
  <div class="user-update">
    <div class="title-label">
      개인정보 입력
    </div>
    <div class="user-email-text">
      gd.godong9@kakaocorp.com
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
    <div class="confirm-update-wrapper"><button>회원가입 완료</button></div>
  </div>
</template>

<script>
  export default {
    name: 'user-update',
    data() {
      return {
        nickname: 'aa',
        company: '',
        companyOptions: [
          { text: 'One', value: 'A' },
          { text: 'Two', value: 'B' },
          { text: 'Three', value: 'C' },
        ],
        password: '',
        passwordConfirm: '',
      };
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
