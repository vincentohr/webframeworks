import fetchIntercept from 'fetch-intercept'

export class FetchInterceptor {
  static theInstance; // the singleton instance that has been registered
  sessionService; // the sessionService which tracks the token
  unregister; // callback function to unregister this instance at shutdown
  router;

  constructor (session, router) {
    FetchInterceptor.theInstance = this
    this.sessionService = session
    // fetchIntercept does not register the object closure, only the methods as functions
    this.unregister = fetchIntercept.register(this)
    this.router = router

    console.log('FetchInterceptor has been registered; current token = ',
      FetchInterceptor.theInstance.sessionService.currentToken)
  }

  request (url, options) {
    const token = FetchInterceptor.theInstance.sessionService.currentToken

    if (token == null) {
      return [url, options]
    } else if (options == null) {
      return [url, { headers: { Authorization: token } }]
    } else {
      const newOptions = { ...options }
      const modifiedHeaders = new Headers(newOptions.headers)
      modifiedHeaders.append('Authorization', token)
      newOptions.headers = modifiedHeaders
      console.log('FetchInterceptor request: ', url, newOptions)
      return [url, newOptions]
    }
  }

  requestError (error) {
    // Called when an error occured during another 'request' interceptor call
    console.log('FetchInterceptor requestError: ', error)
    return Promise.reject(error)
  }

  response (response) {
    // console.log("FetchInterceptor response: ", response);
    FetchInterceptor.theInstance.tryRecoverNewJWToken(response)
    if (response.status >= 400 && response.status < 600) {
      FetchInterceptor.theInstance.handleErrorInResponse(response)
    }
    return response
  }

  responseError (error) {
    // Handle a fetch error
    console.log('FetchInterceptor responseError: ', error)
    return Promise.reject(error)
  }

  async handleErrorInResponse (response) {
    if (response.status === 401) {
      this.router.push({ path: '/sign-out' }) // vue-router
    } else if (response.status !== 406) {
      // TODO handle any other error
    }
  }

  tryRecoverNewJWToken (response) {
    // TODO check the response on availability of a JWT
    //  and request the session service to save that
  }
}
