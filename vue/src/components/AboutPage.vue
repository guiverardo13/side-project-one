<template>
  <div class="container">
    <div class="container-about-page">
  <header>
    <nav class="container-top-about">
        <h1><router-link to="/">GV's Travel Guide</router-link></h1>
        <div class="item"><a href="#">About</a></div>
        <div class="item"><a href="#contact">Contact</a></div>
        <div class="item" @click="openLikesModal">
          {{ isAuthenticated ? 'Likes' : 'Register' }}
        </div>
        <div class="item" @click="handleSignOutClick">
        {{ isAuthenticated ? 'Sign Out' : 'Sign in' }}
        </div>
    </nav>
  </header>
    <div class="middle-container">
      <h1 class="page-title">Guilherme Verardo</h1> <!-- Add this line -->
      <p class="description">A little bit about me</p>
      <img class="gv-circle" src="../assets/circle.png" alt="circle">
    </div>
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
          <a href="https://drive.google.com/uc?id=1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-&export=download" target="_blank" class="resume"><i class="fa-regular fa-file-pdf" style="color: #ffffff;"></i></a>
        </div>
      </div>
    </footer>
      <RegisterUserModal v-if="showRegistrationModal" @close="closeRegistrationModal" @registration-successful="handleRegistrationSuccess" />
      <AccountCreatedModal :showAccountCreatedModal="showAccountCreatedModal" @close-success-modal="closeSuccessModal" @sign-in="handleSignIn" />
      <LikeModal v-if="showLikesModal" :isAuthenticated="isAuthenticated" :likedItems="likedItems" @close="closeLikeModal"/>
      <LoginModal v-if="showLoginModal" @close="closeLoginModal" @login-successful="handleLoginSuccess" />
      <HotelPage />
      <BarPage />
    </div>
  </div>
</template>


<script>
export default {
    name: 'AboutPage'
}
</script>

<style scoped>


  .container-top-about {
    display: flex;
    justify-content: space-between;
    font-size: 18px;
    align-items: center;
    font-family: 'Russo One', sans-serif;
    padding: 20px 0;
    width: 100%;
  }

  .container-top-about h1 {
      font-size: 30px;
      margin: 0;
      padding: 0;
      align-items: flex-start;
  }

  .container-top-about a {
      color: white;
      text-decoration: none;
  }

  .container-top-about a:hover {
    color: #00AFEF;
    text-decoration: none;
  }

  .middle-container {
    text-align: center;
  }

  main {
    display: flex;
    align-content: center;
    text-align: center;
    justify-content: center;
    
  }
  
  .gv-circle {
    width: 180px;
    border: 5px solid black;
    border-radius: 220px;
  }
</style>