
var app = require('http').createServer(handler)
var io = require('socket.io')(app);
var fs = require('fs');

app.listen(8095);

function handler (req, res) {
  fs.readFile(__dirname + '/index.html',
  function (err, data) {
    if (err) {
      res.writeHead(500);
      return res.end('Error loading index.html');
    }

    res.writeHead(200);
    res.end(data);
  });
}

 
io.on('connection', function (socket) {
  socket.emit('news', { hello: 'world' });
  socket.on('my other event', function (data) {
    console.log(data);
  });
  
  // socket.io 使用 emit(eventname,data) 发送消息，使用on(eventname,callback)监听消息
     //监听客户端发送的 sendMsg 事件
	 socket.on("sendMsg", function (data) {
		 // data 为客户端发送的消息，可以是 字符串，json对象或buffer
		 // 使用 emit 发送消息，broadcast 表示 除自己以外的所有已连接的socket客户端。
		 socket.broadcast.emit("receiveMsg", data);
	 })
  
});

