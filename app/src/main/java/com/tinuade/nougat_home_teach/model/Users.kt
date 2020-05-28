package com.tinuade.nougat_home_teach.model


// Data class for parent users
data class ParentUsers(
    var fullName: String,
    var emailAddress: String,
    var password: String,
    override var userType: UserType? = null
) : Users()


// Data class for teachers users
data class TeacherUsers(
    var fullName: String,
    var emailAddress: String,
    var password: String,
    var phoneNumber: String,
    var subject: String,
    var yearOfExperience: String,
    override var userType: UserType? = null
) : Users()

/***
 * super class for both data class
 * it serves as the interface
 */
open class Users {
    open var userType: UserType? = null

}
// enum class to different the two classes

enum class UserType {
    PARENT, TEACHER
}

