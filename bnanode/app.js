kotlin = require('../out/production/BnaServer/lib/kotlin.js');
app = require('express')();
server = require('http').Server(app);
io = require('socket.io')(server);
require('../out/production/BnaServer/BnaServer.js');
