import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router/index.js";
import "./index.css";
import { library } from '@fortawesome/fontawesome-svg-core';
import {
    FontAwesomeIcon,
    FontAwesomeLayers,
    FontAwesomeLayersText
  } from '@fortawesome/vue-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';

library.add(fas, far, fab);
const app = createApp(App)
  .component('font-awesome-icon', FontAwesomeIcon)
  .component('font-awesome-layers', FontAwesomeLayers)
  .component('font-awesome-layers-text', FontAwesomeLayersText);
app.use(router);
app.mount('#app');