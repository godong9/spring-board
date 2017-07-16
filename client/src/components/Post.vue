<template>
  <div class="post-detail">
    <div class="wrapper">
      <div class="content-title">
        <div class="company-wrapper" v-if="post.stock">
          <div class="company">{{ post.stock.name }}</div>
        </div>
        <div class="title">
          {{ post.title }}
        </div>
        <div class="info">
          <span class="text" v-if="post.user">{{ post.user.name }}({{ post.user.company_name }})</span>
          <div class="date-query-count">
            <span class="text">{{ diffDateFormat(post.created_at) }}</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>{{ post.view_count }}</span></span>
          </div>
        </div>
        <div class="line"></div>
      </div>
      <div class="content-wrapper">
        <div class="content-text">
          {{ post.content }}
        </div>
        <div class="content-info">
          <a class="btn like" v-on:click="likePost(true)"><i class="like"></i>{{ post.post_like_count }}</a>
          <a class="btn dislike"><i v-on:click="likePost(false)" class="dislike"></i>{{ post.post_unlike_count }}</a>
          <a class="text-btn list">목록</a>
          <a class="text-btn report">신고</a>
        </div>
      </div>
    </div>
    <div class="line"></div>
    <div class="write-wrapper">
      <div class="comment-label">
        <div>댓글<span class="count">{{comments.length}}</span></div>
      </div>
      <div class="write-area">
        <textarea v-model="commentText" maxlength="300" placeholder="댓글을 입력하세요 (최대 300자)"></textarea>
        <div class="word-count-wrapper"> <span class="count">{{ commentText.length }}</span> / 300</div>
      </div>
      <div class="write-btn-wrapper"><button v-on:click="writeComment">등록</button></div>
    </div>
    <div class="comment-list">
      <div class="comment-item" v-for="comment in comments">
        <div class="comment-title">
          <span>{{comment.user.name}} ({{comment.user.company_name}}) </span><span>|</span> <span>{{ diffDateFormat(comment.created_at)}}</span>
          <a class="delete-ic" v-if="me.id === comment.user.id" v-on:click="deleteComment(comment.id)"><img src="../assets/delete-ic.png"></a>
        </div>
        <div class="comment-content">
          {{ comment.content }}
        </div>
        <div class="line"></div>
      </div>
    </div>
  </div>

</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'post',
    created() {
      this.$store.dispatch('setTitle', '상세글보기');
      this.$store.dispatch('showHeaderButton');
      this.$store.dispatch('getPost', this.$route.params.id);
      this.$store.dispatch('getComments', { 'post.id': this.$route.params.id });
    },
    data() {
      return {
        commentText: '',
        commentTextLength: 0,
      };
    },
    computed: {
      ...mapGetters([
        'post',
        'comments',
        'me',
      ]),
    },
    methods: {
      likePost(like) {
        if (this.post.is_liked) {
          alert('이미 공감한 게시물입니다.');
          return;
        }
        if (this.post.is_unliked) {
          alert('이미 비공감한 게시물입니다.');
          return;
        }
        if (like) {
          this.$store.dispatch('likePost', this.post.id);
        } else {
          this.$store.dispatch('unlikePost', this.post.id);
        }
      },
      writeComment() {
        this.$store.dispatch('writeComment', { post_id: this.post.id, content: this.commentText });
      },
      deleteComment(id) {
        this.$store.dispatch('deleteComment', id);
      },
    },
  };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .post-detail {
    background-color: #151921;
  }
  .post-detail .wrapper {
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
    border-top: solid 1px #191d26;
    margin: 10px 0 0 0;
  }
  .content-wrapper {
    background-color: #151921;
    display: inline-block;
    width: 100%;
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
    color:#c2c7cb;
  }
  .content-info .text-btn.report{
    color: #ff595f;
    margin: 0 26px 0 0;
  }
  .line {
    border-top: solid 1px #191d26;
  }
  .write-wrapper {
    padding: 17px 16px 0 16px;
    height: 190px;
    background-color: #12161d;
  }
  .comment-label {
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
    left:0;
    right:0;
  }
  .write-area textarea{
    height: 86px;
    width: 100%;
    margin: 15px 0 0 0;
    background-color: rgba(31, 37, 51, 0.88);
    border-radius: 4px;
    resize: none;
    color:#c2c7cb;
    font-size: 14px;
    border: none;
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
  .write-btn-wrapper {
    margin: 12px 0 15px 0;
    float:right;
  }
  .write-btn-wrapper button {
    color: #ffffff;
    font-weight: bold;
    width: 60px;
    height: 30px;
    border-radius: 4px;
    background-color: #ff595f;
    border: solid 1px #ff595f;
    font-size: 14px;
    text-align: center;
    text-decoration: none;
  }
  .comment-item {
    background-color: #151921;
    padding: 15px 20px 13px 20px;
  }
  .comment-item .comment-title{
    height: 18px;
    font-size: 12px;
    color: #6f7782;
  }
  .comment-item .comment-content {
    font-size: 14px;
    color: #c2c7cb;
  }
  .comment-item .delete-ic img{
    width: 10px;
    height: 10px;
    float:right;
  }

</style>
