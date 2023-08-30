<template>
  <div class="container-bar-restaurant">
    <header>
    <nav class="container-top-bar-restaurant" v-if="isBarPage">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item register" @click="openRegistrationModal">Register</div>
        <div class="item sign-in" @click="openLoginModal">Sign in</div>
    </nav>
    </header>
    <main>
    <div class="bar-restaurant-main" v-if="isBarPage">
        <div class="cover-bar-container">
            <img src="..\assets\bar_restaurant.jpg" alt="bar-cover" class="cover-picture" v-if="isBarPage">
            <div class="black-overlay"></div>
        </div>
    </div>
    <div class="top-bar" v-if="isBarPage" >
      <h2>Top Bars & Restaurants in {{ $route.params.cityName }}</h2>
    </div>
    <router-link :to="'/city/name/' + $route.params.cityName" class="back-button" v-if="isBarPage">Back</router-link>
    <div class="title" v-if="isBarPage">
    <div class="title-section">
        <h3 class="title bars" v-if="selectedSection === 'Bars'">Bars</h3>
        <h3 class="title restaurant" v-if="selectedSection === 'Restaurants'">Restaurants</h3>
        <div class="toggle-button">
        <i
          class="fa-solid fa-toggle-on"
          v-if="toggleState && isBarPage"
          @click="toggleSection('Restaurants')"
        ></i>
        <i
          class="fa-solid fa-toggle-off"
          v-else-if="!toggleState && isBarPage"
          @click="toggleSection('Bars')"
        ></i>
      </div>
    </div>
    </div>
    <div class="bar-list" v-if="isBarPage && selectedSection === 'Bars' && toggleState">
          <div v-for="bar in bars" :key="bar.bar_id" class="bar-box" >
            <img :src="bar.barPicture" alt="Image">
            <h3>{{ bar.barName}}</h3>
            <p> {{ bar.barPrice }}</p>
            <p> {{ bar.barAddress }}</p>
            <p> {{ bar.barPhone }}</p>
            <a :href="bar.barWebsite" target="_blank" class="menu-link">Reserve</a>
            <i v-if="bar.isLiked" @click="toggleLike(bar)" class="fa-solid fa-heart red-heart-bar"></i>
            <i v-else @click="toggleLike(bar)" class="fa-regular fa-heart red-heart-bar"></i>
          </div>
        </div>
        <div class="restaurant-list" v-if="isBarPage && selectedSection === 'Restaurants' && !toggleState">
          <div v-for="restaurant in restaurants" :key="restaurant.restaurant_id" class="restaurant-box">
            <img :src="restaurant.restaurantPicture" alt="Image">
            <h3>{{ restaurant.restaurantName}}</h3>
            <p> {{ restaurant.restaurantPrice }}</p>
            <p> {{ restaurant.restaurantAddress }}</p>
            <p> {{ restaurant.restaurantPhone }}</p>
            <a :href="restaurant.restaurantWebsite" target="_blank" class="menu-link">Reserve</a>
            <i v-if="restaurant.isLiked" @click="toggleLike(restaurant)" class="fa-solid fa-heart red-heart-bar"></i>
            <i v-else @click="toggleLike(restaurant)" class="fa-regular fa-heart red-heart-bar"></i>
          </div>
        </div>
    </main>
    <footer class="container-footer" id="contact" v-if="isBarPage">
            <div class="footer-content">
        <h2>Contact Me</h2>
        <p>If you have any questions or feedback, feel free to get in touch with me.</p>
    <div class="contact-info">
        <p>Email: gverardo13@gmail.com</p>
        <p>Phone: +1 (641) 758-1207</p>
    </div>
    <div class="social-icons">
      <a href="https://www.linkedin.com/in/guilhermeverardo/"  target="_blank" class="social-icon"><i class="fa-brands fa-linkedin"></i></a>
      <a href="https://github.com/guiverardo13" target="_blank" class="social-icon"><i class="fa-brands fa-github"></i></a>
    </div>
</div>
    </footer>
    <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
          <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal" />
          <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
  </div>
</template>

<script>
import BarServices from '../services/BarServices.js';
import RestaurantServices from '../services/RestaurantServices.js'
import RegisterUserModal from './RegisterUserModal.vue';
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';

export default {
    name: 'BarPage',
    data() {
        return {
            showRegistrationModal: false,
            showAccountCreatedModal: false,
            showLoginModal: false,
            toggleState: true, // Initial toggle state
            selectedSection: 'Bars', // Initial selected section
            bars: [],
            restaurants: [] 
        };
    },
    components: {
        RegisterUserModal,
        AccountCreatedModal,
        LoginModal,
    },
    computed: {
        isBarPage() {
            return this.$route.name === 'BarPage'; // Adjust the route name if needed
        },
    },
    created() {
        // Fetch bar data from the service
        this.fetchBars();
        this.fetchRestaurants();
    },
    methods: {
      async fetchBars() {
        try {
            this.bars = await BarServices.getBarsByCityName(this.$route.params.cityName);
        } catch (error) {
            console.error('Error fetching bar data:', error);
        }
    },

    async fetchRestaurants() {
        try {
            this.restaurants = await RestaurantServices.getRestaurantsByCityName(this.$route.params.cityName);
        } catch (error) {
            console.error('Error fetching restaurant data:', error);
        }
    },
        toggleLike(bar) {
            bar.isLiked = !bar.isLiked;
        },
        openRegistrationModal() {
            this.showRegistrationModal = true;
        },
        closeRegistrationModal() {
            this.showRegistrationModal = false;
        },
        handleRegistrationSuccess() {
            this.closeRegistrationModal();
            this.openSuccessModal();
        },
        closeSuccessModal() {
            this.showAccountCreatedModal = false;
        },
        openSuccessModal() {
            this.showAccountCreatedModal = true;
        },
        openLoginModal() {
            this.showLoginModal = true;
        },
        closeLoginModal() {
            this.showLoginModal = false;
        },
        handleLoginSuccess() {
            this.closeLoginModal();
            // ... handle successful login ...
        },

        toggleSection(section) {
          this.toggleState = !this.toggleState;
          this.selectedSection = section;
        },
    },
};
</script>

