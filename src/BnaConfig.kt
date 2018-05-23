import kotlin.js.Json
import kotlin.js.json

external val server: Server
external val io: IO

fun main(args: Array<String>) {
    server.listen(8080) {
        console.log("Server is now running...")
    }
    io.on("connection") {
        it.apply {
            console.log("Player Connected! ID: ${id}")
            emit("SocketID", json("id" to id))
            broadcast.emit("NewPlayerJoined", json("id" to id))
            on("disconnect") {
                console.log("Player Disconnected, ID: $id")
            }
            on("TestEmit") {
                val name = it.message
                console.log("$id: $name")
            }
        }
    }
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