<template>
  <div class="board">
    <div class="search-wrapper">
      <div class="search-bar">
        <input type="text" placeholder="#종목명및 종목코드를 입력하세요."><i class="search-icon"></i>
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
            <div class="company-info">#카카오</div>
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
            <div class="company-info">#카카오</div>
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
            <div class="company-info">#카카오</div>
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
            <div class="company-info">#카카오</div>
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
            <div class="company-info">#카카오</div>
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
    data() {
      return {
        msg: 'Board page Message',
      };
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
    height: 7vw;
    padding: 10px;
    background: #ededed;
  }
  .search-bar {
    background: white;
    position: relative;
    width:100%;
    height:100%;
  }
  .search-wrapper input {
    width: 85vw;
    height: 100%;
    border: 0;
    margin: 0;
    padding: 0 0 0 10px;
  }
  .search-icon {
    margin: 0 0 0 10px;
    width: 4vw;
    height: 100%;
    background: url(../assets/search-icon.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
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
    font-size: 4.8vw;
  }
  .item .content .info .text {
    font-size: 3.2vw;
    color: #6f7782;
  }
  .item .content .info .divider {
    color: #6f7782;
  }
  .item .content .count-info {
    margin: 2.5vw 0 0 0;
    font-size: 2.7vw;
  }
  .item .content .count-info img {
    width: 12px;
    height: 12px;
    margin: 0 2px 0 0;
  }
  .item .content .count-info .count-wrapper {
    display: inline-block;
    margin: 0 10px 0 0;
  }
  .item .content .company-info {
    color: #ff595f;
    float: right;
  }
  .item .line {
    border: solid 1px #191d26;
  }
  .more-btn {
    text-align: center;
    font-size: 14px;
    line-height: 70px;
    background-color: #151921;
  }
</style>
