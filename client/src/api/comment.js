import Api from '../utils/api';

export default {
  writeComment(data) {
    return Api.post('/comments', data);
  },
  getComments(params) {
    return Api.get('/comments', { params });
  },
  deleteComment(id) {
    return Api.delete(`/comments/${id}`);
  },
};
