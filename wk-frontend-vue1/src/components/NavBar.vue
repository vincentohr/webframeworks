<template>
  <div class="navbar">
<!--    <a href="#">Home</a>-->
    <router-link to="/home">Home</router-link>
    <router-link to="/overview31">Scooter Overview</router-link>
    <router-link to="/overview32">Scooter Details</router-link>

    <div class="dropdown">
      <button class="scooters">Scooters
      </button>
      <div class="scooters-content">
        <a v-for="(subject, index) in scooterssubjects" v-bind:key="index" href="#">{{ subject }}</a>
      </div>
    </div>
<!--    <a href="#mytrips">My Trips</a>-->
<!--    <a href="#myaccount">My account</a>-->
<!--    <a href="#login" class="right">Log in</a>-->
<!--    <a href="#signup" class="right">Sign up</a>-->
    <router-link class="right" to="/sign-up">Sign up</router-link>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: 'NavBar',
      scooterssubjects: [
        'Scooters overview',
        'Scooters details',
        'Available scooters',
        'Abandonned scooters'
      ]
    }
  }
}
</script>

<style scoped>
.navbar {
  overflow: hidden;
  background-color: gray;
}
.navbar .right{
  float: right;
}
.navbar a {
  float: left;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

.navbar a:hover {
  color: #ffffff;
  border-color: #f1a80a;
  background-color: #b68c01;
}

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .scooters {
  font-size: 16px;
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.dropdown:hover {
  color: #ffffff;
  border-color: #f1a80a;
  background-color: #a27e02;
}

.scooters-content {
  display: none;
  position: absolute;
  background-color: darkgray;
  min-width: 160px;
}

.scooters-content a {
  float: none;
  color: white;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.scooters-content a:hover {
  color: #ffffff;
  border-color: #f1a80a;
  background-color: #a27e02;
}

.dropdown:hover .scooters-content {
  display: block;
}

a.active {
  color: #ffffff;
  border-color: #f1a80a;
  background-color: #ce9e02;
}
</style>
