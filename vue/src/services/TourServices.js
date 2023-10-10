import axios from 'axios';


const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
    getToursByCityName(cityName) {
        return instance.get(`/city/name/${cityName}/pois`)
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching poi data:', error);
            throw error;
          });
      }
        
      };