package com.josemanuel.safekeys.data

class Category {
    var id: Int = 0
    var catName: String = ""
    var quantity: Int = 0
    var idUser: String = ""

    constructor(catName: String, quantity: Int, idUser: String) {
        this.catName = catName
        this.quantity = quantity
        this.idUser = idUser
    }

    constructor()
}