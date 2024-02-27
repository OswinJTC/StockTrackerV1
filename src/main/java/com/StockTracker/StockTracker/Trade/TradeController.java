

 package com.StockTracker.StockTracker.Trade;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
 

@RequestMapping("/tradeApi")
@RestController
@CrossOrigin(origins = "https://ntu-us-stock-tracker.onrender.com")

public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/addTrade")
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {



        Trade savedTrade = tradeService.createTrade(trade);

        return new ResponseEntity<>(savedTrade, HttpStatus.CREATED);
    }

    @GetMapping("/allTrades")
    public ResponseEntity<List<Trade>> getAllMovies(){
        return new ResponseEntity<List<Trade>>(tradeService.allTrades(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTrade")
    public ResponseEntity<String> deleteTrade(@RequestBody Map<String, String> requestBody) {
        String tradeReferenceString = requestBody.get("tradeReference");
        int tradeReference = Integer.parseInt(tradeReferenceString); // Parse as integer
        String ownerUsername = requestBody.get("owner");

        tradeService.deleteTrade(tradeReference, ownerUsername);
        return ResponseEntity.ok("Trade with reference " + tradeReference + " belonging to user " + ownerUsername + " deleted successfully.");
    }






}
