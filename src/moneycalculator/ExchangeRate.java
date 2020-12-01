package moneycalculator;

import java.time.LocalDate;


public class ExchangeRate {
    
     private final Currency from;
    private final Currency to;
    private final LocalDate date;
    private final double rate;

    public ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getRate() {
        return rate;
    }
    
    
}
