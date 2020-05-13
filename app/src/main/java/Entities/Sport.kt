package Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sports")
class Sport (id: Int?, nombre: String?, descripcion: String?, frecuencia: String?, urlImage: String?, tipo: String?) :
    Parcelable{

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int

    @ColumnInfo(name = "nombre")
    var nombre: String

    @ColumnInfo(name = "descripcion")
    var descripcion: String

    @ColumnInfo(name = "frecuencia")
    var frecuencia: String

    @ColumnInfo(name = "urlImage")
    var urlImage: String

    @ColumnInfo(name = "tipo")
    var tipo: String

    init {
        this.id = id!!
        this.nombre = nombre!!
        this.descripcion = descripcion!!
        this.frecuencia = frecuencia!!
        this.urlImage = urlImage!!
        this.tipo = tipo!!
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(nombre)
        writeString(descripcion)
        writeString(frecuencia)
        writeString(urlImage)
        writeString(tipo)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Sport> = object : Parcelable.Creator<Sport> {
            override fun createFromParcel(source: Parcel): Sport = Sport(source)
            override fun newArray(size: Int): Array<Sport?> = arrayOfNulls(size)
        }
    }
}




