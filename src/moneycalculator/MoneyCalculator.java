package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception{
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }
    
    private double amount;
    private double exchangeRate;
    private Map<String, Currency> currencies = new HashMap<>();
    private Currency currencyFrom;
    private Currency currencyTo;
    
     public MoneyCalculator(){
        currencies.put("USD", new Currency("USD", "Dólar americano", "$"));
        currencies.put("EUR", new Currency("EUR", "Euros", "€"));
        currencies.put("GBP", new Currency("GBP", "Libras Esterlinas", "£"));
    }
    
    private void execute() throws Exception{
        input();
        process();
        output();
    }
    
    private void input() throws Exception{
        System.out.println("Intoduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        amount = Double.parseDouble(scanner.next());
        
        System.out.println("Intoduzca divisa origen");
        currencyFrom = currencies.get(scanner.next().toUpperCase());
        
        System.out.println("Intoduzca divisa destino");
        currencyTo = currencies.get(scanner.next().toUpperCase());
       
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(currencyFrom.getCode(), currencyTo.getCode());
        
    }
    
    private void output(){
        System.out.println(amount + " " + currencyFrom + " equivalen a " + amount *
                exchangeRate + " " + currencyTo);
    }
    
    private static double getExchangeRate(String from, String to) throws IOException{
        URL url = 
            new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" +
                    from + "_" + to + "&compact=y");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to)+12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    } 
}
