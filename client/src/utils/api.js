import Vue from 'vue';

const getServerPath = path => process.env.SERVER_HOST + path;
const errorHandler = (error) => {
  if (error.status === 403) {
    window.vm.$router.push('/login');
  } else {
    alert((error && error.body && error.body.error && error.body.error.message) || '요청에 실패 했습니다. 잠시후 다시 시도 해주세요.');
  }
  return Promise.reject(error);
};
export default {
  getServerPath,
  get(url, request) {
    return Vue.http.get(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch(error => errorHandler(error));
  },
  post(url, request) {
    return Vue.http.post(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch(error => errorHandler(error));
  },
  put(url, request) {
    return Vue.http.put(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch(error => errorHandler(error));
  },
  delete(url, request) {
    return Vue.http.delete(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch(error => errorHandler(error));
  },
};
