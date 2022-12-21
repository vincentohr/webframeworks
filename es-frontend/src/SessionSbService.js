export class SessionSbService {
  _RESOURCES_URL
  _BROWSER_STORAGE_ITEM_NAME
  _currentToken
  _currentUser

  constructor (resourcesUrl, browserStorageItemName) {
    console.log('Created SessionService...')
    this._BROWSER_STORAGE_ITEM_NAME = browserStorageItemName
    this._RESOURCES_URL = resourcesUrl
    this._currentUser = null
    this._currentToken = null
    // retrieve the current user info from browser storage,
    // e.g. after a page reload or when a new tab is opened.
    this.getTokenFromBrowserStorage()
    this._resourcesUrl = resourcesUrl
    this._browserStorageItemName = browserStorageItemName
  }

  async asyncSignIn (email, password) {
    const body = JSON.stringify({
      email: email,
      password: password
    })
    const response = await fetch(this._RESOURCES_URL + '/login',
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: body,
        credentials: 'include'
      })
    if (response.ok) {
      const user = await response.json()
      this.saveTokenIntoBrowserStorage(
        response.headers.get('Authorization'), user)
      return user
    } else {
      console.log(response)
      return null
    }
  }

  signOut () {
    this.saveTokenIntoBrowserStorage(null, null)
  }

  saveTokenIntoBrowserStorage (token, user) {
    this._currentToken = token
    // allow for different user sessions from the same computer
    // sessionStorage keeps different items per browser tab
    // localStorage keeps a single item per browser vendor
    // both isolate the items per server domain of the page (including port number?)
    if (token == null) {
      this._currentUser = null
      window.sessionStorage.removeItem(this._BROWSER_STORAGE_ITEM_NAME)
      window.sessionStorage.removeItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC')
      if (localStorage === sessionStorage) {
        window.localStorage.removeItem(this._BROWSER_STORAGE_ITEM_NAME)
        window.localStorage.removeItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC')
      }
    } else {
      console.log('New token for ' + user.name + ': ' + token)
      window.sessionStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME, token)
      window.sessionStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME + 'ACC', JSON.stringify(user))
      window.localStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME, token)
      window.localStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME + 'ACC', JSON.stringify(user))
    }
  }

  getTokenFromBrowserStorage () {
    if (this._currentToken != null) return this._currentToken
    this._currentToken = window.sessionStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME)
    const user = window.sessionStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC')

    if (this._currentToken == null) {
      if (localStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME) &&
        localStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC')) {
        sessionStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME, localStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME))
        sessionStorage.setItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC', localStorage.getItem(this._BROWSER_STORAGE_ITEM_NAME + '_ACC'))
      }
    }
    if (user != null) {
      this._currentUser = JSON.parse(user)
    }
    // console.log("SessionService recovered token: ", this._currentToken);
    // console.log("Current Account:", this._currentAccount);
    return this._currentToken
  }

  get RESOURCES_URL () {
    return this._RESOURCES_URL
  }

  get BROWSER_STORAGE_ITEM_NAME () {
    return this._BROWSER_STORAGE_ITEM_NAME
  }

  get resourcesUrl () {
    return this._resourcesUrl
  }

  get browserStorageItemName () {
    return this._browserStorageItemName
  }

  get currentUser () {
    return this._currentUser
  }

  get currentToken () {
    return this._currentToken
  }
}
