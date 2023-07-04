package org.wirehood.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.wirehood.inventoryservice.model.Inventory;
import org.wirehood.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
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

				inventoryRepository.save(inventory1);
				inventoryRepository.save(inventory2);
			};
		}
}
