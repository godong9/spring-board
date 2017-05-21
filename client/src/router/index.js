import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import SignupIntro from '@/components/SignupIntro';
import Terms from '@/components/Terms';
import Signup from '@/components/Signup';
import EmailSuccess from '@/components/EmailSuccess';
import PaymentSignup from '@/components/PaymentSignup';
import NeedPurchase from '@/components/NeedPurchase';
import Login from '@/components/Login';
import Board from '@/components/Board';
import Post from '@/components/Post';
import Nav from '@/components/Nav';


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
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/board/:id',
      name: 'board',
      component: Board,
    },
    {
      path: '/post/:id',
      name: 'post',
      component: Post,
    },
  ],
});
