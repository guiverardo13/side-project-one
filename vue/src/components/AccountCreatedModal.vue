<template>
  <div>
    <div class="success-user" v-if="showAccountCreatedModal">
      <div class="success-content">
        <h2>Welcome Aboard!</h2>
        <p>Please sign in to your account & start planning your next trip.</p>
        <div id="close-success" @click="closeSuccessModal">+</div>
        <button class="success-button" @click="openLoginModalAndCloseSuccessModal">Sign in</button>
      </div>
    </div>
    <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
  </div>
</template>

<script>
import LoginModal from './LoginModal.vue';

export default {
    showLoginModal: false,
    props: {
    showAccountCreatedModal: Boolean, 
  },

  components: {
    LoginModal,
  },

  data() {
    return {
      showLoginModal: false,
    }
  },

  methods: {
    closeSuccessModal() {
      // Close the account created modal
      this.$emit('close-success-modal');
    },

    openLoginModalAndCloseSuccessModal() {
      // Close the account created modal
      this.closeSuccessModal();
      // Open the login modal
      this.showLoginModal = true;
    },

    closeLoginModal() {
      this.showLoginModal = false;
    },

    handleLoginSuccess() {
      // ... handle successful login ...
    },
  },
};
</script>

<style>
.success-user {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.success-content {
  width: 80%;
  max-width: 380px; 
  height: 200px;
  background-color: white;
  padding: 20px; 
  border-radius: 4px;
  text-align: center;
  align-items: center;
  position: absolute;
}

.success-content .success-button {
  margin-top: 20px;
}

#close-success {
  position: absolute;
  top: 0;
  height: 20px;
  right: 0;
  font-size: 40px;
  transform: rotate(45deg);
  cursor: pointer;
}

</style>