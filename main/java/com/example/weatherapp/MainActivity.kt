package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        fetchWeatherData()
    }
    private fun SearchCity(){}
    val searchView = binding.searchView
     val searchView.setOnQuerryTextListener(Object:SearchView.onQuerryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean{
            fetchWeatherData(query)
            return true
        }
    })
    private fun fetchWeatherData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConvertorFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5")
            .build().create(Apiinterface::class.java)
        val response = retrofit.getWeatherData(city: "kanpur" , apiid:"244452352352asdsf9553e2",units:"metric")
            response.enqueue(object:Callback<WeatherApp>{
                override fun onResponse(call:Call<WeatherApp>, response:Response<WeatherApp>){
                    val responseBody = response.body()
                    if(response.isSuccessful ){
                        val temperature = responseBody.main.temp.toString()
                       val humidity = responseBody.main.humidity
                        val sunRise = responseBody.sys.sunrise
                        // Log.d(tag:"TAG" , "onResponse: $temperature"
                       binding.textView2.text = "$temperature "C""
                       binding.textView3.text = "MaxTemp : $maxTemp"
                    }
                }
                override fun onFailure(call:Call<WeatherApp>,t:Throwable){
                    // TODO: "Not yet Derived 
                }

            })
    }

}