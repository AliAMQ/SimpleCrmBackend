package com.practice1;

import com.practice1.model.Customer;
import com.practice1.service.CustomerService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Profile("rand")
public class CustomerServiceUnitTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    private Customer customer;

    @Before
    public void setup(){
        when(customer.getName()).thenReturn("");
    }

    @Test(expected = Exception.class)
    public void testAddCustomerWithNullParameter(){
        customerService.addCustomer(null);
    }

    @Test(expected = Exception.class)
    public void testAddCustomerWithNoCustomerName(){
        customerService.addCustomer(customer);
    }

}
