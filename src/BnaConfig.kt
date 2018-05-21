import kotlin.js.Json
import kotlin.js.json

external val server: Server
external val io: IO

fun main(args: Array<String>) {
    server.listen(8080) {
        console.log("Server is now running...")
    }
    io.on("connection") { socket ->
        console.log("Player Connected!")
        socket.emit("SocketID", json("id" to socket.id))
        socket.broadcast.emit("NewPlayerJoined", json("id" to socket.id))
        socket.on("disconnect") {
            console.log("Player Disconnected")
        }
    }
}

external class Server {
    fun listen(port: Int, callback: () -> Unit)
}

external class IO {
    fun on(event: String, callback: (Socket) -> Unit)
}

external class Socket {
    fun on(event: String, callback: () -> Unit)

    fun emit(name: String, info: Json)

    var id: String

    var broadcast: Socket
}

/*
io.on('connection', function(socket) {
    console.log("Player connected!");
    socket.emit('SocketID', { id: socket.id })
    socket.broadcast.emit('NewPlayerJoined', { id: socket.id });
    socket.on('disconnect', function() {
        console.log("Player disconnected");
    });
});

function host(id, jsonGameState) {
    this.id = id;
    this.state = jsonGameState;
}

function player (id) {
    this.id = id;
}
 */