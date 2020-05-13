package Fragments

import Entities.User
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
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

    lateinit var mp: MediaPlayer
    lateinit var sound: ToggleButton
    lateinit var txtMusic: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view_register = inflater.inflate(R.layout.fragment_register, container, false)

        user_register = view_register.findViewById(R.id.editText_user_nuser)
        pass_register = view_register.findViewById(R.id.editText_pass_nuser)
        btn_new_user = view_register.findViewById(R.id.button_newuser)

        mp = MediaPlayer.create(requireActivity(), R.raw.rocky)
        sound = view_register.findViewById(R.id.music)
        txtMusic = view_register.findViewById(R.id.music_state)

        return view_register
    }

    override fun onStart() {
        super.onStart()

        sound.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                mp.pause()
                txtMusic.text = "OFF"
            } else {
                // The toggle is disabled
                mp.start()
                txtMusic.text = "ON"
            }
        }

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

    override fun onResume() {
        super.onResume()
        mp.start()
        txtMusic.text = "ON"
    }

    override fun onStop() {
        super.onStop()
        mp.pause()
        txtMusic.text = "OFF"
    }

}
