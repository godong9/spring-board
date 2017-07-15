import post from '../../api/post';
import * as types from '../mutation-types';

// initial state
const state = {
  postItems: [],
};

// getters
const getters = {
  postItems: paramState => paramState.postItems,
};

// actions
const actions = {
  getPosts({ commit }) {
    post.getPosts(items => commit(types.RECEIVE_POSTS, { items }));
  },
  writePost({ commit }, postData) {
    post.writePost(postData)
      .then(response => commit(types.WRITE_POST, { response }));
  },
};

// mutations
const mutations = {
  [types.RECEIVE_POSTS](paramState, { items }) {
    paramState.postItems = items;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
