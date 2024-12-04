package MHR.practice;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Named
@Transactional(REQUIRED)

@ApplicationScoped
public class URLService {
    @Inject
    EntityManager em;

    @Inject
    URLGenerator generator;

}
