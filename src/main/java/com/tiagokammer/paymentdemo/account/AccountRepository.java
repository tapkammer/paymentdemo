package com.tiagokammer.paymentdemo.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByDocumentNumber(String documentNumber);
}
