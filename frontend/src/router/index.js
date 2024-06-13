import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import BreedsPage from '../views/BreedsPage.vue';

const routes = [
  { path: '/', component: HomePage },
  { path: '/breeds', component: BreedsPage }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;