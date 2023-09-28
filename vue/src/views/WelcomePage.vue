<template>
  <div class="container">
    <header>
      <nav class="container-top">
        <h1 class="logo"><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item" @click="openLikesModal">
          {{ isAuthenticated ? 'Likes' : 'Register' }}
        </div>
        <div class="item sign" @click="handleSignOutClick">
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
    <div class="main-input" id="blue">
      <div id="where-to-flex">
        <button class="drop-down" aria-haspopup="true" aria-controls="dropdown-menu" @click="toggleDropdown">
          <i class="fa-solid fa-location-dot"></i>{{ citySearchQuery ? citySearchQuery : 'Please select a city' }}<i :class="showDropdown ? 'fa-solid fa-chevron-up' : 'fa-solid fa-chevron-up fa-rotate-180'"></i>
        </button>
        <div class="drop-down-list" @click="closeDropDownList" v-if="showDropdown" >
          <ul>
            <li class="none"  @click="setCitySearchQuery('Please select a city')"></li>
            <li @click="setCitySearchQuery('Pittsburgh')">Pittsburgh</li>
            <li @click="setCitySearchQuery('Miami')">Miami</li>
            <li @click="setCitySearchQuery('New York City')">New York City</li>
            <li @click="setCitySearchQuery('Seattle')">Seattle</li>
            <li @click="setCitySearchQuery('Los Angeles')">Los Angeles</li>
            <li @click="setCitySearchQuery('Washington D.C.')">Washington D.C.</li>
          </ul>
        </div>
      </div>
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
            <i class="fa-solid fa-earth-americas" :class="{ 'rotate-icon': isIconClicked }" style="color: #008793" @click="toggleRotation"></i>
            <div>Discover</div>
            <div class="text text-discover">
              Discover new locations and open up <br>
              a world of possibilities. We will <br>
              guide you through captivating <br>
              destinations</div>
          </div>
          <div class="plan">
            <i class="fa-regular fa-pen-to-square"  :class="{ 'rotate-icon': isIconClicked }" style="color: #008793"  @click="toggleRotation"></i>
            <div>Plan</div>
            <div class="text text-plan">
              Effortlessly plan your dream trips,  <br>
              explore destinations, create itineraries, <br>
              and collaborate with ease,  <br> 
              making every journey <br>
              a memorable adventure.</div>
          </div>
          <div class="enjoy">
            <i class="fa-solid fa-champagne-glasses"  @click="toggleRotation" :class="{ 'rotate-icon': isIconClicked }" style="color: #008793"></i>
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
        <div class="top-searches" id="hard">
          <h3>Top Searches</h3>
        </div>
        <div class="box">
          <div class="city-pictures">
            <div class="pittsburgh"><a href="/city/name/Pittsburgh">
              <img src="../assets/pitt.jpg" alt="Pitt">
              </a>
              <div class="pittsburgh-text">
                <a href="/city/name/Pittsburgh">
                  <h4>Pittsburgh</h4>
                </a>
              
              </div>
            </div>
            <div class="miami">
              <a href="/city/name/Miami">
                    <img src="../assets/mia.jpg" alt="MIA">
              </a>
                <div class="miami-text">
                    <a href="/city/name/Miami">
                      <h4>Miami</h4>
                    </a>
                </div>
              </div>
            <div class="nyc">
              <a href="/city/name/New%20York%20City">
                    <img src="../assets/nyc.jpg" alt="NYC">
                  </a>
                <div class="nyc-text">
                    <a href="/city/name/New%20York%20City">
                        <h4>New York City</h4>
                    </a>
                </div> 
            </div> 
            <div class="seattle">
              <a href="/city/name/Seattle">
                    <img src="../assets/seattle.jpg" alt="Seattle">
                  </a>
                <div class="seattle-text">
                    <a href="/city/name/Seattle">
                        <h4>Seattle</h4>
                    </a>
                </div>    
            </div> 
            <div class="los-angeles">
              <a href="/city/name/Los%20Angeles">
                    <img src="../assets/1.jpg" alt="LA">
              </a>
                <div class="la-text">
                    <a href="/city/name/Los%20Angeles">
                        <h4>Los Angeles</h4>
                    </a>
                </div>    
            </div>    
            <div class="dc">
              <a href="/city/name/Washington%20D.C.">
                    <img src="../assets/dc.jpg" alt="DC">
                  </a>
                <div class="dc-text">
                    <a href="/city/name/Washington%20D.C.">
                        <h4>Washington <br>D.C.</h4> 
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
              <a href="https://drive.google.com/file/d/1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-/view" target="_blank" class="resume"><i class="fa-regular fa-file-pdf"></i></a>
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
        citySearchQuery: 'Please select a city',
        showRegistrationModal: false,
        showAccountCreatedModal: false,
        showLoginModal: false,
        showLikesModal: false,
        likedItems: [], // Initialize likedItems as an empty array
        isIconClicked: false,
        showDropdown: false,
      };
    }, 
  
    components: {
      RegisterUserModal,
      AccountCreatedModal,
      LoginModal,
      LikeModal
    },
  
    methods: {
      closeModalOutside(event) {
        if (event.target.classList.contains('drop-down-list')) {
          this.showDropdown = false;
        }
      },
      
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
  // Check if citySearchQuery is "Please select a city"
  if (this.citySearchQuery === 'Please select a city') {
    window.alert('Please select a valid city.');
    return; // Exit the function to prevent further execution
  }

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
      setCitySearchQuery(city) {
        this.citySearchQuery = city;
        this.showDropdown = false; // Hide the dropdown after selecting a city
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
    },

    toggleRotation() {
      this.isIconClicked = !this.isIconClicked;
    },

    toggleDropdown() {
      // Toggle the showDropdown property to show/hide the dropdown
      this.showDropdown = !this.showDropdown;
    },
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

.drop-down-list {
  right: 35.4%;
  background: white;
  border: 1px solid black;
  width: 646px;
  z-index: 2;
  position: absolute;
}


.drop-down-list ul {
  list-style: none; /* Remove bullet points */
  padding: 0;
  margin: 0;
}

.drop-down-list li:hover {
  background: #e4e4e4;
}

.drop-down-list ul li {
  padding: 10px;
  text-align: left;
  cursor: pointer;
}

.drop-down:hover {
  background: #e4e4e4;
}

.fa-chevron-up.fa-rotate-180 {
  position: absolute; /* Set position to absolute */
  right: 685px; /* Adjust the right distance as needed */
  top: 47.3%; /* Position it vertically in the middle */
  /* transform: translateY(-50%); Adjust vertical alignment */
}

.fa-chevron-up {
  position: absolute; /* Set position to absolute */
  right: 685px; /* Adjust the right distance as needed */
  top: 47.3%; /* Position it vertically in the middle */
  /* transform: translateY(-50%); Adjust vertical alignment */
}

.none {
  height: 20px;
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

  .drop-down {
      border: 1px solid transparent;
      font-size: 18px;   
      font-family:'Russo One', sans-serif;
      padding-left: 10px;
      width: 650px;  
      text-align: start;
      left: 0;
      background: white;
      border: 1px solid black;
      width: 650px;
      z-index: 2;     
    }

    .container-input .main-input {
      display: flex;
      justify-content: center;
      margin-top: 150px;
      background-color: #004D7A;
      padding: 7px;
      border-radius: 6px;
      gap: 4px;
      height: 45px; /* Add a fixed height */
    }
  
  
  .container-middle {
      display: flex;
      flex-direction: column;
      justify-content: center;
      margin-top: 70px;
      z-index: 1;
  }

  .fa-location-dot {
    padding-right: 15px;
  }

  .fa-chevron-up.fa-rotate-180 {
    margin-left: 400px;
  }
/*   
  #where-to-flex input {
      border: 1px solid transparent;
      font-size: 18px;   
      font-family:'Russo One', sans-serif;
      padding-left: 10px;
      width: 650px;  
  } */
  
  /* #where-to-flex {
      background-color: white;
      padding: 10px 8px;
      border-radius: 3px;
      text-align: center;
  } */
  
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
      position: relative;
      justify-content: center;
      margin-top: 100px;
      margin-bottom: 0;
      font-size: 40px;
      z-index: 1;
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
  
  .city-pictures{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 8px;
    padding-bottom: 100px;
  }
  
  .city-pictures img {
    width: 350px;
    height: 235.55px;
    border-radius: 25px;
  }

  .pittsburgh {
  display: flex;
  position: relative;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}

/* Style for the h4 text */
.pittsburgh-text h4 {
  font-size: 40px;
  top: 21%;
  left: 20%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
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
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}

.miami-text h4 {
  font-size: 40px;
  top: 21%;
  left: 32%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
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
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}
  
  
.nyc-text h4 {
  font-size: 40px;
  top: 20%;
  left: 12%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
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
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}
  
.seattle-text h4 {
  font-size: 40px;
  top: 20%;
  left: 27%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
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
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}

.la-text h4 {
  font-size: 40px;
  top: 20%;
  left: 15%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
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
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}
      
  
  .dc-text h4 {
    font-size: 40px;
  top: 15%;
  left: 18%;
  position: absolute;
  color: white;
  text-align: center;
  z-index: 2;
  }
  
  .dc a {
    color: white;
    text-decoration: none;
  }
  
  .dc a :hover{
    color: #cfc8c8;
  } */
   */
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

.fa-file-pdf {
  font-size: 25px;
  padding-left: 10px;
  
}

.fa-file-pdf:hover {
  cursor: pointer;
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

.fa-regular.fa-file-pdf {
  font-size: 24px;
  color: rgb(255, 255, 255);
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

.city-pictures img {
    width: 350px;
    border-radius: 25px;
    transition: transform 0.3s ease; /* Add a smooth transition */
    position: relative;
    z-index: 1; /* Set a higher z-index for the images */
  }

  /* Apply zoom effect on hover */
  .city-pictures img:hover {
    transform: scale(1.1);
    z-index: 2; /* Set a higher z-index for the zoomed-in image */
  }

  .fa-earth-americas, 
  .fa-pen-to-square, 
  .fa-champagne-glasses {  
    transition: transform 0.3s ease; /* Add a smooth transition */
  }

  /* Apply zoom effect on hover */
  .fa-earth-americas:hover, 
  .fa-pen-to-square:hover, 
  .fa-champagne-glasses:hover {
    transform: scale(1.3); /* Enlarge the image on hover */
    cursor: pointer;
  }


  .rotate-icon {
  animation: rotate 2s linear infinite;
}

@keyframes rotate {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

@media (max-width: 380px) {

 .container {
  width: 80%;
  margin: 0px;
  padding-right: 0px;
 }

 body {
  height: 100px;
  display: flex;
  background-image: linear-gradient(to bottom, #051937, #004D7A, #008793, white 70%);
                    /* linear-gradient(to top, #051937,white,white, , white 50%); */
  background-repeat: no-repeat;
  /* margin: 0;
  display: flex;
  flex-direction: column;
  font-family: 'Russo One', sans-serif;
 
  background-attachment: scroll;
  background-position: top, bottom;
  background-size: 100% 50%; */
}

.container-top {
    display: flex;
    justify-content: space-between;
    font-size: 15px;
    width: 110%;

    font-family: 'Russo One', sans-serif;
    
  }

  .container-top h1 {
    font-size: 18px;
    text-align: center;
    display: flex;
    flex-wrap: wrap;
    width: 10px;
    margin-right: 50px;
  }

  .container-main h2 {
    margin-top: 25px;
    font-size: 20px;
  }

  .container-main p {
    margin: 4px;
    font-size: 11px;
  }

  .container-input {
    width: 100%;
  }

  #blue {
    margin-top: 40px;
    width: 300px;
    margin-left: 80px;
    margin-right: 40px;
    height: 35px;
  }

  #where-to-flex {
    margin-top: 10px;

  }

  .drop-down {
    width: 200px;
    position: relative;
    height: 35px;
    font-size: 12px;
    top: -42%;
  }

  .drop-down-list {
    width: 197px;
    position: relative;
    right: 0%;
    top: -40%
  }

  .drop-down-list li {
    font-size: 13px;
  }


  .search-button {
    width: 60px;
    height: 35px;
    font-size: 10px;
  }

  #how-it-works > h3 {
    font-size: 20px;
    margin-top: 0px;
  }

  #how-it-works {
    align-items: center;
    text-align: center;
  }

  .container-middle {
    margin-top: 30px;
    margin-left: 35px;
    margin-bottom: 0px;
  }

  #items {
    width: 20px;
    align-content: center;
    height: 135px;
    font-size: 15px;
    margin-top: 30px;
    margin-left: 120px;
  }

  #items i {
    font-size: 20px;
  }


.text {
    font-size: 10px;
  }

  .plan {
    border-right: 3px solid #224458;
    padding: 0px;
  }

  .discover {
    padding-right: 5px;
    border-right: 3px solid #224458;
  }

  .text.text-discover {
    font-size: 8px;
    width: 100px;
    padding: 0px;
    padding-top: 10px;
  }


  .text.text-enjoy {
    font-size: 8px;
    width: 100px;
  }

  .text.text-plan {
    font-size: 8px;
    width: 130px;
  }
  
  .container-bottom h3 {
    margin-left: 20px;
    margin-top: 0px;
  }

  .top-searches h3 {
    font-size: 20px;
  }

  #hard {
    margin-top: 30px;
  }

  #hard h3{
    font-size: 20px;
    width: 350px;
    margin-top: 0px;
    margin-left: -5px;
    position: relative;
    top: 65%;
    }

    .city-pictures{
      display: flex;
      
      margin-left: 32px;      
      padding-bottom: 0px;
      margin-bottom: 30px;
    }

    .city-pictures img {
    
      width: 130px;
      height: 100px;
      border-radius: 12px;
    }

    .pittsburgh-text h4 {
      font-size: 15px;
      top: 21%;
      left: 20%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .miami-text h4 {
      font-size: 15px;
      top: 21%;
      left: 32%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .nyc-text h4 {
      font-size: 15px;
      top: 21%;
      left: 12%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .seattle-text h4 {
      font-size: 15px;
      top: 21%;
      left: 27%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .la-text h4 {
      font-size: 15px;
      top: 21%;
      left: 15%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .dc-text h4 {
      font-size: 15px;
      top: 21%;
      left: 18%;
      position: absolute;
      color: white;
      text-align: center;
      z-index: 2;
    }

    .footer-content {
      margin: 10px 10px 0px 0px;
      padding: 0px;
    }

    .footer-content h2 {
      font-size: 20px;
      margin-bottom: 20px;
      padding-top: 5px;
    }

    .footer-content p {
      font-size: 12px;
      margin-bottom: 5px;
      
    }
    
    .social-icon {
      font-size: 24px;
      color: black;
      margin: 0 10px;
      
    }
   
    .fa-regular.fa-file-pdf {
      font-size: 24px;
      color: black;
    }

    .container-footer {
      border-top: 1px solid black;
      width: 340px;
    }
}



@media (max-width: 450px) {

.container {
 width: 80%;
 margin: 0px;
 padding-right: 0px;
}

body {
 height: 100px;
 display: flex;
 background-image: linear-gradient(to bottom, #051937, #004D7A, #008793, white 70%);
                   /* linear-gradient(to top, #051937,white,white, , white 50%); */
 background-repeat: no-repeat;
 background-size: 110% 50%;
 /* margin: 0;
 display: flex;
 flex-direction: column;
 font-family: 'Russo One', sans-serif;

 background-attachment: scroll;
 background-position: top, bottom;
 background-size: 100% 50%; */
}

.container-top {
   display: flex;
   justify-content: space-between;
   font-size: 15px;
   width: 110%;

   font-family: 'Russo One', sans-serif;
   
 }

 .container-top h1 {
   font-size: 18px;
   text-align: center;
   display: flex;
   flex-wrap: wrap;
   width: 10px;
   margin-right: 50px;
 }

 .container-main h2 {
   margin-top: 25px;
   font-size: 30px;
 }

 .container-main p {
   margin: 4px;
   font-size: 15px;
 }

 .container-input {
   width: 100%;
 }

 #blue {
   margin-top: 45px;
   width: 300px;
   margin-left: 80px;
   margin-right: 40px;
   height: 35px;
 }

 #where-to-flex {
   margin-top: 10px;

 }

 .drop-down {
   width: 200px;
   position: relative;
   height: 35px;
   font-size: 12px;
   top: -42%;
 }

 .drop-down-list {
   width: 197px;
   position: relative;
   right: 0%;
   top: -40%
 }

 .drop-down-list li {
   font-size: 13px;
 }


 .search-button {
   width: 60px;
   height: 35px;
   font-size: 10px;
 }

 #how-it-works > h3 {
   font-size: 20px;
   margin-top: 10px;
 }

 #how-it-works {
   align-items: center;
   text-align: center;
   margin-left: 20px;
 }

 .container-middle {
   margin-top: 30px;
   margin-left: 35px;
   margin-bottom: 0px;
 }

 #items {
   width: 30px;
   align-content: center;
   height: 175px;
   font-size: 17px;
   margin-top: 30px;
   margin-left: 130px;
 }

 #items i {
   font-size: 30px;
 }

 .plan {
   border-right: 3px solid #224458;
   padding: 0px;
 }

 .discover {
   padding-right: 5px;
   border-right: 3px solid #224458;
   padding-left: 10px;
 }

 .enjoy {
  padding-right: 7px;
 }

 .text.text-discover {
   font-size: 10px;
   width: 115px;
   padding: 0px;
   padding-top: 10px;
 }


 .text.text-enjoy {
   font-size: 10px;
   width: 115px;
 }

 .text.text-plan {
   font-size: 10px;
   width: 135px;
   padding-right: 10px;
   padding-left: 10px;
 }
 
 .container-bottom h3 {
   margin-left: 20px;
   margin-top: 0px;
 }

 .top-searches h3 {
   font-size: 20px;
 }

 #hard h3{
   font-size: 20px;
   width: 350px;
   margin-top: 0px;
   margin-left: 8px;
   position: relative;
   }

   #hard {
    margin-top: 30px;
   }

   .city-pictures{
     margin-left: 44px;
     padding-bottom: 0px;
     margin-bottom: 30px;
   }

   .city-pictures img {
     width: 130px;
     height: 100px;
     border-radius: 12px;
   }

   .pittsburgh-text h4 {
     font-size: 15px;
     top: 21%;
     left: 20%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .miami-text h4 {
     font-size: 15px;
     top: 21%;
     left: 32%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .nyc-text h4 {
     font-size: 15px;
     top: 21%;
     left: 12%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .seattle-text h4 {
     font-size: 15px;
     top: 21%;
     left: 27%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .la-text h4 {
     font-size: 15px;
     top: 21%;
     left: 15%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .dc-text h4 {
     font-size: 15px;
     top: 21%;
     left: 18%;
     position: absolute;
     color: white;
     text-align: center;
     z-index: 2;
   }

   .footer-content {
     margin: 10px 10px 0px 0px;
     padding: 0px;
   }

   .footer-content h2 {
     font-size: 20px;
     margin-bottom: 20px;
     padding-top: 5px;
   }

   .footer-content p {
     font-size: 12px;
     margin-bottom: 5px;
     
   }
   
   .social-icon {
     font-size: 24px;
     color: black;
     margin: 0 10px;
     
   }
  
   .fa-regular.fa-file-pdf {
     font-size: 24px;
     color: black;
   }

   .container-footer {
     border-top: 1px solid black;
     width: 340px;
   }
}
  </style>