<template>
  <div class="columns">
    <div class="content" id="content-1">
      <h1>{{ pickabrand }}</h1>
      <select name="brand">
        <option value="Lime-S">Lime-S</option>
      </select>
      <h1>{{ availability }}</h1>
      <ul id="list">
        <li>{{ leidseplein }}</li>
        <li>{{ rembrandtplein }}</li>
        <li>{{ centraal_station }}</li>
      </ul>
    </div>
    <div class="content" id="content-2">
      <h1>{{ photo_galery }}</h1>
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
      pickabrand: 'Pick a brand:',
      availability: 'Availability:',
      photo_galery: 'Photo gallery:',
      tours: 'City tours:',
      centraal_station: 'Centraal Station',
      leidseplein: 'Leidseplein',
      rembrandtplein: 'Rembrandtplein',
      tour_info: 'Enjoy one of our city tours with your E-scooter. Go shopping in the nine streets, visit the Albert Cuyp market or the museum square. It is also allowed to take a moped onto the sub-way.'
    }
  }
}
</script>

<style scoped>

.content {
  background-color: grey;
  padding: 15px;
  margin-top: 10px;
  min-height: 200%;
}

#content-2 {
  margin: 10px;
}

#content-3 {
  float: right;
}

.columns {
  display: grid;
  grid-template-columns: 275px auto 400px;
  grid-template-rows: auto;
}

h1 {
  color: white;
}

li {
  display: table;
  margin-bottom: 20px;
  list-style-type: none;
}

</style>
