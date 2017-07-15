import post from '../../api/post';
import * as types from '../mutation-types';

// initial state
const state = {
  posts: [],
  isComplete: false,
  page: 0,
};

// getters
const getters = {
  posts: paramState => paramState.posts,
  isComplete: paramState => paramState.isComplete,
  page: paramState => paramState.page,
};

// actions
const actions = {
  getPosts({ commit }, params) {
    if (params.page === 0) {
      state.isComplete = false;
      state.page = 0;
      state.posts = [];
    }
    if (state.isComplete) {
      return;
    }
    post.getPosts(params)
      .then(posts => commit(types.RECEIVE_POSTS, { posts }));
  },
  writePost({ commit }, postData) {
    return post.writePost(postData);
  },
};

// mutations
const mutations = {
  [types.RECEIVE_POSTS](paramState, { posts }) {
    const postList = posts.data || [];
    paramState.page += 1;
    paramState.posts = paramState.posts.concat(postList);
    paramState.isComplete = postList.length < 1;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
