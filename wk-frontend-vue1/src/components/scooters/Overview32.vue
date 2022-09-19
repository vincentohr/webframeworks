<template>
  <div id="content">
    <table>
      <tr>
        <th>
          Tag:
        </th>
      </tr>
      <tr v-for="(tag, index) in tags" @click.left="selected (index)" :key="index">
        <td>{{tag}}</td>
      </tr>
      <tr>{{selectedScooter}}</tr>
    </table>
    <button @click="onNewScooter">New Scooter</button>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  props: ['scooters'],
  data () {
    return {
      count: 0,
      selectedScooter: null,
      tags: ['td1', 'td2', 'td3', 'td4', 'td5', 'td6']
    }
  },
  methods: {
    selected (index) {
      this.selectedScooter = this.tags.at(index)
      this.count++
      const name = this.selectedScooter
    },
    onNewScooter (pId) {
      pId = this.lastId
      this.lastId++
      this.scooters.push(
        Scooter.createSampleScooter(pId)
      )
    }
  }
}
</script>

<style scoped>
table {
  font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 20%;
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
  background-color: #dddddd;
}
</style>
