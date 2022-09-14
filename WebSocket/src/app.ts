import {createServer} from 'http';
import {Server, Socket} from 'socket.io';
import Message from './models/Message';
import GamePlay from "./models/GamePlay";
import MultiGame from "./models/MultiGame";
import axios from "axios";

const httpServer = createServer();

const io = new Server(httpServer, {
    cors: {
        origin: 'http://localhost:4200',
    }
});

let springServerUrl = 'http://localhost:8080';


io.on('connection', (socket: Socket) => {

    let jwtToken: string | null;

    let config = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + jwtToken
        }
    };

    socket.on('token', (token: string) => {
        jwtToken = token;
        config.headers.Authorization = 'Bearer ' + jwtToken;
    });

    socket.on('message', (msg: Message) => {
        io.emit('message', msg);
    });

    socket.on('createRoom', async (username: string) => {
        let serverResponse: MultiGame;
        await axios.post(springServerUrl + '/api/multi/create', {"username": username}, config).then((response) => {
            serverResponse = response.data;
            socket.join(serverResponse.gameId);
            io.emit('roomCreated', serverResponse);
        }).catch((error) => {
            console.log(error);
        });
    });

    socket.on('connectRoom', async (username: string, gameId: string) => {
        let serverResponse: MultiGame;
        let data = {
            player: {
                username: username,
            },
            gameId: gameId
        }
        await axios.post(springServerUrl + '/api/multi/connect', data, config).then((response) => {
            serverResponse = response.data;
            socket.join(serverResponse.gameId);
            io.emit('connectRoom', serverResponse);
        }).catch((error) => {
            console.log(error);
        });
    });

    socket.on('connectRandom', async (username: string) => {
        let serverResponse: MultiGame;
        await axios.post(springServerUrl + '/api/multi/connect/random', {"username": username}, config).then((response) => {
            serverResponse = response.data;
            socket.join(serverResponse.gameId);
            io.emit('randomConnected', serverResponse);
        }).catch((error) => {
            console.log(error);
        });
    });

    socket.on('play', async (play: GamePlay) => {
        let serverResponse: MultiGame;
        await axios.post(springServerUrl + '/api/multi/play', play, config).then((response) => {
            serverResponse = response.data;
            io.to(serverResponse.gameId).emit('play', serverResponse);
        }).catch((error) => {
            console.log(error);
        });
    });

});

httpServer.listen(3000, () => {
    console.log("listening on *:3000");
});
