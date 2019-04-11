package com.glassdoor.test.intern.first.helper;

import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.repo.PayeeRepo;
import com.glassdoor.test.intern.first.model.repo.PayerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Class that loads both payee & payer records at the beginning of the project & purges everything at the end
 */
@Component
public class DataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private PayerRepo payerRepo;
    private PayeeRepo payeeRepo;

    @Autowired
    public DataLoader(PayerRepo payerRepo, PayeeRepo payeeRepo) {
        this.payerRepo = payerRepo;
        this.payeeRepo = payeeRepo;
    }

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        LOG.trace("Entering loadData method");
        List<Payer> payers = DataLoaderHelper.getPayersInfo();
        LOG.debug("Loading {} Payers into the in-memory database", payers.size());
        payerRepo.save(payers);
        LOG.info("Loaded data for Payer to in-memory database");

        List<Payee> payees = DataLoaderHelper.getPayeesInfo();
        LOG.debug("Loading {} Payees into the in-memory database", payees.size());
        payeeRepo.save(payees);
        LOG.info("Loaded data for Payee to in-memory database");
        LOG.trace("Finishing loadData method");
    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        LOG.trace("Entering removeData method");
        payerRepo.deleteAll();
        LOG.info("Purged all the data for Payer & Payee from in-memory database");
        LOG.trace("Finishing removeData method");
    }
}