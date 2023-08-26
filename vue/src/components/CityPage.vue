<template>
    <div class="container-new-page">
<header>
    <nav class="container-top-new-page">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item about"><a href="#">About</a></div>
        <div class="item contact"><a href="#contact">Contact</a></div>
        <div class="item register"><a href="#">Register</a></div>
        <div class="item sign-in"><a href="#">Sign in</a></div>
    </nav>
    </header>
<main>
    <div class="container-main">
        <div class="cover-picture-container">
            <img v-if="coverPictureUrl" :src="coverPictureUrl" alt="City Cover" class="cover-picture">
            <h1 class="city-name">
            <a :href="videoLink" target="_blank" class="city-link">{{ cityName }}</a>
          </h1>
        </div>
    </div>
</main>
    <div class="city-description">
        <p>{{ cityDescription }}</p>
    </div>
    <section class="things-to-do-container">
        <section class="things-to-do">
            <h2>Things To Do</h2>
            <div class="categories">
          <div class="category hotels">
            <div class="category-text">Hotels</div>
          </div>
          <div class="category bars-restaurants">
            <div class="category-text">Bars <br> & <br> Restaurants</div>
          </div>
        </div>
        <div class="categories">
          <div class="category events">
            <div class="category-text">Events</div>
          </div>
          <div class="category pois">
            <div class="category-text">Points <br> Of <br> Interest</div>
          </div>
        </div>
        </section>
    </section>

    <footer class="container-footer" id="contact">
            <div class="footer-content">
        <h2>Contact Us</h2>
        <p>If you have any questions or feedback, feel free to get in touch with us.</p>
    <div class="contact-info">
        <p>Email: info@gvstravelguide.com</p>
        <p>Phone: +1 (123) 456-7890</p>
    </div>
    <div class="social-icons">
        <a href="#" class="social-icon"><i class="fa-brands fa-facebook"></i></a>
        <a href="#" class="social-icon"><i class="fa-brands fa-twitter"></i></a>
        <a href="#" class="social-icon"><i class="fa-brands fa-instagram"></i></a>
    </div>
</div>
    </footer>
</div>
</template>

<script>
import cityServices from '../services/CityServices.js';

export default {
  name: 'CityPage',
  props: {
    cityName: String // This prop will hold the city name from the search
  },
  data() {
    return {
      coverPictureUrl: '',
      cityDescription: '',
      videoLink: ''
    };
  },

  mounted() {
    this.getCoverPicture();
    this.getCityDescription();
    this.getCityVideoLink();
  },

  methods: {
    async getCoverPicture() {
      try {
        // Fetch the cover picture URL using the cityServices
        const coverPictureUrl = await cityServices.getCoverPictureByCityName(this.cityName);
        this.coverPictureUrl = coverPictureUrl;
      } catch (error) {
        console.error('An error occurred:', error);
      }
    },

    async getCityDescription() {
      try {
        // Fetch the city description using the cityServices
        const cityDescription = await cityServices.getCityDescriptionByCityName(this.cityName);
        this.cityDescription = cityDescription;
      } catch (error) {
        console.error('An error occurred:', error);
      }
    },

    async getCityVideoLink() {
      try {
        // Fetch the video link using the cityServices
        const videoLink = await cityServices.getVideoByCityName(this.cityName);
        this.videoLink = videoLink;
      } catch (error) {
        console.error('An error occurred while fetching video link:', error);
      }
    },
  }
};
</script>

<style>

.categories {
  display: flex;
  justify-content: space-between; /* Evenly space the boxes horizontally */
  gap: 20px; /* Adjust the gap between boxes */
  margin-top: 20px;
  max-width: 800px; /* Limit the maximum width */
  margin: 0 auto; /* Center the container horizontally */
}

.category {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center the content horizontally */
  align-items: center; /* Center the content vertically */
  background: grey;
  width: calc(50% - 10px); /* Adjust the width of each box */
  height: 180px; /* Adjust the height as needed */
  padding: 10px;
  text-align: center;
  color: white;
  border-radius: 25px;
}

.categories .events {
    background-image: url("../assets/events.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.categories .hotels {
    background-image: url("../assets/hotel.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.categories .bars-restaurants {
    background-image: url("../assets/restaurant.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.categories .pois {
    background-image: url("../assets/bean.jpeg");
  background-size: cover;
  background-position: center;
  height: 180px; /* Set the height as needed */
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 30px;
  text-align: center;
}

.city-link {
  color: white; /* Set the color to white */
  text-decoration: none; /* Remove underlining */
}

.city-link:hover {
    color:#00AFEF;
}

.category-text {
  font-size: 30px; /* Adjust the font size as needed */
  color: white;
  padding: 20px;
}
.things-to-do {
  font-size: 23px;
  text-align: center; /* Center-align the text */
  padding-bottom: 60px;
}

.things-to-do h2 {
  border-bottom: black solid 1px; /* Add border below h2 */
  padding-bottom: 10px; /* Add some space between h2 and border */
}

.things-to-do-container {
    border-bottom: 1px solid black;
    padding-bottom: 20px;
}

.hotels,
.bars-restaurants,
.events,
.pois {
  display: flex;
  flex-direction: column;
  flex-basis: calc(50% - 10px); /* 50% width minus margin */
  background: grey;
  margin: 5px;
}

.city-description {
  text-align: center; /* Center-align the text */
  margin: 20px auto; /* Add some margin for spacing */
  font-size: 15px;
  padding-bottom: 10px;
}

.cover-picture-container {
  position: relative;
  width: 100%;
  overflow: hidden;
  
  margin: 20px 0; /* Add spacing around the picture */
}

.cover-picture {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border: 5px solid rgb(0, 0, 0);
  box-sizing: border-box; /* Include border in total width/height */
}

.city-name {
  position: absolute;
  bottom: 0;
  left: 0;
  margin: 10px;
  margin-bottom: 20px;
  padding: 10px;
  color: white;
  font-size: 70px;
}
/* Adjust the navigation width based on your layout */
.container-top-new-page {
width: 30%;
margin: 0;
padding: 20px;
float: left;
color: white;
}

.container-new-page {
margin: 0 auto;
max-width: 1200px;
padding: 0 20px;
flex: 1;
}

.container-top-new-page {
display: flex;
justify-content: space-between;
font-size: 18px;
align-items: center;
font-family: 'Russo One', sans-serif;
padding: 20px 0;
width: 100%;
}

.container-top-new-page h1 {
  font-size: 30px;
  margin: 0;
  padding: 0;
  align-items: flex-start;
}


.container-top-new-page a {
  color: white;
  text-decoration: none;
}

.about:hover,
.sign-in:hover,
.contact:hover,
.register:hover {
  color: #00AFEF;
  text-decoration: underline;
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

.footer-background {
position: absolute;
bottom: 0;
left: 0;
height: 350px; /* Adjust the height as needed */
background-image: linear-gradient(to top, #008793, #004D7A, #051937, white, white);
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

.cover-picture {
/* Add styles for cover picture width, margin, and positioning */
width: 100%; /* Make the picture width cover the container */
max-height: 400px; /* Limit the picture's height */
margin: 20px 0; /* Add spacing around the picture */
margin-top: 80px;
}

</style>