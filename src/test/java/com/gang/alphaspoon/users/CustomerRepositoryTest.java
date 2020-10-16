/*package com.gang.alphaspoon.users;

import com.gang.alphaspoon.domain.entity.Customer;
import com.gang.alphaspoon.domain.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private List<Customer> customerList;


    @BeforeEach
    void setUp() {
        Customer customer1 = new Customer((long) 1, "Diego2", "password2", 961460601, "diegojohnson46@gmail.com");
        Customer customer2 = new Customer((long) 2, "Diego2", "password2", 961460601, "diegojohnson46@gmail.com");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

    @AfterEach
    void tearDown(){
        customerRepository.deleteAll();
    }

    @Test
    void findAllCustomers(){
        List<Customer> result = customerRepository.findAllCustomers();
        assertThat(result, is(notNullValue()));
    }
}
*/