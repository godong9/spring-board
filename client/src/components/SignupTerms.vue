<template>
  <div class="terms">
    <div class="wrapper">
      <div class="service-agreement">
        <div class="title">
          <span>서비스 이용약관</span>
          <input type="checkbox">
        </div>
        <div class="line"></div>
        <div class="description">
          제 1장 총칙
          제 1조 (목적)
          본 약관은 씨제이올리브네트웍스(주) (이하 “당사＂라함)가 제
          공하는 CJ ONE(이하 “CJ ONE”이라 함) 서비스 이용과 관련
          하여 당사와 회원의 제반 권리, 의무, 관련 절차 등을 규정하는
          데 그 목적이 있습니다.
        </div>
      </div>
      <div class="service-agreement">
        <div class="title">
          <span>개인정보 처리방침</span>
          <input type="checkbox">
        </div>
        <div class="line"></div>
        <div class="description">
          멤버십 서비스 제공, 본인 확인 및 가입 의사 확인, 만 14세 미
          만 회원의 경우 회원가입 처리를 위한 법정 대리인 동의 여부 확
          인 및 추후 법정 대리인에 대한 본인 확인, 회원에 대한 고지 사
          항 전달, 통합 ID 관리, 카드발급, 포인트 적립 사용 정산, 고객
          센터 운영, 불량회원 부정이용 방지 및 비인가 사용 방지, VIP
          서비스 제공멤버십 서비스 제공, 본인 확인 및 가입 의사 확인,
          만 14세 미만 회원의 경우 회원가입 처리를 위한 법정 대리인
        </div>
      </div>
      <div class="service-agreement">
        <div class="title">
          만<span class="red"> 14세 이상</span>입니다.
          <input type="checkbox">
        </div>
      </div>
    </div>
    <div class="button-agreement-wrapper"><button>동의합니다. </button></div>
  </div>
</template>

<script>
  export default {
    name: 'signup-terms',
    data() {
      return {
        msg: 'Signup page Message',
      };
    },
    beforeCreate() {
      /* TODO:store pattern 구현 */
      console.log('this', this);
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .wrapper {
    padding: 13px 15px 0px 15px;
    word-wrap: break-word;
    word-break: keep-all;
  }
  .service-agreement {
    border-radius: 2px;
    background-color: #ffffff;
    margin: 0 0 10px 0;
  }

  .service-agreement .title{
    font-size: 16px;
    color: #17181a;
    height: 50px;
    line-height: 50px;
    margin: 0 0 0 15px;
  }
  .service-agreement input{
    float: right;
    width: 26px;
    height: 50px;
    vertical-align: middle;
    margin: 0 12px 0 0;
    padding: 0;
  }
  .service-agreement .description{
    font-size: 12px;
    font-weight: 300;
    color: #17181a;
    padding:15px 15px 7px 15px;
  }
  .button-agreement-wrapper{
    position: fixed;
    bottom: 20px;
    left: 15px;
    right: 15px;
  }
  .button-agreement-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
  }
  .line {
    border-top: solid 1px #d5d5d5;
  }
  .red {
    font-weight: bold;
    color: #ff595f;
  }
</style>
