function ServerPlugin() {}

/*eslint-disable */
ServerPlugin.install = function (Vue) {
  Vue.prototype.getServerPath = function (path) {
    return process.env.SERVER_HOST + path;
  };

  Vue.prototype.errorHandler = function (response) {
    if (response.body && response.body.error) {
      alert(response.body.error.message);
    }
  };

  Vue.prototype.validateEmail = function (email) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  };
};
/*eslint-enable */

export default ServerPlugin;
