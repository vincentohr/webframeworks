<template>
  <div v-if="selectedScooter !== null">
    <table>
      <tr>
        <th>Scooter id = {{ selectedScooter._id }}</th>
      </tr>
      <tr>
        <td>{{ tagText }}</td>
        <td><input v-model="selectedScooter._tag"></td>
      </tr>
      <tr>
        <td>{{ statusText }}</td>
        <td><select v-model="status">
          <option v-for="state in ScooterStatus" :key="state" selected>{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{ batteryChargeText }}</td>
        <td><input v-model="selectedScooter._batteryCharge"></td>
      </tr>
      <tr>
        <td>{{ gpsLocationText }}</td>
        <td><input v-model="selectedScooter._gpsLocation"></td>
      </tr>
      <tr>
        <td>{{ mileageText }}</td>
        <td><input v-model="selectedScooter._mileage"></td>
      </tr>
    </table>
    <button v-show="true" id="deleteScooter" @click="onDelete()">Delete</button>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  name: 'ScootersDetail32',
  props: ['selectedScooter', 'status'],
  emits: ['delete-scooter'],
  data () {
    return {
      tagText: 'Tag',
      statusText: 'Status',
      batteryChargeText: 'Battery Charge (%):',
      gpsLocationText: 'GPS Location:',
      mileageText: 'Total Mileage (km):',
      ScooterStatus: Scooter.Status
    }
  },
  methods: {
    onDelete () {
      this.$emit('delete-scooter')
    }
  }
}
</script>

<style scoped>
table {
  margin-top: 2%;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 700px;
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

input {
  width: 95%;
}

#deleteScooter {
  display: block;
  border-style: none;
  border-radius: 5px;
  background-color: red;
  color: white;
  padding: 10px;
  margin-right: auto;
}

</style>
