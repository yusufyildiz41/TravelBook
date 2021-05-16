package com.yusufyildiz.kotlintravelbook.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yusufyildiz.kotlintravelbook.Adapter.CustomAdapter
import com.yusufyildiz.kotlintravelbook.Model.Place
import com.yusufyildiz.kotlintravelbook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var placeArray = ArrayList<Place>()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try
        {
            var database = this.openOrCreateDatabase("Places",Context.MODE_PRIVATE,null)
            var cursor = database.rawQuery("SELECT * FROM places",null)
            var addressIndex = cursor.getColumnIndex("address")
            var latitudeIndex = cursor.getColumnIndex("latitude")
            var longitudeIndex = cursor.getColumnIndex("longitude")
            while(cursor.moveToNext())
            {
                var addressFromDatabase = cursor.getString(addressIndex)
                var latitudeFromDatabase = cursor.getDouble(latitudeIndex)
                var longitudeFromDatabase = cursor.getDouble(longitudeIndex)

                val myPlace = Place(addressFromDatabase,latitudeFromDatabase,longitudeFromDatabase)

                placeArray.add(myPlace)



            }
            cursor.close()


        }catch (e:Exception)
        {
            e.printStackTrace()

        }
        val customAdapter = CustomAdapter(placeArray,this)
        listView.adapter = customAdapter
        listView.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("selectedPlace",placeArray.get(position))
            startActivity(intent)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_place,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.add_place_option)
        {
            var intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("info","new")

            startActivity(intent)


        }
        return super.onOptionsItemSelected(item)
    }
}