package com.luna.TodoList;

import com.luna.TodoList.model.Memo;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.MemoRepository;
import com.luna.TodoList.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class TodoListApplication {

	private static final Logger log = LoggerFactory.getLogger(TodoListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MemoRepository memoRepository, UserRepository userRepository) {
		return (args) -> {
			// save a few memoRepository
			memoRepository.save(new Memo(1L,"this is the 1st message","xian",true,false,LocalDate.now(),LocalDate.now(),1L));
			memoRepository.save(new Memo(2L,"this is the second message","xian",false,true,LocalDate.now(),LocalDate.now(),2L));
			memoRepository.save(new Memo(3L,"this is the ~3~ message","chun",false,false,LocalDate.now(),LocalDate.now(),2L));

			// save a few user
			userRepository.save(new User(1L,"Luna", LocalDate.of(1995,11,18),"unswlun@gmail.com","0412218970"));
			userRepository.save(new User(2L,"Luna", LocalDate.of(1993,9,23),"who@gmail.com","123455431"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			memoRepository.findAll().stream().map(Memo::toString).forEach(log::info);
			log.info("");

			// fetch an individual customer by ID
			Memo memo = memoRepository.findById(1L).orElse(null);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(memo.toString());
			log.info("~~~~~~Test finish~~~~~");


		} ;
	}
}
