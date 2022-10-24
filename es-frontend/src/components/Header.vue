<template>
  <div id="content">
    <h1 id="title">{{ title }}</h1>
    <h3 id="subtitle">{{ subtitle }}</h3>
    <p> today is {{ datenow() }}</p>
    <div id="logo" class="logo">
      <img v-bind:src="logo">
    </div>
    <div id="logo2" class="logo">
      <img v-bind:src="logo2">
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      date: '',
      title: 'E-scooters Amsterdam!',
      subtitle: 'Get your kicks on Route 66!',
      logo: require('../assets/images/blue-scooter.png'),
      logo2: require('../assets/images/yellow-scooter.png')
    }
  },
  methods: {
    datenow () {
      const date = new Date()
      const year = date.getFullYear()
      let day = date.getDay()
      let month = date.getMonth() + 1
      const ndate = date.toLocaleDateString()
      const nday = ndate.substring(0, ndate.indexOf('-'))

      switch (day) {
        case 0:
          day = 'Sunday'
          break
        case 1:
          day = 'Monday'
          break
        case 2:
          day = 'Tuesday'
          break
        case 3:
          day = 'Wednesday'
          break
        case 4:
          day = 'Thursday'
          break
        case 5:
          day = 'Friday'
          break
        case 6:
          day = 'Saturday'
          break
      }

      switch (month) {
        case 1:
          month = 'January'
          break
        case 2:
          month = 'February'
          break
        case 3:
          month = 'March'
          break
        case 4:
          month = 'April'
          break
        case 5:
          month = 'May'
          break
        case 6:
          month = 'June'
          break
        case 7:
          month = 'July'
          break
        case 8:
          month = 'August'
          break
        case 9:
          month = 'September'
          break
        case 10:
          month = 'October'
          break
        case 11:
          month = 'November'
          break
        case 12:
          month = 'December'
          break
      }
      // eslint-disable-next-line no-return-assign
      return this.date = day + ', ' + nday + ' ' + month + ' ' + year
    }
  }
}
</script>

<style scoped>
p {
  position: absolute;
  top: 13%;
  margin-left: 20%;
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
  top: 7%;
  padding-right: 15%;
  font-size: 200%;
  margin-left: 8%;
  margin-right: 2%;
}

#title {
  position: relative;
  text-align: center;
  font-size: 375%;
  top: 20%;
}

#logo {
  position: relative;
  max-width: 50px;
  max-height: 170px;
  bottom: 123%;
  right: 0;
}

#logo2 {
  position: relative;
  max-width: 50px;
  bottom: 225%;
  left: 85%;
}

</style>
