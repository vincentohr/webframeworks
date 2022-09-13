<template>
  <div id="content">
    <h1 id="title">{{ header }}</h1>
    <h3 id="subtitle">{{ subheader }}</h3>
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
      header: 'E-scooters Amsterdam!',
      subheader: 'Get your kicks on Route 66!',
      logo: require('../assets/images/blue-scooter.png'),
      logo2: require('../assets/images/yellow-scooter.png')
    }
  }
}
</script>

<style scoped>

* {
  color: white;
  border-color: aliceblue;
  margin: auto;
}

#content {
  height: 125pt;
  background-image: url("../assets/images/night-sky.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

#subtitle {
  position: relative;
  text-align: right;
  top: 25%;
  padding-right: 20%;
  font-size: 200%;
  margin: auto;
}

#title {
  position: relative;
  text-align: center;
  font-size: 400%;
  top: 20%;
}

.logo {
  position: absolute;
  top: -2%;
  margin-top: 19px
}

#logo2 {
  right: 5px;
  margin-left: 2em;
}

</style>
