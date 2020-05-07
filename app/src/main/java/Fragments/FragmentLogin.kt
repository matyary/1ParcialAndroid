package Fragments

import Entities.User
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

import com.utn.tp3.R
import database.appDatabase
import database.userDao

/**
 * A simple [Fragment] subclass.
 */
class FragmentLogin : Fragment() {

    lateinit var view_flogin: View
    lateinit var user_flogin: EditText
    lateinit var pass_flogin: EditText
    lateinit var btn_new_user: Button
    lateinit var btn_flogin_to_fselect: Button

    private var db: appDatabase? = null
    private var userDao: userDao? = null
    lateinit var  userList :MutableList<User>

    var flag_log_ok : Int =0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view_flogin = inflater.inflate(R.layout.fragment_login, container, false)

        user_flogin = view_flogin.findViewById(R.id.editText_user_flogin)
        pass_flogin = view_flogin.findViewById(R.id.editText_pass_flogin)
        btn_new_user = view_flogin.findViewById(R.id.button_flogin)
        btn_flogin_to_fselect = view_flogin.findViewById(R.id.button_flogin_to_fselect)

        return view_flogin
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(view_flogin.context)
        userDao = db?.userDao()

        btn_new_user.setOnClickListener {

            val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister()
            view_flogin.findNavController().navigate(action)

        }

        btn_flogin_to_fselect.setOnClickListener {

            if ( user_flogin.text.toString() != "" && pass_flogin.text.toString() != "") {

                userList = userDao?.loadAllPersons() as MutableList<User>
                for ( actualUser in userList){
                    if( actualUser.getNombre() == user_flogin.text.toString() && actualUser.getClave() == pass_flogin.text.toString() ){
                        val action2 = FragmentLoginDirections.actionFragmentLoginToFragmentSelect()
                        view_flogin.findNavController().navigate(action2)
                        flag_log_ok = 1
                    }
                }
                if (flag_log_ok != 1) {
                    Snackbar.make(view_flogin, "Error de Inicio de Sesión", Snackbar.LENGTH_LONG).show()
                }
                flag_log_ok = 0
            }

            else {
                Snackbar.make(view_flogin, "Datos incompletos", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
