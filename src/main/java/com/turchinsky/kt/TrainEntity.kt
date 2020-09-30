//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "train", schema = "public", catalog = "sbb")
//open class TrainEntity {
//    @get:Id
//    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
//    var id: Int? = null
//
//    @get:Basic
//    @get:Column(name = "capacity", nullable = true)
//    var capacity: Int? = null
//
//    @get:Basic
//    @get:Column(name = "type", nullable = true)
//    var type: String? = null
//
//    @get:Basic
//    @get:Column(name = "number", nullable = true)
//    var number: String? = null
//
//    @get:Basic
//    @get:Column(name = "velocity", nullable = true)
//    var velocity: Int? = null
//
//    @get:OneToMany(mappedBy = "refTrainEntity")
//    var refScheduleDetailsEntities: List<ScheduleDetailsEntity>? = null
//
//    @get:OneToMany(mappedBy = "refTrainEntity")
//    var refTrainHasScheduleAndRouteEntities: List<TrainHasScheduleAndRouteEntity>? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "id = $id " +
//                    "capacity = $capacity " +
//                    "type = $type " +
//                    "number = $number " +
//                    "velocity = $velocity " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as TrainEntity
//
//        if (id != other.id) return false
//        if (capacity != other.capacity) return false
//        if (type != other.type) return false
//        if (number != other.number) return false
//        if (velocity != other.velocity) return false
//
//        return true
//    }
//
//}
//
