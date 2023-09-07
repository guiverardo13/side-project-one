import { createStore } from 'vuex';
import axios from 'axios'; // Import Axios

// Define currentToken and currentUser with default values
const currentToken = localStorage.getItem('token') || null;
const currentUser = JSON.parse(localStorage.getItem('user')) || {};

export default createStore({
  state: {
    token: currentToken,
    user: currentUser,
    isAuthenticated: false,
    userId: undefined, // Initialize userId
    likedHotels: []
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },

    SET_USER(state, user) {
      console.log('SET_USER mutation called with user:', user);
      state.user = user;
      state.userId = user.userId; // Set the userId from user data
      localStorage.setItem('user', JSON.stringify(user));
    },

    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.userId = null;
      axios.defaults.headers.common = {};
    },

    setAuthenticationStatus(state, status) {
      state.isAuthenticated = status;
    },

    addToLikedHotels(state, hotelId) {
      if (!state.likedHotels.includes(hotelId)) {
        state.likedHotels.push(hotelId);
      }
    },

    removeFromLikedHotels(state, hotelId) {
      const index = state.likedHotels.indexOf(hotelId);
      if (index !== -1) {
        state.likedHotels.splice(index, 1);
      }
    },
  },
});