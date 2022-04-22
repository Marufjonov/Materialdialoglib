package com.marufjonov.materialdialoglib

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.color.colorChooser
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder




object MySimpleDialog {


    fun showLocation(
        context: Context,
        title: String,
        message: String,
        posBtn: String,
        negBtn: String
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(negBtn) { dialog, which ->

            }
            .setPositiveButton(posBtn) { dialog, which ->

            }
            .show()
    }

    fun showNetworkList(
        context: Context,
        title: String,
        posBtn: String,
        singleList: Array<String>,
        chekedItem: Int
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setPositiveButton(posBtn) { dialog, which ->

            }

            .setSingleChoiceItems(singleList, chekedItem) { dialog, which ->
                Toast.makeText(context, singleList[which], Toast.LENGTH_SHORT).show()
            }
            .show()
    }


    fun showDatePicker(title: String, fragmentManager: FragmentManager) {
        var datapicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(title)
            .build()
        datapicker.show(fragmentManager, "tag")
    }

    fun showWifi(context: Context) {
        var dialog = createDialog(context) as Dialog
        dialog.show()

    }


    @SuppressLint("CheckResult")
    fun showColor(context: Context, title: String, colorList: IntArray) {
        MaterialDialog(context).show {
            title(R.string.app_name, title)
            colorChooser(colorList) { dialog, color ->
                Toast.makeText(context, color.toString(), Toast.LENGTH_SHORT).show()
            }
            negativeButton(null, "Cancel") {
                it.dismiss()
            }
            positiveButton(null, "Select")
        }
    }

    fun createDialog(context: Context): Dialog {
        var dialog = Dialog(context) as Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.item_bottom)


        var txtCancel = dialog.findViewById<TextView>(R.id.txt_cancel)
        var txtConnect = dialog.findViewById<TextView>(R.id.txt_connect)
        var checkbox1 = dialog.findViewById<CheckBox>(R.id.checkbox1)
        var etPassword = dialog.findViewById<EditText>(R.id.et_password)



        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        txtCancel.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()


        }

        txtConnect.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()

        }
        
        
        checkbox1.setOnClickListener {

            if (checkbox1.isChecked){
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                checkbox1.setText("Hide password")
            }else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                checkbox1.setText("Show password")
            }
        }

        return dialog
    }

}