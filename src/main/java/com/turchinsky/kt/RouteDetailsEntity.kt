//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "route_details", schema = "public", catalog = "sbb")
//@IdClass(RouteDetailsEntityPK::class)
//open class RouteDetailsEntity {
//    @get:Id
//    @get:Column(name = "route_id", nullable = false, insertable = false, updatable = false)
//    var routeId: Int? = null
//
//    @get:Id
//    @get:Column(name = "station_id", nullable = false, insertable = false, updatable = false)
//    var stationId: Int? = null
//
//    @get:Id
//    @get:Column(name = "station_order", nullable = false, insertable = false, updatable = false)
//    var stationOrder: Int? = null
//
//    @get:Basic
//    @get:Column(name = "km", nullable = true)
//    var km: Int? = null
//
//    @get:Basic
//    @get:Column(name = "type", nullable = true)
//    var type: Int? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "route_id", referencedColumnName = "id")
//    var refRouteEntity: RouteEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "station_id", referencedColumnName = "id")
//    var refStationEntity: StationEntity? = null
//
//    @get:OneToMany(mappedBy = "refRouteDetailsEntity")
//    var refScheduleDetailsEntities: List<ScheduleDetailsEntity>? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "routeId = $routeId " +
//                    "stationId = $stationId " +
//                    "stationOrder = $stationOrder " +
//                    "km = $km " +
//                    "type = $type " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as RouteDetailsEntity
//
//        if (routeId != other.routeId) return false
//        if (stationId != other.stationId) return false
//        if (stationOrder != other.stationOrder) return false
//        if (km != other.km) return false
//        if (type != other.type) return false
//
//        return true
//    }
//
//}
//
//class RouteDetailsEntityPK : java.io.Serializable {
//    @get:Id
//    @get:Column(name = "route_id", nullable = false, insertable = false, updatable = false)
//    var routeId: Int? = null
//
//    @get:Id
//    @get:Column(name = "station_id", nullable = false, insertable = false, updatable = false)
//    var stationId: Int? = null
//
//    @get:Id
//    @get:Column(name = "station_order", nullable = false, insertable = false, updatable = false)
//    var stationOrder: Int? = null
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as RouteDetailsEntityPK
//
//        if (routeId != other.routeId) return false
//        if (stationId != other.stationId) return false
//        if (stationOrder != other.stationOrder) return false
//
//        return true
//    }
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//}
