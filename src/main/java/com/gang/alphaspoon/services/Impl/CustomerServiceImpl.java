package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.converters.CustomerConverter;
import com.gang.alphaspoon.dtos.requests.LoginRequest;
import com.gang.alphaspoon.dtos.resources.LoginResource;
import com.gang.alphaspoon.entity.Customer;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.services.CustomerService;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.validators.CustomerValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gang.alphaspoon.repository.CustomerRepository;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Value("${jwt.password}")
    private String jwtSecret;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(()->new NoDataFoundException("User with"+       email + "not found"));
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new NoDataFoundException("Customer", "Id", customerId));
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        try {
            CustomerValidator.validate(customer);

            Customer existCustomer = customerRepository.findByEmail(customer.getEmail())
                    .orElse(null);
            if(existCustomer!=null)
                throw new ValidateServiceException("El correo ya esta en uso");
            return customerRepository.save(customer);
        } catch (ValidateServiceException | NoDataFoundException e){
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerRequest) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setName(customerRequest.getName());
            customer.setPassword(customerRequest.getPassword());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhoneNumber(customerRequest.getPhoneNumber());
            return customerRepository.save(customer);
        }).orElseThrow(()->new NoDataFoundException("Customer", "Id", customerId));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        return customerRepository.findById(customerId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new NoDataFoundException("Customer", "Id", customerId));
    }

    public LoginResource login(LoginRequest request){
         try{
             Customer customer = customerRepository.findByEmail(request.getEmail())
                     .orElseThrow(()->new ValidateServiceException("Usuario y/o Contraseña incorrecto"));
             if(!customer.getPassword().equals(request.getPassword()))
                 throw new ValidateServiceException("Usuario y/o Contraseña incorrecto");

             String token = createToken(customer);

             return new LoginResource(customerConverter.fromEntity(customer), token);
         } catch (ValidateServiceException | NoDataFoundException e){
             throw e;
         }catch(Exception e){
             throw new GeneralServiceException(e.getMessage(), e);
         }
    }

    public String createToken (Customer customer){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()*(1000*60*60));

        return Jwts.builder()
                .setSubject(customer.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
