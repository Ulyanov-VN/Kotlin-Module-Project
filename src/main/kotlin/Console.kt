class Console : UserInterface {
    override fun showMessage(message: String) = println(message)

    override fun promptString(prompt: String): String {
        println(prompt)
        return readLine()?.trim().orEmpty()
    }

    override fun promptInt(prompt: String): Int? {
        println(prompt)
        return readLine()?.trim()?.toIntOrNull()
    }
}