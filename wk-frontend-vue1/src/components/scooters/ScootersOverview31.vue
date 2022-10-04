<template>
  <h2>All Scooters:</h2>
  <table>
    <tr>
      <th>Id:</th>
      <th>Tag:</th>
      <th>Status:</th>
      <th>Battery Charge:</th>
      <th>GPSLocation:</th>
      <th>Mileage:</th>
    </tr>
    <tr v-for="scooter in scooters" :key="scooter._id">
      <td>{{scooter._id}}</td>
      <td>{{scooter._tag}}</td>
      <td>{{scooter._status}}</td>
      <td>{{scooter._batteryCharge}}</td>
      <td>{{scooter._gpsLocation}}</td>
      <td>{{scooter._mileage}}</td>
    </tr>
  </table>
<!--  <Overview32 :scooters="scooters" ></Overview32>-->
<!--  <Overview32 v-if="visible"></Overview32>-->
  <button id="addScootie" @click="onNewScooter"><b>New Scooter</b></button>
</template>

<script>
import { Scooter } from '@/models/scooter'
import Overview32 from '@/components/scooters/Overview32'

export default {
  name: 'ScootersOverview31',
  // components: { Overview32 },
  created () {
    this.lastId = 30_000
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
        Scooter.createSampleScooter(this.lastId++)
      )
    }
  },
  data () {
    return {
      pId: this.lastId,
      scooters: []
    }
  },
  methods: {
    onNewScooter (pId) {
      pId = this.lastId
      this.lastId++
      this.scooters.push(
        Scooter.createSampleScooter(pId)
      )
    }
  }
}
</script>

<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left: 5%;
}
th{
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
#addScootie {
  display: block;
  margin-left: auto;
  margin-right: 5%;
  border-style: none;
  border-radius: 5px;
  background-color: #11bd11;
  color: white;
  padding: 10px;
}
</style>
