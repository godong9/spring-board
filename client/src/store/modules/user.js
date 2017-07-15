import user from '../../api/user';

// actions
const actions = {
  login({ commit }, data) {
    return user.login(data);
  },
};

export default {
  actions,
};
