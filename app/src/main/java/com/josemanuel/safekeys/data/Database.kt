package com.josemanuel.safekeys.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val BD = "SafeKeys"

class Database(context: Context): SQLiteOpenHelper(context, BD, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        /* Creating the user table. */
        val userTable = "CREATE TABLE User (username VARCHAR PRIMARY KEY, pin VARCHAR NOT NULL)"
        db?.execSQL(userTable)

        /* Creating the category table. */
        val categoryTable = "CREATE TABLE Category (id INTEGER PRIMARY KEY AUTOINCREMENT, catName VARCHAR NOT NULL, quantity INTEGER NOT NULL, idUser VARCHAR NOT NULL)"
        db?.execSQL(categoryTable)

        /* Creating the key table. */
        val keyTable = "CREATE TABLE Kei (id INTEGER PRIMARY KEY, keyName VARCHAR NOT NULL, keyUser VARCHAR NOT NULL, keyPass VARCHAR NOT NULL, keyDescription VARCHAR NOT NULL, isFavorite INTEGER NOT NULL, idCat VARCHAR NOT NULL, idUser VARCHAR NOT NULL)"
        db?.execSQL(keyTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        /* Nothing here. */
    }

    fun insertUser(user: User): String {
        /* Prepare the database to write inside it. */
        val db = this.writableDatabase

        /* Content values to put them inside the database. */
        val content = ContentValues()
        content.put("username", user.username)
        content.put("pin", user.pin)

        /* The insert sentence. */
        val result = db.insert("User", null, content)
        return if(result == (-1).toLong()) {
            "La inserción falló."
        } else {
            "Usuario registrado correctamente."
        }
    }

    fun searchUser(user: User): MutableList<User> {
        val list: MutableList<User> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence. */
        val sql = "SELECT * FROM User WHERE username='${user.username}' AND pin='${user.pin}'"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val u = User()
                u.username = result.getString(0).toString()
                u.pin = result.getString(1).toString()
                list.add(u)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }

    fun insertCategory(category: Category): String {
        /* Prepare the database to write inside it. */
        val db = this.writableDatabase

        /* Content values to put them inside the database. */
        val content = ContentValues()
        content.put("catName", category.catName)
        content.put("quantity", category.quantity)
        content.put("idUser", category.idUser)

        /* The insert sentence. */
        val result = db.insert("Category", null, content)
        return if(result == (-1).toLong()) {
            "La inserción falló."
        } else {
            "Categoría registrada correctamente."
        }
    }

    fun searchCategories(username: String): MutableList<Category> {
        val list: MutableList<Category> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence. */
        val sql = "SELECT * FROM Category WHERE idUser='$username'"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val c = Category()
                c.id = result.getInt(0)
                c.catName = result.getString(1).toString()
                c.quantity = result.getInt(2)
                c.idUser = result.getString(3).toString()
                list.add(c)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }

    fun incrementQuantity(categoryName: String) {
        /* Prepare the database to write inside it. */
        val db = this.writableDatabase
        /* Execute the sql sentence. */
        db?.execSQL("UPDATE Category SET quantity=quantity+1 WHERE catName='$categoryName'")
        /* Close the database. */
        db.close()
    }

    fun insertKey(key: Kei): String {
        /* Prepare the database to write inside it. */
        val db = this.writableDatabase

        /* Content values to put them inside the database. */
        val content = ContentValues()
        content.put("keyName", key.keyName)
        content.put("keyUser", key.keyUser)
        content.put("keyPass", key.keyPass)
        content.put("keyDescription", key.keyDescription)
        content.put("isFavorite", key.isFavorite)
        content.put("idCat", key.idCat)
        content.put("idUser", key.idUser)

        /* The insert sentence. */
        val result = db.insert("Kei", null, content)
        return if(result == (-1).toLong()) {
            "La inserción falló."
        } else {
            "Contraseña insertada correctamente."
        }
    }

    fun searchKeys(username: String): MutableList<Kei> {
        val list: MutableList<Kei> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence */
        val sql = "SELECT * FROM Kei WHERE idUser='$username'"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val k = Kei()
                k.id = result.getInt(0)
                k.keyName = result.getString(1)
                k.keyUser = result.getString(2)
                k.keyPass = result.getString(3)
                k.keyDescription = result.getString(4)
                k.isFavorite = result.getInt(5)
                k.idCat = result.getString(6)
                k.idUser = result.getString(7)
                list.add(k)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }

    fun searchFavoritesKeys(username: String): MutableList<Kei> {
        val list: MutableList<Kei> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence */
        val sql = "SELECT * FROM Kei WHERE idUser='$username' AND isFavorite=1"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val k = Kei()
                k.id = result.getInt(0)
                k.keyName = result.getString(1)
                k.keyUser = result.getString(2)
                k.keyPass = result.getString(3)
                k.keyDescription = result.getString(4)
                k.isFavorite = result.getInt(5)
                k.idCat = result.getString(6)
                k.idUser = result.getString(7)
                list.add(k)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }

    fun searchKeysByName(keyName: String?, username: String): MutableList<Kei> {
        val list: MutableList<Kei> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence */
        val sql = "SELECT * FROM Kei WHERE idUser='$username' AND keyName LIKE '$keyName%'"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val k = Kei()
                k.id = result.getInt(0)
                k.keyName = result.getString(1)
                k.keyUser = result.getString(2)
                k.keyPass = result.getString(3)
                k.keyDescription = result.getString(4)
                k.isFavorite = result.getInt(5)
                k.idCat = result.getString(6)
                k.idUser = result.getString(7)
                list.add(k)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }

    fun searchFavoritesKeysByName(keyName: String?, username: String): MutableList<Kei> {
        val list: MutableList<Kei> = ArrayList()
        /* Prepare the database to read inside it. */
        val db = this.readableDatabase

        /* The SQL Sentence */
        val sql = "SELECT * FROM Kei WHERE idUser='$username' AND isFavorite=1 AND keyName LIKE '$keyName%'"

        /* Read the result from the sql sentence and put them into a list. */
        val result = db.rawQuery(sql, null)
        if(result.moveToFirst()) {
            do {
                val k = Kei()
                k.id = result.getInt(0)
                k.keyName = result.getString(1)
                k.keyUser = result.getString(2)
                k.keyPass = result.getString(3)
                k.keyDescription = result.getString(4)
                k.isFavorite = result.getInt(5)
                k.idCat = result.getString(6)
                k.idUser = result.getString(7)
                list.add(k)
            } while(result.moveToNext())
        }

        /* Close the result and return the list. */
        result.close()
        return list
    }
}