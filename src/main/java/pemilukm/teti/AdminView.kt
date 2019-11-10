package pemilukm.teti

import javafx.application.Platform
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import javafx.stage.StageStyle
import pemilukm.teti.controls.helpers.ControlsHelper
import util.mysqlcon.MySqlCon

import java.awt.*
import java.io.IOException

class AdminView internal constructor() : VBox() {
    internal var content: Parent
    @FXML
    private val root: Parent? = null
    private val desktop = Desktop.getDesktop()
    @FXML
    private val usernameField: TextField? = null
    @FXML
    private val passwordField: PasswordField? = null
    @FXML
    private val urlField: TextField? = null
    @FXML
    private val dbNameField: TextField? = null
    @FXML
    private val connectBtn: Button? = null
    @FXML
    private val createDbBtn: Button? = null
    @FXML
    private val backupBtn: Button? = null
    @FXML
    private val exitBtn: Button? = null
    @FXML
    private val openFolderBtn: Button? = null
    @FXML
    private val backBtn1: Button? = null
    @FXML
    private val backBtn: Button? = null
    private var dir: String? = null
    private val primaryStage: Stage? = null

    init {
        content = ControlsHelper.initControl(this)
    }

    @FXML
    protected fun onUsername() {
        usernameField!!.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                onConnectBtn()
            }
        }
    }

    @FXML
    protected fun onConnectBtn() {
        val loadingWindow = LoadingWindow()
        val loadingDialog = ControlsHelper.createModalStageFor(content, loadingWindow.content, "")
        loadingWindow.stage = loadingDialog
        loadingDialog.initStyle(StageStyle.UNDECORATED)


        //
        val task = object : Task<Boolean>() {
            public override fun call(): Boolean {
                setData()
                MySqlCon.startConnection()
                return true
            }
        }

        task.setOnRunning { e ->
            loadingDialog.show()

        }
        task.setOnSucceeded { e ->
            loadingDialog.hide()
            loadingDialog.close()
        }
        Thread(task).start()

    }

    @FXML
    protected fun onBackupBtn() {
        setData()
        dir?.let { MySqlCon.backupDbtoSql(it) }
    }

    @FXML
    protected fun onCreateDbBtn() {
        setData()
        MySqlCon.newDb()
    }

    private fun setData() {
        MySqlCon.username = usernameField!!.text
        MySqlCon.password = passwordField!!.text
        MySqlCon.address = urlField!!.text

        MySqlCon.dbName = dbNameField!!.text
    }

    @FXML
    protected fun onOpenFolderBtn() {
        val directoryChooser = DirectoryChooser()
        dir = directoryChooser.showDialog(primaryStage).absolutePath
    }

    @FXML
    protected fun onBackBtn() {
        GlobalVar.primaryStage?.scene = GlobalVar.mainScene
        GlobalVar.primaryStage?.isFullScreen  = true
        GlobalVar.primaryStage?.show()
    }


    @FXML
    protected fun onExitBtn() {
        Platform.exit()
        System.exit(0)
    }
}
