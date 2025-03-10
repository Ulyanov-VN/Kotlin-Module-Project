interface UserInterface {
    fun showMessage(message: String)
    fun promptString(prompt: String): String
    fun promptInt(prompt: String): Int?
}