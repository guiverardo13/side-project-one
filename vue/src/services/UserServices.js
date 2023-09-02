import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
        async registerUser(user) {
          return instance.post('/register', user);
        },

        login(user) {
          return instance.post('/login', user); // Use the instance with specified baseURL
        },

        logout() {
          // Since there is no dedicated logout endpoint on the server,
          // you can simply clear the token and user data on the client side
          localStorage.removeItem('token'); // Remove the token from local storage
          localStorage.removeItem('user');  // Remove the user data from local storage
        },
        
      };


    