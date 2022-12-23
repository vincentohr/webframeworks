export class RESTAdaptorInterface {
  resourcesURL
  copyConstructor

  constructor (resourcesURL) {
    this.resourcesURL = resourcesURL
    console.log('Created RESTAdaptorInterface for: ' + resourcesURL)
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
    console.log('RESTAdaptorInterface.asyncFindAll()...')
    const url = this.fullURL('/trips', queryParams)
    const trips = await this.fetchJson(this.resourcesURL)
    this.copyConstructor = Object.assign(trips)
    console.log(this.copyConstructor)
    return this.copyConstructor.map(s => s)
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
