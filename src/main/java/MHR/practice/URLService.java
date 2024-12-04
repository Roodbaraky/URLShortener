package MHR.practice;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Named
@Transactional(REQUIRED)

@ApplicationScoped
public class URLService {
    @Inject
    EntityManager em;

    @Inject
    URLGenerator generator;

    public URL generate(URL url) {
        url.setShortURL(generator.generateURL(url.getRawURL()));
        em.persist(url);
        return url;
    }

    public String find(String shortUrl) {
        System.out.println(shortUrl);
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.shortURL = :shortUrl", URL.class);
        query.setParameter("shortUrl", shortUrl);
        return query.getResultList().getFirst().getRawURL();
    }
}
