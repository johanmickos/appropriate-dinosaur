package converter.view;

import converter.controller.ConversionController;
import converter.model.ConversionDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("converterManager")
@ConversationScoped
public class ConverterManager implements Serializable {
    @EJB
    private ConversionController conversionController;

    private double amountToConvert;
    private String currencyFrom;
    private String currencyTo;
    private ConversionDTO conversion;
    private Exception conversionFailure;
    @Inject
    private Conversation conversation;
    private List currencies;

    private String conversionOutput;

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

    @PostConstruct
    public void init() {
        currencies = conversionController.getCurrencies();
    }

    public ConversionDTO getConversion()
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

    public String getConversionOutput()
    {
        return conversionOutput;
    }

    public void setConversionOutput(String conversionOutput)
    {
        this.conversionOutput = conversionOutput;
    }

    public String convert() {
        try {
            startConversation();
            conversionFailure = null;

            this.conversion = conversionController.findConversion(currencyFrom, currencyTo);
            generateConversionOutputString();

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

    private void generateConversionOutputString()
    {
        this.conversionOutput = "Conv: " +
                this.amountToConvert +
                " " +
                this.conversion.getCurrencyFrom() +
                " = " +
                this.conversion.convert(amountToConvert) +
                this.conversion.getCurrencyTo();
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

    public boolean getSuccess() {
        return conversionFailure == null;
    }

    public Exception getException() {
        return conversionFailure;
    }

    public List getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List currencies) {
        this.currencies = currencies;
    }
}
