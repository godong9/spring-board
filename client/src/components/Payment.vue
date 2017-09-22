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
        <input type="number" maxlength="4" max="9999" v-model="card1">
        <input type="number" maxlength="4" max="9999" v-model="card2">
        <input type="number" maxlength="4" max="9999" v-model="card3">
        <input type="password" maxlength="4" v-model="card4">
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">유효기간</div>
      <div class="card-year">
        <select class="month" v-model="month">
          <option disabled value="">MM</option>
          <option v-for="option in monthOptions" v-bind:value="option.value">
            {{ option.text }}
          </option>
        </select>
        <span>/</span>
        <select class="year" v-model="year">
          <option disabled value="">YYYY</option>
          <option v-for="option in yearOptions" v-bind:value="option.value">
            {{ option.text }}
          </option>
        </select>
        <span class="description">월 2자리 / 년 4자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">비밀번호</div>
      <div class="card-password">
        <input type="password" maxlength="2" v-model="pwd2Digit">
        <span>* *</span>
        <span class="description">비밀번호 앞 2자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">생년월일</div>
      <div class="birthday">
        <input type="number" max="999999" maxlength="6" v-model="birth">
        <span class="description">법정 생년 월일 6자리</span>
      </div>
    </div>
    <div class="line"></div>
    <div class="info">
      <div class="label">결제금액</div>
      <div class="value price">11,000원</div>
    </div>
    <div class="button-payment-wrapper" v-bind:class="classObject" v-on:click="subscribe"><button>결제하기</button></div>
  </div>
</template>

<script>
  export default {
    name: 'payment',
    data() {
      return {
        card1: '',
        card2: '',
        card3: '',
        card4: '',
        month: '',
        year: '',
        pwd2Digit: '',
        birth: '',
        monthOptions: [
          { text: '1월', value: '01' },
          { text: '2월', value: '02' },
          { text: '3월', value: '03' },
          { text: '4월', value: '04' },
          { text: '5월', value: '05' },
          { text: '6월', value: '06' },
          { text: '7월', value: '07' },
          { text: '8월', value: '08' },
          { text: '9월', value: '09' },
          { text: '10월', value: '10' },
          { text: '11월', value: '11' },
          { text: '12월', value: '12' },
        ],
        yearOptions: [
          { text: '2017년', value: '2017' },
          { text: '2018년', value: '2018' },
          { text: '2019년', value: '2019' },
          { text: '2020년', value: '2020' },
          { text: '2021년', value: '2021' },
          { text: '2022년', value: '2022' },
          { text: '2023년', value: '2023' },
          { text: '2024년', value: '2024' },
          { text: '2025년', value: '2025' },
          { text: '2026년', value: '2026' },
          { text: '2027년', value: '2027' },
        ],
      };
    },
    computed: {
      classObject: function classObject() {
        return {
          active: this.card1 && this.card2 && this.card3 && this.card4 &&
          this.month && this.year && this.pwd2Digit && this.birth,
        };
      },
    },
    methods: {
      subscribe: function subscribe() {
        if (!this.classObject.active) {
          return;
        }
        const params = {
          card_number: `${this.card1}-${this.card2}-${this.card3}-${this.card4}`,
          expiry: `${this.year}-${this.month}`,
          birth: this.birth,
          pwd_2digit: this.pwd2Digit,
        };

        this.$store.dispatch('subscribe', params).then(() => {
          this.$router.push('complete-purchase');
        });
      },
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
