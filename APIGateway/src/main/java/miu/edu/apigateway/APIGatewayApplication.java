package miu.edu.apigateway;

import miu.edu.apigateway.domain.Role;
import miu.edu.apigateway.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class APIGatewayApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role("CUSTOMER", "/customer");
        roleRepository.save(role);
        role = new Role("CUSTOMER", "/customer/**");
        roleRepository.save(role);
        role = new Role("PRODUCT", "/product");
        roleRepository.save(role);
        role = new Role("PRODUCT", "/product/**");
        roleRepository.save(role);
        role = new Role("SHOPPING", "/shopping");
        roleRepository.save(role);
        role = new Role("SHOPPING", "/shopping/**");
        roleRepository.save(role);

        role = new Role("ADMIN", "/customer");
        roleRepository.save(role);
        role = new Role("ADMIN", "/customer/**");
        roleRepository.save(role);
        role = new Role("ADMIN", "/product");
        roleRepository.save(role);
        role = new Role("ADMIN", "/product/**");
        roleRepository.save(role);
        role = new Role("ADMIN", "/shopping");
        roleRepository.save(role);
        role = new Role("ADMIN", "/shopping/**");
        roleRepository.save(role);
    }
}
