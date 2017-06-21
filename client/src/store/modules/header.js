import * as types from '../mutation-types';

// initial state
const state = {
  titleText: '',
  showHeaderButton: false,
};

// getters
const getters = {
  titleText: paramState => paramState.titleText,
  showHeaderButton: paramState => paramState.showHeaderButton,
};

// actions
const actions = {
  setTitle({ commit }, text) {
    commit(types.SET_HEADER_TEXT, { text });
  },
  showHeaderButton({ commit }) {
    commit(types.SHOW_HEADER_BUTTON);
  },
};

// mutations
const mutations = {
  [types.SET_HEADER_TEXT](paramState, { text }) {
    paramState.titleText = text;
  },
  [types.SHOW_HEADER_BUTTON](paramState) {
    paramState.showHeaderButton = true;
  },

};

export default {
  state,
  getters,
  actions,
  mutations,
};
