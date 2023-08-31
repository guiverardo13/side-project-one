<template>
  <div>
    <div class="login-modal" >
        <div class="login-content">
          <h2>Sign In</h2>
          <form @submit.prevent="loginUser">
            <div id="close-modal"  @click="closeLoginModal">+</div>
            <div class="form-group">
              <label for="username">Username:</label>
              <input type="text" id="username" name="username" placeholder="Username" v-model="user.username" required />
            </div>
            <div class="form-group">
              <label for="password">Password:</label>
              <input type="password" id="password" name="password" placeholder="Password" v-model="user.password" required />
            </div>
            <div class="button-group">
              <button type="submit">Sign in</button>
            </div>
          </form>
        </div>
      </div>
    </div>
</template>
<script>
import UserServices from '../services/UserServices.js';

export default {
    name: "LoginBox",
    emits: ['close', 'login-successful'],
    data() {
        return {
            user: {
                "username": "",
                "password": "",
            },
        }
    },

    methods: {
        
        clearForm() {
            this.user.username = "";
            this.user.password = "";
        },

        loginUser() {
            UserServices
            .login(this.user)
            .then((response) => {
                if (response.status == 200) {
                this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                this.$store.commit("SET_USER", response.data.user);
                }
            })
            .catch((error) => {
            const response = error.response;

            if (response.status === 401) {
                this.invalidCredentials = true;
            } else {
                this.credentialsNotFound = true;
            }
            });
        },

        closeLoginModal() {
            this.clearForm();
            this.$emit('close'); // Emit event to close the modal
        },
    },
};
</script>

<style>
.login-modal {
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

.login-content {
  width: 80%;
  max-width: 300px; 
  height: 200px;
  background-color: white;
  padding: 20px; 
  border-radius: 4px;
  text-align: center;
  align-items: center;
  position: absolute;
}

.login-content button {
  margin-top: 8px;
  width: 80px;
  font-size: 15px;
  border-radius: 10px;
}

.login-content h2 {
  margin-top: 8px;
}

.login-content h2 {
    border-bottom: 1px solid black;
    padding-bottom: 10px;
}

</style>