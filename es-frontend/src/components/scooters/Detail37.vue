<template>
  <div v-if="selectedScooter !== null">
    <table>
      <tr>
        <th>Scooter id = {{ selectedScooter.id }}</th>
      </tr>
      <tr>
        <td>{{ tagText }}</td>
        <td><input v-model="selectedScooter.tag" type="text" @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ statusText }}</td>
        <td><select v-bind:value="status">
          <option v-for="state in ScooterStatus " :key="state" selected>{{ state }}</option>
        </select></td>
      </tr>
      <tr>
        <td>{{ batteryChargeText }}</td>
        <td><input v-model="selectedScooter.batteryCharge" type="number"
                   @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ gpsLocationText }}</td>
        <td><input v-model="selectedScooter.gpsLocation" @change="enabled()"></td>
      </tr>
      <tr>
        <td>{{ mileageText }}</td>
        <td><input v-model="selectedScooter.mileage" type="number" @change="enabled()"></td>
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
  name: 'ScootersDetail37',
  props: ['selectedScooter', 'status'],
  emits: ['delete-scooter', 'deselect-scooter'],
  inject: ['scootersService'],
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
    async onDelete () {
      if (confirm(`are you sure to delete\n ${this.selectedScooter.type} book` +
        `(id=${this.selectedScooter.id})?`)) {
        await this.scootersService.asyncDeleteById(this.selectedScooter.id)
        this.copy = null
        this.$emit('refresh')
      }
    },
    onClear () {
      if (this.saved === false) {
        this.onReset()
      }
      this.selectedScooter.tag = ''
      this.selectedScooter.batteryCharge = ''
      this.selectedScooter.gpsLocation = ''
      this.selectedScooter.mileage = ''
      this.enabledValue = true
    },
    onReset () {
      this.selectedScooter.tag = this.copy.tag
      this.selectedScooter.batteryCharge = this.copy.batteryCharge
      this.selectedScooter.gpsLocation = this.copy.gpsLocation
      this.selectedScooter.mileage = this.copy.mileage
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
      // todo ff checken
      this.scootersService.asyncSave(this.selectedScooter)
      this.saved = true
      this.onCancel()
      this.$emit('refresh')
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
