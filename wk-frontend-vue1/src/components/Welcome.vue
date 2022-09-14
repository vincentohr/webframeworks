<template>
  <div class="columns">
    <div class="content" id="content-1">
      <h1>{{ pickabrand }}</h1>
      <select name="brand">
        <option value="Lime-S">Lime-S</option>
      </select>
      <h1>{{ availability }}</h1>
      <ul id="list">
        <li v-for="(place, index) in places" v-bind:key="index">{{ place }}: {{placeholdNumbers()}}</li>
      </ul>
    </div>
    <div class="content" id="content-2">
      <h1>{{ photo_galery }}</h1>
      <div>
        <img v-for="(image, index) in images" v-bind:src="image" v-bind:key="index">
      </div>
    </div>
    <div class="content" id="content-3">
      <h1>{{ tours }}</h1>
      <p>{{ tour_info }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      places: [
        'Leidseplein',
        'Rembrandtplein',
        'Centraal Station'
      ],
      images: [
        require('../assets/images/motorgang.jpg'), require('../assets/images/gold-car.jpg'),
        require('../assets/images/frontside-black-car.jpg'), require('../assets/images/green-electric-scooter.jpg'),
        require('../assets/images/grey-scooter.jpg'), require('../assets/images/black-scooter.jpg'),
        require('../assets/images/red-scooter.jpg'), require('../assets/images/darkblue-scooter.jpg'),
        require('../assets/images/orange-scooter.jpg')
      ],
      pickabrand: 'Pick a brand:',
      availability: 'Availability:',
      photo_galery: 'Photo gallery:',
      tours: 'City tours:',
      tour_info: 'Enjoy one of our city tours with your E-scooter. Go shopping in the nine streets, visit the ' +
        'Albert Cuyp market or the museum square. It is also allowed to take a moped onto the sub-way.'
    }
  },
  methods: {
    placeholdNumbers () {
      return Math.round(Math.random() * 5)
    }
  }
}
</script>

<style scoped>
.content {
  background-color: grey;
  padding: 15px;
  margin-top: 10px;
  min-height: 100%;
}

#content-2 {
  margin: 10px;
}

#content-3 {
  float: right;
}

.columns {
  display: grid;
  grid-template-columns: 300px auto 300px;
  grid-column-start: auto;
}

img {
  max-height: 300px;
  max-width: 300px;
  margin: 1%;
  text-align: center;
}

li {
  display: table;
  margin-bottom: 20px;
  list-style-type: none;
}
</style>
