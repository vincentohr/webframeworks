<template>
  <div v-if="selectedScooter !== null">
    <table>
      <tr>
        <th>Scooter id = {{ selectedScooter._id }}</th>
      </tr>
      <tr>
        <td>{{ tagText }}</td>
        <td><input v-model="selectedScooter._tag" type="text" @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ statusText }}</td>
        <td><select v-bind:value="status">
          <option v-for="state in ScooterStatus " :key="state" selected>{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{ batteryChargeText }}</td>
        <td><input v-model="selectedScooter._batteryCharge" type="number"
                   @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ gpsLocationText }}</td>
        <td><input v-model="selectedScooter._gpsLocation" @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ mileageText }}</td>
        <td><input v-model="selectedScooter._mileage" type="number" @change="enabled()"></td>
      </tr>
    </table>
    <button v-show="true" :disabled="enabledValue === true" class="button" @click="onDelete()">Delete</button>
    <button v-show="true" class="button" @click="onClear()">Clear</button>
    <button v-show="true" :disabled="enabledValue === false" class="button" @click="onReset()">Reset</button>
    <button v-show="true" class="button" @click="onCancel()">Cancel</button>
    <button v-show="true" :disabled="enabledValue === false" class="button" @click="onSave()">Save</button>
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
      copy: '',
      saved: false
    }
  },
  watch: {
    selectedScooter () {
      this.copy = Scooter.copyConstructor(this.selectedScooter)
      this.enabledValue = false
    }
  },
  methods: {
    onDelete () {
      this.$emit('delete-scooter')
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
    },
    onReset () {
      this.selectedScooter._tag = this.copy._tag
      this.selectedScooter._batteryCharge = this.copy._batteryCharge
      this.selectedScooter._gpsLocation = this.copy._gpsLocation
      this.selectedScooter._mileage = this.copy._mileage
      this.enabledValue = false
    },
    onCancel () {
      if (this.saved === false) {
        this.onReset()
      }
      this.$emit('deselect-scooter')
      this.$router.push(this.$route.matched[0].path)
    },
    onSave () {
      this.saved = true
      this.onCancel()
    },
    enabled () {
      this.enabledValue = true
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
