package converter.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name="Conversion.getAllCurrencies",
                query="SELECT distinct c.currencyFrom FROM Conversion c"),
        @NamedQuery(name="Conversion.findAll",
                query="SELECT c FROM Conversion c"),
        @NamedQuery(name="Conversion.findByFromTo",
                query="SELECT c FROM Conversion c WHERE c.currencyFrom = :from AND c.currencyTo = :to"),
})
public class Conversion implements ConversionDTO, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int conversionId;
    private double conversionRate;
    private String currencyFrom;
    private String currencyTo;

    public Conversion(String from, String to, double rate) {
        conversionRate = rate;
        currencyFrom = from;
        currencyTo = to;
    }

    public Conversion() {
    }

    public int getConversionId() {
        return conversionId;
    }

    @Override
    public double getConversionRate() {
        return conversionRate;
    }

    @Override
    public double convert(double amount) {
        return amount * conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }
}
