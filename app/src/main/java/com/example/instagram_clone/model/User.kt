package com.example.instagram_clone.model

class User {
    var fullname: String = ""
    var email: String = ""

    constructor(fullname: String, email: String) {
        this.fullname = fullname
        this.email = email
    }
}