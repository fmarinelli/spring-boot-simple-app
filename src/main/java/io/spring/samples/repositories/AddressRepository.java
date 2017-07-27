package io.spring.samples.repositories;

import io.spring.samples.domain.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, String> {

    Address findByEmail(String email);

    List<Address> findDistinctBySurname(String surname);

}