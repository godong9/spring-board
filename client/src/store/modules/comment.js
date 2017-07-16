import Comment from '../../api/comment';
import * as types from '../mutation-types';

// initial state
const state = {
  comments: [],
  isCommentsComplete: false,
  commentsPage: 0,
  commentsSize: 20,
};

// getters
const getters = {
  comments: paramState => paramState.comments,
  isCommentsComplete: paramState => paramState.isCommentsComplete,
  commentsPage: paramState => paramState.commentsPage,
  commentsSize: paramState => paramState.commentsSize,
};

// actions
const actions = {
  getComments({ commit }, params) {
    if (state.isCommentsComplete) {
      return;
    }
    params.page = state.commentsPage;
    params.size = state.commentsSize;
    Comment.getComments(params)
      .then(posts => commit(types.RECEIVE_COMMENTS, { posts }));
  },
  writeComment({ commit }, postData) {
    return Comment.writeComment(postData)
      .then(comment => commit(types.WRITE_COMMENT, { comment }));
  },
  deleteComment({ commit }, commentId) {
    return Comment.deleteComment(commentId)
      .then(() => commit(types.DELETE_COMMENT));
  },
};

// mutations
const mutations = {
  [types.RECEIVE_COMMENTS](paramState, { comments }) {
    const commentList = comments.data || [];
    paramState.commentsPage += 1;
    paramState.comments = paramState.posts.concat(comments);
    paramState.isCommentsComplete = commentList.length < 1 ||
      commentList.length < paramState.commentsSize;
  },
  [types.INIT_COMMENTS](paramState) {
    paramState.commentsPage = 0;
    paramState.comments = [];
    paramState.isCommentsComplete = false;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
