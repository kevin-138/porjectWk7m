package violet.evergarden.a7minutesworkout

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import violet.evergarden.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {
    private var Binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null

    private var exerciseTimer: CountDownTimer? = null

//    rest progress sisa restnya
    private var restOfProgress = 0

    private var exerciseProgress = 0

    private var exerciseList:ArrayList<ExerciseTemplate>? = null
    private var exercisePosition = -1

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(Binding?.root)
        setSupportActionBar(Binding?.exerciseToolbar)

//        support actionbor itu ngeccek kalo activity ini ada action bar ato enggak, kalo ga ada dia return null
        if (supportActionBar != null){
//            setdisplayhomeasupenabled buat pasang back button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

//        onclick listner buat navigation, karena toolbar itu navigation kita kasi listner
        Binding?.exerciseToolbar?.setNavigationOnClickListener{
//            on back pressed itu buat back
            onBackPressed()
        }

        exerciseList = Constants.defaultExerciseList()

        tts =  TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS){
                tts!!.setLanguage(Locale.ENGLISH)
                tts?.speak("The Next Exercise is ${exerciseList!![exercisePosition+1].getName()}", TextToSpeech.QUEUE_FLUSH, null, "")
            }else{
                Toast.makeText(this, "TTS Initialization failed", Toast.LENGTH_LONG)
                Log.e("TTS","TTS Initialization failed")

            }
        })

//        Binding?.progressBar?.visibility= View.GONE
        setupRestView()
    }

//    ini coman buat make sure timer kita  ga running pas ada timer baru
    private fun setupRestView(){
    Binding?.upcomingTitleText?.visibility= View.VISIBLE
    Binding?.upcomingExercise?.visibility= View.VISIBLE
    Binding?.timerFrame?.visibility= View.VISIBLE
    Binding?.titleText?.visibility= View.VISIBLE
    Binding?.exerciseTimerFrame?.visibility= View.GONE
    Binding?.exerciseText?.visibility=View.GONE
    Binding?.exImage?.visibility=View.INVISIBLE
    Binding?.upcomingExercise?.text = "${exerciseList!![exercisePosition+1].getName()}"

    speaker("The Next Exercise is ${exerciseList!![exercisePosition+1].getName()}")

        if (restTimer!=null){
            restTimer?.cancel()
            restOfProgress = 0
        }

        setRestProgressBar()

    }

    private fun setupExerciseView(){
        Binding?.timerFrame?.visibility= View.GONE
        Binding?.titleText?.visibility= View.INVISIBLE

        Binding?.upcomingTitleText?.visibility= View.INVISIBLE
        Binding?.upcomingExercise?.visibility= View.INVISIBLE

        Binding?.exerciseText?.visibility=View.VISIBLE
        Binding?.exerciseText?.text= "${exerciseList!![exercisePosition].getName()}"
        Binding?.exerciseTimerFrame?.visibility= View.VISIBLE
        Binding?.exImage?.setImageResource(exerciseList!![exercisePosition].getImage())
        Binding?.exImage?.visibility=View.VISIBLE

        if (exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        Binding?.progressBarView?.progress = restOfProgress

//        speaker("The Next Exercise is ${exerciseList!![exercisePosition+1].getName()}")

        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restOfProgress++
                Binding?.progressBarView?.progress = 10 - restOfProgress
                Binding?.textViewTimer?.text = "${(millisUntilFinished/1000).toString()}"
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "The exercise starts now!", Toast.LENGTH_LONG).show()
                exercisePosition++
                setupExerciseView()
            }
        }.start()
    }

    private fun  setExerciseProgressBar(){
        Binding?.exerciseProgressBar?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(60000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                Binding?.exerciseProgressBar?.progress = 60 - exerciseProgress
                Binding?.exerciseTimertextView?.text = "${(millisUntilFinished/1000).toString()}"
            }

            override fun onFinish() {
                if (exercisePosition < exerciseList!!.size-1){
                    Toast.makeText(this@ExerciseActivity, "Time's up! Take a rest for a bit", Toast.LENGTH_LONG).show()
                    Binding?.titleText?.text="TAKE A REST"
                    setupRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity, "Congratulations, you finished exercise session.", Toast.LENGTH_LONG).show()
                }

            }
        }.start()
    }

    private fun speaker(text: String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer!=null){
            restTimer?.cancel()
            restOfProgress = 0
        }

        if (tts!=null){
            tts?.stop()
            tts?.shutdown()
        }

        if (exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        Binding=null
    }
}