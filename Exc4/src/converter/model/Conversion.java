package converter.model;

public class Conversion
{
    private double amountToConvert;
    private double convertedAmount;
    private String currencyFrom;
    private String currencyTo;

    public Conversion(double amountToConvert, double convertedAmount, String currencyFrom, String currencyTo)
    {
        this.amountToConvert = amountToConvert;
        this.convertedAmount = convertedAmount;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    public double getAmountToConvert()
    {
        return amountToConvert;
    }

    public void setAmountToConvert(double amountToConvert)
    {
        this.amountToConvert = amountToConvert;
    }

    public double getConvertedAmount()
    {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount)
    {
        this.convertedAmount = convertedAmount;
    }

    public String getCurrencyFrom()
    {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom)
    {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo()
    {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo)
    {
        this.currencyTo = currencyTo;
    }
}
