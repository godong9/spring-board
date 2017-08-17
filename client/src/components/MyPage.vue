<template>
  <div class="my-page">
    <div class="my-page-label">
      닉네임이 쓴 글
    </div>
    <ul class="item-list">
      <router-link v-bind:to="'/post/' + item.id" class="item" v-for="item in posts" tag="li" :key="item.id">
        <div class="content">
          <div class="title">
            {{ item.title }}
          </div>
          <div class="info">
            <span class="text">{{ item.user.name }}({{ item.user.company_name }})</span>
            <span class="divider">|</span>
            <span class="text">{{ diffDateFormat(item.created_at) }}</span>
            <span class="divider">|</span>
            <span class="text">조회 <span>{{ item.view_count }}</span></span>
          </div>
          <div class="count-info">
            <div class="count-wrapper"><img src="../assets/like-ic.png"><span class="count">{{ item.post_like_count }}</span></div>
            <div class="count-wrapper"><img src="../assets/dislike-ic.png"><span class="count">{{ item.post_unlike_count }}</span></div>
            <div class="count-wrapper"><img src="../assets/comment-ic.png"><span class="count">{{ item.comment_count }}</span></div>
            <div class="company-info" v-if="item.stock">{{ item.stock.name }}</div>
          </div>
        </div>
        <div class="line"></div>
      </router-link>
    </ul>
    <div class="more-btn">더보기</div>
    <div class="license-info">
      <span class="label">
        이용권 (정기결제)
      </span>
      <br>
      <span class="duration">
        ~ {{ formatDate(me.paid_expired_at) }}까지
      </span>
    </div>
    <div class="button-wrapper">
      <div class="logout" v-on:click="logout">
        로그아웃
      </div>
      <div class="withdraw" v-on:click="withdraw">
        탈퇴하기
      </div>
    </div>

  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'mypage',
    created() {
      this.$store.dispatch('setTitle', '닉네임');
      this.$store.dispatch('initPosts');
      this.$store.dispatch('getPosts', { 'user.id': this.$route.params });
    },
    computed: {
      ...mapGetters([
        'me',
        'posts',
      ]),
    },
    methods: {
      logout() {
        this.$store.dispatch('logout')
          .then(() => {
            this.$router.push('/');
          });
      },
      unsubscribe: function unsubscribe() {
        if (!confirm('정말 해지하시겠습니까?')) {
          return;
        }
        this.$store.dispatch('subscribe');
      },
      withdraw: function withdraw() {
        this.$router.push('/mypage/' + this.me.id + '/withdraw');
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
  .logout {
    width: 160px;
    height: 50px;
    border-radius: 2px;
    border: solid 1px #3b4251;
    font-size: 16px;
    text-align: center;
    color: #969fa6;
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
