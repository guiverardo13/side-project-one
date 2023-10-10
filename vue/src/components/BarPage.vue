<template>
  <div class="container-bar-restaurant">
    <header>
    <nav class="container-top-hotel" v-if="isBarPage">
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
            <i v-if="bar.isLiked" @click="likeBar(bar)" class="fa-solid fa-heart red-heart-bar"></i>
            <i v-else @click="likeBar(bar)" class="fa-regular fa-heart red-heart-bar"></i>
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
            <i v-if="restaurant.isLiked" @click="likeRestaurant(restaurant)" class="fa-solid fa-heart red-heart-bar"></i>
            <i v-else @click="likeRestaurant(restaurant)" class="fa-regular fa-heart red-heart-bar"></i>
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
      <a href="https://drive.google.com/file/d/1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-/view" target="_blank" class="resume"><i class="fa-regular fa-file-pdf" style="color: #ffffff;"></i></a>
    </div>
</div>
    </footer>
    <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
          <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal"  @sign-in="handleSignIn" />
          <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
          <LikeModal v-if="showLikesModal" :isAuthenticated="isAuthenticated" :likedItems="likedItems" @close="closeLikeModal" @liked-items-updated="handleLikedItemsUpdated" @like-removed="handleLikeRemoved"/>
  </div>
</template>

<script>
import BarServices from '../services/BarServices.js';
import RestaurantServices from '../services/RestaurantServices.js'
import UserServices from '../services/UserServices';
import RegisterUserModal from './RegisterUserModal.vue';
import AccountCreatedModal from './AccountCreatedModal.vue';
import LoginModal from './LoginModal.vue';
import LikeModal from './LikeModal.vue'; 
import LikeService from '../services/LikeService.js';

