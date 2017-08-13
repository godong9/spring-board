import Payment from '../../api/payment';

const actions = {
  unsubscribe() {
    return Payment.unsubscribe();
  },
};

export default {
  actions,
};

