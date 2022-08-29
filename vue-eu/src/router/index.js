import Vue from 'vue'
import Router from 'vue-router'

import Login from "../views/Login";
import Reg from "../views/Reg";
import Index from "../views/Index";
import Personal from "../views/Personal";
import love from "../views/love";
import myhistory from "../views/myhistory"
import myinfo from "../views/myinfo";
import Detail from "../views/Detail";
import addDetail from "../views/addDetail";
import updatepwd from "../views/updatepwd";
import order from "../views/order";
Vue.use(Router);


export default new Router({
  mode: 'history',
  routes: [

    {
      path: '/',
      redirect:'/index'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/updatepwd',
      component: updatepwd
    },
    {
      path: '/reg',
      component: Reg
    },
    {
      path: '/index',
      component: Index
    },
    {
      path: '/personal',
      component: Personal,
      children: [
        {path: '/user/love', component: love},
        {path: '/user/myhistory', component: myhistory},
        {path: '/user/myinfo', component: myinfo},
        {path: '/user/order', component: order}
      ]
    },
    {
      path: '/detail',
      component: Detail
    },
    {
      path: '/add',
      component: addDetail
    },
  ]
});
