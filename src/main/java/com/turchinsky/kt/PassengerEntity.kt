//package com.turchinsky.kt
//
//import javax.persistence.*
//
//@Entity
//@Table(name = "passenger", schema = "public", catalog = "sbb")
//open class PassengerEntity {
//    @get:Id
//    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
//    var id: Int? = null
//
//    @get:Basic
//    @get:Column(name = "name", nullable = true)
//    var name: String? = null
//
//    @get:Basic
//    @get:Column(name = "surname", nullable = true)
//    var surname: String? = null
//
//    @get:Basic
//    @get:Column(name = "birth_date", nullable = true)
//    var birthDate: java.sql.Date? = null
//
//    @get:Basic
//    @get:Column(name = "username", nullable = false)
//    var username: String? = null
//
//    @get:Basic
//    @get:Column(name = "email", nullable = true)
//    var email: String? = null
//
//    @get:OneToMany(mappedBy = "refPassengerEntity")
//    var refPassengerHasTicketEntities: List<PassengerHasTicketEntity>? = null
//
//    override fun toString(): String =
//            "Entity of type: ${javaClass.name} ( " +
//                    "id = $id " +
//                    "name = $name " +
//                    "surname = $surname " +
//                    "birthDate = $birthDate " +
//                    "username = $username " +
//                    "email = $email " +
//                    ")"
//
//    // constant value returned to avoid entity inequality to itself before and after it's update/merge
//    override fun hashCode(): Int = 42
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as PassengerEntity
//
//        if (id != other.id) return false
//        if (name != other.name) return false
//        if (surname != other.surname) return false
//        if (birthDate != other.birthDate) return false
//        if (username != other.username) return false
//        if (email != other.email) return false
//
//        return true
//    }
//
//}
//
