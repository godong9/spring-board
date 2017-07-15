import Vue from 'vue';
import Vuex from 'vuex';
import createLogger from 'vuex/dist/logger';

// import * as actions from './actions';
// import * as getters from './getters';
import header from './modules/header';
import post from './modules/post';
import user from './modules/user';

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
  // actions,
  // getters,
  modules: {
    namespaced: true,
    header,
    post,
    user,
  },
  strict: debug,
  plugins: debug ? [createLogger()] : [],

});
