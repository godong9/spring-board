import Api from '../utils/api';

export default {
  unsubscribe() {
    return Api.delete('/payments/subscribe');
  },
};
