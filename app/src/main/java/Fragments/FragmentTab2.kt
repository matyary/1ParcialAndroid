package Fragments

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.utn.tp3.R

class FragmentTab2 : Fragment() {

    lateinit var view_tab2: View
    lateinit var btnMail : Button
    lateinit var btnWhatsApp : Button
    lateinit var btnCall : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        view_tab2 = inflater.inflate(R.layout.fragment_tab2, container, false)

        btnMail = view_tab2.findViewById(R.id.btn_mail)
        btnWhatsApp = view_tab2.findViewById(R.id.btn_wa)
        btnCall = view_tab2.findViewById(R.id.btn_call)

        checkPermission()

        btnMail.setOnClickListener {
            val mailto = "mailto:"+ "matias.gruner@gmail.com"
            "?cc=" + "" +
                    "&subject=" + Uri.encode("Consulta") +
                    "&body=" + Uri.encode("Hola Matías")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
            }

        }

        btnWhatsApp.setOnClickListener {

            val uri: Uri = Uri.parse("smsto:1164548117")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.setPackage("com.whatsapp")
            startActivity(Intent.createChooser(intent, ""))

        }

        btnCall.setOnClickListener {

            callPhone()
        }
        return view_tab2
    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                            Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.CALL_PHONE),
                        42)
            }
        } else {
            // Permission has already been granted
            //   callPhone()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                //  callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))
        if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}
