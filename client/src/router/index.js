import Vue from 'vue';
import Router from 'vue-router';
import Main from '@/components/Main';
import Login from '@/components/Login';
import Board from '@/components/Board';
import Post from '@/components/Post';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
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
