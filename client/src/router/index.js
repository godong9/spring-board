import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import Signup from '@/components/Signup';
import Terms from '@/components/Terms';
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
      path: '/signup',
      name: 'Signup',
      component: Signup,
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
