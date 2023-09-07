import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:9000', // Replace with your actual backend URL
});

export default {
  async getLikesByUserId(userId) {
    try {
      const response = await instance.get(`/likes/${userId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching liked items data:', error);
      throw error;
    }
  },

  async addLikeToList(userId, like, hotel) {
    try {
      like.likeHotelId = hotel.hotelId;
      const response = await instance.post(`/likes/add/user/${userId}`, like);
      console.log('Response from backend:', response.data); // Log the response data
      return response.data;
    } catch (error) {
      console.error('Error adding like:', error);
      throw error;
    }
  },

  async addUserLike(userId, likeId) {
    try {
      const response = await instance.post(`/likes/add/like/${likeId}/user/${userId}`);
      return response.data;
    } catch (error) {
      console.error('Error adding user like:', error);
      throw error;
    }
  },

  async deleteLike(likeId, userId) {
    try {
      const response = await instance.delete(`/like/delete/${likeId}/user/${userId}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting like:', error);
      throw error;
    }
  },
};