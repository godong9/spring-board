<template>
  <div class="my-page">
    <div class="my-page-label">
      닉네임이 쓴 글
    </div>
    <ul class="item-list">
      <li class="item">
        <div class="content">
          <div class="title">
            이제 막차떠납니다.
          </div>
          <div class="info">
            <span class="text">닉네임(회사명)</span>
            <span class="divider">|</span>
            <span class="text">18:01</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>67</span></span>
          </div>
          <div class="count-info">
            <div class="count-wrapper"><img src="../assets/like-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/dislike-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/comment-ic.png"><span class="count">100</span></div>
            <div class="company-info">카카오톡</div>
          </div>
        </div>
        <div class="line"></div>
      </li>
      <li class="item">
        <div class="content">
          <div class="title">
            이제 막차떠납니다.
          </div>
          <div class="info">
            <span class="text">닉네임(회사명)</span>
            <span class="divider">|</span>
            <span class="text">18:01</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>67</span></span>
          </div>
          <div class="count-info">
            <div class="count-wrapper"><img src="../assets/like-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/dislike-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/comment-ic.png"><span class="count">100</span></div>
            <div class="company-info">카카오톡</div>
          </div>
        </div>
        <div class="line"></div>
      </li>
      <li class="item">
        <div class="content">
          <div class="title">
            이제 막차떠납니다.
          </div>
          <div class="info">
            <span class="text">닉네임(회사명)</span>
            <span class="divider">|</span>
            <span class="text">18:01</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>67</span></span>
          </div>
          <div class="count-info">
            <div class="count-wrapper"><img src="../assets/like-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/dislike-ic.png"><span class="count">100</span></div>
            <div class="count-wrapper"><img src="../assets/comment-ic.png"><span class="count">100</span></div>
            <div class="company-info">카카오톡</div>
          </div>
        </div>
        <div class="line"></div>
      </li>
    </ul>
    <div class="more-btn">더보기</div>
    <div class="license-info">
      <span class="label">
        이용권 (정기결제)
      </span>
      <br>
      <span class="duration">
        2017년 5월 1일 ~ 2017년 5월 31일
      </span>
    </div>
    <div class="button-wrapper">
      <div class="license-cancel">
        이용권해지
      </div>
      <div class="withdraw" v-on:click="withdraw">
        탈퇴하기
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'mypage',
    created() {
      this.$store.dispatch('setTitle', '닉네임');
    },
    methods: {
      withdraw: function withdraw() {
        this.$router.push('/mypage/1/withdraw'); // TODO: userId 숴정
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .my-page {
    display: inline-block;
    width: 100%;
    background-color: #151921;
  }
  .my-page-label {
    margin: 11px 0 0 16px;
    height: 18px;
    font-size: 12px;
    color: #6f7782;
  }

  .item-list {
    list-style: none;
    padding:0;
    color: #ffffff;
    margin: 0;
  }
  .item {
    background-color: #151921;
  }
  .item .content {
    padding:10px 16px 10px 16px;
  }
  .item .content .title {
    font-size: 18px;
  }
  .item .content .info .text {
    font-size: 12px;
    color: #6f7782;
  }
  .item .content .info .divider {
    color: #6f7782;
  }
  .item .content .count-info {
    margin: 16px 0 0 0;
    font-size: 10px;
    position: relative;
  }
  .item .content .count-info img {
    width: 11px;
    height: 11px;
    margin: 0 2px 0 0;
    vertical-align: middle;
  }
  .item .content .count-info .count-wrapper {
    display: inline-block;
    margin: 0 10px 0 0;
  }
  .item .content .company-info {
    font-size: 12px;
    line-height: 22px;
    padding:0px 10px 0px 10px;
    border-radius: 2px;
    background-color: #0c0e13;
    color: #ff595f;
    display: inline-block;
    position: absolute;
    right: 0;
    bottom: 0;
    font-weight: bold;
  }
  .item .line {
    border: solid 1px #191d26;
  }
  .more-btn {
    text-align: center;
    font-size: 14px;
    margin: 20px 0 18px 0;
    font-weight: bold;
    color: #969fa6;
  }
  .license-info {
    margin: 0 16px 0 16px;
    height: 80px;
    border-radius: 2px;
    background-color: #f6f6f6;
  }
  .license-info .label {
    display: inline-block;
    margin: 16px 0 0 18px;
    font-size: 16px;
    color: #151921;
    font-weight: bold;
  }
  .license-info .duration {
    display: inline-block;
    margin: 5px 0 0 18px;
    font-size: 12px;
    color: #969fa6;
  }
  .button-wrapper {
    margin: 30px 16px 30px 16px;
    font-weight: bold;
  }
  .license-cancel {
    width: 160px;
    height: 50px;
    border-radius: 2px;
    border: solid 1px #ff595f;
    font-size: 16px;
    text-align: center;
    color: #ff595f;
    line-height: 50px;
    display: inline-block;
  }
  .withdraw {
    width: 160px;
    height: 50px;
    border-radius: 2px;
    border: solid 1px #3b4251;
    font-size: 16px;
    text-align: center;
    color: #969fa6;
    line-height: 50px;
    display: inline-block;
    float: right;
  }

</style>
