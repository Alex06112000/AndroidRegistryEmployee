package com.alex.registryemployers.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alex.registryemployers.R
import com.alex.registryemployers.model.Employer
import kotlinx.android.synthetic.main.employer_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var employerList = emptyList<Employer>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.employer_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = employerList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstNameval
        holder.itemView.lastName_txt.text = currentItem.lastName
        holder.itemView.age_txt.text = currentItem.age.toString()
        holder.itemView.positionInCompany_txt.text = currentItem.positionInCompany
        holder.itemView.dateOfEmployment_txt.text = currentItem.dateOfEmployment

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener{
            val action = ListFragmentDirections.actionItemFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return employerList.size
    }

    fun setData(employer: List<Employer>){
        this.employerList = employer
        notifyDataSetChanged()
    }

}