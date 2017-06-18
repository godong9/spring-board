<template>
  <div class="title-header">
    <div class="back"><</div>
    <div class="title">{{ title }}</div>
  </div>
</template>

<script>
  export default {
    name: 'title-header',
    data() {
      return {
        title: this.title || '글쓰기',
      };
    },
    methods: {
      updateTitle(title) {
        this.title = title;
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .title-header {
    height: 50px;
    background-color: #ffffff;
    text-align: center;
    vertical-align: middle;
  }
  .title {
    display: inline-block;
    font-size: 18px;
    color: #151921;
    line-height: 50px;
  }
  .back {
    display: inline-block;
    line-height: 50px;
    position: absolute;
    width: 12px;
    left: 12px;
    font-size: 18px;
    font-weight: bold;
  }
</style>
