//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "passenger_has_ticket", schema = "public", catalog = "sbb")
//open class PassengerHasTicketEntity {
//    @get:Basic
//    @get:Column(name = "passenger_id", nullable = true, insertable = false, updatable = false)
//    var passengerId: Int? = null
//
//    @get:Basic
//    @get:Column(name = "ticket_id", nullable = true, insertable = false, updatable = false)
//    var ticketId: Int? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "passenger_id", referencedColumnName = "id")
//    var refPassengerEntity: PassengerEntity? = null
//
//    @get:ManyToOne(fetch = FetchType.LAZY)
//    @get:JoinColumn(name = "ticket_id", referencedColumnName = "id")
//    var refTicketEntity: TicketEntity? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "passengerId = $passengerId " +
//                    "ticketId = $ticketId " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as PassengerHasTicketEntity
//
//        if (passengerId != other.passengerId) return false
//        if (ticketId != other.ticketId) return false
//
//        return true
//    }
//
//}
//
