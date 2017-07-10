import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import SignupTerms from '@/components/SignupTerms';
import Signup from '@/components/Signup';
import EmailSuccess from '@/components/EmailSuccess';
import SignupStep2 from '@/components/SignupStep2';
import NeedPurchase from '@/components/NeedPurchase';
import CompletePurchase from '@/components/CompletePurchase';
import PaymentTerms from '@/components/PaymentTerms';
import Payment from '@/components/Payment';
import ResetPassword from '@/components/ResetPassword';
import ChangePassword from '@/components/ChangePassword';
import Login from '@/components/Login';
import Posts from '@/components/Posts';
import BoardDetail from '@/components/BoardDetail';
import Post from '@/components/Post';
import Nav from '@/components/Nav';
import TitleHeader from '@/components/TitleHeader';
import Footer from '@/components/Footer';
import MyPage from '@/components/MyPage';
import Withdraw from '@/components/Withdraw';
import Cancel from '@/components/Cancel';
import CancelComplete from '@/components/CancelComplete';
import Terms from '@/components/Terms';
import Privacy from '@/components/Privacy';

Vue.use(Router);
window.Vue = Vue;

window.jQuery = require('jquery');

window.$ = window.jQuery;
export default new Router({
  mode: 'history',
  scrollBehavior: (to, from, savedPosition) => {
    if (savedPosition) {
      return savedPosition;
    }
    return { x: 0, y: 0 };
  },
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
    },
    {
      path: '/signup-terms',
      name: 'SignupTerms',
      components: {
        default: SignupTerms,
        nav: Nav,
      },
    },
    {
      path: '/signup',
      name: 'Signup',
      components: {
        default: Signup,
        nav: Nav,
      },
    },
    {
      path: '/email-success',
      name: 'EmailSuccess',
      components: {
        default: EmailSuccess,
        nav: Nav,
      },
    },
    {
      path: '/signup-step2',
      name: 'SignupStep2',
      components: {
        default: SignupStep2,
        nav: Nav,
      },
    },
    {
      path: '/need-purchase',
      name: 'NeedPurchase',
      components: {
        default: NeedPurchase,
        nav: Nav,
      },
    },
    {
      path: '/complete-purchase',
      name: 'CompletePurchase',
      components: {
        default: CompletePurchase,
        nav: Nav,
      },
    },
    {
      path: '/payment-terms',
      name: 'PaymentTerms',
      components: {
        default: PaymentTerms,
        nav: Nav,
      },
    },
    {
      path: '/payment',
      name: 'Payment',
      components: {
        default: Payment,
        nav: Nav,
      },
    },
    {
      path: '/change-password',
      name: 'ChangePassword',
      components: {
        default: ChangePassword,
        nav: Nav,
      },
    },
    {
      path: '/reset-password',
      name: 'ResetPassword',
      components: {
        default: ResetPassword,
        nav: Nav,
      },
    },
    {
      path: '/login',
      name: 'Login',
      components: {
        default: Login,
        nav: Nav,
      },
    },
    {
      path: '/posts/:id',
      name: 'Posts',
      components: {
        default: Posts,
        nav: Nav,
        footer: Footer,
      },
    },
    {
      path: '/board/:id/detail',
      name: 'BoardDetail',
      components: {
        nav: TitleHeader,
        default: BoardDetail,
      },
    },
    {
      path: '/board/:id/post',
      name: 'Post',
      component: Post,
    },
    {
      path: '/mypage/:id',
      name: 'MyPage',
      components: {
        default: MyPage,
        nav: TitleHeader,
        footer: Footer,
      },
    },
    {
      path: '/mypage/:id/withdraw',
      name: 'Withdraw',
      components: {
        default: Withdraw,
        nav: TitleHeader,
      },
    },
    {
      path: '/mypage/:id/cancel',
      name: 'Cancel',
      components: {
        default: Cancel,
        nav: TitleHeader,
      },
    },
    {
      path: '/mypage/:id/cancel-complete',
      name: 'CancelComplete',
      components: {
        default: CancelComplete,
        nav: TitleHeader,
      },
    },
    {
      path: '/terms',
      name: 'Terms',
      components: {
        default: Terms,
        nav: Nav,
      },
    },
    {
      path: '/privacy',
      name: 'Privacy',
      components: {
        default: Privacy,
        nav: Nav,
      },
    },
    {
      path: '*',
      redirect: '/',
    },
  ],
});
