import Api from '../utils/api';

export default {
  getPosts(data) {
    return Api.get('/posts', { params: data });
  },
  writePost(data) {
    return Api.post('/posts', data);
  },
  getPost(id) {
    return Api.get(`/posts/${id}`);
  },
  likePost(id) {
    return Api.post(`/posts/${id}/like`, { unlike: false });
  },
  unlikePost(id) {
    return Api.post(`/posts/${id}/like`, { unlike: true });
  },
};
