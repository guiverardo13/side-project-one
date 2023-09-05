<template>
   <div class="like-modal" v-if="isAuthenticated">
    <div class="like-container">
        <h2>My Likes</h2>
    <div id="close-modal" @click="closeLoginModal">+</div>
        <div class="like-list">
            <div v-for="item in items" :key="item.item.id" class="item-box">
                <img :src="item.ItemPicture" alt="Item Image">
                <h3>{{ item.itemName}}</h3>
                <p> {{ item.itemCity }}</p>
                <p> {{ item.itemPhone }}</p>
                <p> {{ item.itemAddress }}</p>
                <p> {{ item.itemPrice }}</p>
                <a :href="item.itemWebsite" target="_blank" class="book-link">Website</a>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import LikeService from '../services/LikeService.js';

export default {
    name: 'LikePage',

    props: {
    isAuthenticated: Boolean, // Pass the user's authentication state as a prop
    // Pass the list of liked items as a prop
  },

  data() {
    return {
        likes: []
    }
  },
  
  created() {
    this.fetchLikedItems();
  },

  methods: {
    async fetchLikedItems() {
      try {
        // Fetch liked items for the user
        this.likedItems = await LikeService.getLikesByUserId(this.userId);
      } catch (error) {
        console.error('Error fetching liked items data:', error);
      }
    },
  },

  }
</script>

<style>
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
  max-width: 650px; 
  height: 600px;
  background-color: white;
  padding: 20px; 
  border-radius: 4px;
  text-align: center;
  align-items: center;
  position: absolute;
}

.like-container h2 {
    font-size: 35px;
}

#close-modal {
    font-size: 50px;
}

</style>