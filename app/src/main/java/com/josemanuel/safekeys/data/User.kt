package com.josemanuel.safekeys.data

class User {
    var username: String = ""
    var pin: String = ""

    constructor(username: String, pin: String) {
        this.username = username
        this.pin = pin
    }

    constructor()
}