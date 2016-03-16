package org.spring.micro.order.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.spring.micro.customer.rest.Customer;
import org.spring.micro.product.rest.Product;
import org.springframework.web.client.RestTemplate;

@Named
@Path("/")
public class OrderRest {
	
	/**
	 * At this point, the reader may notice a bug in our order service: subsequent calls will generate the same ID order! 
	 * This is due to our mock variable that generates the ids be declared as a global variable that is recreated every 
	 * new instance of the class. As REST services have request scope, every request generates a new instance, 
	 * which means that the variable is never incremented through the calls. 
	 * 
	 * One of the simplest ways of fixing this bug is declaring the variable as static.
	 */
	private static long id = 1;
	
	@Inject
	private RestTemplate restTemplate;
	
	@GET
	@Path("order")
	@Produces(MediaType.APPLICATION_JSON)
	public Order submitOrder(@QueryParam("idCustomer") long idCustomer,
			@QueryParam("idProduct") long idProduct,
			@QueryParam("amount") long amount) {
		Order order = new Order();
		Customer customer = restTemplate.getForObject(
				"http://localhost:8081/customer?id={id}", Customer.class,
					idCustomer);
		Product product = restTemplate.getForObject(
				"http://localhost:8082/product?id={id}", Product.class,
					idProduct);
		order.setCustomer(customer);
		order.setProduct(product);
		order.setId(id);
		order.setAmount(amount);
		order.setDateOrder(new Date());
		id++;
		return order;
	}
}