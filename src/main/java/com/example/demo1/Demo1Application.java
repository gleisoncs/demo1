package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class Demo1Application {

//	@Bean
//	CommandLineRunner commandLineRunner(ReservationRepository reservationRepository){
//		return strings -> {
//			Stream.of("Petter", "Hanna", "Eric", "Lisa").forEach(a -> reservationRepository.save(new Reservation(a)));
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
}

@Component
class DymmyDataCLR implements CommandLineRunner{

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Petter", "Hanna", "Eric", "Lisa").forEach(a -> this.reservationRepository.save(new Reservation(a)));
	}
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@RestResource(path = "by-name")
	Collection<Reservation> findByReservationName(String rn);
}

@Entity
class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Reservation() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}
}
