import user from '../../api/user';
import company from '../../api/company';
import * as types from '../mutation-types';
// initial state
const state = {
  companies: [],
  me: null,
};

// getters
const getters = {
  companies: paramState => paramState.companies,
  me: paramState => paramState.me,
};

// actions
const actions = {
  login({ commit }, data) {
    return user.login(data);
  },
  signupEmail({ commit }, data) {
    return user.signupEmail(data);
  },
  signupUser({ commit }, data) {
    return user.signupUser(data);
  },
  getCompanies({ commit }, data) {
    return company.getCompanies(data)
      .then(companies => commit(types.RECEIVE_COMPANIES, { companies }));
  },
  getMe({ commit }) {
    return user.getMe()
      .then(me => commit(types.GET_ME, { me }));
  },
};

// mutations
const mutations = {
  [types.RECEIVE_COMPANIES](paramState, { companies }) {
    paramState.companies = companies.data;
  },
  [types.GET_ME](paramState, { me }) {
    paramState.me = me.data;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};

