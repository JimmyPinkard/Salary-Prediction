import { createApp } from 'vue'
import {createWebHistory, createRouter} from "vue-router";
import './style.css'
import App from './App.vue'
import Home from "./components/Home.vue";
import CandidateTable from "./components/CandidateTable.vue";

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
app.mount('#app');

