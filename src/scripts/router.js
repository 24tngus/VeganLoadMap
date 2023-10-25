import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import Shop from "@/pages/Shop.vue";
import Reserve from "@/components/Reserve.vue";
import Mypage from "@/components/Mypage.vue";
import Cart from "@/components/Cart.vue";
import Menu from "@/components/Menu.vue";
import Map from "@/components/Map.vue";
import Faq from "@/components/Faq.vue";
import Vegan from "@/components/Vegan.vue";
import Vegan2 from "@/components/Vegan2.vue";
import Korean from "@/components/foodCategory/Korean.vue";
import Western from "@/components/foodCategory/Western.vue";
import Fusion from "@/components/foodCategory/Fusion.vue";

const routes = [
    {path:'/', component: Home},
    {path: '/login', component: Login},
    {path: '/shop', component: Shop},
    {path: '/reserve', component: Reserve},
    {path: '/mypage', component: Mypage},
    {path: '/cart', component: Cart},
    {path: '/menu', component: Menu},
    {path: '/map', component: Map},
    {path: '/faq', component: Faq},
    {path: '/vegan', component: Vegan},
    {path: '/vegan2', component: Vegan2},
    {path: '/foodCategory/Korean', component: Korean},
    {path: '/foodCategory/Western', component: Western},
    {path: '/foodCategory/Fusion', component: Fusion}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;