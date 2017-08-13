import User from '../../api/user';
import company from '../../api/company';
import * as types from '../mutation-types';
// initial state
const state = {
  companies: [],
  me: {},
};

// getters
const getters = {
  companies: paramState => paramState.companies,
  me: paramState => paramState.me,
};

// actions
const actions = {
  login({ commit }, data) {
    return User.login(data);
  },
  logout() {
    return User.logout();
  },
  signupEmail({ commit }, data) {
    return User.signupEmail(data);
  },
  signupUser({ commit }, data) {
    return User.signupUser(data);
  },
  withdraw() {
    return User.withdraw();
  },
  getCompanies({ commit }, data) {
    return company.getCompanies(data)
      .then(companies => commit(types.RECEIVE_COMPANIES, { companies }));
  },
  getMe({ commit }, handler) {
    return User.getMe(handler)
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

