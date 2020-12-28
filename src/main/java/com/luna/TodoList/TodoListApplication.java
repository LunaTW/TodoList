package com.luna.TodoList;

import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoListApplication {

	private static final Logger log = LoggerFactory.getLogger(TodoListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MemoRepository memoRepository) {
		return (args) -> {
			// save a few memoRepository
			memoRepository.save(new Memo(1L,"this is the 1st message","xian",false,LocalDate.now(),LocalDate.now()));
			memoRepository.save(new Memo((long)2,"this is the second message","xian",false,LocalDate.now(),LocalDate.now()));
			memoRepository.save(new Memo((long)3,"this is the ~3~ message","chun",false,LocalDate.now(),LocalDate.now()));

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
