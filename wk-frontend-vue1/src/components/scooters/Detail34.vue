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
          <option selected v-for="state in ScooterStatus" :key="state">{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{ batteryChargeText }}</td>
        <td><input v-model="selectedScooter._batteryCharge" @change="enabled(selectedScooter._batteryCharge)"></td>
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
    <button class="button" :disabled="enabledValue === false" v-show="true" @click="onDelete()">Delete</button>
    <button class="button" v-show="true" @click="onClear()">Clear</button>
    <button class="button" v-show="true" @click="onReset()">Reset</button>
    <button class="button" v-show="true">Cancel</button>
    <button class="button" :disabled="enabledValue === false" v-show="true">Save</button>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  name: 'ScootersDetail32',
  props: ['selectedScooter', 'status'],
  emits: ['delete-scooter'],
  created () {
    Scooter.copyConstructor(this.selectedScooter)
    console.log(Scooter.copyConstructor(this.selectedScooter))
  },
  data () {
    return {
      tagText: 'Tag',
      statusText: 'Status',
      batteryChargeText: 'Battery Charge (%):',
      gpsLocationText: 'GPS Location:',
      mileageText: 'Total Mileage (km):',
      ScooterStatus: Scooter.Status,
      enabledValue: false
    }
  },
  watch: {
    selectedScooter (newValue, oldValue) {
      console.log(newValue)
      console.log(oldValue)
    }
  },
  methods: {
    onDelete () {
      this.$emit('delete-scooter')
    },
    onClear () {
      this.selectedScooter._id = ''
      this.selectedScooter._tag = ''
      this.selectedScooter._batteryCharge = ''
      this.selectedScooter._gpsLocation = ''
      this.selectedScooter._mileage = ''
    },
    onReset () {
      this.selectedScooter = this.copy
    },
    enabled (change) {
      if (this.test !== change) {
        console.log(change)
        console.log(this.test)
        this.enabledValue = true
        console.log(this.enabledValue)
      }
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

.button {
  display: inline;
  border-style: none;
  border-radius: 5px;
  /*background-color: red;*/
  /*color: white;*/
  padding: 10px;
  margin-right: 5%;
}

</style>
