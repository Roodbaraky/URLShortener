package MHR.practice;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
public class URLRepository {

    @Inject
    private EntityManager em;

    public URL findByRawURL(String rawURL) {
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.rawURL = :rawURL", URL.class);
        query.setParameter("rawURL", rawURL);
        List<URL> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.getFirst();
    }

    public URL findByShortURL(String shortURL) {
        TypedQuery<URL> query = em.createQuery("SELECT u FROM URL u WHERE u.shortURL = :shortURL", URL.class);
        query.setParameter("shortURL", shortURL);
        List<URL> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.getFirst();
    }

    public void save(URL url) {
        em.persist(url);
    }

    public void deleteByShortURL(String shortURL) {
        Query query = em.createQuery("DELETE FROM URL u WHERE u.shortURL = :shortURL");
        query.setParameter("shortURL", shortURL);
        query.executeUpdate();
    }
}
