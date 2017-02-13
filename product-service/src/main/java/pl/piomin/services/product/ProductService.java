package pl.piomin.services.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.piomin.service.common.message.Order;
import pl.piomin.service.common.message.Product;

@Service
public class ProductService {
	
	private List<Product> products;
	
	public ProductService() {
		products = new ArrayList<>();
		products.add(new Product(1, "Example#1", 500));
		products.add(new Product(2, "Example#2", 100));
		products.add(new Product(3, "Example#3", 1000));
		products.add(new Product(4, "Example#4", 200));
	}
	
	public Product processOrder(Order order) {
		return products.stream().filter(p -> p.getName().equals(order.getProduct().getName())).findAny().get();
	}

}
