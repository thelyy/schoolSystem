import Vue from 'vue'
import App from './App'

import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import VueAxios from 'vue-axios';
import head from "./components/head";
import foot from "./components/foot"
Vue.use(VueAxios, axios);
Vue.use(router);
Vue.use(ElementUI);
Vue.component("Head", head);
Vue.component("Foot", foot);



new Vue({
  el: '#app',
  components: {App},
  router,
  render: h => h(App)
});
