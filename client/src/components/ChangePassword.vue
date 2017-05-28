<template>
  <div class="reset-password">
    <div class="title-label">
      새로운 비밀번호 설정
    </div>
  </div>
</template>

<script>
  export default {
    name: 'change-password',
    data() {
      return {
        password: '',
        passwordConfirm: '',
      };
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
