import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import axios from 'axios';

// Задаем базовый URL для всех запросов из приложения
axios.defaults.baseURL = 'http://localhost:8080/';

createApp(App).use(router).mount('#app')
