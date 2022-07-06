package com.example.timepass

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.*


class NewPage : AppCompatActivity() {

    companion object {
     const val NAME_EXTRA = "name_extra"
    }
    var currentImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_page)

       val name = intent.getStringExtra(NAME_EXTRA)
        val Memetext = findViewById<TextView>(R.id.Memetext)
        Memetext.text = "Hey $name! Enjoy your day by seeing this cool meme."

        CreateMeme()
    }

    private fun CreateMeme()
    {
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val memeimage = findViewById<ImageView>(R.id.memeimage)

        progressbar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
       val jsonObjectRequest = JsonObjectRequest(
           Request.Method.GET, url,  null,
            Response.Listener { response ->
            currentImageUrl = response.getString("url")
            Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressbar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressbar.visibility = View.GONE
                    return false
                }
            }).into(memeimage)

            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
    fun shareButton(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey! check out this cool meme I got from Reddit $currentImageUrl")
        val chooser = Intent.createChooser(intent,"Share this meme using...")
        startActivity(chooser)
    }
    fun nextButton(view: View) {
      CreateMeme()
    }
}
