<template>
  <div id="content">
    <div id="table">
      <table>
        <tr>
          <th>
            Tag:
          </th>
        </tr>
        <tr v-for="(scooter, index) in scooters" :key="index" @click="onSelect (scooter)">
          <td :class="{'activeTag': selectedScooter === scooter}">{{ scooter.tag }}</td>
        </tr>
      </table>
      <button id="button" @click="onNewScooter">New Scooter</button>
    </div>
  </div>
  <div id="text">
    <h3 v-if="!this.isActive">Select a scooter from the list at the left!</h3>
  </div>
  <div id="detail">
    <router-view @delete-scooter="remove()" :selected-scooter="selectedScooter"></router-view>
  </div>
</template>

<script>
import { Scooter } from '@/models/scooter'

export default {
  name: 'Overview33',
  created () {
    this.lastId = 30_000
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
        Scooter.createSampleScooter(this.lastId++)
      )
    }
  },
  data () {
    return {
      pId: this.lastId,
      scooters: [],
      selectedScooter: null,
      isActive: false
    }
  },
  // watch: {
  //   selectedScooter (newValue, oldValue) {
  //     if (newValue !== oldValue) {
  //       oldValue = newValue
  //       newValue = null
  //     }
  //   }
  // },
  // Geen idee of dit werkt, niet zo lang aan gezeten.
  methods: {
    onNewScooter (pId) {
      pId = this.lastId
      this.lastId++
      this.scooters.push(
        Scooter.createSampleScooter(pId)
      )
      this.selectedScooter = this.scooters.at(this.scooters.length - 1)
    },
    onSelect (scooter) {
      this.isActive = true
      if (this.selectedScooter !== scooter) {
        this.selectedScooter = scooter
        this.$router.push(this.$route.matched[0].path + '/' + scooter.id)
      } else {
        this.selectedScooter = null
        this.isActive = false
      }
      // if (scooter != null && scooter !== this.selectedScooter) {
      //   console.log(this.$route.path)
      //   this.$router.push(this.$route.matched[0].path + '/' + scooter.id)
      // } else if (this.selectedScooter != null) {
      //   this.$router.push(this.$route.matched[0].path + '/')
      // }
    },
    remove () {
      const index = this.scooters.indexOf(this.selectedScooter.id)
      this.scooters.splice(index, 1)
      this.selectedScooter = null
      this.isActive = false
      alert('Helaas nog niet werkend, verwijderd de verkeerde scooter.')
    }
  }
  // watch: {
  //   '$route' () {
  //     this.selectedScooter = this.findSelectedFromRouteParams(this.$route)
  //   }
  // }
}
</script>

<style scoped>

div {
  display: inline;
  position: absolute;
}

#detail {
  margin-left: 60%;
  width: 200px;
}

#table {
  margin-left: 20px;
}

#text {
  position: absolute;
  margin-left: 40%
}

table {
  margin-top: 3%;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 500px;
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
h3{
  color: black;
}
.activeTag {
  background-color: #11bd11;
}

#button{
  display: block;
  margin-left: auto;
  border-style: none;
  border-radius: 5px;
  background-color: #11bd11;
  color: white;
  padding: 10px;
}
</style>
