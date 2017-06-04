<template>
  <footer>
    <div class="warning">
      • 주식 블라인드의 모든 내용은 투자 참고사항으로 게시된<br>
      내용에 의한 투자결과에 법적 책임을 지지않습니다.<br>
      • 내부정보를 게시하는 것은 불법입니다.<br>
      • 게시된 정보는 무단으로 배포할 수 없습니다.
    </div>
    <div class="terms">
      <a href="">이용약관</a> |
      <a href="">개인정보 처리방</a>
    </div>
  </footer>
</template>

<script>
  export default {
    name: 'footer',
    data() {
      return {
        msg: 'Nav page Message',
      };
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  footer {
    padding: 16px 0 0 16px;
    background-color: #101319;
    height: 200px;
  }
  .warning {
    height: 86px;
    font-size: 14px;
  }
  .terms {
    margin: 70px 0 0 0;
  }
  .terms a {
    text-decoration: none;
    color: #2c3e50;
  }

</style>
