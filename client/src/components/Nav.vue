<template>
  <div class="nav">
    <div class="blind-logo"></div>
    <div class="blind-logo-text"></div>
    <div v-if="showHeaderButton" class="button-wrapper">
      <router-link class="btn write" to="/posts/write"><i class="write"></i>글쓰기</router-link>
      <router-link class="btn" v-bind:to="'mypage/'+me.id"><i class="person"></i>My</router-link>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'nav',
    created() {
      const errorHandler = (error) => {
        if (error.status === 403) {
          this.$router.push('/login');
        } else {
          alert((error && error.body && error.body.error && error.body.error.message) || '요청에 실패 했습니다. 잠시후 다시 시도 해주세요.');
        }
      };

      this.$store.dispatch('getMe', errorHandler);
    },
    computed: {
      ...mapGetters([
        'showHeaderButton',
        'me',
      ]),
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .nav {
    text-align: left;
    height: 50px;
    background-color: #ffffff;
  }
  .blind-logo {
    width: 31px;
    height: 27px;
    display: inline-block;
    margin: 11px 0 0 15px;
    background: url('../assets/jutu-logo-big.png') no-repeat center;
    background-size: contain;
  }
  .blind-logo-text {
    width: 44px;
    height: 26px;
    display: inline-block;
    background: url('../assets/jutu-logo-text-small.png') no-repeat center;
    background-size: contain;
  }
  .button-wrapper {
    float: right;
    line-height: 50px;
    margin: 0 8px 0 0;
  }
  .btn {
    font-size: 12px;
    background-color:#ffffff;
    -moz-border-radius:6px;
    -webkit-border-radius:6px;
    border-radius:6px;
    border:1px solid #ff595f;
    display:inline-block;
    cursor:pointer;
    color:#ff595f;
    font-weight:bold;
    line-height: 28px;
    padding: 0 8px 0 8px;
    text-decoration:none;
    text-shadow:0px 1px 0px #ffffff;
  }
  .btn:hover {
    background-color:#f6f6f6;
  }
  .btn:active {
    position:relative;
    top:1px;
  }
  .btn i {
    width: 15px;
    height: 15px;
    margin: 0 5px 2px 0;
    line-height: 28px;
    vertical-align: middle;
    display: inline-block;
  }
  i.write {
    background: url(../assets/write-ic.png) no-repeat;
    background-size: contain;
  }
  i.person {
    background: url(../assets/mypage-ic.png) no-repeat;
    background-size: contain;
  }

</style>
