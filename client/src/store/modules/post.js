import post from '../../api/post';
import * as types from '../mutation-types';

// initial state
const state = {
  posts: [],
  isComplete: false,
  page: 0,
  size: 20,
};

// getters
const getters = {
  posts: paramState => paramState.posts,
  isComplete: paramState => paramState.isComplete,
  page: paramState => paramState.page,
  size: paramState => paramState.size,
};

// actions
const actions = {
  getPosts({ commit }, params) {
    if (state.isComplete) {
      return;
    }
    params.size = state.size;
    post.getPosts(params)
      .then(posts => commit(types.RECEIVE_POSTS, { posts }));
  },
  writePost({ commit }, postData) {
    return post.writePost(postData);
  },
  initPosts({ commit }) {
    commit(types.INIT_POSTS);
  },
};

// mutations
const mutations = {
  [types.RECEIVE_POSTS](paramState, { posts }) {
    const postList = posts.data || [];
    paramState.page += 1;
    paramState.posts = paramState.posts.concat(postList);
    paramState.isComplete = postList.length < 1 || postList.length < paramState.size;
  },
  [types.INIT_POSTS](paramState) {
    paramState.page = 0;
    paramState.posts = [];
    paramState.isComplete = false;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
