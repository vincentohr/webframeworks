<template>
  <div id="content">
    <table>
      <tr>
        <th id="head">Scooter details (id = {{id}} )</th>
      </tr>
      <tr>
        <td>{{tagText}}</td>
        <td><input type="text" v-model="selectedScooter"></td>
      </tr>
      <tr>
        <td>{{statusText}}</td>
        <td><select v-model="status">
          <option selected v-for="state in statusArr" :key="state">{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{batteryChargeText}}</td>
        <td><input type="text" v-model="batteryCharge"></td>
      </tr>
      <tr>
        <td>{{gpsLocationText}}</td>
        <td><input type="text" v-model="gpsLocation"></td>
      </tr>
      <tr>
        <td>{{mileageText}}</td>
        <td><input type="text" v-model="mileage"></td>
      </tr>
    </table>
    <button id="deleteScooter">Delete</button>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'
export default {
  props: ['selectedScooter', 'status', 'batteryCharge', 'gpsLocation', 'mileage', 'id'],
  emits: ['delete-scooter'],
  data () {
    return {
      tagText: 'Tag',
      statusText: 'Status',
      batteryChargeText: 'Battery Charge (%):',
      gpsLocationText: 'GPS Location:',
      mileageText: 'Total Mileage (km):',
      statusArr: Scooter.statusArray
    }
  }
}
</script>

<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 30%;
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
  background-color: grey;
}

input{
  width: 95%;
}
#deleteScooter{
  display: block;
  margin-left: 25%;
  margin-right: auto;
}

</style>
