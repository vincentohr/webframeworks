export class Trip {
  id
  startDateTime
  endDateTime
  startPosition
  endPosition
  mileage
  cost

  constructor (id, startDateTime, endDateTime, startPosition, endPosition, mileage, cost) {
    this.id = id
    this.startDateTime = startDateTime
    this.endDateTime = endDateTime
    this.startPosition = startPosition
    this.endPosition = endPosition
    this.mileage = mileage
    this.cost = cost
  }

  static copyConstructor (trip) {
    if (trip === null || trip === undefined) {
      return null
    } else {
      return Object.assign(new Trip(0), trip)
    }
  }
}
