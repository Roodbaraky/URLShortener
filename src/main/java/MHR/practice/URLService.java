package MHR.practice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class URLService {

    @Inject
    URLRepository urlRepository;

    @Inject
    URLGenerator generator;

    public URLTO check(URLTO url) {
        URL existingURL = urlRepository.findByRawURL(url.getRawURL());
        if (existingURL != null) {
            url.setShortURL(existingURL.getShortURL());
            return url;
        }
        return null;
    }

    public URLTO generate(URLTO url) {
        URL existingURL = urlRepository.findByRawURL(url.getRawURL());
        if (existingURL != null) {
            return toTO(existingURL);
        }

        url.setShortURL(generator.generateURL(url.getRawURL()));
        while (true) {
            try {
                urlRepository.save(toEntity(url));
                break;
            } catch (Exception e) {
                url.setShortURL(generator.generateURL(url.getRawURL()));
            }
        }
        return url;
    }

    public String find(String shortUrl) throws Exception {
        URL url = urlRepository.findByShortURL(shortUrl);
        if (url == null) {
            throw new Exception("No URL found with the given shortUrl: " + shortUrl);
        }
        return url.getRawURL();
    }

    public void delete(String shortUrl) throws Exception {
        URL url = urlRepository.findByShortURL(shortUrl);
        if (url == null) {
            throw new Exception("No URL found with the given shortUrl: " + shortUrl);
        }
        urlRepository.deleteByShortURL(shortUrl);
    }
    private URLTO toTO(URL entity) {
        URLTO to = new URLTO();
        to.setRawURL(entity.getRawURL());
        to.setShortURL(entity.getShortURL());
        return to;
    }

    private URL toEntity(URLTO to) {
        URL entity = new URL();
        entity.setRawURL(to.getRawURL());
        entity.setShortURL(to.getShortURL());
        return entity;
    }

}
