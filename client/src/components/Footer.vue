<template>
  <footer>
    <div class="warning">
      <div>
         <img class="icon" src="../assets/warning-ic.png">
        <span class="title">주의해주세요!</span>
      </div>
      <div class="content">
        주식 블라인드의 모든 내용은 투자 참고사항으로 게시된<br>
        내용에 의한 투자결과에 법적 책임을 지지않습니다.<br>
        내부정보를 게시하는 것은 불법입니다.<br>
        게시된 정보는 무단으로 배포할 수 없습니다.
      </div>
      <div class="line"></div>
    </div>
    <div class="company-info">
      내부자들<br>
      서울특별시 금천구 독산동 | 대표 : 강진성 | 개인정보담당자 : 강진성 |<br>
      사업자등록번호 : 000-00-000000 사업자정보확인 |<br>
      통신판매업신고 : 2017-서울금천-0000호<br>
      고객센터 : jutu.kr@gmail.com  전화 : 02-1588-0000<br>
    </div>
    <div class="terms">
      <a href="">이용약관</a> |
      <a href="">개인정보 처리방침</a>
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
    padding: 0 16px 0 16px;
    background-color: #101319;
  }
  .warning {
    padding: 20px 0 0 0;
  }
  .warning .icon{
    width: 16px;
    height: 15px;
    vertical-align: middle;
  }
  .warning .title {
    height: 21px;
    font-size: 14px;
    font-weight: bold;
    color: #ff595f;
    vertical-align: middle;
  }
  .warning .content {
    margin: 10px 0 0 0;
    font-size: 14px;
    color: #636b7d;
  }
  .terms {
    padding: 14px 0 22px 0;
    font-size: 12px;
    font-weight: bold;
  }
  .terms a {
    text-decoration: none;
    color: #3b4251;
  }
  .line {
    margin: 20px 0 0 0;
    border-top: solid 1px #191d26;
  }
  .company-info {
    margin: 17px 0 0 0;
    font-size: 12px;
    line-height: 21px;
    color: #3b4251;
  }
</style>
