import Vue from 'vue';

const getServerPath = path => process.env.SERVER_HOST + path;
const defaultHandler = (error) => {
  alert((error && error.body && error.body.error && error.body.error.message) || '요청에 실패 했습니다. 잠시후 다시 시도 해주세요.');
};
export default {
  getServerPath,
  get(url, request, handler) {
    const errorHandler = handler || defaultHandler;
    return Vue.http.get(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch((error) => {
        errorHandler(error);
        return Promise.reject(error);
      });
  },
  post(url, request, handler) {
    const errorHandler = handler || defaultHandler;
    return Vue.http.post(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch((error) => {
        errorHandler(error);
        return Promise.reject(error);
      });
  },
  put(url, request, handler) {
    const errorHandler = handler || defaultHandler;
    return Vue.http.put(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch((error) => {
        errorHandler(error);
        return Promise.reject(error);
      });
  },
  delete(url, request, handler) {
    const errorHandler = handler || defaultHandler;
    return Vue.http.delete(getServerPath(url), request)
      .then(response => Promise.resolve(response.body))
      .catch((error) => {
        errorHandler(error);
        return Promise.reject(error);
      });
  },
};
