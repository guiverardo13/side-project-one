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
      <div class="top-middle">
        <h1 class="page-title">Guilherme Verardo</h1>
        <p class="description">A little bit about me</p>
      </div>
      <div class="middle-middle-pic">
        <img class="gv-circle" src="../assets/circle.png" alt="circle">
      </div>
        <div class="middle-middle-p">
          <p>I am a multilingual professional from Brazil with a diverse background in basketball, team leadership, customer service, and now Java is my newest language. I have developed strong communication and interpersonal skills that have been invaluable throughout my career. As a dedicated basketball player since the age of 12, I honed my leadership abilities and thrived in a team-oriented environment where collective success was key. This led to my appointment as captain, enabling me to excel in guiding and motivating my teammates towards achieving our shared goals. Additionally, my exceptional performance earned me the privilege of representing Brazil on four occasions at the national level, which ultimately led to a full scholarship at Slippery Rock University, where I earned a Bachelor's Degree in Business Management.

            With a solid foundation in customer service and problem-solving, I have cultivated a keen ability to understand and address the needs of others. Having most recently served as a Sales Consultant at Mercedes Benz, I have consistently excelled in identifying solutions and exceeding customer expectations. However, it was during this experience that I rediscovered my passion for technology, as the intricacies of understanding how things work reignited my curiosity. Coding, much like playing basketball, fuels my drive for challenges and achieving victories. Each successful problem solved in programming feels akin to making a game-winning shot at the buzzer.
            Looking ahead, my goal after graduating from Tech Elevator is to secure a Junior Software Developer role. I am eager to find a dynamic team where I can contribute my skills and collaborate on tackling complex problems that stimulate my growth. With my strong foundation in leadership, problem-solving, and a genuine enthusiasm for technology, I am poised to thrive in a role that combines my passion for coding with my drive to excel in a collaborative team environment.</p>

            <h2>Full Stack Development</h2>

            <div class="icons-top">
              <img class="java" src="../assets/java.png" alt="java" title="Java">
              <img class="c-sharp" src="../assets/csharp.png" alt="c" title="C-Sharp">
              <img src="../assets/springboot.png" alt="boot" class="spring-boot" title="Spring Boot">
              <img src="../assets/aspnet-featured.png" alt="aspen" class="net" title="ASP.NET Web API">
              <img src="../assets/Postgresql_elephant.svg.png" alt="post" class="postgres" title="PostgreSQL">
              <img src="../assets/sqlserver.png" alt="sql" class="sqlserver" title="MS SQL Server">
            </div>
            <div class="icons-middle">
              <img src="../assets/postman.png" alt="post" class="postman" title="Postman">
              <img src="../assets/html.png" alt="html" class="ht" title="HTML" >
              <img src="../assets/css.png" alt="css" class="cs" title="CSS">
              <img src="../assets/js.png" alt="jvs" class="js" title="JavaScript">
              <img src="../assets/Vue.js_Logo_2.svg.png" alt="vue" class="vuejs" title="Vue.js">
            </div>
            <div class="icons-bottom">
              <img src="../assets/git.png" alt="gt" class="git" title="Git">
              <img src="../assets/intelliJ.png" alt="ilj" class="intellij" title="IntelliJ">
              <img src="../assets/vscode.png" alt="vsc" class="vcode" title="Visual Studio Code">
              <img src="../assets/Visual-Studio-logo.png" alt="vstud" class="vstudio" title="Vistual Studio">
            </div>
          </div>


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
          <a href="https://drive.google.com/file/d/1UID0Fpt7Rl_OvHpJsiDGTLcyHefAYuw-/view" target="_blank" class="resume"><i class="fa-regular fa-file-pdf" style="color: #ffffff;"></i></a>
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

  .icons-top,
  .icons-middle,
  .icons-bottom {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    gap: 20px;
  }

  /* Add the following CSS for image hover effect */
  .icons-top img,
  .icons-middle img,
  .icons-bottom img {
    width: 70px;
    transition: transform 0.3s ease; /* Add a smooth transition effect */
  }

  /* Define the hover effect for the images */
  .icons-top img:hover,
  .icons-middle img:hover,
  .icons-bottom img:hover {
    transform: translateY(-10px); /* Elevate the image slightly on hover */
  }

  .icons-top {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    gap: 20px;
  }

  .icons-middle {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    gap: 30px;
  }

  .icons-bottom {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 45px;
    gap: 25px;
  }

  .java {
    width: 70px;
  }

  .c-sharp {
    width: 75px;
    height: 88px;
  }

  .spring-boot {
    width: 80px;
  }

  .net {
    width: 120px;
  }

  .postgres {
    width: 80px;
  }

  .sqlserver {
    width: 80px;
  }

  .postman {
    width: 70px;
  }

  .ht, .cs, .js {
    width: 60px;
  }

  .vuejs {
    width: 80px;
  }

  .git {
    width: 70px;
  }

  .intellij {
    width: 70px;
  }

  .vcode {
    width: 70px;
  }

  .vstudio {
    width: 70px;
  }


  
  .middle-middle-pic {
    margin-top: 50px;
    margin-bottom: 50px;
  }

  .middle-middle-p p {
    text-align: center;
    margin-bottom: 50px;
  }

  .middle-middle-p h2 {
    font-size: 45px;
    margin-bottom: 45px;
  }

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

  .page-title {
    margin-top: 130px;
    font-size: 50px;
  }

  .description {
    font-size: 25px;
  }
</style>