<template>
    <div class="container-new-page">
<header>
    <nav class="container-top-new-page">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item register" @click="openRegistrationModal">Register</div>
        <div class="item sign-in" @click="openLoginModal">Sign in</div>
    </nav>
    </header>
<main>
    <div class="container-main">
        <div class="cover-picture-container">
            <img v-if="coverPictureUrl" :src="coverPictureUrl" alt="City Cover" class="cover-picture">
            <h1 class="city-name">
            <a :href="videoLink" target="_blank" class="city-link">{{ cityName }}</a>
          </h1>
        </div>
    </div>
</main>
    <div class="city-description">
        <p>{{ cityDescription }}</p>
    </div>
    <section class="things-to-do-container" >
        <section class="things-to-do">
            <h2>Things To Do</h2>
            <div class="categories">
          <div class="category hotels">
            <router-link  class="category-text" :to="{ name: 'HotelPage', params: { cityName: selectedCity } }">Hotels</router-link>
          </div>
          <div class="category bars-restaurants">
            <router-link  class="category-text" :to="{ name: 'BarPage', params: { cityName: selectedCity } }">Bars <br> & <br> Restaurants</router-link>
          </div>
        </div>
        <div class="categories">
          <div class="category events">
            <div class="category-text">Events</div>
          </div>
          <div class="category pois">
            <div class="category-text">Points <br> Of <br> Interest</div>
          </div>
        </div>
        </section>
    </section>

    <footer class="container-footer" id="contact">
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
          <HotelPage />
          <BarPage />
      </div>
</template>

<script>
import cityServices from '../services/CityServices.js';
import RegisterUserModal from './RegisterUserModal.vue';
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';
import HotelPage from './HotelPage.vue';
import BarPage from './BarPage.vue';

export default {
  name: 'CityPage',
  components: {
    RegisterUserModal,
    AccountCreatedModal,
    LoginModal,
    HotelPage,
    BarPage
  },

  props: {
    cityName: String 
  },

  data() {
    return {
      coverPictureUrl: '',
      cityDescription: '',
      videoLink: '',
      showRegistrationModal: false,
      showAccountCreatedModal: false,
      showLoginModal: false,
    };
  },

  mounted() {
    this.getCoverPicture();
    this.getCityDescription();
    this.getCityVideoLink();
  },

  methods: {
    async getCoverPicture() {
      try {
        const coverPictureUrl = await cityServices.getCoverPictureByCityName(this.cityName);
        this.coverPictureUrl = coverPictureUrl;
      } catch (error) {
        console.error('An error occurred:', error);
      }
    },

    async getCityDescription() {
      try {
        const cityDescription = await cityServices.getCityDescriptionByCityName(this.cityName);
        this.cityDescription = cityDescription;
      } catch (error) {
        console.error('An error occurred:', error);
      }
    },

    async getCityVideoLink() {
      try {
        const videoLink = await cityServices.getVideoByCityName(this.cityName);
        this.videoLink = videoLink;
      } catch (error) {
        console.error('An error occurred while fetching video link:', error);
      }
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

.categories {
  display: flex;
  justify-content: space-between; /* Evenly space the boxes horizontally */
  gap: 20px; /* Adjust the gap between boxes */
  margin-top: 20px;
  max-width: 800px; /* Limit the maximum width */
  margin: 0 auto; /* Center the container horizontally */
}

.category {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center the content horizontally */
  align-items: center; /* Center the content vertically */
  background: grey;
  width: calc(50% - 10px); /* Adjust the width of each box */
  height: 180px; /* Adjust the height as needed */
  padding: 10px;
  text-align: center;
  color: white;
  border-radius: 25px;
}

.categories .events {
    background-image: url("../assets/events.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.categories .hotels {
    background-image: url("../assets/hotel.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.category-text:hover {
  color:  #36bfe9;
}

.category-text {

  text-decoration: none;
}

.categories .bars-restaurants {
    background-image: url("../assets/restaurant.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.categories .pois {
    background-image: url("../assets/bean.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.city-link {
  color: white; /* Set the color to white */
  text-decoration: none; /* Remove underlining */
}

.city-link:hover {
    color:#36bfe9;
}

.category-text {
  font-size: 30px; /* Adjust the font size as needed */
  color: white;
  padding: 20px;
}
.things-to-do {
  font-size: 23px;
  text-align: center; /* Center-align the text */
  padding-bottom: 60px;
}

.things-to-do h2 {
  border-bottom: black solid 1px; /* Add border below h2 */
  padding-bottom: 10px; /* Add some space between h2 and border */
}

.things-to-do-container {
    padding-bottom: 20px;
}

.hotels,
.bars-restaurants,
.events,
.pois {
  display: flex;
  flex-direction: column;
  flex-basis: calc(50% - 10px); /* 50% width minus margin */
  background: grey;
  margin: 5px;
}

.city-description {
  text-align: center; /* Center-align the text */
  margin: 20px auto; /* Add some margin for spacing */
  font-size: 15px;
  padding-bottom: 10px;
}

.cover-picture-container {
  position: relative;
  width: 100%;
  overflow: hidden;
  margin: 20px 0; /* Add spacing around the picture */
}

.cover-picture {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 5px solid rgb(0, 0, 0);
  box-sizing: border-box; /* Include border in total width/height */
}

.city-name {
  position: absolute;
  bottom: 0;
  left: 0;
  margin: 10px;
  margin-bottom: 20px;
  padding: 10px;
  color: white;
  font-size: 70px;
}
/* Adjust the navigation width based on your layout */
.container-top-new-page {
width: 30%;
margin: 0;
padding: 20px;
float: left;
color: white;
}

.container-new-page {
margin: 0 auto;
max-width: 1200px;
padding: 0 20px;
flex: 1;
}

.container-top-new-page {
display: flex;
justify-content: space-between;
font-size: 18px;
align-items: center;
font-family: 'Russo One', sans-serif;
padding: 20px 0;
width: 100%;
}

.container-top-new-page h1 {
  font-size: 30px;
  margin: 0;
  padding: 0;
  align-items: flex-start;
}


.container-top-new-page a {
  color: white;
  text-decoration: none;
}

.about:hover,
.sign-in:hover,
.contact:hover,
.register:hover {
  color: #00AFEF;
  text-decoration: underline;
}

.container-footer {
position: relative;
padding: 0;
color: rgb(0, 0, 0);
text-align: center;
border-top: 1px solid black;
}

.footer-content {
max-width: 1000px;
margin: 0 auto;
padding: 40px 0;
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

.cover-picture {
/* Add styles for cover picture width, margin, and positioning */
width: 100%; /* Make the picture width cover the container */
max-height: 400px; /* Limit the picture's height */
margin: 20px 0; /* Add spacing around the picture */
margin-top: 80px;
}

</style>