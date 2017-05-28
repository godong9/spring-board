<template>
  <div class="board">
    <div class="search-wrapper">
      <div class="search-bar">
        <input type="text" placeholder="#종목명및 종목코드를 입력하세요."><i class="search-icon"></i>
      </div>
    </div>
    <ul class="item-list">
      <li class="item">
        <div class="title">
          이제 막차떠납니다.
        </div>
        <div class="info">
          <span class="text">닉네(회사명)</span>
          <span class="text">18:01</span>
          <span class="text">조 67</span>
        </div>
        <div class="count-info">
          <span>100</span>
          <span>100</span>
          <span>100</span>
          <span>카카</span>
        </div>
      </li>
    </ul>
    <div class="footer">

    </div>
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
    background-color: #101319;
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
    background: #151921;
    color: #ffffff;
    margin: 0 16px 0 16px;
  }
  .item {
    width:100%;
    padding:10px 0 10px 0;
  }
  .item .title {
    font-size: 4.8vw;
  }
  .item .info .text {
    font-size: 3.2vw;
    color: #6f7782;
  }
  .item .count-info {
    margin: 2.5vw 0 0 0;
    font-size: 2.7vw;
  }
</style>
