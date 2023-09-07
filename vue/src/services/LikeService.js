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

  async addLikeToList(like) {
    try {
      const response = await instance.post('/likes/add', like);
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

  async removeLikeFromList(likeId) {
    try {
      const response = await instance.delete(`/likes/remove/${likeId}`);
      return response.data;
    } catch (error) {
      console.error('Error removing like from list:', error);
      throw error;
    }
  },

  async removeUserLike(userId, likeId) {
    try {
      const response = await instance.delete(`/likes/remove/user/like?userId=${userId}&likeId=${likeId}`);
      return response.data;
    } catch (error) {
      console.error('Error removing user like:', error);
      throw error;
    }
  },
};