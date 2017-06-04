<template>
  <div class="board-detail">
    <div class="wrapper">
      <div class="content-title">
        <div class="company-wrapper">
          <div class="company">#카카오</div>
        </div>
        <div class="title">
          이제 막차 떠납니다. 월요일 쩜상 예약! 어서 월요일 쩜상 예약하세요!
        </div>
        <div class="info">
          <span class="text">닉네임(회사명)</span>
          <div class="date-query-count">
            <span class="text">18:01</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>67</span></span>
          </div>
        </div>
        <div class="line"></div>
      </div>
      <div class="content-wrapper">
        <div class="content-text">
          추세가 살아있는 주식은 기관이 매수하면 따라 추매내지 홀딩.. 
          기관매도에서 추세하락이면 매도. 뭐그리 분석이고 나발이고 고생함? 기관중 연기금 매매동향을 주시..
        </div>
        <div class="content-info">
          <a class="btn like"><i class="like"></i>999</a>
          <a class="btn dislike"><i class="dislike"></i>999</a>
          <a class="text-btn list">목록</a>
          <a class="text-btn report">신고</a>
        </div>
      </div>
    </div>
    <div class="line"></div>
    <div class="write-wrapper">
      <div class="comment-label">
        <div>댓글<span class="count">5</span></div>
      </div>
      <div class="write-area">
        <textarea placeholder="댓글을 입력하세요 (최대 300자)"></textarea>
        <div class="word-count-wrapper">300 / <span class="count">0</span></div>
      </div>
    </div>
  </div>

</template>

<script>
  export default {
    name: 'board-detail',
    created() {
    },
  };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .board-detail {
    background-color: #151921;
  }
  .board-detail .wrapper {
    padding: 0px 16px 0 16px;
  }
  .company-wrapper {
    text-align: center;
    display: inline-block;
    margin: 11px 0 11px 0;
    width: 68px;
    height: 22px;
    border-radius: 2px;
    background-color: #0c0e13;
  }
  .company-wrapper .company {
    display: inline-block;
    width: 48px;
    line-height: 22px;
    font-size: 12px;
    font-weight: bold;
    color: #ff595f;
  }

  .content-title {
    height: 134px;
  }
  .content-title .title{
    height: 54px;
    font-size: 18px;
    color: #f0f2f5;
  }
  .content-title .info .text {
    font-size: 3.2vw;
    color: #6f7782;
  }
  .content-title .info .divider {
    color: #6f7782;
  }
  .content-title .info .date-query-count {
    display: inline-block;
    float: right;
  }
  .content-title .line {
    border: solid 1px #191d26;
    margin: 10px 0 0 0;
  }
  .content-wrapper {
    background-color: #151921;
    display: inline-block;
  }
  .content-text {
    margin: 10px 0 0 0;
    height: 88px;
    font-size: 15px;
    color: #c2c7cb;
  }
  .content-info {
    margin: 66px 0 20px 0;
  }
  .content-info .btn {
    width: 60px;
    height: 30px;
    line-height: 30px;
    border-radius: 4px;
    display: inline-block;
    text-align: center;
    font-size: 14px;
  }
  .content-info .btn.like{
    border: solid 1px #ff595f;
    color: #ff595f;
  }
  .content-info .btn.dislike{
    margin: 0 0 0 20px;
    border: solid 1px #6f7782;
    color: #6f7782;
  }
  .content-info .btn i.like {
    width: 11px;
    height: 11px;
    background: url(../assets/list-like-ic.png) no-repeat;
    background-size: contain;
    display: inline-block;
    margin: 0 10px 0 0;
  }
  .content-info .btn i.dislike {
    width: 11px;
    height: 11px;
    background: url(../assets/list-dislike-ic.png) no-repeat;
    background-size: contain;
    display: inline-block;
    margin: 0 10px 0 0;
  }
  .content-info .text-btn {
    font-size: 14px;
    line-height: 26px;
    float: right;
  }
  .content-info .text-btn.report{
    color: #ff595f;
    margin: 0 26px 0 0;
  }
  .line {
    border: solid 1px #191d26;
  }
  .write-wrapper {
    padding: 0px 16px 0 16px;
    height: 190px;
    background-color: #12161d;
  }
  .comment-label {
    margin: 17px 0 0 0;
    height: 15px;
    font-size: 14px;
    font-weight: bold;
    color: #c2c7cb;
  }
  .comment-label .count {
    margin: 0 0 0 6px;
    color: #ff595f;
  }
  .write-area {
    position: relative;
  }
  .write-area textarea{
    height: 86px;
    width: 98%;
    margin: 15px 0 0 0;
    background-color: rgba(31, 37, 51, 0.88);
    border-radius: 4px;
    resize: none;
    color:#c2c7cb;
    font-size: 14px;
  }
  ::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:14px;
    line-height: 22px;
    padding:12px 0 0 12px;
    color:#636b7d;
  }
  ::-moz-placeholder { /* Firefox 19+ */
    font-size:14px;
    line-height: 22px;
    padding:12px 0 0 12px;
    color:#636b7d;
  }
  :-ms-input-placeholder { /* IE 10+ */
    font-size:14px;
    line-height: 22px;
    padding:12px 0 0 12px;
    color:#636b7d;
  }
  :-moz-placeholder { /* Firefox 18- */
    font-size:14px;
    line-height: 22px;
    padding:12px 0 0 12px;
    color:#636b7d;
  }
  .write-area .word-count-wrapper{
    position: absolute;
    bottom: 9px;
    right: 9px;
    font-size: 12px;
    color: #c2c7cb;
  }
  .write-area .word-count-wrapper .count{
    color: #636b7d;
  }
</style>
