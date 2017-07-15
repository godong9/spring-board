import moment from 'moment';

function ServerPlugin() {}

ServerPlugin.install = (Vue) => {
  Vue.prototype.getServerPath = path => process.env.SERVER_HOST + path;

  Vue.prototype.errorHandler = (response) => {
    if (response.body && response.body.error) {
      alert(response.body.error.message);
    }
  };
  Vue.prototype.validateEmail = (email) => {
    // eslint-disable-next-line no-useless-escape
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  };

  Vue.prototype.diffDateFormat = (inputDate) => {
    const today = moment().day();
    const date = moment(inputDate).day();
    if (today !== date) {
      return moment(inputDate).format('YY.MM.DD HH:mm');
    }
    return moment(inputDate).format('HH:mm');
  };
  Vue.prototype.formatDate = date => moment(date).format('YYYY년 M월 D일');
};

export default ServerPlugin;
