<template>
  <div class="terms">
    <div class="title-label">
      이용권 결제하기
    </div>
    <div class="tab-container">
      <div class="tab1">1. 결제 정보 확인</div>
      <div class="tab2">2. 카드 정보 입력</div>
    </div>
    <div class="info">
      <div class="label">상품</div>
      <div class="value">스탁블라인드 1개월 이용권</div>
      <div class="line"></div>
    </div>
    <div class="info">
      <div class="label">주문자</div>
      <div class="value">올라라</div>
      <div class="line"></div>
    </div>
    <div class="info">
      <div class="label">결제금액</div>
      <div class="value price">13,000원</div>
    </div>
    <div class="wrapper">
      <div class="service-agreement">
        <div class="period-payment">
          <span>30일마다 자동 결제 되는 서비스임을 확인했습니다.</span>
          <input type="checkbox">
        </div>
      </div>
      <div class="service-agreement">
        <div class="title">
          <span>서비스 이용약관에 동의합니다.</span>
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
          <span>해지, 환불 규정을 확인했습니다.</span>
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
    </div>
    <div class="button-agreement-wrapper"><button>카드 정보 입력하기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'payment-terms',
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
  .title-label {
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    color: #f0f2f5;
    margin:36px 0 32px 0;
  }
  .tab-container {
    font-size: 12px;
    color: #3b4251;
    position: relative;
    height: 54px;
    font-weight: bold;
  }
  .tab-container .tab1 {
    position: absolute;
    left: 75px;
    color: #ff595f;
    border-bottom: solid 1px #ff595f;
    display: inline;
    padding-bottom: 3px;
    padding-right: 5px;
  }
  .tab-container .tab2 {
    position: absolute;
    right:75px;
    border-bottom: solid 1px #3b4251;
    display: inline;
    padding-bottom: 3px;
    padding-right: 5px;
  }
  .info {
    font-size:14px;
    position: relative;
    height: 34px;
    line-height: 22px;
    margin: 20px 0 0 0;
    font-weight: bold;
    color:#969fa6;
  }
  .info .label {
    position: absolute;
    left: 29px;
  }
  .info .value {
    position: absolute;
    right: 29px;
    color: #f6f6f6;
  }
  .info .price {
    color: #ff595f;
    font-size:18px;
  }
  .info .line {
    position: absolute;
    border-top: solid 1px rgba(60, 67, 81, 0.5);
    bottom:0px;
    left:15px;
    right:15px;
  }
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
  .service-agreement .period-payment {
    font-size: 16px;
    color: #17181a;
    padding: 11px 0 11px 15px;
  }
  .service-agreement .period-payment span{
    display: inline-block;
    width:220px;
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
    padding: 3px 15px 26px 15px;
  }
  .button-agreement-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
    font-weight: bold;
  }
  .service-agreement .line {
    border-top: solid 1px #d5d5d5;
  }
</style>
