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
        <router-link to="/overview31">All Scooters Overview</router-link>
        <router-link to="/overview32">Scooters edit (comp)</router-link>
        <router-link to="/scooters/overview33">Scooters edit (router)</router-link>
        <router-link to="/scooters/overview34">Scooters edit (managed)</router-link>
        <router-link to="/scooters/overview37">Scooters edit (FETCH)</router-link>
        <router-link to="/">Abandoned Scooters</router-link>
      </div>
    </div>
    <div class="dropdown">
      <div class="scooters">Trips
        <div class="scooters-content">
          <router-link to="/trips/overview45">Trips (Overview45)</router-link>
          <router-link to="/trips/overview45">History</router-link>
        </div>
      </div>
    </div>
    <!--    <a href="#mytrips">My Trips</a>-->
    <!--    <a href="#myaccount">My account</a>-->
    <!--    <a href="#login" class="right">Log in</a>-->
    <!--    <a href="#signup" class="right">Sign up</a>-->
    <router-link v-if="!loggedIn" class="right" @click="signIn" to="/sign-in">Log in</router-link>
    <a v-if="loggedIn" id="signOut" class="right" @click.prevent="signOut">Log out</a>
    <router-link class="right" v-if="!loggedIn" to="/sign-up">Sign up</router-link>
  </div>
</template>

<script>
export default {
  inject: ['sessionService'],
  created () {
    this.sessionService.isAuthenticated().then((result) => {
      this.data = result
      console.log('NavBarSb: ' + this.data)
      if (this.data === true) {
        this.loggedIn = this.data
        console.log('NavBarSb: ' + this.loggedIn)
      }
    }).catch((error) => {
      console.log(error)
    })
  },
  data () {
    return {
      name: 'NavBar',
      loggedIn: false
    }
  },
  methods: {
    signOut () {
      this.$router.push({ path: '/sign-in', query: { signOut: true } })
      this.loggedIn = false
    },
    signIn () {
      this.$router.push('/sign-in')
      this.loggedIn = true
    }
  }
}
</script>

<style scoped>
#signOut{
  background-color: gray;
  color: white;
  padding: 15px;
  text-align: center;
}

#signOut:hover{
  border-color: #f1a80a;
  background-color: #b68c01;
}
.navbar {
  overflow: hidden;
  background-color: gray;
}

.navbar .right {
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
