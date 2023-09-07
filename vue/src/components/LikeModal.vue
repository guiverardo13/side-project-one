<template>
  <div class="like-modal" v-if="isAuthenticated">
    <div class="like-container">
      <h2>My Likes</h2>
      <div id="close-modal" @click="closeLikeModal">+</div>
      <div class="like-list">
        <div class="liked-items-container" v-if="fetchedLikedItems.length > 0">
          <!-- Create a container for the liked items -->
          <div class="liked-items-wrapper">
            <div v-for="like in fetchedLikedItems" :key="like.id" class="item-box">
              <img :src="like.likePicture" alt="like image">
              <!-- Use flexbox for positioning -->
              <div class="like-info">
                <div class="top-right">
                  <i v-if="like.isLiked" @click="toggleLike(like)" class="fa-solid fa-heart red-heart"></i>
                  <i v-else @click="toggleLike(like)" class="fa-regular fa-heart red-heart"></i>
                </div>
                <h3>Name: {{ like.likeName }}</h3>
                <p>City: {{ like.likeCityName }}</p>
                <p>Address: {{ like.likeAddress }}</p>
                <p>Phone: {{ like.likePhone }}</p>
                <p>
                  <span class="price-label">{{ like.likePrice }}</span>
                  <a :href="like.likeWebsite" target="_blank" class="book-link">Website</a>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div v-else>
          <p class="no-likes">You have no likes yet.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LikeService from '../services/LikeService.js';

export default {
  name: 'LikeModal',

  props: {
    isAuthenticated: Boolean,
    likedItems: Array,
  },

  data() {
    return {
      fetchedLikedItems: [],
    };
  },

  methods: {
    async fetchLikedItems(userId) {
      try {
        console.log('User ID from store:', this.$store.state.userId);
        // Fetch liked items for the user
        const fetchedItems = await LikeService.getLikesByUserId(userId);
        console.log('Fetched items:', fetchedItems); // Add this line
        this.fetchedLikedItems = fetchedItems; // Update the local data property
      } catch (error) {
        console.error('Error fetching liked items data:', error);
      }
    },

    closeLoginModal() {
      // Implement the logic to close the modal here
    },

    closeLikeModal() {
      this.$emit('close'); // Emit event to close the modal
    }
  },

  created() {
    // Debugging statements
    console.log('isAuthenticated:', this.isAuthenticated);
    console.log('User ID:', this.$store.state.userId);

    if (this.isAuthenticated) {
      const userId = this.$store.state.user.userId;
      this.fetchLikedItems(userId);
    }
  },
};
</script>

<style scoped>
.like-modal {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.like-container {
  width: 80%;
  max-width: 700px; 
  height: 600px;
  background-color: white;
  padding: 20px; 
  border-radius: 4px;
  text-align: center;
  align-items: center;
  position: absolute;
}

.liked-items-container {
  display: flex;
  flex-direction: column;
  gap: 16px; /* Adjust the gap between items as needed */
}


.like-container h2 {
  font-size: 35px;
  border-bottom: 1px black solid;
  padding-bottom: 20px;
}

#close-modal {
  font-size: 50px;
}

.liked-items-wrapper {
  max-height: 470px; /* Set a maximum height for the wrapper */
  overflow-y: auto; /* Add a vertical scrollbar when items overflow */
}


.liked-items-container {
  display: flex;
  flex-direction: column;
  gap: 16px; /* Adjust the gap between items as needed */
  max-height: 600px; /* Set a maximum height for the container */
  overflow-y: auto; /* Add a vertical scrollbar when items overflow */
}

.top-right {
  position: absolute;
  top: 3px;
  right: 200px;
}

.no-likes {
  color: rgba(0, 0, 0, 0.7);
}

.red-heart {
  color: red;
  font-size: 25px;
  cursor: pointer;
}

/* Modify the item-box class to include relative positioning */
.item-box {
  position: relative;
  display: flex;
  flex: 1;
  background-color: #e7dfce;
  padding: 16px;
  border: 2px solid #000000;
  border-radius: 5px;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.9);
  margin-bottom: 7px;
}

.item-box img {
  width: 200px;
  height: 150px;
  border: 3px solid black;
}

.like-info {
  display: flex;
  flex-direction: column;
  margin-left: 10px; /* Adjust the left margin for spacing */
  text-align: left;
}

.like-info h3,
.like-info p {
  margin: 5px 0; /* Adjust the top and bottom margin for spacing */
}

.book-link {
  margin-left: 20px;
}
</style>