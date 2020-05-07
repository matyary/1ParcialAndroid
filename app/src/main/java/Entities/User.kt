package Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User (id : Int, name : String, pass : String) {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int

    @ColumnInfo(name = "name")
    var name : String

    @ColumnInfo(name = "pass")
    var pass : String

    init {
        this.id = id
        this.name = name
        this.pass = pass
    }

    fun getNombre() : String{
        return (this.name)
    }

    fun getClave() : String{
        return (this.pass)
    }
}