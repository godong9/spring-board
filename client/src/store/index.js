import createLogger from 'vuex/dist/logger';

import Vuex from 'vuex';
// import * as actions from './actions';
import * as getters from './getters';
import header from './modules/header';

const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
  // actions,
  getters,
  modules: {
    header,
  },
  strict: debug,
  plugins: debug ? [createLogger()] : [],

});
