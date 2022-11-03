import { Scooter } from '@/models/scooter'

export class ScooterAdaptor {
  resourcesUrl;

  constructor (resourcesURL) {
    this.resourcesUrl = resourcesURL
    console.log('Created Scooteradaptor for: ' + resourcesURL)
  }

  async fetchJson (url, options = null) {
    const response = await fetch(url, options)
    if (response.ok) {
      return await response.json()
    } else {
      console.log(response, !response.bodyUsed ? await response.text() : '')
      return null
    }
  }

  async asyncFindAll () {
    console.log('ScooterAdaptor.asyncFindAll()...')
    const scooters = await this.fetchJson(this.resourcesUrl)
    console.log(scooters.map(s => Scooter.copyConstructor(s)))
    return scooters.map(s => Scooter.copyConstructor(s))
  }

  async asyncFindById (id) {
    console.log('ScooterAdaptor.asyncFindById()...')
    return this.fetchJson(this.resourcesUrl + '/' + id, {
      method: 'GET'
    })
    // test
  }

  async asyncAddNewScooter () {
    console.log('ScooterAdaptor.addScootie()...')
    return this.fetchJson(this.resourcesUrl + '/', {
      method: 'POST'
    })
    // test
  }

  async asyncSave (scooter) {
    console.log('ScooterAdaptor.asyncSave()...')
    // const scooterlist = this.fetchJson(this.resourcesUrl)
    // if (scooterlist.indexOf(scooter) >= 0) {
    //   return this.fetchJson(this.resourcesUrl + '/' + scooter.id, {
    //     method: 'PUT'
    //   })
    // } else {
    return this.fetchJson(this.resourcesUrl + '/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(scooter)
    })
    // }
  }

  async asyncDeleteById (id) {
    console.log('ScooterAdaptor.asyncDeleteById()...')
    return this.fetchJson(this.resourcesUrl + '/' + id, {
      method: 'DELETE'
    })
  }
}
