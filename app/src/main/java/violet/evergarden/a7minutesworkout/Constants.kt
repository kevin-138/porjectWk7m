package violet.evergarden.a7minutesworkout

object Constants {
    const val TTS_Status: String = "off"

    fun defaultExerciseList(): ArrayList<ExerciseTemplate>{
        val exerciseList = ArrayList<ExerciseTemplate>()

        val stretching =  ExerciseTemplate(
            1,
            "Stretching and Warm Up",
            R.drawable.exercise1,
            false,
            false
        )
        exerciseList.add(stretching)

        val pullUp =  ExerciseTemplate(
            2,
            "Pull Up",
            R.drawable.exercise2,
            false,
            false
        )
        exerciseList.add(pullUp)

        val sitUp =  ExerciseTemplate(
            3,
            "Sit Up",
            R.drawable.exercise3,
            false,
            false
        )
        exerciseList.add(sitUp)

        val pushUp =  ExerciseTemplate(
            4,
            "Push Up",
            R.drawable.exercise4,
            false,
            false
        )
        exerciseList.add(pushUp)

        val sideUp =  ExerciseTemplate(
            5,
            "Side Push Up",
            R.drawable.exercise5,
            false,
            false
        )
        exerciseList.add(sideUp)

        val lunge =  ExerciseTemplate(
            6,
            "Lunges",
            R.drawable.exercise6,
            false,
            false
        )
        exerciseList.add(lunge)

        val jump =  ExerciseTemplate(
            7,
            "Straight Jump",
            R.drawable.exercise7,
            false,
            false
        )
        exerciseList.add(jump)


        return exerciseList
    }

}