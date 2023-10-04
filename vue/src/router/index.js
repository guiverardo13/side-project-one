import { createRouter, createWebHistory } from 'vue-router';
import CityPage from '../components/CityPage.vue';
import WelcomePage from '../views/WelcomePage.vue';
import HotelPage from '../components/HotelPage.vue';
import BarPage from '../components/BarPage.vue';
import EventPage from '../components/EventPage.vue';
import AboutPage from '../components/AboutPage.vue';
import store from '../store'; // Import your Vuex store

const routes = [
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
    // meta: { requiresAuth: true }, // Add meta field to require authentication
  },
  {
    path: '/city/name/:cityName/events',
    name: 'EventPage',
    component: EventPage,
    // meta: { requiresAuth: true }, // Add meta field to require authentication
  },
  {
    path: '/about',
    name: 'AboutPage',
    component: AboutPage,
    // meta: { requiresAuth: true }, // Add meta field to require authentication
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Add a navigation guard to protect routes that require authentication
router.beforeEach((to, from, next) => {
  const isAuthenticated = store.state.token; // Assuming 'token' is your authentication state in Vuex

  // Check if the route requires authentication and the user is not authenticated
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login'); // Redirect to the login page if not authenticated
  } else {
    next(); // Proceed with navigation
  }
});

export default router;
