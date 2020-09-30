//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "train_has_schedule_and_route", schema = "public", catalog = "sbb")
//@IdClass(TrainHasScheduleAndRouteEntityPK::class)
//open class TrainHasScheduleAndRouteEntity {
//    @get:Id
//    @get:Column(name = "train_id", nullable = false, insertable = false, updatable = false)
//    var trainId: Int? = null
//
//    @get:Id
//    @get:Column(name = "route_id", nullable = false, insertable = false, updatable = false)
//    var routeId: Int? = null
//
//    @get:Id
//    @get:Column(name = "departure_time", nullable = false)
//    var departureTime: java.sql.Timestamp? = null
//
//    @get:Basic
//    @get:Column(name = "ordered_seats", nullable = true)
//    var orderedSeats: Int? = null
//
//    @get:Basic
//    @get:Column(name = "free_seats", nullable = true)
//    var freeSeats: Int? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "train_id", referencedColumnName = "id")
//    var refTrainEntity: TrainEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "route_id", referencedColumnName = "id")
//    var refRouteEntity: RouteEntity? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "trainId = $trainId " +
//                    "routeId = $routeId " +
//                    "departureTime = $departureTime " +
//                    "orderedSeats = $orderedSeats " +
//                    "freeSeats = $freeSeats " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as TrainHasScheduleAndRouteEntity
//
//        if (trainId != other.trainId) return false
//        if (routeId != other.routeId) return false
//        if (departureTime != other.departureTime) return false
//        if (orderedSeats != other.orderedSeats) return false
//        if (freeSeats != other.freeSeats) return false
//
//        return true
//    }
//
//}
//
//class TrainHasScheduleAndRouteEntityPK : java.io.Serializable {
//    @get:Id
//    @get:Column(name = "train_id", nullable = false, insertable = false, updatable = false)
//    var trainId: Int? = null
//
//    @get:Id
//    @get:Column(name = "route_id", nullable = false, insertable = false, updatable = false)
//    var routeId: Int? = null
//
//    @get:Id
//    @get:Column(name = "departure_time", nullable = false)
//    var departureTime: java.sql.Timestamp? = null
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as TrainHasScheduleAndRouteEntityPK
//
//        if (trainId != other.trainId) return false
//        if (routeId != other.routeId) return false
//        if (departureTime != other.departureTime) return false
//
//        return true
//    }
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//}
