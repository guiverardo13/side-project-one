import axios from 'axios';


const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
    getRestaurantsByCityName(cityName) {
        return instance.get(`/city/name/${cityName}/restaurants`)
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching hotel data:', error);
            throw error;
          });
      }
        
      };