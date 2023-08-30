<template>
  <div class="container-hotel">
    <header>
    <nav class="container-top-hotel" v-if="isHotelPage">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item register" @click="openRegistrationModal">Register</div>
        <div class="item sign-in" @click="openLoginModal">Sign in</div>
    </nav>
    </header>
    <main>
    <div class="hotel-main">
        <div class="cover-hotel-container">
            <img src="../assets/hotel.avif" alt="hotel-cover" class="cover-picture" v-if="isHotelPage">
            <div class="black-overlay"></div>
        </div>
    </div>
    <div class="top-hotels" v-if="isHotelPage" >
      <h2>Top Hotels in {{ $route.params.cityName }}</h2>
    </div>
    <router-link :to="'/city/name/' + $route.params.cityName" class="back-button" v-if="isHotelPage">Back</router-link>
    <div class="hotel-list" v-if="isHotelPage">
          <div v-for="hotel in hotels" :key="hotel.hotel_id" class="hotel-box">
            <img :src="hotel.hotelPicture" alt="Hotel Image">
            <h3>{{ hotel.hotelName}}</h3>
            <p> {{ hotel.hotelPrice }}</p>
            <p> {{ hotel.hotelAddress }}</p>
            <p> {{ hotel.hotelPhone }}</p>
            <a :href="hotel.hotelWebsite" target="_blank" class="book-link">Book a room</a>
            <i
          v-if="hotel.isLiked"
          @click="toggleLike(hotel)"
          class="fa-solid fa-heart red-heart"
        ></i>
        <i
          v-else
          @click="toggleLike(hotel)"
          class="fa-regular fa-heart red-heart"
        ></i>
          </div>
        </div>
    </main>
    <footer class="container-footer" id="contact">
            <div class="footer-content" v-if="isHotelPage">
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
import HotelServices from '../services/HotelServices.js';
import RegisterUserModal from './RegisterUserModal.vue'
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';

export default {
    name: 'HotelPage',  
    data() {
      return {
        showRegistrationModal: false,
        showAccountCreatedModal: false,
        showLoginModal: false,
        hotels: [] 
      };
  },

    components: {
      RegisterUserModal,
      AccountCreatedModal,
      LoginModal,
    },  

    computed: {
    isHotelPage() {
      return this.$route.name === 'HotelPage'; // Adjust the route name if needed
    },
  },

  created() {
    // Fetch hotel data from the service
    this.fetchHotels();
  },

  methods: {
    async fetchHotels() {
      try {
        this.hotels = await HotelServices.getHotelsByCityName(this.$route.params.cityName);
      } catch (error) {
        console.error('Error fetching hotel data:', error);
      }
    },

    toggleLike(hotel) {
      hotel.isLiked = !hotel.isLiked;
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
  },
};
</script>

<style>
.back-button {
  font-size: 20px;
  color: blue;
}

.top-hotels {
  display: flex;
  justify-content: center;
  align-items: flex-start; /* Align content slightly lower */
  margin-top: 150px; /* Adjust margin to control distance from navigation */
}

.top-hotels h2 {
  font-size: 40px;
  text-align: center;
  margin: 0;
}

.container-hotel {
margin: 0 auto;
max-width: 1200px;
padding: 0 20px;
flex: 1;
}

.container-top-hotel {
display: flex;
justify-content: space-between;
font-size: 18px;
align-items: center;
font-family: 'Russo One', sans-serif;
padding: 20px 0;
width: 100%;
}

.container-top-hotel h1 {
  font-size: 30px;
  margin: 0;
  padding: 0;
  align-items: flex-start;
}

.book-link {
  color: blue; /* Change the color to match your design */
  text-decoration: underline; /* Add underline to the link */
}

.top-hotels h2 {
    font-size: 50px;
}


.container-top-hotel a {
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
}

.footer-background {
position: absolute;
bottom: 0;
left: 0;
height: 350px; /* Adjust the height as needed */
background-image: linear-gradient(to top, #008793, #004D7A, #051937, white, white);
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

.social-icons {
margin-top: 20px;
}

.social-icon {
font-size: 24px;
color: white;
margin: 0 10px;
text-decoration: none;
}

.cover-hotel-container {
  position: relative;
}
/* do this for bar */
.cover-picture {
  width: 100%;
  max-height: 400px;
}

.top-hotels {
  position: absolute;
  top: 480px;
  left: 380px;
  margin: 0;
  font-size: 24px;
  z-index: 1; /* Place on top of overlay */
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

.hotel-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Align content to the left */
  margin-top: 30px; /* Adjust margin as needed */
}

.hotel-box {
  width: 310px;
  padding: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  border: 1px solid #1a1818;
  background: #E9E5E9;
  text-align: left; /* Align content to the left */
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.9); /* Add box shadow */;
}

.hotel-box img {
  border: 3px solid black;
  max-height: 200px;
  width: 100%;
}

.hotel-box h3 {
  margin-top: 10px;
  font-size: 18px;
  color: black; /* Set font color to black */
}
/* do this for bar */
.hotel-box p {
  margin: 5px 0;
  color: black; /* Set font color to black */
}

.red-heart {
  position: relative;
  font-size: 25px;
  bottom: -5px;
  right: -190px;
  color: red; /* Change the color as needed */
}

.red-heart:hover {
  cursor: pointer;
}


</style>