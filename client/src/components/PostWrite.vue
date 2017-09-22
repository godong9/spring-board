<template>
  <div class="post">
    <div class="post-header">
      <span class="cancel" v-on:click="cancel">취소</span>
      <span class="title">글쓰기</span>
      <span class="write" v-on:click="write"><i class="ok-ic"></i>등록</span>
    </div>
    <div class="search-wrapper">
      <div class="search-bar">
        <vue-typeahead placeholder="종목추가"
                       v-model="stockName"
                       :default-suggestion="false"
                       :suggestion-template="suggestionTemplate"
                       :remote="autoCompleteUrl"
                       responseWrapper="data"
                       display-key='name'
                       classes="search-input"
                       v-on:selected="done">
        </vue-typeahead>
      </div>
      <i v-if="showDelete" v-on:click="deleteStockId" class="delete-icon"></i><i class="search-icon"></i>
    </div>
    <div class="post-title-wrapper">
      <input class="post-title" type="text" v-model="title" placeholder="제목을 입력하세요 (50자 이내)" maxlength="50">
    </div>
    <div class="line"></div>
    <div class="content" contenteditable="true" placeholder="내용을 입력하세요"></div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import Api from '../utils/api';

  Vue.component('vueTypeahead', require('vuejs-autocomplete'));

  export default {
    name: 'post',
    data() {
      return {
        title: '',
        showDelete: false,
        stockName: '',
        stockId: '',
        suggestionTemplate: '<div><span class="code">{{code}}</span><span class="name">{{name}}</span></div>',
        autoCompleteUrl: Api.getServerPath('/stocks') + '?name=%QUERY',
      };
    },
    created() {

    },
    methods: {
      cancel() {
        this.$router.back();
      },
      deleteStockId() {
        this.stockId = '';
        this.stockName = '';
        this.showDelete = false;
        const element = this.$el.querySelector('.search-input.tt-input');
        element.removeAttribute('disabled');
      },
      write: function write() {
        /* 글자체크 */
        if (this.title.trim().length < 1) {
          alert('제목을 입력해주세요.');
          return;
        }

        if (this.title.trim().length > 50) {
          alert('제목이 너무 깁니다. (공백 포함 최대 50자)');
          return;
        }
        const contentElement = this.$el.querySelector('.content');
        const contentTxt = contentElement.innerText || contentElement.textContent || '';

        if (contentTxt.trim().length < 1) {
          alert('내용을 입력 해주세요');
        }
        if (contentTxt.length > 5000) {
          confirm('내용이 너무 깁니다. (공백 포함 최대 5000자)');
          return;
        }
        this.$store.dispatch('writePost', {
          title: this.title,
          content: contentElement.innerHTML,
          stock_id: this.stockId,
        }).then(() => {
          this.$router.push('/posts');
        });
      },
      done: function done(data) {
        this.stockId = data.id;
        this.showDelete = true;
        const element = this.$el.querySelector('.search-input.tt-input');
        element.setAttribute('disabled', 'disabled');
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
  .post {
    background-color: #ffffff;
    min-height: 110vh;
  }
  .post-header {
    height: 50px;
    background-color: #ffffff;
    line-height: 50px;
    text-align: center;
  }
  .title {
    font-size: 18px;
    color: #151921;
  }
  .cancel {
    position: absolute;
    left:16px;
    font-size: 16px;
    color: #ff595f;
    cursor: pointer;
  }
  .write {
    position: absolute;
    right: 16px;
    font-size: 16px;
    color: #ff595f;
    cursor: pointer;
  }
  .ok-ic {
    width: 12px;
    height: 12px;
    background: url(../assets/ok-ic.png) no-repeat;
    background-size: contain;
    display: inline-block;
    margin: 0 8px 0 0;
  }
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
    right: 18px;
    width:16px;
    height:16px;
    background: url(../assets/search-icon.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
  }
  .delete-icon {
    position: absolute;
    top: 13px;
    right: 45px;
    width:16px;
    height:16px;
    background: url(../assets/delete-tag-ic.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
  }
  .search-wrapper input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .search-wrapper input::-moz-placeholder { /* Firefox 19+ */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .search-wrapper input:-ms-input-placeholder { /* IE 10+ */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .search-wrapper input:-moz-placeholder { /* Firefox 18- */
    font-size:14px;
    line-height: 20px;
    color:#c2c7cb;
  }
  .post-title-wrapper {
    height: 56px;
    background-color: #ffffff;
    padding:0 16px 0 16px;
  }
  .post-title {
    width:100%;
    height: 22px;
    font-size: 15px;
    margin:18px 0 16px 0;
    padding:0;
    border:none;
  }
  .post-title::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .post-title::-moz-placeholder { /* Firefox 19+ */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .post-title:-ms-input-placeholder { /* IE 10+ */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .post-title:-moz-placeholder { /* Firefox 18- */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .line {
    width: 100%;
    border-top: solid 1px #ececec;
  }
  .content {
    padding: 18px 16px 0 16px;
  }

  [contenteditable=true]:empty:before{
    content: attr(placeholder);
    display: block; /* For Firefox */
    color: #c2c7cb;
    font-size: 15px;
  }
</style>
