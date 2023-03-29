package com.example.smarthome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*


class SecondActivity:Activity() {
    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: ArrayList<String>? = null
    private var data_for_list:HashMap<String, List<String>> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val back_image = findViewById<ImageView>(R.id.back_image)
        back_image.setOnClickListener {
            val start_intent = Intent(this, MainActivity::class.java)
            startActivity(start_intent)
        }

        val light: MutableList<String> = ArrayList()
        light.add("India")
        light.add("Pakistan")
        light.add("Australia")
        light.add("England")
        light.add("South Africa")

        val socket: MutableList<String> = ArrayList()
        socket.add("Brazil")
        socket.add("Spain")
        socket.add("Germany")
        socket.add("Netherlands")
        socket.add("Italy")

        val kettle: MutableList<String> = ArrayList()
        kettle.add("United States")
        kettle.add("Spain")
        kettle.add("Argentina")
        kettle.add("France")
        kettle.add("Russia")

        data_for_list.put("Light", light)
        data_for_list.put("Socket", socket)
        data_for_list.put("Kettle", kettle)
        titleList = ArrayList(data_for_list.keys)
        expandableListView = findViewById(R.id.expendableList)
        if (expandableListView != null) {
            adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, data_for_list)
            expandableListView!!.setAdapter(adapter)
            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            expandableListView!!.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                Toast.makeText(
                    applicationContext,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + data_for_list[(
                            titleList as
                                    ArrayList<String>
                            )
                            [groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

        }
        var spinner = findViewById<Spinner>(R.id.spinner)
        val title_for_spinner = ArrayList<String>()
        title_for_spinner.add("All")
        title_for_spinner.addAll(titleList!!)
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.my_selected_item, title_for_spinner)
        arrayAdapter.setDropDownViewResource(R.layout.my_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                var list = ArrayList<String>()
                if (title_for_spinner[p2] == "All") {
                    list = ArrayList(data_for_list.keys)
                } else {
                    list.add(title_for_spinner[p2])
                }
                Log.e("ss", list.size.toString())
                expandableListView = findViewById(R.id.expendableList)
                if (expandableListView != null) {
                    adapter = CustomExpandableListAdapter(this@SecondActivity, list as ArrayList<String>, data_for_list)
                    expandableListView!!.setAdapter(adapter)
                    expandableListView!!.setOnGroupExpandListener { groupPosition ->
                        Toast.makeText(
                            applicationContext,
                            (list as ArrayList<String>)[groupPosition] + " List Expanded.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                        Toast.makeText(
                            applicationContext,
                            (list as ArrayList<String>)[groupPosition] + " List Collapsed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    expandableListView!!.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                        Toast.makeText(
                            applicationContext,
                            "Clicked: " + (list as ArrayList<String>)[groupPosition] + " -> " + data_for_list[(
                                    list as
                                            ArrayList<String>
                                    )
                                    [groupPosition]]!!.get(
                                childPosition
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                        false
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }

        }
    }
}


