import axios from 'axios';


const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});


export default {
    getLikesByUserId(userId) {
        return instance.get(`/likes/${userId}`)
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching hotel data:', error);
            throw error;
          });
      }
        
      };