import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import SignupIntro from '@/components/SignupIntro';
import Terms from '@/components/Terms';
import Signup from '@/components/Signup';
import SignupSuccess from '@/components/SignupSuccess';
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
      path: '/signup-success',
      name: 'SignupSuccess',
      components: {
        default: SignupSuccess,
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
