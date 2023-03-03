package violet.evergarden.a7minutesworkout

class ExerciseTemplate(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {
    fun getId():Int{
        return id
    }

    fun setId(Id: Int){
        this.id = Id
    }

    fun getName():String{
        return name
    }

    fun setName(Name: String){
        this.name = Name
    }

    fun getImage():Int{
        return image
    }

    fun setImage(Image: Int){
        this.image = Image
    }

    fun getStatus():Boolean{
        return isCompleted
    }

    fun setStatus(status: Boolean){
        this.isCompleted = status
    }

    fun getSelected():Boolean{
        return isSelected
    }

    fun setSelected(selected: Boolean){
        this.isSelected = selected
    }

}