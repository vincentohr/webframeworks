<template>
  <div id="content">
    <h1 id="title">{{ title }}</h1>
    <h3 id="subtitle">{{ subtitle }}</h3>
    <div class="logo" id="logo">
      <img v-bind:src="logo">
    </div>
    <div class="logo" id="logo2">
      <img v-bind:src="logo2">
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      title: 'E-scooters Amsterdam!',
      subtitle: 'Get your kicks on Route 66!',
      logo: require('../assets/images/blue-scooter.png'),
      logo2: require('../assets/images/yellow-scooter.png')
    }
  }
}
</script>

<style scoped>

#content {
  height: 125pt;
  background-image: url("../assets/images/night-sky.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

#subtitle {
  position: relative;
  text-align: right;
  top: 7%;
  padding-right: 15%;
  font-size: 200%;
  margin: auto;
}

#title {
  position: relative;
  text-align: center;
  font-size: 400%;
  top: 20%;
}

#logo {
  position: relative;
  max-width: 50px;
  max-height: 170px;
  bottom: 107%;
}

#logo2 {
  position: relative;
  max-width: 50px;
  bottom: 210%;
  left: 85%;
}

</style>
