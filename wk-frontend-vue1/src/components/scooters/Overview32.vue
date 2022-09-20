<template>
  <div id="content">
    <table>
      <tr>
        <th>
          Tag:
        </th>
      </tr>
      <tr v-for="(tag, index) in tags" :key="index" @click="selected (index)">
        <td>{{tag}}</td>
      </tr>
    </table>
    <button @click="newTag">New Scooter</button>
  </div>
  <p>{{text}}</p>
  <Detail32
    v-model:selected-scooter="selectedScooter"
    v-model:status="status"
    v-model:battery-charge="batteryCharge"
    v-model:gps-location="gpsLocation"
    v-model:mileage="mileage"
    v-model:id="id"
  >
  </Detail32>
</template>

<script>
import Detail32 from '@/components/scooters/Detail32'
import { Scooter } from '@/models/scooter'

export default {
  components: { Detail32 },
  data () {
    return {
      id: '',
      number: '',
      count: '',
      text: 'Select a scooter',
      selectedScooter: null,
      scooter: '',
      tags: [],
      status: '',
      batteryCharge: '',
      gpsLocation: '',
      mileage: ''
    }
  },
  methods: {
    selected (index) {
      this.count++
      this.text = ''
      if (index === this.number && this.count >= 2) {
        this.selectedScooter = null
        this.count = 0
        this.text = 'Select a scooter'
      }
      this.selectedScooter = this.tags.at(index)
      this.number = index
      this.status = Scooter.createSampleScooter(index).status
      this.batteryCharge = Scooter.createSampleScooter(index).batteryCharge
      this.gpsLocation = Scooter.createSampleScooter(index).gpsLocation
      this.mileage = Scooter.createSampleScooter(index).mileage
      this.id = Scooter.createSampleScooter(index).id
    },
    newTag (pId) {
      pId = this.lastId
      this.lastId++
      this.tags.push(
        Scooter.createSampleScooter(pId).tag
      )
    }
  }
}
</script>

<style scoped>

table {
  margin-top: 3%;
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
</style>
