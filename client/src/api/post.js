import Api from '../utils/api';

export default {
  getPosts(data) {
    return Api.get('/posts', { params: data });
  },
  writePost(data) {
    return Api.post('/posts', data);
  },
};
