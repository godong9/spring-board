<template>
  <div class="reset-password">
    <div class="title-label">
      비밀번호 초기화
    </div>
    <div class="user-email-input">
      <input type="text" v-model="email" placeholder="회사 이메일을 입력하세요">
    </div>
    <div class="confirm-reset-wrapper"><button>비밀번호 초기화 메일받기</button></div>
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
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
