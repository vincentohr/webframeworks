<template>
  <h1>Scooters in my neighbourhood</h1>
  <h2>New trip:</h2>
  <div id="table">
    <table>
      <tr>
        <th>
          Tag:
        </th>
      </tr>
      <tr>
        <th>
          Location:
        </th>
      </tr>
      <tr>
        <th>
          Battery: %
        </th>
      </tr>
    </table>
  </div>
  <div id="map"></div>
</template>

<script>
import leaflet from 'leaflet'
import { onMounted } from 'vue'

export default {
  name: 'Map45',
  setup () {
    let map = null
    onMounted(() => {
      map = leaflet.map('map').setView([52.372405, 4.899631], 12)
      leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      }).addTo(map)
    })
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
