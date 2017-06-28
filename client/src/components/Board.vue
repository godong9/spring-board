<template>
  <div class="board">
    <div class="search-wrapper">
      <div class="search-bar">
        <input type="text" placeholder="종목명 및 종목코드로 검색하세요.">
        <i class="search-icon"></i>
      </div>
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
            <div class="company-info">카카오</div>
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
  </div>
</template>

<script>
  export default {
    name: 'board',
    created() {
      this.$store.dispatch('showHeaderButton');
    },
    beforeCreated() {
      // 참고: https://github.com/pagekit/vue-resource/blob/develop/docs/recipes.md
      this.$http.get('https://httpbin.org/get').then((response) => {
        // get body data
        this.bodyData = response.body;
        console.log(this.bodyData);
      }, (response) => {
        this.errorData = response.body;
        // error callback
      });
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .board {
    /*background-color: #101319;*/
  }
  .search-wrapper {
    height: 32px;
    background-color: #ededed;
    padding: 6px 8px 6px 8px;
  }
  .search-bar {
    height: 20px;
    border-radius: 2px;
    background-color: #ffffff;
    padding: 6px 28px 6px 10px;
    position: relative;
  }
  .search-wrapper input {
    width: 100%;
    height: 20px;
    font-size: 14px;
    color: #c2c7cb;
    margin:0;
    padding: 0;
    border: none;
    color: #17181a;
  }
  .search-wrapper input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    color: #c2c7cb;
  }
  .search-wrapper input::-moz-placeholder { /* Firefox 19+ */
    color: #c2c7cb;
  }
  .search-wrapper input:-ms-input-placeholder { /* IE 10+ */
    color: #c2c7cb;
  }
  .search-wrapper input:-moz-placeholder { /* Firefox 18- */
    color: #c2c7cb;
  }
  .search-icon {
    background: url(../assets/search-icon.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
    width: 16px;
    height: 16px;
    border-radius: 1px;
    position: absolute;
    right: 6px;
    bottom: 8px;
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
    height: 27px;
    line-height: 27px;
    font-size: 18px;
    color: #ffffff;
  }
  .item .content .info {
    height: 18px;
    font-size: 12px;
    color: #6f7782;
    margin: 4px 0 0 0;
  }

  .item .content .info .divider {
    color: #2d2d2d;
  }
  .item .content .count-info {
    margin: 16px 0 0 0;
    height: 15px;
    line-height: 15px;
    font-size: 10px;
    color: #6f7782;
  }
  .item .content .count-info img {
    width: 12px;
    height: 12px;
    margin: 0 3px 0 0;
    vertical-align: middle;
  }
  .item .content .count-info .count-wrapper {
    display: inline-block;
    margin: 0 10px 0 0;
  }
  .item .content .company-info {
    font-size: 12px;
    font-weight: bold;
    color: #ff595f;
    width: 68px;
    height: 22px;
    line-height: 22px;
    text-align: center;
    vertical-align: middle;
    border-radius: 2px;
    background-color: #0c0e13;
    float: right;
    margin:-4px 0 0 0;
  }
  .item .line {
    border-top: solid 1px #191d26;
  }
  .more-btn {
    text-align: center;
    font-size: 14px;
    margin: 20px 0 18px 0;
    font-weight: bold;
    color: #969fa6;
  }
</style>
