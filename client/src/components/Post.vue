<template>
  <div class="post">
    <div class="post-header">
      <span class="cancel" v-on:click="cancel">취소</span>
      <span class="title">글쓰기</span>
      <span class="write" v-on:click="write"><i class="ok-ic"></i>등록</span>
    </div>
    <div class="bar">
      <div class="search-bar">
        <vue-typeahead placeholder="종목추가"
                       v-model="value"
                       :default-suggestion="false"
                       :suggestion-template="myTemplate"
                       :local="companies"
                       display-key='name'
                       classes="search-input"
                       v-on:selected="done">
        </vue-typeahead>
      </div>
      <i v-if="showDelete" class="delete-icon"></i><i class="search-icon"></i>
    </div>
    <div class="post-title-wrapper">
      <input class="post-title" type="text" placeholder="제목을 입력하세요 (50자 이내)">
    </div>
    <div class="line"></div>
    <div class="content" contenteditable="true" placeholder="내용을 입력하세요"></div>
  </div>
</template>

<script>
  import Vue from 'vue';

  window.Vue = Vue;

  window.jQuery = require('jquery');

  window.$ = window.jQuery;

  Vue.component('vueTypeahead', require('vuejs-autocomplete'));

  export default {
    name: 'post',
    data() {
      return {
        showDelete: false,
        value: '',
        myTemplate: '<div><span>{{code}}</span><span>{{name}}</span><span>{{type}}</span></div>',
        companies: [{ code: '1111', name: '카카오', type: '코스닥' }, { code: '2222', name: 'SKT', type: '코스닥' }, { code: '9999', name: '이더리움', type: '코스닥' }],
      };
    },
    created() {
      this.$store.dispatch('setTitle', '글쓰기');
    },
    methods: {
      cancel: function cancel() {
        this.$router.back();
      },
      write: function write() {
      },
      done: function done(data) {
        console.log(data);
      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .bar .twitter-typeahead {
    height: 20px;
    font-size: 14px;
    background-color: #ffffff!important;
    width:100%;
    border: none;
    padding:0;
    margin:0;
  }
  .bar .twitter-typeahead .tt-menu {
    background-color: #ffffff;
    padding: 0 0 0 10px;
    left:-10px!important;
    right:0;
  }
  .bar .twitter-typeahead .tt-suggestion {
    background-color: #ffffff;
  }
</style>
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
  .bar {
    height: 44px;
    background-color: #ededed;
    display: inline-block;
    width: 100%;
    position: relative;
  }
  .bar .search-bar {
    height: 20px;
    border-radius: 2px;
    background-color: #ffffff;
    margin: 6px 8px 6px 8px;
    padding: 6px 0px 6px 10px;
  }
  .bar .search-input {
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
  .delete-icon {
    position: absolute;
    top: 8px;
    right: 8px;
    width:16px;
    height:16px;
    background: url(../assets/delete-tag-ic.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
  }
  .bar input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .bar input::-moz-placeholder { /* Firefox 19+ */
    font-size:14px;
    line-height: 20px;
    padding:12px 0 0 12px;
    color:#ff595f;
  }
  .bar input:-ms-input-placeholder { /* IE 10+ */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .bar input:-moz-placeholder { /* Firefox 18- */
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
