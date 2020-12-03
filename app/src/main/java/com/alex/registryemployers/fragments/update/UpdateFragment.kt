package com.alex.registryemployers.fragments.update

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alex.registryemployers.R
import com.alex.registryemployers.model.Employer
import com.alex.registryemployers.viewmodel.EmployerViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mEmployerViewModel: EmployerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mEmployerViewModel = ViewModelProvider(this).get(EmployerViewModel::class.java)

        view.updateFirstName_et.setText(args.CurentEmployer.firstNameval)
        view.updateLastName_et.setText(args.CurentEmployer.lastName)
        view.updateAge_et.setText(args.CurentEmployer.age.toString())
        view.update_Position_et.setText(args.CurentEmployer.positionInCompany)
        view.update_date_of_employment.setText(args.CurentEmployer.dateOfEmployment)

        view.update_button.setOnClickListener {
            updateItem()
        }
        //Add menu
        setHasOptionsMenu(true)


        val mPickTimeBtn = view.update_data_picker
        val textView     = view.update_date_of_employment

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

    private fun updateItem(){

        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = updateAge_et.text.toString()
        val positionInCompany = update_Position_et.text.toString()
        val dateOfEmployment = update_date_of_employment.text.toString()

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(dateOfEmployment) && !TextUtils.isEmpty(positionInCompany)
                && !TextUtils.isEmpty(lastName) && !updateAge_et.text.isEmpty() && updateAge_et.text.toString().length > 0){
            //Create Employer Object
            val updateEmployer = Employer(args.CurentEmployer.id, firstName, lastName, Integer.parseInt(age), positionInCompany,  dateOfEmployment )
            //Update Current Employer
            mEmployerViewModel.updateEmployer(updateEmployer)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.registry_navigation_resource)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
           deleteEmployer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEmployer(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mEmployerViewModel.deleteEmployer(args.CurentEmployer)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.CurentEmployer.firstNameval}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.registry_navigation_resource)
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete ${args.CurentEmployer.firstNameval}?")
        builder.setMessage("Are you sure you want to delete ${args.CurentEmployer.firstNameval}")
        builder.create().show()
    }

}