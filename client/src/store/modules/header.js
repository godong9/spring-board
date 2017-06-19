import * as types from '../mutation-types';

// initial state
const state = {
  titleText: '',
};

// getters
const getters = {
  titleText: paramState => paramState.titleText,
};

// actions
const actions = {

};

// mutations
/* eslint no-param-reassign: ["error", { "props": false }]*/
const mutations = {
  [types.SET_HEADER_TEXT](paramState, { text }) {
    paramState.titleText = text;
  },

};

export default {
  state,
  getters,
  actions,
  mutations,
};
