package com.example.happyplaces.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.R
import com.example.happyplaces.models.HappyPlaceModel

class HappyPlaceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_place_detail)

        var happyPlaceDetailModel : HappyPlaceModel? = null
        val toolbarHappyPlaceDetail : Toolbar = findViewById(R.id.toolbar_happy_place_detail)
        val ivPlaceImage : ImageView = findViewById(R.id.iv_place_image)
        val tvDescription : TextView = findViewById(R.id.tv_description)
        val tvLocation : TextView = findViewById(R.id.tv_location)
        val btnViewOnMap : Button = findViewById(R.id.btn_view_on_map)

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAIL)){
            happyPlaceDetailModel = intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAIL) as HappyPlaceModel?
        }

        if (happyPlaceDetailModel != null){
            setSupportActionBar(toolbarHappyPlaceDetail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailModel.title

            toolbarHappyPlaceDetail.setNavigationOnClickListener {
                onBackPressed()
            }
        }
        ivPlaceImage.setImageURI(Uri.parse(happyPlaceDetailModel?.image))
        tvDescription.text = happyPlaceDetailModel?.description
        tvLocation.text = happyPlaceDetailModel?.location

        btnViewOnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_PLACE_DETAIL, happyPlaceDetailModel)
            startActivity(intent)
        }
    }
}