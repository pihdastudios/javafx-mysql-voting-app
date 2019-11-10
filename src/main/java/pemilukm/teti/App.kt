package pemilukm.teti

object App {
    val APP_VERSION = "1.0"

    @JvmStatic
    fun main(args: Array<String>) {
        val mainView = MainView()
        mainView.newObj(args)
    }

}
