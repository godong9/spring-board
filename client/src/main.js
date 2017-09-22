// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import VueResource from 'vue-resource';

import Vue from 'vue';
import App from './App';
import store from './store';
import router from './router';
import CommonUtil from './utils/CommonUtil';

Vue.config.productionTip = false;

Vue.use(VueResource);
Vue.use(CommonUtil);

window.vm = new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App },
});

