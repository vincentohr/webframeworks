import { Scooter } from '@/models/scooter'

export class RESTAdaptorInterface {
  resourcesURL
  copyConstructor

  constructor (resourcesURL) {
    this.resourcesURL = resourcesURL
    console.log('Created RESTAdaptorInterface for: ' + resourcesURL)
    // this.copyConstructor = Object.assign(new Trip(0), scooter)
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

  async asyncFindAll (queryParams = null) {
    const url = this.fullURL('/trips', queryParams)
    console.log('RESTAdaptorInterface.asyncFindAll()...')
    const scooters = await this.fetchJson(this.resourcesURL)
    // console.log(scooters.map(s => this.copyConstructor(s)))
    console.log(scooters)
    // return scooters.map(s => Scooter.copyConstructor(s))
    return null
  }

  asyncFindById (queryParams = null) {

  }

  asyncSave (queryParams = null) {

  }

  asyncDelete (queryParams = null) {

  }

  fullURL (target, queryParams) {
    let url = this.resourcesURL + target
    if (queryParams != null) {
      const newUrl = new URL(url)
      newUrl.search = new URLSearchParams(queryParams).toString()
      url = newUrl.toString()
    }
    return url
  }
}
