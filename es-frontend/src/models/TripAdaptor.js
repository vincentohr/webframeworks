import { Trip } from '@/models/Trip'
import { Scooter } from '@/models/scooter'
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
      try {
        return await response.json()
      } catch (err) {
        console.log(err)
      }
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

  async asyncSave (trip) {
    console.log('ScooterAdaptor.asyncSave()...')
    console.log(JSON.stringify(trip))
    return this.fetchJson(this.resourcesUrl + '/' + trip, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(trip)
    })
    // }
  }

  async asyncAddTrip (scooterId, trip) {
    console.log('add new trip')
    return this.fetchJson('http://localhost:8085/api/scooters/' + scooterId + '/trips', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(trip)
    })
  }

  async asyncDeleteById (id) {
    console.log('ScooterAdaptor.asyncDeleteById()...')
    return this.fetchJson(this.resourcesUrl + '/' + id, {})
  }
}
