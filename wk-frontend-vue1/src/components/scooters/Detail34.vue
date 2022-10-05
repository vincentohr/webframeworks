<template>
  <div v-if="selectedScooter !== null">
    <table>
      <tr>
        <th>Scooter id = {{ selectedScooter._id }}</th>
      </tr>
      <tr>
        <td>{{ tagText }}</td>
        <td><input type="text" v-model="selectedScooter._tag" @change="enabled(selectedScooter._tag)"></td>
      </tr>
      <tr>
        <td>{{ statusText }}</td>
        <td><select v-model="status">
          <option selected v-for="state in ScooterStatus " :key="state">{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{ batteryChargeText }}</td>
        <td><input type="number" v-model="selectedScooter._batteryCharge" @change="enabled(selectedScooter._batteryCharge)"></td>
      </tr>
      <tr>
        <td>{{ gpsLocationText }}</td>
        <td><input v-model="selectedScooter._gpsLocation" @change="enabled(selectedScooter._gpsLocation)"></td>
      </tr>
      <tr>
        <td>{{ mileageText }}</td>
        <td><input type="number" v-model="selectedScooter._mileage" @change="enabled(selectedScooter._mileage)"></td>
      </tr>
    </table>
    <button class="button" :disabled="enabledValuedelete === false" v-show="true" @click="onDelete()">Delete</button>
    <button class="button" v-show="true" @click="onClear()">Clear</button>
    <button class="button" :disabled="enabledValue === false" v-show="true" @click="onReset()">Reset</button>
    <button class="button" v-show="true" @click="onCancel()">Cancel</button>
    <button class="button" :disabled="enabledValue === false" v-show="true" @click="onSave()">Save</button>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  name: 'ScootersDetail32',
  props: ['selectedScooter', 'status'],
  emits: ['delete-scooter', 'deselect-scooter'],
  created () {
    this.copy = Scooter.copyConstructor(this.selectedScooter)
    // console.log(this.copy)
  },
  data () {
    return {
      tagText: 'Tag',
      statusText: 'Status',
      batteryChargeText: 'Battery Charge (%):',
      gpsLocationText: 'GPS Location:',
      mileageText: 'Total Mileage (km):',
      ScooterStatus: Scooter.Status,
      enabledValue: false,
      enabledValuedelete: true,
      copy: '',
      saved: false
    }
  },
  watch: {
    selectedScooter () {
      this.copy = Scooter.copyConstructor(this.selectedScooter)
      this.enabledValue = false
      this.enabledValuedelete = true
      console.log(this.copy)
      this.temp = this.copy
    }
  },
  methods: {
    onDelete () {
      this.$emit('delete-scooter')
      this.enabledValuedelete = false
    },
    onClear () {
      if (this.saved === false) {
        this.onReset()
      }
      this.selectedScooter._tag = ''
      this.selectedScooter._batteryCharge = ''
      this.selectedScooter._gpsLocation = ''
      this.selectedScooter._mileage = ''
      this.enabledValue = true
      this.enabledValuedelete = false
    },
    onReset () {
      this.selectedScooter._tag = this.copy._tag
      this.selectedScooter._batteryCharge = this.copy._batteryCharge
      this.selectedScooter._gpsLocation = this.copy._gpsLocation
      this.selectedScooter._mileage = this.copy._mileage
      this.enabledValue = false
      this.enabledValuedelete = false
    },
    onCancel () {
      if (this.saved === false) {
        this.onReset()
      }
      this.enabledValuedelete = false
      this.$emit('deselect-scooter')
      this.$router.push(this.$route.matched[0].path)
    },
    onSave () {
      this.saved = true
      this.onCancel()
      this.enabledValuedelete = false
    },
    enabled (change) {
      if (this.test !== change) {
        console.log(change)
        console.log(this.test)
        this.enabledValue = true
        this.enabledValuedelete = false
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
