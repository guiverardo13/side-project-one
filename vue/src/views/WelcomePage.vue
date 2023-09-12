<template>
  <div class="container">
    <header>
      <nav class="container-top">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item" @click="openLikesModal">
          {{ isAuthenticated ? 'Likes' : 'Register' }}
        </div>
        <div class="item" @click="handleSignOutClick">
        {{ isAuthenticated ? 'Sign Out' : 'Sign in' }}
        </div>
      </nav>
    </header>
      <main>
        <div class="container-main">
          <div class="welcome">
            <div>
              <h2>Welcome {{ isAuthenticated ? user.username : 'to my first project' }}!</h2>
            </div>
            <p>
              All the information you need within a few clicks
            </p>
          </div>
        </div>
      </main>
      <div class="container-input">
      <div class="main-input">
        <div id="where-to-flex">
          <!-- Input field for city search -->
          <i class="fa-solid fa-location-dot"></i>
          <input v-model="citySearchQuery" name="where-to" id="where-to" type="text" placeholder="What city are you traveling to?">
        </div>
        <!-- Search button -->
        <div id="search-button-flex">
          <button class="search-button" @click="searchCity">Search</button>
        </div>
        </div>
      </div>
      <section class="container-middle">
        <div id="how-it-works">
          <h3>How it works</h3>
          <span>Here to help you create memories</span>
        </div>
        <div id="items">
          <div class="discover">
            <i class="fa-solid fa-earth-americas" style="color: #008793;"></i>
            <div>Discover</div>
            <div class="text text-discover">
              Discover new locations and open up <br>
              a world of possibilities. We will <br>
              guide you through captivating <br>
              destinations</div>
          </div>
          <div class="plan">
            <i class="fa-regular fa-pen-to-square" style="color: #008793;"></i>
            <div>Plan</div>
            <div class="text text-plan">
              Effortlessly plan your dream trips,  <br>
              explore destinations, create itineraries, <br>
              and collaborate with ease,  <br> 
              making every journey <br>
              a memorable adventure.</div>
          </div>
          <div class="enjoy">
            <i class="fa-solid fa-champagne-glasses" style="color: #008793;"></i>
            <div>Enjoy</div>
            <div class="text text-enjoy">
              Joyful moments, laughter, <br> 
              and shared cheers as you create <br>
              and embark on <br>
              unforgettable adventures together.</div>
          </div>
        </div>
      </section>
      <section class="container-bottom">
        <div class="top-searches">
          <h3>Top Searches</h3>
        </div>
        <div class="box">
          <div class="city-pictures">
            <div class="pittsburgh">
              <img src="../assets/pitt.jpg" alt="Pitt">
              <div class="pittsburgh-text">
                <a href="/city/name/Pittsburgh">
                  <h4>Pittsburgh</h4>
                </a>
              </div>
            </div>
            <div class="miami">
                    <img src="../assets/mia.jpg" alt="MIA">
                <div class="miami-text">
                    <a href="/city/name/Miami">
                      <h4>Miami</h4>
                    </a>
                </div>
              </div>
            <div class="nyc">
                    <img src="../assets/nyc.jpg" alt="NYC">
                <div class="nyc-text">
                    <a href="/city/name/New%20York%20City">
                        <h4>New York City</h4>
                    </a>
                </div> 
            </div> 
            <div class="seattle">
                    <img src="../assets/seattle.jpg" alt="Seattle">
                <div class="seattle-text">
                    <a href="/city/name/Seattle">
                        <h4>Seattle</h4>
                    </a>
                </div>    
            </div> 
            <div class="los-angeles">
                    <img src="../assets/1.jpg" alt="LA">
                <div class="la-text">
                    <a href="/city/name/Los%20Angeles">
                        <h4>Los Angeles</h4>
                    </a>
                </div>    
            </div>    
            <div class="dc">
                    <img src="../assets/dc.jpg" alt="DC">
                <div class="dc-text">
                    <a href="/city/name/Washington%20D.C.">
                        <h4>Washington D.C.</h4> 
                    </a>    
                </div>    
            </div>    
          </div>      
        </div>
          </section>
          <footer class="container-footer" id="contact">
          <div class="footer-content">
          <h2>Contact Me</h2>
          <p>If you have any questions or feedback, feel free to get in touch with me.</p>
            <div class="contact-info">
              <p>Email: gverardo13@gmail.com</p>
              <p>Phone: +1 (641) 758-1207</p>
            </div>
            <div class="social-icons">
              <a href="https://www.linkedin.com/in/guilhermeverardo/"  target="_blank" class="social-icon"><i class="fa-brands fa-linkedin"></i></a>
              <a href="https://github.com/guiverardo13" target="_blank" class="social-icon"><i class="fa-brands fa-github"></i></a>
            </div>
          </div>
          <div class="footer-background"></div>
          </footer>
          <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
          <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal" @sign-in="handleSignIn" />
          <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
          <LikeModal v-if="showLikesModal" :isAuthenticated="isAuthenticated" :likedItems="likedItems" @close="closeLikeModal"/>
      </div>
  </template>
  
  <script>
  import UserServices from '../services/UserServices.js';
  import RegisterUserModal from '../components/RegisterUserModal.vue';
  import AccountCreatedModal from '../components/AccountCreatedModal.vue';
  import CityService from '../services/CityServices.js';
  import LoginModal from '../components/LoginModal.vue';
  import LikeModal from '../components/LikeModal.vue';
  
  export default {
    name: 'WelcomePage',
  
    created() {
      // Check if the user is authenticated by looking at the Vuex store and local storage
      this.checkUserAuthentication();
    },
    
    data() {
      return {
        isAuthenticated: false,
        citySearchQuery: '',
        showRegistrationModal: false,
        showAccountCreatedModal: false,
        showLoginModal: false,
        showLikesModal: false,
        likedItems: [], // Initialize likedItems as an empty array
      };
    },

    
  
    components: {
      RegisterUserModal,
      AccountCreatedModal,
      LoginModal,
      LikeModal
    },
  
    methods: {
      async checkUserAuthentication() {
        const token = this.$store.state.token;
        if (token) {
          this.isAuthenticated = true;
          this.user = this.$store.state.user;
        }
      },
      
      async handleRegistrationSuccess() {
        // Close the registration modal and open the success modal
        this.closeRegistrationModal();
        this.openSuccessModal();
      },

      openLikesModal() {
      // Show the Like modal when the user clicks "Likes" if authenticated
      if (this.isAuthenticated) {
        this.showLikesModal = true;
      } else {
        this.openRegistrationModal();
      }
    },
  
      async handleSignOutClick() {
        if (this.isAuthenticated) {
          this.logoutUser(); // Call the logoutUser method when Sign Out is clicked
        } else {
          this.openLoginModal(); // Open the login modal when not authenticated
        }
      },
  
      async searchCity() {
        try {
          const data = await CityService.getCityByName(this.citySearchQuery);
          if (data) {
            this.$router.push({ name: 'city-page', params: { cityName: this.citySearchQuery } });
          } else {
            console.log('City not found');
          }
        } catch (error) {
          console.error('Error searching for city:', error);
        }
      },
  
      async handleLoginSuccess(response) {
          this.closeLoginModal();
          this.isAuthenticated = true; // Set isAuthenticated to true
          this.user = response.data.user; // Update the user data

          try {
            // Update the Vuex store with the new user data
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            // Update local storage
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('user', JSON.stringify(response.data.user));
          } catch (error) {
            console.error('Error updating Vuex store and local storage:', error);
          }
        },

      handleSignIn() {
        console.log('Sign In button clicked'); // Add this line for debugging
          this.closeSuccessModal();
          this.openLoginModal();
        },
  
      logoutUser() {
  // Clear the token and user data from local storage
    UserServices.logout();

    // Clear user data and token in your Vuex store
    this.$store.commit('LOGOUT');

    // Reset the user in Vuex store to an empty object
    this.$store.commit('SET_USER', {});
    window.alert("Sign out Successful!");
    // Update the isAuthenticated status
    this.isAuthenticated = false;
  },
  
      openRegistrationModal() {
        console.log('Opening registration modal');
        this.showRegistrationModal = true;
      },
  
      closeRegistrationModal() {
        console.log('Closing the modal');
        this.showRegistrationModal = false; // Close the modal
      },
  
      closeSuccessModal() {
        this.showAccountCreatedModal = false;
      },
  
      openSuccessModal() {
        this.showAccountCreatedModal = true;
      },
  
      openLoginModal() {
        this.showLoginModal = true;
      },
  
      closeLoginModal() {
        this.showLoginModal = false;
      },

      closeLikeModal() {
      this.showLikesModal = false;
    }
    },
  };
  </script>

