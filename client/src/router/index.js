import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import SignupIntro from '@/components/SignupIntro';
import Terms from '@/components/Terms';
import Signup from '@/components/Signup';
import EmailSuccess from '@/components/EmailSuccess';
import PaymentSignup from '@/components/PaymentSignup';
import NeedPurchase from '@/components/NeedPurchase';
import CompletePurchase from '@/components/CompletePurchase';
import UserUpdate from '@/components/UserUpdate';
import ResetPassword from '@/components/ResetPassword';
import ChangePassword from '@/components/ChangePassword';
import Login from '@/components/Login';
import Board from '@/components/Board';
import BoardDetail from '@/components/BoardDetail';
import Post from '@/components/Post';
import Nav from '@/components/Nav';
import TitleHeader from '@/components/TitleHeader';
import Footer from '@/components/Footer';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
    },
    {
      path: '/signup-intro',
      name: 'SignupIntro',
      component: SignupIntro,
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
      path: '/payment-signup',
      name: 'PaymentSignup',
      components: {
        default: PaymentSignup,
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
      path: '/user-update',
      name: 'UserUpdate',
      components: {
        default: UserUpdate,
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
      path: '/board/:id',
      name: 'Board',
      components: {
        default: Board,
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
      path: '/post/:id',
      name: 'post',
      component: Post,
    },
  ],
});
