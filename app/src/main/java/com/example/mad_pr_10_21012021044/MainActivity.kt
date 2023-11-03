package com.example.mad_pr_10_21012021044

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var personListView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personListView=findViewById(R.id.listview_main)

        val btn_flt :FloatingActionButton=findViewById(R.id.floating_btn)
        btn_flt.setOnClickListener{
            setPersonDataToListView()
        }
    }
    fun setPersonDataToListView(){

        //personListView.adapter=PersonAdapter(this, arrayListOf(
           // Person("01","Ash","ash@email.com","9568472103","ahemedabad", 23.0225 , 72.5714),
           // Person("02","Krup","krup@email.com","9568472104","my home", 23.0225 , 72.5714),
            //Person("03","Rutz","rutz@email.com","9568472105","ahemedabad", 23.0225 , 72.5714),
            //Person("04","Kru","kru@email.com","9568472106","ahemedabad", 23.0225 , 72.5714),
            //Person("05","Vish","vish@email.com","9568472107","ahemedabad", 23.0225 , 72.5714),
            //Person("06","Sujz","suj@email.com","9568472108","ahemedabad", 23.0225 , 72.5714),
        //))

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = HttpRequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "463uju7rr7teg8g3gut6r299uy4dyuyewxtkxapf")
                withContext(Dispatchers.Main) {
                    try {
                        if(data != null)
                            runOnUiThread{getPersonDetailsFromJson(data)}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            val listView1 : ListView = findViewById(R.id.listview_main)
            listView1.adapter = PersonAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
}