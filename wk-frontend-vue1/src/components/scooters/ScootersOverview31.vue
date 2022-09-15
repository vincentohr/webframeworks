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
    <tr v-for="scooter in scooters" :key="scooter">
      <td>t1</td>
      <td>t2</td>
      <td>t3</td>
      <td>t4</td>
      <td>t5</td>
      <td>t6</td>
    </tr>
  </table>
  <button id="addScootie" @click="onNewScooter">New Scooter</button>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  name: 'ScootersOverview31',
  created () {
    this.lastId = 30000
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
        Scooter.createSampleScooter(this.lastId)
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
      pId += 1
      this.scooters.push(
        Scooter.createSampleScooter(this.pId)
      )
    }
  }
}
</script>

<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
th{
  background-color: goldenrod;
  text-align: center;
}

td, th {
  border: 1px solid #dddddd;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
#addScootie {
  display: block;
  margin-left: auto;
  margin-right: 0;
}
</style>
