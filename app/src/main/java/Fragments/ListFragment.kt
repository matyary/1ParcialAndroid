package Fragments

import Adapters.SportListAdapter
import Entities.Sport

import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import database.appDatabase
import database.sportDao

import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import androidx.fragment.app.Fragment

import com.utn.tp3.R

class ListFragment : Fragment() {

    lateinit var view_sport: View
    lateinit var recSport: RecyclerView
    lateinit var  btn_add:FloatingActionButton

    private var db: appDatabase? = null
    private var sportDao: sportDao? = null

    private var listSport : MutableList<Sport>? = null

    private lateinit var viewModelTab1: FragmentTab1ViewModel

    val args: ListFragmentArgs by navArgs()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var sportListAdapter: SportListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view_sport = inflater.inflate(R.layout.list_fragment, container, false)

        recSport = view_sport.findViewById(R.id.rec_sport)
        btn_add = view_sport.findViewById(R.id.add_sport_btn)

        setHasOptionsMenu(true)

        return view_sport
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelTab1 = ViewModelProvider(requireActivity()).get(FragmentTab1ViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(view_sport.context)
        sportDao = db?.sportDao()

        val actividad = args.actividad
        val flag_newitem = args.newitem

        when (actividad){

            1 ->{
                listSport = sportDao?.loadAerobicoType() as MutableList<Sport>
            }
            2 ->{
                listSport = sportDao?.loadMusculacionType() as MutableList<Sport>
            }
            3 ->{
                listSport = sportDao?.loadFlexibilidadType() as MutableList<Sport>
            }
        }

        if(listSport == null){
            listSport = ArrayList()
        }

        recSport.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)
        recSport.layoutManager = linearLayoutManager

        sportListAdapter = SportListAdapter(listSport!!) {position: Int ->
            viewModelTab1.ItemClicked.value = listSport!![position]
            val actiontab = ListFragmentDirections.actionListFragmentToContainerFragment()
            view_sport.findNavController().navigate(actiontab)
            //Log.d("Test", listSport!![position].descripcion)
        }

        if (flag_newitem == 1)
            sportListAdapter.notifyDataSetChanged()

        recSport.adapter = sportListAdapter

        add_sport_btn.setOnClickListener {
            val actionadd = ListFragmentDirections.actionListFragmentToNewItem()
            view_sport.findNavController().navigate(actionadd)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.action_add -> {
                val action_toolbar = ListFragmentDirections.actionListFragmentToNewItem()
                view_sport.findNavController().navigate(action_toolbar)
            }

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }
}


