package tottaly.not.websocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : AppCompatActivity() {

    private lateinit var websocketListener: WebsocketListener
    private lateinit var viewModel: MainViewModel
    private val okHttpClient = OkHttpClient()
    private var webSocket: WebSocket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnect = findViewById<Button>(R.id.btnConnect)
        val btnDisconnect = findViewById<Button>(R.id.btnDisconnect)
        val message = findViewById<TextView>(R.id.message)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val mensagemText = findViewById<TextView>(R.id.mensagemText)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        websocketListener = WebsocketListener(viewModel)

        viewModel.socketStatus.observe(this, Observer {
            message.text = if (it) "Connected" else "Disconnected"
        })

        var text = ""
        var lastMessage = ""
        viewModel.message.observe(this, Observer{
            text += "${if (it.first) "You: " else "Other: "} ${it.second}\n"
            message.text = text
            lastMessage = it.second
        })
        btnConnect.setOnClickListener {
            webSocket = okHttpClient.newWebSocket(createRequest(), websocketListener)
        }
        btnDisconnect.setOnClickListener {
            webSocket?.close(1000, "Cancelled Manually")
        }
        btnEnviar.setOnClickListener {
            if (mensagemText.text.toString().isNotEmpty()) {
                webSocket?.send(mensagemText.text.toString())
                viewModel.setMessage(Pair(true, mensagemText.text.toString()))
                mensagemText.run { setText("") }
            } else {
                Toast.makeText(this, "Digite alguma coisa...", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createRequest(): Request {
        val webSocketUrl = "wss://free.blr2.piesocket.com/v3/1?api_key=eNh8bNEpZ3mA5jViP7Mo8BoRICiexy5Zt9dPh6xp&notify_self=1"
        return Request.Builder()
            .url(webSocketUrl)
            .build()
    }
}