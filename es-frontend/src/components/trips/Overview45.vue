<template>
  <h3>full history of my trips:</h3>
  <p>(click an active trip to finish the ride)</p>
  <div id="content">
    <div id="table">
      <table>
        <tr>
          <th>Trip Id:</th>
          <th>Scooter Tag:</th>
          <th>Start Date</th>
          <th>Running:</th>
          <th>Start Location:</th>
          <th>End Location:</th>
          <th>Mileage:</th>
        </tr>
        <tr v-for="(trip, index) in trips" :key="index" @click="onSelect (trip)">
          <td class="placeholder">{{ trip.id }}</td>
          <td class="placeholder">{{ trip.scooter.tag }}</td>
          <td class="placeholder">{{ trip.startDateTime }}</td>
          <td class="placeholder">{{ trip.active }}</td>
          <td class="placeholder">{{ trip.startPosition }}</td>
          <td class="placeholder">{{ trip.endPosition }}</td>
          <td :class="{'finishRun': trip.mileage === 0}">{{ trip.mileage }}</td>
        </tr>
      </table>
    </div>
  </div>
  <div id="detail">
    <router-view :selected-scooter="selectedTrip" @delete-scooter="remove()"
                 @deselect-scooter="deselect()" @refresh="refresh()"></router-view>
  </div>
</template>

<script>
export default {
  name: 'Overview45',
  inject: ['tripsService'],
  async created () {
    this.trips = await this.tripsService.asyncFindAll()
  },
  data () {
    return {
      pId: this.lastId,
      trips: [],
      selectedTrip: null,
      isActive: false
    }
  },
  methods: {
    refresh () {
      window.location.reload()
    },
    onSelect (trip) {
      this.isActive = true
      // if (trip !== null && trip !== this.selectedTrip) {
      //   this.$router.push(this.$route.matched[0].path + '/' + trip.id)
      //   this.selectedTrip = trip
      // } else if (this.selectedTrip !== null) {
      //   this.$router.push(this.$route.matched[0].path + '/')
      // }
      // if (this.selectedScooter !== scooter) {
      //   // this.$router.push(this.$route.matched[0].path + '/' + scooter._id)
      //   this.selectedScooter = scooter
      // } else {
      //   this.selectedScooter = null
      //   this.isActive = false
      // }
    },
    remove () {
      // console.log(this.selectedScooter.id - 30000)
      // this.scooters.splice(this.selectedScooter.id - 30000, 1)
      // this.scooters.filter(scooter => scooter.id === (this.selectedScooter.id - 30000))
      // this.selectedScooter = null
      // this.isActive = false
    },
    deselect () {
      // this.selectedScooter = null
      // this.isActive = false
    },
    findSelectedFromRouteParams (route) {
      // const id = route.params.id
      // const scooter = this.scooters.find(scooter => scooter._id === id)
      // if (scooter !== undefined) {
      //   return scooter
      // } else {
      //   return null
      // }
    }
  },
  watch: {
    // Updates the selected scooter for every change in the path
    // '$route' () {
    //   this.selectedScooter = this.findSelectedFromRouteParams(this.$route)
    // }
  }
}
</script>

<style scoped>

h3, p{
  margin-left: 5%;
  color:black
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left: 5%;
}

th {
  background-color: #11bd11;
  text-align: center;
}

td, th {
  border: 1px solid #dddddd;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: grey;
}

tr:nth-child(odd) {
  background-color: darkgray;
}

#button {
  display: block;
  margin-left: auto;
  border-style: none;
  border-radius: 5px;
  background-color: #11bd11;
  color: white;
  padding: 10px;
}
.finishRun {
  content-visibility: hidden;
}

</style>
