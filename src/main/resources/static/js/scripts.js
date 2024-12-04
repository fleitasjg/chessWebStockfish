document.addEventListener("DOMContentLoaded", function() {
    const chessboard = document.getElementById("chessboard");
    
    // Inicializa el tablero
    for (let i = 0; i < 64; i++) {
        const square = document.createElement("div");
        square.dataset.index = i;
        chessboard.appendChild(square);
    }

    // Función para hacer el movimiento
    window.makeMove = function() {
        const move = document.getElementById("moveInput").value;
        fetch(`/move?move=${move}`, {
            method: 'POST'
        })
        .then(response => response.text())
        .then(data => {
            document.getElementById("response").innerText = data;
            document.getElementById("moveInput").value = ""; // Limpiar el campo de entrada
        });
    };
});