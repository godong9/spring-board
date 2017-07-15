import Api from '../utils/api';

export default {
  login(data) {
    return Api.post('/users/login', data);
  },
};
