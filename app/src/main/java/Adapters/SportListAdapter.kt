package Adapters

import Entities.Sport
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.tp3.R

class SportListAdapter (private var sportList: MutableList<Sport>,val adapterOnClick : (pos: Int) -> Unit) : RecyclerView.Adapter<SportListAdapter.SportHolder>() {

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
        holder.getCardLayout().setOnClickListener {
            adapterOnClick(position)
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

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }

    }

}