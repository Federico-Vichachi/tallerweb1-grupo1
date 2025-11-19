'use strict';

document.addEventListener('DOMContentLoaded', function () {

    if (typeof destinatarioUsername === 'undefined' || destinatarioUsername === null) {
        return;
    }

    const messageForm = document.querySelector('#messageForm');
    const messageInput = document.querySelector('#message');
    const messageArea = document.querySelector('#messages');

    if (!messageForm || !messageInput || !messageArea) {
        console.error('Error: No se encontraron los elementos necesarios para el chat en el HTML.');
        return;
    }

    let stompClient = null;

    function displayMessage(message, type) {
        const li = document.createElement('li');
        li.classList.add(type);

        const div = document.createElement('div');
        div.classList.add('message-bubble');

        const p = document.createElement('p');
        p.classList.add('mb-0');
        p.textContent = message.contenido;

        div.appendChild(p);
        li.appendChild(div);
        messageArea.appendChild(li);

        messageArea.scrollTop = messageArea.scrollHeight;
    }

    function sendMessage(event) {
        event.preventDefault();

        const messageContent = messageInput.value.trim();

        if (messageContent && stompClient) {
            const chatMessage = {
                remitente: remitenteUsername,
                contenido: messageContent
            };

            stompClient.send("/app/chat.private." + destinatarioUsername, {}, JSON.stringify(chatMessage));
            displayMessage(chatMessage, 'sent');
            messageInput.value = '';
        }
    }

    function onMessageReceived(payload) {
        const message = JSON.parse(payload.body);
        displayMessage(message, 'received');
    }

    function onConnected() {
        stompClient.subscribe('/user/queue/reply', onMessageReceived);
    }

    function connect() {
        const socket = new SockJS(contextPath + 'chat-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, (error) => {
            console.error('No se pudo conectar a WebSocket: ' + error);
        });
    }

    connect();

    messageForm.addEventListener('submit', sendMessage);

    messageArea.scrollTop = messageArea.scrollHeight;
});