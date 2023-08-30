import { createRouter, createWebHistory } from 'vue-router';
import CityPage from '../components/CityPage.vue';
import WelcomePage from '../views/WelcomePage.vue';
import HotelPage from '../components/HotelPage.vue';
import BarPage from '../components/BarPage.vue';

const routes = [
    // ... your other routes  
    {
      path: '/',
      name: 'WelcomePage',
      component: WelcomePage, // Assuming this is your WelcomePage component
    },
    {
      path: '/city/name/:cityName',
      name: 'city-page',
      component: CityPage,
      props: true, // This allows passing route parameters as props
    },
    {
      path: '/city/name/:cityName/hotels',
      name: 'HotelPage',
      component: HotelPage,
    },
    {
      path: '/city/name/:cityName/bars',
      name: 'BarPage',
      component: BarPage,
    },
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  export default router;

