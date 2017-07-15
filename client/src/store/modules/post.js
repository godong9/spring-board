import post from '../../api/post';
import * as types from '../mutation-types';

// initial state
const state = {
  posts: [],
  isComplete: false,
};

// getters
const getters = {
  posts: paramState => paramState.posts,
  isComplete: paramState => paramState.isComplete,
};

// actions
const actions = {
  getPosts({ commit }, params) {
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
