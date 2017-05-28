function ServerPlugin() {}

/*eslint-disable */
ServerPlugin.install = function (Vue) {
  Vue.prototype.getServerPath = function (path) {
    return process.env.SERVER_HOST + path;
  }
};
/*eslint-enable */

export default ServerPlugin;
