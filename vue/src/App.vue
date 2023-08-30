<template>
  <div id="app">
   <router-view></router-view>
   
  </div>
  
</template>

<script>

export default {
  name: 'App',
  components: {
  
  }
}
</script>

<style>
 html {
      min-height: 100%;
    }

    body {
    margin: 0;
    background-image: linear-gradient(to bottom, #051937, #004D7A, #008793);
    background-repeat: no-repeat;
    background-attachment: fixed; /* Change this to fixed */
    font-family: 'Russo One', sans-serif;
    display: flex;
    flex-direction: column;
    min-height: 100vh; /* Ensure the body takes at least the viewport height */
  }
</style> 