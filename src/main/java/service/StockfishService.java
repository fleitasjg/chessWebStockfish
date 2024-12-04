package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class StockfishService {
    private Process stockfishProcess;

    public StockfishService() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("src/main/resources/stockfish/stockfish");
        stockfishProcess = processBuilder.start();
    }

    public String sendCommand(String command) throws IOException {
        stockfishProcess.getOutputStream().write((command + "\n").getBytes());
        stockfishProcess.getOutputStream().flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
            if (line.startsWith("bestmove")) break; // Detener si se recibe la mejor jugada
        }
        return output.toString();
    }

    public void close() {
        stockfishProcess.destroy();
    }
}