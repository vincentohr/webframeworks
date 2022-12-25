<template>
  <h1>Scooters in my neighbourhood</h1>
  <h2>New trip:</h2>
  <div id="table">
    <table>
      <tr>
        <th>
          Tag: {{this.tag}}
        </th>
      </tr>
      <tr>
        <th>
          Location: {{this.location}}
        </th>
      </tr>
      <tr>
        <th>
          Battery: {{this.battery}}%
        </th>
      </tr>
    </table>
  </div>
  <div id="map">
    <l-map
      v-model="zoom"
      v-model:zoom="zoom"
      :center="[52.371098, 4.897821]"
    >
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      ></l-tile-layer>
      <l-control-layers />
      <l-marker v-for="(scooter, index) in scooters" :key="index" @click="onSelect (scooter)"
                :lat-lng="[scooter.gpsLocation.slice(0, scooter.gpsLocation.indexOf(' ')), scooter.gpsLocation.slice(scooter.gpsLocation.indexOf(' ') + 1, scooter.gpsLocation.length)]">
<!--        <l-icon :icon-url="iconUrl" :icon-size="iconSize" />-->
      </l-marker>
    </l-map>
  </div>
</template>

<script>
import {
  LMap,
  // LIcon,
  LTileLayer,
  LMarker,
  LControlLayers,
  LTooltip,
  LPopup

} from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'
export default {
  name: 'Map45',
  inject: ['scootersService'],
  async created () {
    this.scooters = await this.scootersService.asyncFindAll()
  },
  components: {
    LMap,
    // LIcon,
    LTileLayer,
    LMarker,
    LControlLayers
  },
  data () {
    return {
      tag: '',
      battery: '',
      location: '',
      zoom: 6,
      iconWidth: 25,
      iconHeight: 40,
      scooters: []
    }
  },
  computed: {
    // iconUrl () {
    //   return `https://placekitten.com/${this.iconWidth}/${this.iconHeight}`
    // },
    iconSize () {
      return [this.iconWidth, this.iconHeight]
    }
  },
  methods: {
    onSelect (scooter) {
      this.tag = scooter.tag
      this.location = scooter.gpsLocation
      this.battery = scooter.batteryCharge
    }
  }
}
</script>

<style scoped>

*{
  color: black;
  z-index: -1;
}
div {
  display: inline;
  position: absolute;
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
