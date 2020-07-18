package com.restful.mvc.bootstrap;

import com.restful.mvc.domain.Category;
import com.restful.mvc.domain.Customer;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.repository.CategoryRepository;
import com.restful.mvc.repository.CustomerRepository;
import com.restful.mvc.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {


    public static final String FIRSTNAME = "johnny";
    public static final String LASTNAME = "Law";
    public static final String LASTNAME1 = "Gala";
    public static final String FIRSTNAME1 = "Susy";
    public static final String FIRSTNAME2 = "Marie";
    public static final String LASTNAME2 = "Curie";

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;

        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategory();
        loadCustomer();
        loadVendor();
    }

    private void loadCategory() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        Category dried = new Category();
        dried.setName("Dried");

        categoryRepository.save(fruits);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        categoryRepository.save(dried);
    }

    private void loadCustomer(){
        Customer johnny = new Customer();
        johnny.setFirstname(FIRSTNAME);
        johnny.setLastname(LASTNAME);

        Customer susy = new Customer();
        susy.setLastname(LASTNAME1);
        susy.setFirstname(FIRSTNAME1);

        Customer marie = new Customer();
        marie.setFirstname(FIRSTNAME2);
        marie.setLastname(LASTNAME2);

        customerRepository.save(johnny);
        customerRepository.save(susy);
        customerRepository.save(marie);

    }

    private void loadVendor(){
        Vendor johnny = new Vendor();
        johnny.setName(FIRSTNAME);
        johnny.setId(1L);

        Vendor susy = new Vendor();
        susy.setName(FIRSTNAME1);

        Vendor marie = new Vendor();
        marie.setName(FIRSTNAME2);

        vendorRepository.save(johnny);
        vendorRepository.save(susy);
        vendorRepository.save(marie);
    }

}