<style>
  html {
    min-height: 100%;
    scroll-behavior: smooth;
  }

  body {
  height: 100%;
  margin: 0;
  display: flex;
  flex-direction: column;
  font-family: 'Russo One', sans-serif;
  background-image: linear-gradient(to bottom, #051937, #004D7A, #008793, white 45%),
                    linear-gradient(to top, #051937, #004D7A, #008793, white 25%);
  background-repeat: no-repeat;
  background-attachment: scroll;
  background-position: top, bottom;
  background-size: 100% 50%;
}
  .container {
    margin: 0 auto;
    max-width: 1200px;
    padding: 0 20px;
    flex: 1;
  }

  .container-top {
    display: flex;
    justify-content: space-between;
    font-size: 18px;
    align-items: center;
    font-family: 'Russo One', sans-serif;
    padding: 20px 0;
    width: 100%;
  }
  
  .main-name {
      display: flex;
      justify-content: center;
  }
  
  .container-top h1 {
      font-size: 30px;
      margin: 0;
      padding: 0;
      align-items: flex-start;
  }
  
  .container-input {
    display: flex;
    justify-content: center;
  }
  
  .container-top a {
      color: white;
      text-decoration: none;
  }
  
  main h2 {
      display: flex;
      align-items: start;
      color: white;
      font-size: 50px;
      margin-top: 120px;
      margin-bottom: 0;;
  }
  
  main p {
      color: white;
      font-size: 20px;
      margin: 0;
      padding: 0;
  }
  
  .container-input .main-input {
      display:flex;
      margin-top: 150px;
      justify-content: center;
      background-color: #004D7A;
      padding:7px 7px 7px 7px;
      border-radius: 6px;
      gap: 4px;
      
  }
  
  .container-middle {
      display: flex;
      flex-direction: column;
      justify-content: center;
  }
  
  #where-to-flex input {
      border: 1px solid transparent;
      font-size: 18px;   
      font-family:'Russo One', sans-serif;
      padding-left: 10px;
      width: 650px;  
  }
  
  #where-to-flex {
      background-color: white;
      padding: 10px 8px;
      border-radius: 3px;
      text-align: center;
  }
  
  button {
      font-size: 17px;
      font-family:'Russo One', sans-serif;
      padding: 10px 8px;
      width: 90px;
      text-align: center;
      background-color: white;
      border-radius: 3px;
  }
  
    .about :hover {
      color: #00AFEF;
      text-decoration: none;
    } 
  
    .contact :hover {
      color: #00AFEF;
    }

    .container-middle h3 {
      display: flex;
      justify-content: center;
      margin-top: 100px;
      margin-bottom: 0;
      font-size: 40px;
    }
  
    .container-middle span {
      display: flex;
      justify-content: center;
      font-size: 20px;
      margin: 0;
      padding: 0;
    }
  
    #items {
      display: flex;
      font-size: 18px;
      width: 60vw;
      justify-content: space-evenly;
      text-align: center;
      margin-top: 100px;
    }
  
    .discover {
      border-right: #224458 solid 7px;
      padding-right: 15px;
    }
  
    .text.text-discover {
      padding-right: 20px;
    }
    
    
    .discover i{
      font-size: 50px;
      padding-bottom: 15px;
  
    }
  
    .plan {
      border-right: #224458 solid 7px;
      padding-right: 50px; 
    }
  
    .plan i {
      font-size: 50px;
      padding-bottom: 15px;
    }
  
    .enjoy i {
      font-size: 50px;
      padding-bottom: 15px;
    }
  
    .text {
      padding-top: 10px;
      font-size: 16px;
    }

    .item {
      color: #fff;
      cursor: pointer;
    }
  
    section .top-searches {
    text-align: center;
    margin-top: 100px; /* Adjust as needed */
    margin-bottom: 0; /* Adjust as needed */
    padding-bottom: 0; /* Adjust as needed */
  }

    section .top-searches h3 {
    font-size: 40px;
    padding-bottom: 15px;
    width: 100%; /* Ensure the width matches the content container */
    border-bottom: black solid 1px;
  }
  
  .box .city-pictures{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 8px;
    padding-bottom: 100px;
  }
  
  .city-pictures img {
    width: 350px;
    border-radius: 25px;
  }
  
  .pittsburgh {
    display: flex;
    position: relative;
  }
  
  .pittsburgh-text {
  position: absolute;
  top: 13%;
  margin-left: 40px;
  font-size: 40px;
  color: white;
  padding: 20px;
  }
  
  .pittsburgh a {
    color: white;
    text-decoration: none;
  }
  
  .pittsburgh a :hover{
    color: #cfc8c8;
  }
  
  .miami {
    display: flex;
    position: relative;
  }
  
  .miami-text {
  position: absolute;
  top: 13%;
  margin-left: 90px;
  font-size: 40px;
  color: white;
  padding: 20px;
  }
  
  .miami a {
    color: white;
    text-decoration: none;
  }
  
  .miami a :hover{
    color: #cfc8c8;
  }
  
  .nyc {
    display: flex;
    position: relative;
  }
  
  .nyc-text {
    position: absolute;
    top: 12%;
    margin-left: 20px;
    font-size: 40px;
    color: white;
    padding: 20px;
  }
  
  .nyc a {
    color: white;
    text-decoration: none;
  }
  
  .nyc a :hover{
    color: #cfc8c8;
  }
    
  .seattle {
    display: flex;
    position: relative;
  }
  
  .seattle-text {
    position: absolute;
    top: 13%;
    margin-left: 80px;
    font-size: 40px;
    color: white;
    padding: 20px;
  } 
  
  .seattle a {
    color: white;
    text-decoration: none;
  }
  
  .seattle a :hover{
    color: #cfc8c8;
  }
      
    .los-angeles {
      display: flex;
      position: relative;
  }
    
  .la-text {
    position: absolute;
    top: 13%;
    margin-left: 30px;
    font-size: 40px;
    color: white;
    padding: 20px;
  }
  
  .los-angeles a {
    color: white;
    text-decoration: none;
  }
   
  .los-angeles a :hover{
    color: #cfc8c8;
  }
      
  .dc {
    display: flex;
    position: relative;
  }     
  
  .dc-text {
    position: absolute;
    text-align: center;
    top: 6%;
    margin-left: 7px;
    font-size: 40px;
    color: white;
    padding: 20px;
  }
  
  .dc a {
    color: white;
    text-decoration: none;
  }
  
  .dc a :hover{
    color: #cfc8c8;
  }
  
  .container-footer {
    position: relative;
    padding: 0;
    color: rgb(0, 0, 0);
    text-align: center;
  }

  .footer-content {
    max-width: 1000px;
    margin: 0 auto;
    padding: 40px 0;
  }

.footer-content h2 {
  font-size: 28px;
  margin-bottom: 20px;
}

.footer-content p {
  font-size: 18px;
  margin-bottom: 20px;
}

.contact-info p {
  font-size: 16px;
  margin: 5px 0;
}

.social-icons {
  margin-top: 20px;
}

.social-icon {
  font-size: 24px;
  color: white;
  margin: 0 10px;
  text-decoration: none;
}

.account-box {
  position: absolute;
  top: 100%;
  right: 0;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  padding: 10px;
  display: none;
  flex-direction: column;
  z-index: 1;
}

.account-box .likes,
.account-box .sign-out {
  cursor: pointer;
  margin-bottom: 5px;
}

.account-box .likes:hover,
.account-box .sign-out:hover {
  background-color: #eee;
}

.item:hover {
  color: #00AFEF;
}
  </style>