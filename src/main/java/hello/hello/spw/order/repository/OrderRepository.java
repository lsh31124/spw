package hello.hello.spw.order.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import hello.hello.spw.order.entity.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order){
		em.persist(order);
	}

	public Order findOne(Long id){
		return em.find(Order.class, id);
	}

	public List<Order> finAll(){
		return em.createQuery("select i from Order i", Order.class)
			.getResultList();
	}
}
