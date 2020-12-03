   package com.alex.registryemployers.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alex.registryemployers.R
import com.alex.registryemployers.model.Employer
import com.alex.registryemployers.viewmodel.EmployerViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import java.util.*

   class AddFragment : Fragment() {

    private lateinit var mEmployerViewModel: EmployerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mEmployerViewModel = ViewModelProvider(this).get(EmployerViewModel::class.java)

        view.findViewById<TextView>(R.id.add_button).setOnClickListener{
            insertDataToDatabase()
        }

        val mPickTimeBtn = view.data_picker
        val textView     = view.date_of_employment

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(layoutInflater.context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textView.setText("" + dayOfMonth + "." + month + "." + year)
            }, year, month, day)
            dpd.show()

        }

        return view
    }

   private fun insertDataToDatabase() {

       val firstName = addFirstName_et.text.toString()
       val lastName = addLastName_et.text.toString()
       val age = addAdge_et.text
       val positionInCompany = addPosition_et.text.toString()
       val dateOfEmployment = date_of_employment.text.toString()

       if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(dateOfEmployment) && !TextUtils.isEmpty(positionInCompany)
               && !TextUtils.isEmpty(lastName) && !age.isEmpty() && age.toString().length > 0) {
           //Create Employer Object
           val employer = Employer(0, firstName, lastName, Integer.parseInt(age.toString()), positionInCompany, dateOfEmployment)
           //Add data to database
           mEmployerViewModel.addEmployer(employer)
           Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
           //Navigate Back
           findNavController().navigate(R.id.action_addFragment_to_itemFragment)
       } else {
           Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
       }
   }
}