export class Scooter {
  constructor (id, tag, status, gpsLocation, mileage, batteryCharge) {
    this.id = id
    this.tag = tag
    this.status = status
    this.gpsLocation = gpsLocation
    this.mileage = mileage
    this.batteryCharge = batteryCharge
  }

  static createSampleScooter (pId = 0) {
    const scooter = new Scooter()
    const statusArray = ['IDLE', 'INUSE', 'MAINTENANCE']
    const gpsLocations = ['Museumplein', 'Leidseplein', 'Nemo']
    scooter.gpsLocation = gpsLocations[Math.round(Math.random() * 3)]
    console.log(Math.round(Math.random() * 2))
    scooter.tag = Math.random().toString(36).slice(2, 10)
    scooter.status = statusArray[Math.round(Math.random() * 3)]
    scooter.mileage = Math.round(Math.random() * 10_000)
    scooter.batteryCharge = Math.round(Math.random() * 95) + 5
    return new Scooter(pId, scooter.tag, scooter.status, scooter.gpsLocation, scooter.mileage, scooter.batteryCharge)
  }
}
