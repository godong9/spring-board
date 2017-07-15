import user from '../../api/user';
import company from '../../api/company';
import * as types from '../mutation-types';
// initial state
const state = {
  companies: [],
};

// getters
const getters = {
  companies: paramState => paramState.companies,
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
};

// mutations
const mutations = {
  [types.RECEIVE_COMPANIES](paramState, { companies }) {
    paramState.companies = companies;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};

