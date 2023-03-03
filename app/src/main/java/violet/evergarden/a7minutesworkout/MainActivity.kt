package violet.evergarden.a7minutesworkout

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import violet.evergarden.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    pertama taro view binding di gradle, trus bikkin var ini
//    datatypenya namafilexml tanpa _ +Binding, contoh:
    private var binding: ActivityMainBinding? = null
    var ttsSwitch: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        layourinflater buat nge inflate xml jadi bisa dipake di setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding ini jadi ke connect dengan file xml activity main kita
        setContentView(binding?.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

//        kalo uda ada view binding gaperlu pake ini
//        val startButton: FrameLayout = findViewById(R.id.buttonFrameView)

//        bisa langsung kayak gini
        binding?.buttonFrameView?.setOnClickListener{
            Toast.makeText(this,"we're gonna start the Exercise",Toast.LENGTH_LONG).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra(Constants.TTS_Status,ttsSwitch)
//            putExtra(Constants.tts_Status,ttsSwitch)
            startActivity(intent)
        }
//        jadi binding itu megang 1 file xml itu jadi kita tinggal nge declare bindingnya
//                trus semua isi dari file xml itu jaadi udah ada gperlu findviewbyid tiap view

        binding?.ttsButton?.setOnClickListener {
            if(binding?.ttsButton?.text == "TTS OFF"){
                ttsSwitch = "on"
                binding?.ttsButton?.text = "TTS ON"
                binding?.ttsButton?.setTextColor(Color.parseColor("#EB6440"))
            }
            else if (binding?.ttsButton?.text == "TTS ON"){
                ttsSwitch = "off"
                binding?.ttsButton?.text = "TTS OFF"
                binding?.ttsButton?.setTextColor(Color.WHITE)
            }
        }

    }

//    jangan lupa kalo pake binding di paling bawah main activity
//    di onDestroy buat mencegah memory leak
    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}