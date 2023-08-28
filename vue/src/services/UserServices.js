import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
        async registerUser(user) {
          return instance.post('/register', user);
        },

        login(user) {
          return axios.post('/login', user)
        },
        
      };


    