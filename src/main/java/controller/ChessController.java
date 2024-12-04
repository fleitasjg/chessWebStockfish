package controller;

import service.StockfishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ChessController {
    private final StockfishService stockfishService;

    @Autowired
    public ChessController(StockfishService stockfishService) {
        this.stockfishService = stockfishService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Devuelve la vista index.html
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam String move) throws IOException {
        // Enviar el movimiento al motor Stockfish
        String stockfishResponse = stockfishService.sendCommand("position startpos moves " + move);
        return stockfishResponse; // Devuelve la respuesta de Stockfish
    }
}