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
      height: 100%;
      background-image: linear-gradient(to bottom, #051937, #004D7A, #008793 );
      background-repeat: no-repeat;
      background-attachment: scroll;
      font-family: 'Russo One', sans-serif;
      margin: 0;
      display: flex;
      flex-direction: column;
    }
</style> 