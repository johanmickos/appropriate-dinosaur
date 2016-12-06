package converter.view;

import converter.model.Conversion;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

@Named("converterManager")
@ConversationScoped
public class ConverterManager implements Serializable {
    private double amountToConvert;
    private String currencyFrom;
    private String currencyTo;
    private Conversion conversion;
    private Exception conversionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public Conversion getConversion()
    {
        return conversion;
    }

    public double getAmountToConvert() {
        return amountToConvert;
    }

    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
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

    public String convert() {
        try {
            startConversation();
            conversionFailure = null;

            System.out.println("Amount to convert: " + amountToConvert +
                "currency from: " + currencyFrom +
                "currency to: " + currencyTo);

            this.conversion = new Conversion(amountToConvert, amountToConvert*10, currencyFrom, currencyTo);

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        conversionFailure = e;
    }

    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }

}