export default {
    name: 'BarPage',
    data() {
        return {
            showRegistrationModal: false,
            showAccountCreatedModal: false,
            showLoginModal: false,
            isAuthenticated: false,
            toggleState: true, // Initial toggle state
            selectedSection: 'Bars', // Initial selected section
            showLikesModal: false,
            bars: [],
            restaurants: [],
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
        isBarPage() {
            return this.$route.name === 'BarPage'; // Adjust the route name if needed
        },
    },

    created() {
    this.fetchBars();
    this.fetchRestaurants();
    this.checkUserAuthentication();

    if (this.isAuthenticated) {
      const userId = this.$store.state.user.userId;
      // Fetch liked items for bars
      this.fetchLikedItems(userId, this.bars, 'bar_id');
      // Fetch liked items for restaurants
      this.fetchLikedItems(userId, this.restaurants, 'restaurant_id');
    }
  },

    methods: {

      handleLikedItemsUpdated(updatedLikedItems, items, type) {
  // Update the likedItems array with the updated data
  this.likedItems = updatedLikedItems;

  // Check if items is defined before iterating
  if (items && items.length > 0) {
    items.forEach((item) => {
      this.toggleLike(item, type);
    });
  }
},

      handleLikeRemoved(item, itemType) {
  // Determine the array of items based on the itemType
  const itemsArray = itemType === 'bar' ? this.bars : this.restaurants;

  // Find the item in the itemsArray based on its ID and update its `isLiked` property to `false`
  const itemToUpdate = itemsArray.find((itemData) => itemData.id === item.itemId);

  if (itemToUpdate) {
    itemToUpdate.isLiked = false;
  }
},

async fetchLikedItems(userId, items, type) {
  try {
    // Fetch liked items for the user
    const fetchedItems = await LikeService.getLikesByUserId(userId);
    console.log('Fetched items:', fetchedItems); // Log the fetched data
    this.likedItems = fetchedItems; // Update the likedItems array

    // Determine the itemIdKey based on the type
    const itemIdKey = type === 'bar' ? 'barId' : 'restaurantId';

    // Iterate through the items (bars or restaurants) and update their isLiked property
    items.forEach((item) => {
      // Check if the itemIdKey matches the item's ID
      item.isLiked = !!this.likedItems.find((likedItem) => likedItem[itemIdKey] === item[itemIdKey]);
    });
  } catch (error) {
    console.error('Error fetching liked items data:', error);
  }
},


      async checkUserAuthentication() {
        const token = this.$store.state.token;
        
        // Check if token is not undefined or null to set isAuthenticated
        if (token !== undefined && token !== null) {
          this.isAuthenticated = true; // Set isAuthenticated to true
          this.user = this.$store.state.user;
        }
      },

      async fetchBars() {
        try {
          // Fetch hotel data for the current city
          const cityName = this.$route.params.cityName;
          this.bars = await BarServices.getBarsByCityName(cityName);

          // Fetch the user's liked items
          const userId = this.$store.state.user.userId; // Assuming you have access to the user's ID
          const userLikedItems = await LikeService.getLikesByUserId(userId);

          // Loop through the fetched hotels and set the isLiked property
          this.bars.forEach((bar) => {
            // Check if the hotel is in the user's liked items
            bar.isLiked = userLikedItems.some((item) => item.likeBarId === bar.barId);
          });
        } catch (error) {
          console.error('Error fetching hotel data:', error);
        }
      },

      async fetchRestaurants() {
          try {
            // Fetch hotel data for the current city
            const cityName = this.$route.params.cityName;
            this.restaurants = await RestaurantServices.getRestaurantsByCityName(cityName);

            // Fetch the user's liked items
            const userId = this.$store.state.user.userId; // Assuming you have access to the user's ID
            const userLikedItems = await LikeService.getLikesByUserId(userId);

            // Loop through the fetched hotels and set the isLiked property
            this.restaurants.forEach((restaurant) => {
              // Check if the hotel is in the user's liked items
              restaurant.isLiked = userLikedItems.some((item) => item.likeRestaurantId === restaurant.restaurantId);
            });
          } catch (error) {
            console.error('Error fetching hotel data:', error);
          }
        },

      async handleSignOutClick() {
          if (this.isAuthenticated) {
          this.logoutUser(); // Call the logoutUser method when Sign Out is clicked
        } else {
          this.openLoginModal(); // Open the login modal when not authenticated
        }
      },

      async likeBar(bar) {
  if (!this.isAuthenticated) {
    window.alert('Please sign in to like bars');
  } else {
    bar.isLiked = !bar.isLiked;
    try {
      if (bar.isLiked) {
        // If liked, make an API request to add the hotel to the user's likes
        const userId = this.$store.state.user.userId;
        const like = {
          // Include other like properties as needed
          likeBarId: bar.barId // Set the likeHotelId here
        };
        const response = await LikeService.addLikeBarToList(userId, like, bar);

        // Check if the response status is successful (e.g., 200 OK)
        if (response.status === 200 || response.status === 201) {
          // Check if the response data has the expected structure
          if (response.data && response.data.likeId) {
            const likeId = response.data.likeId; // Get the returned like_id

            // Make an API request to associate the hotel with the user using the like_id
            await LikeService.addUserLike(userId, likeId); // Pass the userId here

            // Add the liked item to the likedItems array
            this.likedItems.push({ id: likeId, likeBarId: bar.barId });
          } else {
            console.error('Invalid response structure:', response.data);
          }
        } else {
          console.error('Failed to add like:', response.status, response.statusText);
          console.log('API Response:', response);
        }
      } else {
        // If unliked, retrieve the likeId from the likedItems array
        const likeItem = this.likedItems.find((likedItem) => likedItem.likeBarId === bar.barId);
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

async likeRestaurant(restaurant) {
  if (!this.isAuthenticated) {
    window.alert('Please sign in to like restaurants');
  } else {
    restaurant.isLiked = !restaurant.isLiked;
    try {
      if (restaurant.isLiked) {
        // If liked, make an API request to add the hotel to the user's likes
        const userId = this.$store.state.user.userId;
        const like = {
          // Include other like properties as needed
          likeRestaurantId: restaurant.restaurantId // Set the likeHotelId here
        };
        const response = await LikeService.addLikeRestaurantToList(userId, like, restaurant);

        // Check if the response status is successful (e.g., 200 OK)
        if (response.status === 200 || response.status === 201) {
          // Check if the response data has the expected structure
          if (response.data && response.data.likeId) {
            const likeId = response.data.likeId; // Get the returned like_id

            // Make an API request to associate the hotel with the user using the like_id
            await LikeService.addUserLike(userId, likeId); // Pass the userId here

            // Add the liked item to the likedItems array
            this.likedItems.push({ id: likeId, likeRestaurantId: restaurant.restaurantId });
          } else {
            console.error('Invalid response structure:', response.data);
          }
        } else {
          console.error('Failed to add like:', response.status, response.statusText);
          console.log('API Response:', response);
        }
      } else {
        // If unliked, retrieve the likeId from the likedItems array
        const likeItem = this.likedItems.find((likedItem) => likedItem.likeRestaurantId === restaurant.restaurantId);
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

        toggleSection(section) {
          this.toggleState = !this.toggleState;
          this.selectedSection = section;
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

        handleSignIn() {
      console.log('Sign In button clicked');
      this.closeSuccessModal();
      this.openLoginModal();
        },

        openLoginModal() {
            this.showLoginModal = true;
        },

        closeLoginModal() {
            this.showLoginModal = false;
        },
        
        closeLikeModal() {
            this.showLikesModal = false;
            this.fetchBars();
            this.fetchRestaurants();
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
      }
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

.red-heart-bar:hover {
  cursor: pointer;
}

.red-heart-restaurant:hover {
  cursor: pointer;
}

.toggle-button {
  cursor: pointer;
}
</style>