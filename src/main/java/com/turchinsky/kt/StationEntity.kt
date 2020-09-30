//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "station", schema = "public", catalog = "sbb")
//open class StationEntity {
//    @get:Id
//    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
//    var id: Int? = null
//
//    @get:Basic
//    @get:Column(name = "name", nullable = true)
//    var name: String? = null
//
//    @get:OneToMany(mappedBy = "refStationEntity")
//    var refRouteDetailsEntities: List<RouteDetailsEntity>? = null
//
//    @get:OneToMany(mappedBy = "refStationEntity")
//    var refTicketEntities: List<TicketEntity>? = null
//
//    @get:OneToMany(mappedBy = "refStationEntity")
//    var refTicketEntities: List<TicketEntity>? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "id = $id " +
//                    "name = $name " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as StationEntity
//
//        if (id != other.id) return false
//        if (name != other.name) return false
//
//        return true
//    }
//
//}
//
