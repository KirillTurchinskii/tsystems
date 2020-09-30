//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "schedule_details", schema = "public", catalog = "sbb")
//open class ScheduleDetailsEntity {
//    @get:Basic
//    @get:Column(name = "route_id", nullable = true, insertable = false, updatable = false)
//    var routeId: Int? = null
//
//    @get:Basic
//    @get:Column(name = "station_id", nullable = true, insertable = false, updatable = false)
//    var stationId: Int? = null
//
//    @get:Basic
//    @get:Column(name = "station_order", nullable = true, insertable = false, updatable = false)
//    var stationOrder: Int? = null
//
//    @get:Basic
//    @get:Column(name = "train_id", nullable = true, insertable = false, updatable = false)
//    var trainId: Int? = null
//
//    @get:Id
//    @get:Column(name = "line_id", nullable = false, insertable = false, updatable = false)
//    var lineId: Int? = null
//
//    @get:Basic
//    @get:Column(name = "arrival_time", nullable = true)
//    var arrivalTime: java.sql.Timestamp? = null
//
//    @get:Basic
//    @get:Column(name = "departure_time", nullable = true)
//    var departureTime: java.sql.Timestamp? = null
//
//    @get:Basic
//    @get:Column(name = "free_seats", nullable = true)
//    var freeSeats: Int? = null
//
//    @get:Basic
//    @get:Column(name = "ordered_seats", nullable = true)
//    var orderedSeats: Int? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumns(
//            JoinColumn(name = "route_id", referencedColumnName = "route_id"),
//            JoinColumn(name = "station_id", referencedColumnName = "station_id"),
//            JoinColumn(name = "station_order", referencedColumnName = "station_order")
//    )
//    var refRouteDetailsEntity: RouteDetailsEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "train_id", referencedColumnName = "id")
//    var refTrainEntity: TrainEntity? = null
//
//    @get:OneToMany(mappedBy = "refScheduleDetailsEntity")
//    var refTicketEntities: List<TicketEntity>? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "routeId = $routeId " +
//                    "stationId = $stationId " +
//                    "stationOrder = $stationOrder " +
//                    "trainId = $trainId " +
//                    "lineId = $lineId " +
//                    "arrivalTime = $arrivalTime " +
//                    "departureTime = $departureTime " +
//                    "freeSeats = $freeSeats " +
//                    "orderedSeats = $orderedSeats " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as ScheduleDetailsEntity
//
//        if (routeId != other.routeId) return false
//        if (stationId != other.stationId) return false
//        if (stationOrder != other.stationOrder) return false
//        if (trainId != other.trainId) return false
//        if (lineId != other.lineId) return false
//        if (arrivalTime != other.arrivalTime) return false
//        if (departureTime != other.departureTime) return false
//        if (freeSeats != other.freeSeats) return false
//        if (orderedSeats != other.orderedSeats) return false
//
//        return true
//    }
//
//}
//
