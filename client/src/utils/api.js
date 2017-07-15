import Vue from 'vue';

export default {
  get(url, request) {
    return Vue.http.get(process.env.SERVER_HOST + url, request)
      .then(response => Promise.resolve(response.body.data))
      .catch(error => Promise.reject(error));
  },
  post(url, request) {
    return Vue.http.post(process.env.SERVER_HOST + url, request)
      .then(response => Promise.resolve(response))
      .catch(error => Promise.reject(error));
  },
  put(url, request) {
    return Vue.http.put(process.env.SERVER_HOST + url, request)
      .then(response => Promise.resolve(response))
      .catch(error => Promise.reject(error));
  },
  delete(url, request) {
    return Vue.http.delete(process.env.SERVER_HOST + url, request)
      .then(response => Promise.resolve(response))
      .catch(error => Promise.reject(error));
  },
};
