const merge = require('webpack-merge');
const prodEnv = require('./prod.env');

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  SERVER_HOST: '"http://192.168.0.3:9700/api"',
});
