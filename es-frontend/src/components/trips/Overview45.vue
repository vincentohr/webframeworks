<template>
  <div id="content">
    <div id="table">
      <table>
        <tr>
          <th>
            Tag:
          </th>
        </tr>
        <tr v-for="(trip, index) in trips" :key="index" @click="onSelect (trip)">
          <td :class="{'activeTag': selectedTrip === trip}">{{ trip.tag }}</td>
        </tr>
      </table>
      <button id="button" @click="onNewScooter">New Trip</button>
    </div>
  </div>
  <div id="text">
    <h3 v-if="!this.isActive">Select a scooter from the list at the left!</h3>
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
  // Geen idee of dit werkt, niet zo lang aan gezeten.
  methods: {
    refresh () {
      window.location.reload()
    },
    onNewScooter (pId) {
      pId = this.lastId
      this.lastId++
      this.trips.push(
        this.tripsService.asyncSave(this.selectedTrip)
      )
      this.selectedTrip = this.scooters.at(this.scooters.length - 1)
    },
    onSelect (trip) {
      this.isActive = true
      if (trip !== null && trip !== this.selectedTrip) {
        this.$router.push(this.$route.matched[0].path + '/' + trip.id)
        this.selectedTrip = trip
      } else if (this.selectedTrip !== null) {
        this.$router.push(this.$route.matched[0].path + '/')
      }
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

div {
  display: inline;
  position: absolute;
}

#detail {
  margin-left: 60%;
  width: 200px;
}

#table {
  margin-left: 20px;
}

#text {
  position: absolute;
  margin-left: 40%
}

table {
  margin-top: 3%;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 500px;
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

h3 {
  color: black;
}

.activeTag {
  background-color: #11bd11;
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
</style>
