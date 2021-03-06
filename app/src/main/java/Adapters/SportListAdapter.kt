package Adapters

import Entities.Sport
import android.graphics.Color
import android.graphics.Paint
import android.net.sip.SipSession
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.tp3.R

class SportListAdapter (private var sportList: MutableList<Sport>,val adapterOnClick: (pos: Int) -> Unit, val adapterOnLongClick: (pos: Int) -> Unit) : RecyclerView.Adapter<SportListAdapter.SportHolder>() {

    companion object {

        private val TAG = "SportListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_sport,parent,false)
        return (SportHolder(view))
    }

    override fun getItemCount(): Int {

        return sportList.size
    }

    override fun onBindViewHolder(holder: SportHolder, position: Int) {

        holder.setName(sportList[position].nombre)
        holder.setFrecuency(sportList[position].frecuencia)
        holder.getCardLayout().setOnClickListener {
            adapterOnClick(position)
        }
        holder.getCardLayout().setOnLongClickListener {
            holder.getCardLayout().setBackgroundColor(Color.MAGENTA)
            adapterOnLongClick(position)
            return@setOnLongClickListener true
        }
    }

    class SportHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View

        init {
            this.view = v
        }

        fun setName(name : String) {
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun setFrecuency(frecuency: String) {
            val fcy: TextView = view.findViewById(R.id.txt_fcy_item)
            fcy.text = frecuency
        }

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }
    }
}

