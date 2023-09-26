<template>
  <div>
    <div class="bg-modal" @click="closeModalOutside">
        <div class="modal-content" :class="{ 'slide-in': showModal }">
          <h2>Create a Traveler Account</h2>
          <form @submit.prevent="registerUser"  action="/register">
            <div id="close-modal"  @click="closeRegistrationModal">+</div>
            <div class="form-group">
              <label for="first-name">First Name:</label>
              <input type="text" id="first-name" name="firstName" placeholder="First Name" v-model="newUser.firstName" required/>
            </div>
            <div class="form-group">
              <label for="last-name">Last Name:</label>
              <input type="text" id="last-name" name="lastName" placeholder="Last Name" v-model="newUser.lastName" required />
            </div>
            <div class="form-group">
              <label for="username">Username:</label>
              <input type="text" id="username" name="username" placeholder="Username" v-model="newUser.username" required />
            </div>
            <div class="form-group">
              <label for="email">Email:</label>
              <input type="email" id="email" name="email" placeholder="Email" v-model="newUser.email" required />
            </div>
            <div class="form-group">
              <label for="password">Password:</label>
              <input type="password" id="password" name="password" placeholder="Password" v-model="newUser.password" required />
            </div>
            <div class="form-group">
              <label for="confirm-password">Confirm Password:</label>
              <input type="password" id="confirm-password" name="confirmPassword" placeholder="Confirm Password" v-model="newUser.confirmPassword" required />
            </div>
            <div class="button-group">
              <button type="submit">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
</template>
  
<script>
import UserServices from '@/services/UserServices.js';

export default {
    emits: ['close', 'registration-successful'],
    data() {
        return {
            showRegistrationModal: false,
            newUser: {
                "username": '',
                "firstName": '',
                "lastName": '',
                "password": '',
                "email": '',
                "confirmPassword": '',
                "role": 'user'
            },
            showModal: false, // Initially hide the modal
        };
    },

    mounted() {
    // Show the modal after a brief delay to allow for the transition effect
    setTimeout(() => {
      this.showModal = true;
    }, 100);
  },

    methods: {
        async registerUser() {
            // Need to perform validation! (user exists -> user with same email(email already exists), same thing with username.)
            // Perform validation here if needed
            if (this.newUser.password !== this.newUser.confirmPassword) {
                // Handle password mismatch
                return;
            }
            // Send the newUser object to the backend API via userServices
            try {
            const response = await UserServices.registerUser(this.newUser);
            console.log('API Response:', response);
            if (response.status === 201 || response.ok) {
              // Registration successful, emit event to close modal and trigger success modal
              this.$emit('close'); // Close the registration modal
              this.$emit('registration-successful'); // Open the success modal
            } else {
              // Handle registration error
            }
          } catch (error) {
            console.error('Error occurred:', error);
          }
        },

        closeModalOutside(event) {
      // Check if the click event occurred outside of the modal content
      if (!event.target.closest('.modal-content')) {
        this.closeRegistrationModal();
      }
    },
    
        closeRegistrationModal() {
          console.log('Closing registration modal in parent');
            this.$emit('close');
        },
      
       
    },
}

</script>

<style>
.bg-modal {
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

#close-modal {
  position: absolute;
  top: 0;
  height: 20px;
  right: 0;
  font-size: 40px;
  transform: rotate(45deg);
  cursor: pointer;
}

.modal-content {
  width: 80%;
  max-width: 380px; 
  height: 350px;
  background-color: white;
  padding: 20px; 
  border-radius: 4px;
  text-align: center;
  align-items: center;
  position: relative;
  transform: translateY(260%); /* Start off-screen */
  transition: transform 0.5s ease; /* Transition on transform property */
}

.modal-content.slide-in {
  transform: translateY(0); /* Slide in from the bottom */
}

.modal-content h2 {
  margin-top: 25px;
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

.success-content button {
  margin-top: 20px;
}

#close-success {
  position: absolute;
  top: 0;
  height: 30px;
  right: 0;
  font-size: 50px;
  transform: rotate(45deg);
  cursor: pointer;
}

.modal-content h2 {
    border-bottom: 1px solid black;
    padding-bottom: 20px;
}

form > input {
  width: 220px;
  display: block;
  margin: 15px auto;
}

button:hover {
  background-color: #c9c7c7;
  cursor: pointer;
}

.form-group {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.form-group label {
  width: 120px; /* Adjust as needed */
  margin-right: 10px;
}

.button-group {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

</style>