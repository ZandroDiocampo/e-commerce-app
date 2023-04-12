package ph.stacktrek.novare.ecommercenovare.diocampo.zandro.model

import java.time.LocalDateTime

open class User(val username:String, val password:String) {
    lateinit var email: String
        private set
    lateinit var userID:String
        private set
    lateinit var mobilenumber: String
        private set
    lateinit var accountCreated: LocalDateTime
        private set
    lateinit var lastLogin: LocalDateTime


    constructor(): this("","")

    init{
        println("init called")
    }
}

class ShopOwner() : User(){
    lateinit var shopName: String
        private set

    lateinit var rating: ArrayList<String>
}

class Buyer(): User(){
    lateinit var cart: ArrayList<String>
}

