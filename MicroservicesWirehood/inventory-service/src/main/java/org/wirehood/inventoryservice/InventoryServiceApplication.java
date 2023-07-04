package org.wirehood.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.wirehood.inventoryservice.model.Inventory;
import org.wirehood.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);}

		@Bean
		public CommandLineRunner loadData (InventoryRepository inventoryRepository)	{
			return args -> {
				Inventory inventory1 = new Inventory();
				inventory1.setSkuCode("R1");
				inventory1.setQuantity(11);

				Inventory inventory2= new Inventory();
				inventory2.setSkuCode("R2");
				inventory2.setQuantity(33);

				Inventory inventory3= new Inventory();
				inventory2.setSkuCode("R3");
				inventory2.setQuantity(0);

				inventoryRepository.save(inventory1);
				inventoryRepository.save(inventory2);
				inventoryRepository.save(inventory3);
			};
		}
}
