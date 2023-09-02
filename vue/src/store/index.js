import { createStore } from 'vuex';
import axios from 'axios'; // Import Axios

// Define currentToken and currentUser with default values
const currentToken = localStorage.getItem('token') || null;
const currentUser = JSON.parse(localStorage.getItem('user')) || {};

export default createStore({
  state: {
      token: currentToken,
      user: currentUser,
  },

  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },

    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
  },
});
