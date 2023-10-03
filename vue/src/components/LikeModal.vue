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
                  <i
            @click="toggleLike(like)"
            :class="{'fa-regular fa-heart red-heart': like.isLiked, 'fa-solid fa-heart red-heart': !like.isLiked }"
          ></i>
                </div>
                <h3>{{ like.likeName }}</h3>
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
    // Fetch liked items for the user
    const fetchedItems = await LikeService.getLikesByUserId(userId);
    console.log('Fetched items:', fetchedItems); // Log the fetched data
    this.fetchedLikedItems = fetchedItems; // Update the local data property

    // Emit the fetchedLikedItems data to the parent component (HotelPage)
    this.$emit('liked-items-updated', this.fetchedLikedItems);
  } catch (error) {
    console.error('Error fetching liked items data:', error);
  }
},


async toggleLike(like) {
  // Toggle the like status
  like.isLiked = !like.isLiked;

  try {
    if (like.isLiked) {
      // If liked, call the deleteLike method
      await this.deleteLike(like.likeId);
    }

    // Emit the fetchedLikedItems data to the parent component (HotelPage)
    this.$emit('liked-items-updated', this.fetchedLikedItems);
  } catch (error) {
    console.error('Error toggling like:', error);
  }
},

async deleteLike(likeId) {
  try {
    const userId = this.$store.state.user.userId;
    
    // Introduce a 0.5-second (500 milliseconds) delay
    await new Promise(resolve => setTimeout(resolve, 80));
    
    const isDeleted = await LikeService.deleteLike(likeId, userId); // Pass the likeId
    console.log('Like object before deletion:', likeId);
    if (isDeleted) {
      // Remove the deleted like from the local list
      this.fetchedLikedItems = this.fetchedLikedItems.filter(item => item.likeId !== likeId);
      this.$emit('like-removed', likeId);
    } else {
      console.error('Failed to delete like');
    }
  } catch (error) {
    console.error('Error deleting like:', error);
  }
},

    closeLoginModal() {
      // Implement the logic to close the modal here
    },

    closeLikeModal() {
      this.$emit('close'); // Emit event to close the modal-
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