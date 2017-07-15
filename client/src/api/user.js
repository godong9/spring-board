import Api from '../utils/api';

export default {
  login(data) {
    return Api.post('/users/login', data);
  },
  signupEmail(data) {
    return Api.post('/users/email', data);
  },
  signupUser(data) {
    return Api.put('/users/data', data);
  },
  getMe() {
    return Api.get('/users/me');
  },
};
