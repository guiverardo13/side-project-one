// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'


// Vue.use(Vuex)

// // Get the token and user data from local storage
// const currentToken = localStorage.getItem('token');
// const currentUser = JSON.parse(localStorage.getItem('user'));

// export default new Vuex.Store({
//   state: {
//     token: currentToken || '',
//     user: currentUser || {},
//     modalFlag: false,
//   },
//   mutations: {
//     SET_AUTH_TOKEN(state, token) {
//       state.token = token;
//       localStorage.setItem('token', token);
//       axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
//     },
//     SET_USER(state, user) {
//       state.user = user;
//       localStorage.setItem('user', JSON.stringify(user));
//     },
//     LOGOUT(state) {
//       localStorage.removeItem('token');
//       localStorage.removeItem('user');
//       state.token = '';
//       state.user = {};
//       axios.defaults.headers.common = {};
//     },
//   }
// })