<style>

.title-section {
  margin-top: 10px; /* Adjust the margin as needed */
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center content vertically */
  align-items: center; /* Center content horizontally */
}

.toggle-button {
  font-size: 30px;
}

.title-container {
  flex-grow: 1;
  margin: 0 20px; /* Add spacing between title and toggle button */
}

.title.bars,
.title.restaurant {
  font-size: 30px;
  margin: 0; /* Remove any default margin */
  margin-top: 10px;
}

.title.bars {
  
  white-space: nowrap;
  font-size: 30px;
}

.title.restaurant {
  white-space: nowrap;
  font-size: 30px;
}

.bar-box {
  width: 310px;
  padding: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  border: 1px solid #1a1818;
  background: #D8D0C1;
  text-align: left; /* Align content to the left */
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.9); /* Add box shadow */;
}

.restaurant-box {
  width: 310px;
  padding: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  border: 1px solid #1a1818;
  background: #D8D0C1;
  text-align: left; /* Align content to the left */
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.9); /* Add box shadow */;
}

.restaurant-box img {
  border: 3px solid black;
  max-height: 200px;
  width: 100%;
}

.bar-box img {
  border: 3px solid black;
  max-height: 200px;
  width: 100%;
}

.restaurant-box h3 {
  margin-top: 10px;
  font-size: 18px;
  color: black; /* Set font color to black */
}

.restaurant-box p {
  margin: 5px 0;
  color: black; /* Set font color to black */
}

.restaurant-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Align content to the left */
  margin-top: 30px; /* Adjust margin as needed */
}

.bar-box h3 {
  margin-top: 10px;
  font-size: 18px;
  color: black; /* Set font color to black */
}

.bar-box p {
  margin: 5px 0;
  color: black; /* Set font color to black */
}

.bar-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Align content to the left */
  margin-top: 30px; /* Adjust margin as needed */
}

.top-bar {
  position: absolute;
  top: 480px;
  left: 380px;
  margin: 0;
  font-size: 24px;
  z-index: 1; /* Place on top of overlay */
}

.top-bar h2 {
  font-size: 45px;
  text-align: center;
  margin: 0;
}

.top-restaurant {
  position: absolute;
  top: 480px;
  left: 380px;
  margin: 0;
  font-size: 24px;
  z-index: 1; /* Place on top of overlay */
}

.top-restaurant h2 {
  font-size: 45px;
  text-align: center;
  margin: 0;
}

.restaurant-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Align content to the left */
  margin-top: 30px; /* Adjust margin as needed */
  margin-bottom: 30px;
}

.container-bar-restaurant {
margin: 0 auto;
max-width: 1200px;
padding: 0 20px;
flex: 1;
}

.container-top-bar-restaurant {
display: flex;
justify-content: space-between;
font-size: 18px;
align-items: center;
font-family: 'Russo One', sans-serif;
padding: 20px 0;
width: 100%;
}

.container-top-bar-restaurant h1 {
  font-size: 30px;
  margin: 0;
  padding: 0;
  align-items: flex-start;
}

.container-top-bar-restaurant a {
  color: white;
  text-decoration: none;
}

.item.about:hover,
.item.sign-in:hover,
.item.contact:hover,
.item.register:hover {
  color: #00AFEF;
  text-decoration: underline;
}

.container-footer {
position: relative;
padding: 0;
color: rgb(0, 0, 0);
text-align: center;
}

.footer-content {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  max-height: 160px; /* Set a max height for the content */
}

.footer-content h2 {
font-size: 28px;
margin-top: 0px;
margin-bottom: 20px;
}

.footer-content p {
font-size: 18px;
margin-bottom: 20px;
}

.contact-info p {
font-size: 16px;
margin: 5px 0;
}

.menu-link {
  color: blue;
}

.social-icon {
font-size: 24px;
margin-top: 20px;
color: white;
margin: 0 10px;
text-decoration: none;
color: blue;
}

.cover-bar-container {
  position: relative;
}

.cover-picture {
  width: 100%;
  max-height: 400px;
}

.black-overlay {
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 85px;
  width: 100%;
  height: 78%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.red-heart-bar {
  position: relative;
  font-size: 25px;
  bottom: -5px;
  right: -220px;
  color: red; /* Change the color as needed */
}

.red-heart:hover {
  cursor: pointer;
}

</style>