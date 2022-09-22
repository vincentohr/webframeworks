export class Scooter {
  static Status = ['IDLE', 'INUSE', 'MAINTENANCE']
  constructor (id, tag, status, gpsLocation, mileage, batteryCharge) {
    this._id = id
    this._tag = tag
    this._status = status
    this._gpsLocation = gpsLocation
    this._mileage = mileage
    this._batteryCharge = batteryCharge
  }

  static createSampleScooter (pId = 0) {
    const scooter = new Scooter()
    const Status = ['IDLE', 'INUSE', 'MAINTENANCE']
    const latitude = 52.3702157
    const longitude = 4.895167899999933
    scooter._gpsLocation = latitude + ' ' + longitude
    scooter._tag = Math.random().toString(36).slice(2, 10)
    scooter._status = Status[Math.round(Math.random() * 3)]
    scooter._mileage = Math.round(Math.random() * 10_000)
    scooter._batteryCharge = Math.round(Math.random() * 95) + 5
    return new Scooter(pId, scooter._tag, scooter._status, scooter._gpsLocation, scooter._mileage, scooter._batteryCharge)
  }

  get id () {
    return this._id
  }

  get tag () {
    return this._tag
  }

  get status () {
    return this._status
  }

  get gpsLocation () {
    return this._gpsLocation
  }

  get mileage () {
    return this._mileage
  }

  get batteryCharge () {
    return this._batteryCharge
  }
}
