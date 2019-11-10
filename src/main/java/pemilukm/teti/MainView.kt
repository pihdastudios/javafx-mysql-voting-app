package pemilukm.teti

import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.stage.WindowEvent
import pemilukm.teti.controls.helpers.ControlsHelper
import util.mysqlcon.MySqlCon

import java.io.IOException
import java.sql.SQLException
import java.util.Objects

class MainView : Application() {

    @FXML
    private var root: Parent? = null
    @FXML
    private val img_1: ImageView? = null
    @FXML
    private val nimFields: TextField? = null
    @FXML
    private val cekNIM_Fields: Text? = null
    @FXML
    private val mainPassFields: PasswordField? = null

    @Throws(IOException::class)
    override fun start(primaryStage: Stage) {
        GlobalVar.primaryStage = primaryStage
        startWindow()
    }

    @Throws(IOException::class)
    private fun startWindow() {
        root = FXMLLoader.load(Objects.requireNonNull(javaClass.getClassLoader().getResource("fxml/MainView.fxml")))

        val scene = Scene(root!!)
        scene.accelerators[KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN)] = Runnable { onAdminBtn() }
        Platform.setImplicitExit(false)
        GlobalVar.mainScene = scene
        GlobalVar.primaryStage!!.title = "Pemilu KMTETI 2019"
        GlobalVar.primaryStage!!.isAlwaysOnTop = true
        //        GlobalVar.primaryStage.setMinWidth(600);
        //        GlobalVar.primaryStage.setMinHeight(400);
        GlobalVar.primaryStage!!.scene = scene
        GlobalVar.kiosk()
        GlobalVar.primaryStage!!.show()
    }

    internal fun newObj(args: Array<String>) {
        Application.launch(*args)
    }

    @FXML
    protected fun onNIM() {
        nimFields!!.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                try {
                    onEnterBtn()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }
        }
    }

    @FXML
    protected fun onPass() {
        nimFields!!.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                try {
                    onEnterBtn()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }
        }
    }

    @FXML
    @Throws(SQLException::class)
    protected fun onEnterBtn() {
        if (mainPassFields!!.text == GlobalVar.passMain) {
            val loadingWindow = LoadingWindow()
            val loadingDialog = ControlsHelper.createModalStageFor(root!!, loadingWindow.content, "")
            loadingWindow.stage = loadingDialog
            loadingDialog.initStyle(StageStyle.UNDECORATED)
            loadingDialog.height = 300.0
            loadingDialog.width = 300.0
            loadingDialog.show()
            if (GlobalVar.connActive == false) {
                cekNIM_Fields!!.text = "Database belum terkoneksi!"
            } else {

                println("Koneksi -> " + MySqlCon.cekConn())

                val cek = MySqlCon.cekNIM(nimFields!!.text)

                if (cek == 0) {
                    cekNIM_Fields!!.text = "NIM Tidak Valid!"
                } else if (cek == 2) {
                    cekNIM_Fields!!.text = "NIM Telah Memilih!"
                } else {
                    val voteWindow = VoteWindow()
                    ControlsHelper.changeScene(voteWindow.content)
                    GlobalVar.textNIM?.let { voteWindow.setTextNIM(it) }
                    //                    GlobalVar.primaryStage.hide();
                    GlobalVar.valueNIM = nimFields.text
                    GlobalVar.kiosk()
                    GlobalVar.primaryStage!!.show()
                    nimFields.text = ""
                    mainPassFields.text = ""
                    cekNIM_Fields!!.text = ""
                }
            }
            loadingWindow.setLabelText("Done")
            loadingDialog.close()
        } else {
            cekNIM_Fields!!.text = "Password salah!"
        }
    }

    @FXML
    protected fun onAdminBtn() {
        val popupView = PopupView()
        val popupDialog = ControlsHelper.createModalStageFor(root!!, popupView.content, "Admin")
        popupView.stage = popupDialog
        popupDialog.initStyle(StageStyle.UNDECORATED)
        popupDialog.height = 300.0
        popupDialog.width = 300.0
        popupDialog.show()
    }
}
