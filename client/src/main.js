// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import VueResource from 'vue-resource';

import Vue from 'vue';
import App from './App';
import router from './router';
import CommonUtil from './utils/CommonUtil';

Vue.config.productionTip = false;

Vue.use(VueResource);
Vue.use(CommonUtil);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
});

