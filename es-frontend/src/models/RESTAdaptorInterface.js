export class RESTAdaptorInterface {
  resourcesURL
  copyConstructor

  constructor (resourcesURL, copyConstructor) {
    this.resourcesURL = resourcesURL
    this.copyConstructor = copyConstructor
    console.log('Created RESTAdaptorInterface for: ' + resourcesURL)
  }

  async asyncFindAll (queryParams = null) {

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
