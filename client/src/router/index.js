import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import Terms from '@/components/Terms';
import Signup from '@/components/Signup';
import EmailSuccess from '@/components/EmailSuccess';
import SignupStep2 from '@/components/SignupStep2';
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
import MyPage from '@/components/MyPage';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
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
  ],
});
