//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "ticket", schema = "public", catalog = "sbb")
//open class TicketEntity {
//    @get:Id
//    @get:Column(name = "id", nullable = false)
//    var id: Int? = null
//
//    @get:Basic
//    @get:Column(name = "price", nullable = true)
//    var price: Int? = null
//
//    @get:Basic
//    @get:Column(name = "station_from", nullable = true, insertable = false, updatable = false)
//    var stationFrom: Int? = null
//
//    @get:Basic
//    @get:Column(name = "station_to", nullable = true, insertable = false, updatable = false)
//    var stationTo: Int? = null
//
//    @get:Basic
//    @get:Column(name = "line_id", nullable = true, insertable = false, updatable = false)
//    var lineId: Int? = null
//
//    @get:Basic
//    @get:Column(name = "passenger_id", nullable = true, insertable = false, updatable = false)
//    var passengerId: Int? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "station_from", referencedColumnName = "id")
//    var refStationEntity: StationEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "station_to", referencedColumnName = "id")
//    var refStationEntity: StationEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "line_id", referencedColumnName = "line_id")
//    var refScheduleDetailsEntity: ScheduleDetailsEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "passenger_id", referencedColumnName = "id")
//    var refPassengerEntity: PassengerEntity? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "id = $id " +
//                    "price = $price " +
//                    "stationFrom = $stationFrom " +
//                    "stationTo = $stationTo " +
//                    "lineId = $lineId " +
//                    "passengerId = $passengerId " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as TicketEntity
//
//        if (id != other.id) return false
//        if (price != other.price) return false
//        if (stationFrom != other.stationFrom) return false
//        if (stationTo != other.stationTo) return false
//        if (lineId != other.lineId) return false
//        if (passengerId != other.passengerId) return false
//
//        return true
//    }
//
//}
//
