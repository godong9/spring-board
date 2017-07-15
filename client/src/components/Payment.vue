<template>
  <div class="payment">
    <div class="title-label">
      이용권 결제하기
    </div>
    <div class="tab-container">
      <div class="tab1">1. 결제 정보 확인</div>
      <div class="tab2">2. 카드 정보 입력</div>
    </div>
    <div class="info">
      <div class="label">카드정보</div>
      <div class="card-number">
        <input type="number">
        <input type="number">
        <input type="number">
        <input type="number">
      </div>
    </div>
    <div class="card-number-error">잘못된 카드 번호입니다.</div>
    <div class="line"></div>
    <div class="info">
      <div class="label">유효기간</div>
      <div class="card-year">
        <select class="month">
          <option>MM</option>
          <option>1월</option>
          <option>2월</option>
          <option>3월</option>
          <option>4월</option>
          <option>5월</option>
          <option>6월</option>
          <option>7월</option>
          <option>8월</option>
          <option>9월</option>
          <option>10월</option>
          <option>11월</option>
          <option>12월</option>
        </select>
        <span>/</span>
        <select class="year">
          <option>YYYY</option>
          <option>1980년</option>
          <option>1981년</option>
          <option>1982년</option>
          <option>1983년</option>
          <option>1984년</option>
          <option>1985년</option>
          <option>1986년</option>
          <option>1987년</option>
          <option>1988년</option>
          <option>1989년</option>
          <option>1990년</option>
        </select>
        <span class="description">월 2자리 / 년 4자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">비밀번호</div>
      <div class="card-password">
        <input type="password" maxlength="2">
        <span>* *</span>
        <span class="description">비밀번호 앞 2자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">생년월일</div>
      <div class="birthday">
        <input type="number">
        <span class="description">법정 생년 월일 6자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">결제금액</div>
      <div class="value price">13,000원</div>
    </div>
    <div class="button-payment-wrapper"><button>결제하기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'payment',
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
    left:75px;
    border-bottom: solid 1px #3b4251;
    display: inline;
    padding-bottom: 3px;
    padding-right: 5px;
  }
  .tab-container .tab2 {
    position: absolute;
    right: 75px;
    color: #ff595f;
    border-bottom: solid 1px #ff595f;
    display: inline;
    padding-bottom: 3px;
    padding-right: 5px;
  }
  .info {
    font-size:14px;
    position: relative;
    height: 34px;
    line-height: 34px;
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
  .info .birthday,
  .info .card-password,
  .info .card-year,
  .info .card-number {
    display: inline-block;
    position: absolute;
    left:106px;
    right:24px;
  }
  .info .card-year .month{
    width: 56px;
    height: 34px;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    text-align: center;
    color: #f6f6f6;
    padding: 0 0 0 6px;
    margin: 0 8px 0 0;
  }
  .info .card-year .year{
    width: 70px;
    height: 34px;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    text-align: center;
    color: #f6f6f6;
    padding: 0 0 0 6px;
    margin: 0 0 0 8px;
  }
  .info .description {
    font-size: 10px;
    color: #636b7d;
    float:right;
  }
  .info .card-number input{
    display: inline-block;
    width:22%;
    height: 32px;
    margin:0 2px 0 0;
    padding:0;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    font-size: 14px;
    color: #f6f6f6;
    text-align: center;
  }
  .card-number-error {
    height: 15px;
    font-size: 10px;
    color: #ff595f;
    margin: 6px 0 0 106px;
  }
  .info .card-password input {
    display: inline-block;
    width:40px;
    height: 32px;
    margin:0 19px 0 0;
    padding:0;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    font-size: 14px;
    color: #f6f6f6;
    text-align: center;
  }
  .info .birthday input {
    display: inline-block;
    width: 84px;
    height: 32px;
    margin:0 19px 0 0;
    padding:0;
    border-radius: 4px;
    background-color: rgba(31, 37, 51, 0.88);
    border: solid 1px #3b4251;
    font-size: 14px;
    color: #f6f6f6;
    text-align: center;
  }
  .info .price {
    color: #ff595f;
    font-size:18px;
  }
  .line {
    border-top: solid 1px rgba(60, 67, 81, 0.5);
    margin: 6px 15px 0 15px;
  }
  .button-payment-wrapper{
    position: absolute;
    left: 15px;
    right: 15px;
    bottom: 20px;
  }
  .button-payment-wrapper button{
    font-size: 16px;
    color: #f0f2f5;
    border: none;
    width:100%;
    height: 50px;
    border-radius: 4px;
    background-color: #ff595f;
    font-weight: bold;
  }
</style>
