package com.example.burak.userservicejwt;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.service.RoleService;
import com.example.burak.userservicejwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceJwtApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService){
		return args -> {
			roleService.saveRole(new Role(null,"ROLE_USER"));
			roleService.saveRole(new Role(null,"ROLE_ADMIN"));
			roleService.saveRole(new Role(null,"ROLE_MANAGER"));

			userService.saveUser(new User(null,"Burak Kizilkaya","burak","123",new ArrayList<>()));
			userService.saveUser(new User(null,"Bill Gates","bill","000",new ArrayList<>()));
			userService.saveUser(new User(null,"Mark Zuckerberg","mark","xxx",new ArrayList<>()));

			userService.addRoleToUser("burak","ROLE_ADMIN");
			userService.addRoleToUser("bill","ROLE_MANAGER");
			userService.addRoleToUser("mark","ROLE_USER");
		};
	}

}
