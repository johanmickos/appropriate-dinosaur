package converter.model;

public interface ConversionDTO {
    /**
     * Get the conversion rate of this Conversion.
     *
     * @return this Conversion's conversion rate
     */
    double getConversionRate();

    /**
     * Converts a given amount from a currency into another.
     *
     * @param amount the amount of currency to convert
     * @return the converted amount.
     */
    double convert(double amount);

    String getCurrencyFrom();
    String getCurrencyTo();
}
