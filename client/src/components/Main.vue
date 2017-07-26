<template>
  <div class="main">
  </div>
</template>

<script>
  export default {
    name: 'main',
    created() {
      this.$router.replace('posts');
    },
  };
</script>
