function ServerPlugin() {}

/*eslint-disable */
ServerPlugin.install = function (Vue) {
  Vue.prototype.getServerPath = function (path) {
    return process.env.SERVER_HOST + path;
  }

  Vue.prototype.errorHandler = function (response) {
    if (response.body && response.body.error) {
      alert(response.body.error.message);
    }
  }
};
/*eslint-enable */

export default ServerPlugin;
