<template>
  <h1>Scooters in my neighbourhood</h1>
  <h2>New trip:</h2>
  <div id="table">
    <table>
      <tr>
        <th>
          Tag: {{ this.tag }}
        </th>
      </tr>
      <tr>
        <th>
          Location: {{ this.location }}
        </th>
      </tr>
      <tr>
        <th>
          Battery: {{ this.battery }}%
        </th>
      </tr>
    </table>
    <button id="newTrip" @click="newTrip">Start Trip</button>
  </div>
  <div id="map">
    <l-map
      v-model="zoom"
      v-model:zoom="zoom"
      :center="[52.371098, 4.897821]"
    >
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        min-zoom="1"
        max-zoom="19"
      ></l-tile-layer>
      <l-control-layers/>
      <l-marker v-for="(scooter, index) in scooters" :key="index" @click="onSelect (scooter)"
                :lat-lng="[scooter.gpsLocation.slice(0, scooter.gpsLocation.indexOf(' ')), scooter.gpsLocation.slice(scooter.gpsLocation.indexOf(' ') + 1, scooter.gpsLocation.length)]">
        <l-icon v-if="scooter.batteryCharge > 60" :icon-url="greenScooter" :icon-size="iconSize" />
        <l-icon v-if="scooter.batteryCharge >= 20 && scooter.batteryCharge <= 60" :icon-url="orangeScooter" :icon-size="iconSize" />
        <l-icon v-if="scooter.batteryCharge < 20" :icon-url="redScooter" :icon-size="iconSize" />
      </l-marker>
            <l-circle-marker v-if="selectedscooter != null" :radius="20"
                             :lat-lng="
                             [this.selectedscooter.gpsLocation.slice(0, this.selectedscooter.gpsLocation.indexOf(' ')),
                              this.selectedscooter.gpsLocation.slice(this.selectedscooter.gpsLocation.indexOf(' ') + 1,
                               this.selectedscooter.gpsLocation.length)]">
            </l-circle-marker>
    </l-map>
  </div>
</template>

<script>
import {
  LMap,
  LIcon,
  LTileLayer,
  LMarker,
  LControlLayers,
  LCircleMarker,
  LTooltip,
  LPopup

} from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'

export default {
  name: 'Map45',
  inject: ['tripsService', 'scootersService'],
  async created () {
    this.scooters = await this.scootersService.asyncFindAllIdleScooters()
  },
  components: {
    LMap,
    LIcon,
    LTileLayer,
    LCircleMarker,
    LMarker,
    LControlLayers
  },
  data () {
    return {
      tag: '',
      battery: '',
      location: '',
      zoom: 13,
      iconWidth: 40,
      iconHeight: 40,
      scooters: [],
      selectedscooter: null,
      lat: 0,
      long: 0,
      test: ''
    }
  },
  computed: {
    greenScooter () {
      return `https://cdn-icons-png.flaticon.com/512/1236/1236909.png?w=1380&t=st=1672328328~exp=1672328928~hmac=03689705bb38e4de8c61bac320d23a191c33ee58e97078317715e9a9bad015bb${this.iconWidth}/${this.iconHeight}`
    },
    orangeScooter () {
      return `https://cdn-icons-png.flaticon.com/512/1198/1198152.png?w=1380&t=st=1672328385~exp=1672328985~hmac=84a5adece7a039faad50815d256134b3d7e28bb386a2e0dead333307aaea83dd${this.iconWidth}/${this.iconHeight}`
    },
    redScooter () {
      return `https://cdn-icons-png.flaticon.com/512/1227/1227962.png?w=1380&t=st=1672328902~exp=1672329502~hmac=6212a6a8f8d9684ca5e63167e560fa764c218f31ac84042f6c2be4320be3c046${this.iconWidth}/${this.iconHeight}`
    },
    iconSize () {
      return [this.iconWidth, this.iconHeight]
    }
  },
  methods: {
    onSelect (scooter) {
      this.selectedscooter = scooter
      this.lat = parseFloat(this.selectedscooter.gpsLocation.slice(0, this.selectedscooter.gpsLocation.indexOf(' ')))
      this.long = parseFloat(this.selectedscooter.gpsLocation.slice(this.selectedscooter.gpsLocation.indexOf(' ') + 1, this.selectedscooter.gpsLocation.length))
      this.tag = scooter.tag
      this.location = scooter.gpsLocation
      this.battery = scooter.batteryCharge
      console.log(scooter.gpsLocation.slice(0, scooter.gpsLocation.indexOf(' ')))
      console.log(scooter.gpsLocation.slice(scooter.gpsLocation.indexOf(' ') + 1, scooter.gpsLocation.length))
    },
    newTrip () {
      if (this.selectedscooter != null) {
        // Adding new trip
        this.tripsService.asyncAddTrip(this.selectedscooter.id, 0)
        // this.selectedscooter.status = 'INUSE'
        // this.scootersService.asyncSave(this.selectedscooter)
        // this.tripsService.asyncSave(12)
        // window.location.reload()
      }
    }
  }
}
</script>

<style scoped>

* {
  color: black;
  z-index: -1;
}

div {
  display: inline;
  position: absolute;
}

#newTrip {
  color: red;
  background-color: #f8a73f;
  border: none;
}

#map {
  width: 80%;
  height: 59%;
  margin-left: 20%;
}

th {
  text-align: center;
}

td, th {
  border: 1px solid #000000;
  padding: 8px;
}

h3 {
  color: black;
}
</style>
