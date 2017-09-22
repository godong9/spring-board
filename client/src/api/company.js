import Api from '../utils/api';

export default {
  getCompanies(data) {
    return Api.get('/companies', { params: data });
  },
};
