<template>
  <div class="board">
    <div class="search-wrapper">
      <div class="search-bar">
        <vue-typeahead placeholder="종목명 및 종목코드를 입력하세요"
                       v-model="value"
                       :default-suggestion="false"
                       :suggestion-template="myTemplate"
                       :local="companies"
                       display-key='name'
                       classes="search-input"
                       v-on:selected="done">
        </vue-typeahead>
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
  import Vue from 'vue';

  Vue.component('vueTypeahead', require('vuejs-autocomplete'));

  export default {
    name: 'board',
    data() {
      return {
        showDelete: false,
        value: '',
        myTemplate: '<div><span class="code">{{code}}</span><span class="name">{{name}}</span><span class="type">{{type}}</span></div>',
        companies: [{ code: '1111', name: '카카오', type: '코스닥' }, { code: '2222', name: 'SKT', type: '코스닥' }, { code: '9999', name: '이더리움', type: '코스닥' }],
      };
    },
    created() {
      this.$store.dispatch('showHeaderButton');
    },
    methods: {
      done: function done(data) {
        console.log(data);
      },
    },
  };
</script>
<style>
  .search-wrapper .twitter-typeahead {
    height: 20px;
    font-size: 14px;
    background-color: #ffffff!important;
    width:100%;
    border: none;
    padding:0;
    margin:0;
  }
  .search-wrapper .twitter-typeahead .tt-menu {
    background-color: #ffffff;
    padding: 0 0 0 10px;
    left:-10px!important;
    right:0;
  }
  .search-wrapper .twitter-typeahead .tt-suggestion {
    background-color: #ffffff;
  }
  .tt-menu {
    margin:6px 0 0 0;
  }
  .tt-suggestion {
    position: relative;
    font-size: 14px;
    height: 32px;
    line-height: 32px;
    vertical-align: middle;
  }
  .tt-suggestion .code {
    color: #969fa6;
  }
  .tt-suggestion .name {
    color: #17181a;
    margin: 0 0 0 23px;
  }
  .tt-suggestion .name .tt-highlight {
    color: #ff595f;
  }
  .tt-suggestion .type {
    font-size: 12px;
    color: #969fa6;
    float: right;
    margin: 0 10px 0 0;
  }
</style>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .search-wrapper {
    height: 44px;
    background-color: #ededed;
    display: inline-block;
    width: 100%;
    position: relative;
  }
  .search-wrapper .search-bar {
    height: 20px;
    border-radius: 2px;
    background-color: #ffffff;
    margin: 6px 8px 6px 8px;
    padding: 6px 0px 6px 10px;
  }
  .search-wrapper .search-input {
    width:100%;
    padding:0;
    margin:0;
    border:none;
    height: 20px;
  }
  .search-icon {
    position: absolute;
    top: 14px;
    right: 8px;
    width:16px;
    height:16px;
    background: url(../assets/search-icon.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
  }
  .search-wrapper input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:14px;
    line-height: 20px;
    color: #c2c7cb;
  }
  .search-wrapper input::-moz-placeholder { /* Firefox 19+ */
    font-size:14px;
    line-height: 20px;
    color: #c2c7cb;
  }
  .search-wrapper input:-ms-input-placeholder { /* IE 10+ */
    font-size:14px;
    line-height: 20px;
    color: #c2c7cb;
  }
  .search-wrapper input:-moz-placeholder { /* Firefox 18- */
    font-size:14px;
    line-height: 20px;
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
    right: 18px;
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
