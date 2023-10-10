<template>
    <div class="container-new-page">
    <header>
    <nav class="container-top-new-page">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item" @click="openLikesModal">
          {{ isAuthenticated ? 'Likes' : 'Register' }}
        </div>
        <div class="item" @click="handleSignOutClick">
        {{ isAuthenticated ? 'Sign Out' : 'Sign in' }}
        </div>
    </nav>
    </header>
<main>
    <div class="container-main">
        <div class="cover-picture-container">
            <img v-if="coverPictureUrl" :src="coverPictureUrl" alt="City Cover" class="cover-picture">
            <h1 class="city-name">
            {{ cityName }}<a :href="videoLink" target="_blank" class="city-link"><i class="fa-brands fa-youtube" :title="'Watch a 4k Drone Video of ' + cityName"></i></a>
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
          <router-link :to="'/'" class="back-button" v-if="isCityPage">Back</router-link>
            <div class="categories">
          <router-link  class="category hotels" :to="{ name: 'HotelPage', params: { cityName: selectedCity } }">
          <div>
            <router-link  class="category-text" :to="{ name: 'HotelPage', params: { cityName: selectedCity } }">Hotels</router-link>
          </div>
        </router-link>
        <router-link class="category bars-restaurants" :to="{ name: 'BarPage', params: { cityName: selectedCity } }">
          <div>
            <router-link  class="category-text" :to="{ name: 'BarPage', params: { cityName: selectedCity } }">Bars & Restaurants</router-link>
          </div>
        </router-link>
        </div>
        <div class="categories">
          <router-link class="category events" :to="{ name: 'EventPage', params: { cityName: selectedCity } }">
            <router-link :to="{ name: 'EventPage', params: { cityName: selectedCity } }">
              <div class="category-text">Events</div>
            </router-link>
          </router-link>
          <router-link class="category pois" :to="{ name: 'TourPage', params: { cityName: selectedCity } }">
            <router-link :to="{ name: 'TourPage', params: { cityName: selectedCity } }">
              <div class="category-text">Points Of Interest</div>
            </router-link>
          </router-link>
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
      <a href="https://drive.google.com/uc?id=1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-&export=download" target="_blank" class="resume"><i class="fa-regular fa-file-pdf" style="color: #ffffff;"></i></a>
    </div>
</div>
    </footer>
    <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
    <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal" @sign-in="handleSignIn" />
    <LikeModal v-if="showLikesModal" :isAuthenticated="isAuthenticated" :likedItems="likedItems" @close="closeLikeModal"/>
    <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
    <HotelPage />
    <BarPage />
      </div>
</template>

<script>
import cityServices from '../services/CityServices.js';
import UserServices from '@/services/UserServices';
import RegisterUserModal from './RegisterUserModal.vue';
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';
import HotelPage from './HotelPage.vue';
import BarPage from './BarPage.vue';
import LikeModal from './LikeModal.vue';

export default {
  name: 'CityPage',

  data() {
    return {
      isAuthenticated: false, // Initialize isAuthenticated to false
      coverPictureUrl: '',
      cityDescription: '',
      videoLink: '',
      showRegistrationModal: false,
      showAccountCreatedModal: false,
      showLoginModal: false,
      showLikesModal: false
    };
  },

  created() {
    // Check if the user is authenticated by looking at the Vuex store and local storage
    this.checkUserAuthentication();
  },

  components: {
    RegisterUserModal,
    AccountCreatedModal,
    LoginModal,
    HotelPage,
    BarPage,
    LikeModal
  },

  props: {
    cityName: String,
    selectedCity: String
  },

  computed: {
    isCityPage() {
            return this.$route.name === 'city-page'; // Adjust the route name if needed
        },
  },

  mounted() {
    this.getCoverPicture();
    this.getCityDescription();
    this.getCityVideoLink();
  },

  methods: {
    handleSignIn() {
      console.log('Sign In button clicked');
      this.closeSuccessModal();
      this.openLoginModal();
    },

    async handleSignOutClick() {
      if (this.isAuthenticated) {
        this.logoutUser();
      } else {
        this.openLoginModal();
      }
    },

    logoutUser() {
      // Clear the token and user data from local storage
      UserServices.logout();

      // Clear user data and token in your Vuex store if needed
      this.$store.commit('LOGOUT');

      // Reset the user in Vuex store to an empty object if needed
      this.$store.commit('SET_USER', {});

      // Update the isAuthenticated status directly
      this.isAuthenticated = false;
      window.alert("Sign out Successful!");
      // Ensure that the route is redirected to another page after logout
      this.$router.push('/'); // Redirect to the home page or another appropriate page
      },

    async checkUserAuthentication() {
      const token = this.$store.state.token;
      if (token) {
        this.isAuthenticated = true;
        // You can also update the user data here if needed
      }
    },

    openLikesModal() {
      // Check if the user is authenticated before showing the Like modal
      if (this.isAuthenticated) {
        this.showLikesModal = true;
      } else {
        // If not authenticated, you can handle it in some way, such as showing a login/register modal
        this.openRegistrationModal(); // You may want to modify this part to fit your application's logic
      }
    },

      async handleLoginSuccess(response) {
          this.closeLoginModal();
          this.isAuthenticated = true; // Set isAuthenticated to true
          this.user = response.data.user; // Update the user data

          try {
            // Update the Vuex store with the new user data
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            // Update local storage
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('user', JSON.stringify(response.data.user));
          } catch (error) {
            console.error('Error updating Vuex store and local storage:', error);
          }
        },

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
      console.log('Registration successful. Opening success modal.');
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

    closeLikeModal() {
      this.showLikesModal = false;
    }

    
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
  margin: 0 auto; 
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

.categories .hotels:hover {
  transform: scale(1.1); /* Increase the scale value for a stronger zoom effect */
  transition: transform 0.3s ease; /* Add a smooth transition effect */
  cursor: pointer;
}

.categories .events:hover {
  transform: scale(1.1); /* Increase the scale value for a stronger zoom effect */
  transition: transform 0.3s ease; /* Add a smooth transition effect */
  cursor: pointer;

}

.categories .bars-restaurants:hover {
  transform: scale(1.1); /* Increase the scale value for a stronger zoom effect */
  transition: transform 0.3s ease; /* Add a smooth transition effect */
  cursor: pointer;
}

.categories .pois:hover {
  transform: scale(1.1); /* Increase the scale value for a stronger zoom effect */
  transition: transform 0.3s ease; /* Add a smooth transition effect */
  cursor: pointer;

}

a {
  text-decoration: none;
}


.category-text:hover {
  color:  #cfc8c8;
}

.container-top-new-page a:hover {
    color: #00AFEF;
    text-decoration: none;
  }


.categories .bars-restaurants {
  background-image: url("../assets/restaurant.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  justify-content: center;
  color: white;
  font-size: 30px;
}


.back-button {
  display: flex;
  text-decoration: underline;
}

.city-link {
  color: red; /* Set the color to white */
  text-decoration: none; /* Remove underlining */
  font-size: 50px;
  margin-left: 20px;
 
  
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