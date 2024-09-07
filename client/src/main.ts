import { createApp } from 'vue'
import {createWebHistory, createRouter} from "vue-router";
import './style.css'
import App from './App.vue'
import Home from "./components/Home.vue";
import CandidateTable from "./components/CandidateTable.vue";
import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';

const routes = [
    {path: "/", component: Home},
    {path: "/home", component: Home},
    {path: "/candidate-table", component: CandidateTable},
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes
});



const app = createApp(App);
app.use(router);
app.use(PrimeVue, {
    // Default theme configuration
    theme: {
        preset: Aura,
        options: {
            prefix: 'p',
            darkModeSelector: 'system',
            cssLayer: false
        }
    }
});

app.mount('#app');

