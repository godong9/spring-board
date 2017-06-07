<template>
  <div class="post">
    <div class="post-header">
      <span class="cancel">취소</span>
      <span class="title">글쓰기</span>
      <span class="write"><i class="ok-ic"></i>등록</span>
    </div>
    <div class="bar">
      <div class="search-bar">
        <input type="text" placeholder="#종목추천"><i class="delete-icon"></i><i class="search-icon"></i>
      </div>
    </div>
    <div>
      <input class="title" type="text" placeholder="제목을 입력하세요 (50자 이내)">
      <div class="line"></div>
    </div>
    <div class="content" contenteditable="true" placeholder="내용을 입력하세요"></div>
  </div>
</template>

<script>
  export default {
    name: 'post',
    data() {
      return {
        msg: 'Post page Message',
      };
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
  }
  .write {
    position: absolute;
    right: 16px;
    font-size: 16px;
    color: #ff595f;
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
  }
  .bar .search-bar {
    position: relative;
    height: 32px;
    border-radius: 2px;
    background-color: #ffffff;
    margin: 6px 8px 6px 8px;
  }

  .bar .search-bar input {
    height: 20px;
    font-size: 14px;
    margin: 3px 0 3px 0;
    width: 313px;
    padding: 0 0 0 10px;
  }
  .search-icon {
    margin: 0 0 0 10px;
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
    right: 25px;
    width:16px;
    height:16px;
    background: url(../assets/delete-tag-ic.png) no-repeat center;
    background-size: contain;
    display: inline-block;
    vertical-align: middle;
  }
  .search-bar input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .search-bar input::-moz-placeholder { /* Firefox 19+ */
    font-size:14px;
    line-height: 20px;
    padding:12px 0 0 12px;
    color:#ff595f;
  }
  .search-bar input:-ms-input-placeholder { /* IE 10+ */
    font-size:14px;
    line-height: 20px;
    color:#ff595f;
  }
  .search-bar input:-moz-placeholder { /* Firefox 18- */
    font-size:14px;
    line-height: 20px;
    color:#c2c7cb;
  }
  .title {
    width: 340px;
    height: 56px;
    background-color: #ffffff;
    padding:0 16px 0 16px;
  }
  .title::-webkit-input-placeholder { /* Chrome/Opera/Safari */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .title::-moz-placeholder { /* Firefox 19+ */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .title:-ms-input-placeholder { /* IE 10+ */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .title:-moz-placeholder { /* Firefox 18- */
    font-size:15px;
    line-height: 22px;
    color:#c2c7cb;
  }
  .line {
    width: 375px;
    border: solid 1px #ececec;
  }
  .content {
    padding: 18px 16px 0 16px;
    height: 90vh;
  }

  [contenteditable=true]:empty:before{
    content: attr(placeholder);
    display: block; /* For Firefox */
    color: #c2c7cb;
    font-size: 15px;
  }
</style>
