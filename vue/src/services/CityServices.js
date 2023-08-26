import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
  getCityByName(cityName) {
    return instance.get(`/city/name/${cityName}`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching city:', error);
        throw error;
      });
  },

  getCities() {
    return instance.get('/city') // Use your instance to make the request
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching cities:', error);
        throw error;
      });
  },

  getCoverPictureByCityName(cityName) {
    return instance.get(`/city/name/${cityName}/cover-picture`)
      .then(response => response.data) // Assuming the URL is returned as plain text
      .catch(error => {
        console.error('Error fetching cover picture:', error);
        throw error;
      });
  },

  getCityDescriptionByCityName(cityName) {
    return instance.get(`/city/name/${cityName}/description`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching city description:', error);
        throw error;
      });
    },

    getVideoByCityName(cityName) {
      return instance.get(`/city/name/${cityName}/video`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching city description:', error);
        throw error;
      });
    }
};