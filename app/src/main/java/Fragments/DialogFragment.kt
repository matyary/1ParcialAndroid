package Fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.github.nikartm.button.FitButton
import com.utn.tp3.R

class DialogFragment :  DialogFragment() {


    lateinit var v: View

    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton

    val args: DialogFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dialog, container, false)

        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        return v
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onStart() {
        super.onStart()

        btnCancel.setOnClickListener() {
            val actionCancel = DialogFragmentDirections.actionDialogFragmentToFragmentSelect()
            v.findNavController().navigate(actionCancel)
        }

        btnAccept.setOnClickListener {
            val actionErase = DialogFragmentDirections.actionDialogFragmentToFragmentSelect(args.EraseQuestionSport)
            v.findNavController().navigate(actionErase)
        }
    }
}



