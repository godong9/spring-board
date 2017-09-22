import Post from '../../api/post';
import * as types from '../mutation-types';

// initial state
const state = {
  posts: [],
  post: {},
  isPostsComplete: false,
  postsPage: 0,
  postsSize: 20,
};

// getters
const getters = {
  posts: paramState => paramState.posts,
  post: paramState => paramState.post,
  isPostsComplete: paramState => paramState.isPostsComplete,
  postsPage: paramState => paramState.postsPage,
  postsSize: paramState => paramState.postsSize,
};

// actions
const actions = {
  initPosts({ commit }) {
    commit(types.INIT_POSTS);
  },
  getPosts({ commit }, params) {
    if (state.isPostsComplete) {
      return;
    }
    params.size = state.postsSize;
    params.page = state.postsPage;
    Post.getPosts(params)
      .then(posts => commit(types.RECEIVE_POSTS, { posts }));
  },
  writePost({ commit }, postData) {
    return Post.writePost(postData);
  },
  getPost({ commit }, params) {
    return Post.getPost(params)
      .then(post => commit(types.GET_POST, { post }));
  },
  likePost({ commit }, postId) {
    return Post.likePost(postId)
      .then(() => commit(types.LIKE_POST));
  },
  unlikePost({ commit }, postId) {
    return Post.unlikePost(postId)
      .then(() => commit(types.UNLIKE_POST));
  },
  report({ commit }, postId, postData) {
    return Post.report(postId, postData);
  },
};

// mutations
const mutations = {
  [types.RECEIVE_POSTS](paramState, { posts }) {
    const postList = posts.data || [];
    paramState.postsPage += 1;
    paramState.posts = paramState.posts.concat(postList);
    paramState.isPostsComplete = postList.length < 1 || postList.length < paramState.postsSize;
  },
  [types.INIT_POSTS](paramState) {
    paramState.postsPage = 0;
    paramState.posts = [];
    paramState.isPostsComplete = false;
  },
  [types.GET_POST](paramState, { post }) {
    paramState.post = post.data;
  },
  [types.LIKE_POST](paramState) {
    paramState.post.is_liked = true;
    paramState.post.post_like_count += 1;
  },
  [types.UNLIKE_POST](paramState) {
    paramState.post.is_unliked = true;
    paramState.post.post_unlike_count += 1;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
