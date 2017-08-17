import Api from '../utils/api';

export default {
  subscribe(data) {
    return Api.post('/payments/subscribe', data);
  },
  unsubscribe() {
    return Api.delete('/payments/subscribe');
  },
};
