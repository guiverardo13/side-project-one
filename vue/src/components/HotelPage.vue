<template>
  <div class="container-hotel">
    <header>
    <nav class="container-top-hotel" v-if="isHotelPage">
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
      <a href="https://drive.google.com/uc?id=1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-&export=download" target="_blank" class="resume"><i class="fa-regular fa-file-pdf" style="color: #ffffff;"></i></a>
    </div>
</div>
    </footer>
    <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
    <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal" @sign-in="handleSignIn" />
    <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
    <LikeModal v-if="showLikesModal" :isAuthenticated="isAuthenticated" :likedItems="likedItems" @close="closeLikeModal" @liked-items-updated="handleLikedItemsUpdated" @like-removed="handleLikeRemoved"/>

  </div>
</template>

<script>
import HotelServices from '../services/HotelServices.js';
import UserServices from '../services/UserServices';
import RegisterUserModal from './RegisterUserModal.vue'
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';
import LikeModal from './LikeModal.vue'; // Import the LikeModal component
import LikeService from '../services/LikeService.js';

export default {
    name: 'HotelPage',

    data() {
      return {
        showRegistrationModal: false,
        showAccountCreatedModal: false,
        showLoginModal: false,
        isAuthenticated: false,
        showLikesModal: false,
        hotels: [],
        likedItems: [],
      };
  },

    components: {
      RegisterUserModal,
      AccountCreatedModal,
      LoginModal,
      LikeModal
    },  

    computed: {
    isHotelPage() {
      return this.$route.name === 'HotelPage';
    },
  },

  created() {
    // Fetch hotel data from the service
    this.fetchHotels();
    this.checkUserAuthentication();
    
    // Fetch liked items and populate likedItems array
    if (this.isAuthenticated) {
      const userId = this.$store.state.user.userId;
      this.fetchLikedItems(userId);
    }
  },

  methods: {

    handleLikedItemsUpdated(updatedLikedItems) {
    // Update the likedItems array with the updated data
    this.likedItems = updatedLikedItems;

    // Iterate through the hotel items and update the isLiked property
    this.hotels.forEach((hotel) => {
      // Check if the likeHotelId matches the hotelId
      hotel.isLiked = !!this.likedItems.find((item) => item.likeHotelId === hotel.hotelId);
    });
  },

  async fetchLikedItems(userId) {
      try {
        // Fetch liked items for the user
        const fetchedItems = await LikeService.getLikesByUserId(userId);
        console.log('Fetched items:', fetchedItems); // Log the fetched data
        this.likedItems = fetchedItems; // Update the likedItems array
        
        // Iterate through the hotel items and update the isLiked property
        this.hotels.forEach((hotel) => {
          // Check if the likeHotelId matches the hotelId
          hotel.isLiked = !!this.likedItems.find((item) => item.likeHotelId === hotel.hotelId);
        });
      } catch (error) {
        console.error('Error fetching liked items data:', error);
      }
    },

    async toggleLike(hotel) {
  if (!this.isAuthenticated) {
    window.alert('Please sign in to like hotels');
  } else {
    hotel.isLiked = !hotel.isLiked;
    try {
      if (hotel.isLiked) {
        // If liked, make an API request to add the hotel to the user's likes
        const userId = this.$store.state.user.userId;
        const like = {
          // Include other like properties as needed
          likeHotelId: hotel.hotelId // Set the likeHotelId here
        };
        const response = await LikeService.addLikeToList(userId, like, hotel);

        // Check if the response status is successful (e.g., 200 OK)
        if (response.status === 200 || response.status === 201) {
          // Check if the response data has the expected structure
          if (response.data && response.data.likeId) {
            const likeId = response.data.likeId; // Get the returned like_id

            // Make an API request to associate the hotel with the user using the like_id
            await LikeService.addUserLike(userId, likeId); // Pass the userId here

            // Add the liked item to the likedItems array
            this.likedItems.push({ id: likeId, likeHotelId: hotel.hotelId });
          } else {
            console.error('Invalid response structure:', response.data);
          }
        } else {
          console.error('Failed to add like:', response.status, response.statusText);
          console.log('API Response:', response);
        }
      } else {
        // If unliked, retrieve the likeId from the likedItems array
        const likeItem = this.likedItems.find((likedItem) => likedItem.likeHotelId === hotel.hotelId);
        if (likeItem) {
          const likeId = likeItem.likeId;
          const userId = this.$store.state.user.userId;
          try {
            // Make an API request to delete the like using likeId and userId
            await LikeService.deleteLike(likeId, userId); // Pass the userId here

            // Remove the unliked item from the likedItems array
            const index = this.likedItems.indexOf(likeItem);
            if (index !== -1) {
              this.likedItems.splice(index, 1);
            }
          } catch (deleteError) {
            console.error('Failed to delete like:', deleteError);
            // Handle delete error appropriately
          }
        }
      }
    } catch (error) {
      console.error('Error toggling like:', error);
    }
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

       
    handleSignIn() {
        console.log('Sign In button clicked'); // Add this line for debugging
          this.closeSuccessModal();
          this.openLoginModal();
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

    async handleSignOutClick() {
      if (this.isAuthenticated) {
        this.logoutUser(); // Call the logoutUser method when Sign Out is clicked
      } else {
        this.openLoginModal(); // Open the login modal when not authenticated
      }
    },

        handleLikeRemoved(item) {
      // Find the hotel in the `hotels` array based on the likeHotelId and update its `isLiked` property to `false`
      const hotelToUpdate = this.hotels.find((hotel) => hotel.hotelId === item.likeHotelId);
      if (hotelToUpdate) {
        hotelToUpdate.isLiked = false;
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
  
  // Check if token is not undefined or null to set isAuthenticated
  if (token !== undefined && token !== null) {
    this.isAuthenticated = true; // Set isAuthenticated to true
    this.user = this.$store.state.user;
  }
},

async fetchHotels() {
  try {
    // Fetch hotel data for the current city
    const cityName = this.$route.params.cityName;
    this.hotels = await HotelServices.getHotelsByCityName(cityName);

    // Fetch the user's liked items
    const userId = this.$store.state.user.userId; // Assuming you have access to the user's ID
    const userLikedItems = await LikeService.getLikesByUserId(userId);

    // Loop through the fetched hotels and set the isLiked property
    this.hotels.forEach((hotel) => {
      // Check if the hotel is in the user's liked items
      hotel.isLiked = userLikedItems.some((item) => item.likeHotelId === hotel.hotelId);
    });
  } catch (error) {
    console.error('Error fetching hotel data:', error);
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

    closeLikeModal() {
      this.showLikesModal = false;
      this.fetchHotels();
    }
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