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

    public URL check(URL url) {
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.rawURL = :rawURL", URL.class);
        query.setParameter("rawURL", url.getRawURL());

        if (!query.getResultList().isEmpty()) {
            System.out.println(query.getResultList().getFirst());
            url.setShortURL((query.getResultList().getFirst()).getShortURL());
            return url;
        }
        return null;
    }

    public URL generate(URL url) {
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.rawURL = :rawURL", URL.class);
        query.setParameter("rawURL", url.getRawURL());

        if (!query.getResultList().isEmpty()) {
            System.out.println(query.getResultList().getFirst());
            url.setShortURL((query.getResultList().getFirst()).getShortURL());
        } else {
            url.setShortURL(generator.generateURL(url.getRawURL()));
            em.persist(url);
        }
        return url;
    }

    public String find(String shortUrl) {
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.shortURL = :shortUrl", URL.class);
        query.setParameter("shortUrl", shortUrl);
        return query.getResultList().getFirst().getRawURL();
    }
}
