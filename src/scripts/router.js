import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import Shop from "@/pages/Shop.vue";
import Reserve from "@/components/Reserve.vue";
import Mypage from "@/components/Mypage.vue";
import Cart from "@/components/Cart.vue";
import Menu from "@/components/Menu.vue";
import Map from "@/components/Map.vue";
import ReserveUpdate from "@/pages/ReserveUpdate.vue";
import ReserveInsert from "@/pages/ReserveInsert.vue";
import ReserveUser from "@/pages/ReserveUser.vue"

const routes = [
    {path:'/', component: Home},
    {path: '/login', component: Login},
    {path: '/shop', component: Shop},
    {path: '/reserve', component: Reserve},
    {path: '/mypage', component: Mypage},
    {path: '/cart', component: Cart},
    {path: '/menu', component: Menu},
    {path: '/map', component: Map},
    {path: '/reservetest', component: ReserveUpdate},
    {path: '/reservetest/update', component: ReserveUpdate},
    {path: '/reservetest/insert', component: ReserveInsert},
    {path: '/reserveuser/insert', component: ReserveUser}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;