package com.project.d288.bootstrap;

import com.project.d288.dao.CustomerRepository;
import com.project.d288.dao.DivisionRepository;
import com.project.d288.entities.Customer;
import com.project.d288.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() <= 1) {

            Division d1 = divisionRepository.findById(Long.valueOf(2)).orElse(null);
            Division d2 = divisionRepository.findById(Long.valueOf(3)).orElse(null);
            Division d3 = divisionRepository.findById(Long.valueOf(4)).orElse(null);
            Division d4 = divisionRepository.findById(Long.valueOf(5)).orElse(null);
            Division d5 = divisionRepository.findById(Long.valueOf(6)).orElse(null);

            Customer luffy = new Customer("Monkey", "D. Luffy", "111 Straw Hat Lane", "11111", "1111111111", d1);
            Customer zoro = new Customer("Roronoa", "Zoro", "222 Straw Hat Lane", "22222", "2222222222", d2);
            Customer  nami = new Customer("Nami", "Nami", "333 Straw Hat Lane", "33333", "3333333333", d3);
            Customer sanji = new Customer("Vinsmoke", "Sanji", "444 Straw Hat Lane", "44444", "4444444444", d4);
            Customer robin = new Customer("Nico", "Robin", "555 Straw Hat Lane", "55555", "5555555555", d5);

            customerRepository.save(luffy);
            customerRepository.save(zoro);
            customerRepository.save(nami);
            customerRepository.save(sanji);
            customerRepository.save(robin);
        }
    }
}

