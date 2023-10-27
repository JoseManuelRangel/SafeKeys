package com.josemanuel.safekeys.data


class Kei {
    var id: Int = 0
    var keyName: String = ""
    var keyUser: String = ""
    var keyPass: String = ""
    var keyDescription: String = ""
    var isFavorite: Int = 0
    var idCat: String = ""
    var idUser: String = ""

    constructor(
        keyName: String,
        keyUser: String,
        keyPass: String,
        keyDescription: String,
        isFavorite: Int,
        idCat: String,
        idUser: String
    ) {
        this.keyName = keyName
        this.keyUser = keyUser
        this.keyPass = keyPass
        this.keyDescription = keyDescription
        this.isFavorite = isFavorite
        this.idCat = idCat
        this.idUser = idUser
    }

    constructor()
}