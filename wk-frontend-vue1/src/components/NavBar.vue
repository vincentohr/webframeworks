<template>
  <div class="navbar">
    <a href="#">Home</a>
    <div class="dropdown">
      <button class="scooters">Scooters
      </button>
      <div class="scooters-content">
        <a href="#">Scooters overview</a>
        <a href="#">Scooters details</a>
        <a href="#">Available Scooters</a>
        <a href="#">Abandonned Scooters</a>
      </div>
    </div>
    <a href="#mytrips">My Trips</a>
    <a href="#myaccount">My account</a>
    <a href="#login" class="right">Log in</a>
    <a href="#signup" class="right">Sign up</a>
  </div>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<style scoped>

.navbar {
  overflow: hidden;
  background-color: gray;
  width: available;
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
background-color: darkgray;
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
  background-color: darkgray;
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
  background-color: gray;
}

.dropdown:hover .scooters-content {
  display: block;
}
</style>
