import { Trip } from '@/models/Trip'
import { RESTAdaptorInterface } from '@/models/RESTAdaptorInterface'

export class TripAdaptor extends RESTAdaptorInterface {
  resourcesUrl;

  constructor (resourcesURL) {
    super(resourcesURL, Trip.copyConstructor)
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
    const trips = await this.fetchJson(this.resourcesUrl)
    console.log(trips.map(s => Trip.copyConstructor(s)))
    return trips.map(s => Trip.copyConstructor(s))
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

  async asyncSave (trip) {
    console.log('ScooterAdaptor.asyncSave()...')
    return this.fetchJson(this.resourcesUrl + '/' + trip, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(trip)
    })
    // }
  }

  async asyncDeleteById (id) {
    console.log('ScooterAdaptor.asyncDeleteById()...')
    return this.fetchJson(this.resourcesUrl + '/' + id, {
    })
  }
}
