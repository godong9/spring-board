<template>
  <div class="terms">
    <div class="service-agreement">
      <div class="title">
        <span>서비스 이용약관</span>
        <input type="checkbox">
      </div>
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
        <span>만 14세 이상입니다.</span>
        <input type="checkbox">
      </div>
    </div>
    <div class="button-agreement-wrapper"><button>동의 하기 </button></div>
  </div>
</template>

<script>
  export default {
    name: 'terms',
    data() {
      return {
        msg: 'Signup page Message',
      };
    },
    methods: {

    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .terms {
    padding: 0px 10px 0px 10px;
    word-wrap: break-word;
    word-break: keep-all;
  }
  .service-agreement {
    margin: 10px 0 0 0;
  }
  .service-agreement .title{
    font-size: 6vw;
    border: 1px solid lightgray;
  }
  .service-agreement input{
    float: right;
    width: 6vw;
    height: 6vw;
  }
  .service-agreement .description{
    border-left: 1px solid lightgray;
    border-right: 1px solid lightgray;
    border-bottom: 1px solid lightgray;
  }
  .button-agreement-wrapper {
    position: absolute;
    left: 10px;
    right: 10px;
    bottom: 10px;
  }
  .button-agreement-wrapper button{
    font-size: 5vw;
    line-height: 8vh;
    width: 100%;
    color:white;
    background-color: red;
  }
</style>
