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
class FragmentRegister : Fragment() {

    lateinit var view_register: View
    lateinit var user_register: EditText
    lateinit var pass_register: EditText
    lateinit var btn_new_user: Button

    private var db: appDatabase? = null
    private var userDao: userDao? = null

    var i : Int =0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view_register = inflater.inflate(R.layout.fragment_register, container, false)

        user_register = view_register.findViewById(R.id.editText_user_nuser)
        pass_register = view_register.findViewById(R.id.editText_pass_nuser)
        btn_new_user = view_register.findViewById(R.id.button_newuser)

        return view_register
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(view_register.context)
        userDao = db?.userDao()

        btn_new_user.setOnClickListener {

            if ( user_register.text.toString() != "" && pass_register.text.toString() != "") {
                userDao?.insertPerson(User(i,user_register.text.toString(),pass_register.text.toString()))
                i += 1
                val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
                view_register.findNavController().navigate(action)
            }
            else{
                Snackbar.make(view_register, "Datos incompletos", Snackbar.LENGTH_LONG).show()
            }

        }
    }

}
