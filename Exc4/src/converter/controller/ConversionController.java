package converter.controller;

import converter.model.Conversion;
import converter.model.ConversionDTO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConversionController {
    @PersistenceContext(unitName = "converter")
    private EntityManager em;


    public ConversionDTO createConversion(String from, String to, double rate) {
        Conversion newConversion = new Conversion(from, to, rate);
        em.persist(newConversion);
        return newConversion;
    }

    public void createConversionPair(String from, String to, double rate) {
        Conversion newConversion1 = new Conversion(from, to, rate);
        Conversion newConversion2 = new Conversion(to, from, 1/rate);
        em.persist(newConversion1);
        em.persist(newConversion2);
    }

    public ConversionDTO findConversion(String from, String to) {
        List found = em.createNamedQuery("Conversion.findByFromTo")
                .setParameter("from", from)
                .setParameter("to", to).getResultList();
        if (found == null || found.size() == 0) {
            throw new EntityNotFoundException("No conversion rate between " + from + " and " + to);
        }
        return (ConversionDTO) found.get(0);
    }

    public List getCurrencies() {
        List found = em.createNamedQuery("Conversion.getAllCurrencies").getResultList();
        if (found == null || found.size() == 0) {
            throw new EntityNotFoundException("No currencies found!");
        }
        return found;
    }

}
