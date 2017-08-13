import Api from '../utils/api';

export default {
  login(data) {
    return Api.post('/users/login', data);
  },
  logout() {
    return Api.post('/users/logout');
  },
  signupEmail(data) {
    return Api.post('/users/email', data);
  },
  signupUser(data) {
    return Api.put('/users/data', data);
  },
  withdraw() {
    return Api.delete('/users/withdraw');
  },
  getMe(handler) {
    return Api.get('/users/me', null, handler);
  },
};
