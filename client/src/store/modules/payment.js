import Payment from '../../api/payment';

const actions = {
  subscribe({ commit }, params) {
    return Payment.subscribe(params);
  },
  unsubscribe() {
    return Payment.unsubscribe();
  },
};

export default {
  actions,
};

