package com.example.mad_pr_10_21012021044

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.ArrayList

class PersonAdapter(context: Context, val array:ArrayList<Person>):ArrayAdapter<Person>(context,0, array) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent,false)
        val persondata=array.get(position)
        view.findViewById<TextView>(R.id.p_name1).text=persondata.name
        view.findViewById<TextView>(R.id.p1_email).text=persondata.emailId
        view.findViewById<TextView>(R.id.p1_add).text=persondata.address
        view.findViewById<TextView>(R.id.p1_num).text=persondata.phoneNo
        return view
    }
}