package hello.hello.spw.order.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hello.spw.order.entity.Order;
import hello.hello.spw.order.entity.OrderStatus;
import hello.hello.spw.order.repository.OrderRepository;
import hello.hello.spw.payment.entity.Payment;
import hello.hello.spw.product.entity.Product;
import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderServiceTest {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceTest.class);
	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception{
		//given
		Product product = new Product();
		product.setProductName("포카리스워트");
		product.setInventory(10);
		product.setProductCnt(50);
		em.persist(product);

		Payment payment = new Payment();
		payment.setPaymentCnt(10);
		payment.setPrice(10000);
		em.persist(payment);

		int paymentCnt =2;

		Order order =new Order();

		//when

		Long orderId = orderService.order(product.getProductId(),payment.getPayId(),order.getId(),paymentCnt);
		//then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus(), "상품 주문시 상태는 ORDER");
		assertEquals(1,getOrder.getProducts().size(),"주문한 상품 종류가 정확해야 한다");
		//assertEquals(10000 * paymentCnt,getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다");
		// assertEquals("주문 수량만큼 재고가 줄어야 한다",getOr);
	}

	@Test
	public void 주문취소() throws Exception{
		//given

		//when

		//then
	}

	@Test
	public void 상품주문_재고수량초과() throws Exception{
		//given

		//when

		//then
	}
}
