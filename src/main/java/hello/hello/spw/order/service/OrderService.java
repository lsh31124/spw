package hello.hello.spw.order.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hello.spw.order.entity.Delivery;
import hello.hello.spw.order.entity.DeliveryStatus;
import hello.hello.spw.order.entity.Order;
import hello.hello.spw.order.repository.OrderRepository;
import hello.hello.spw.payment.entity.Payment;
import hello.hello.spw.payment.repository.PaymentRepository;
import hello.hello.spw.product.entity.Product;
import hello.hello.spw.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final PaymentRepository paymentRepository;

	/*
	 * 주문
	*/
	@Transactional
	public Long order(Long productId ,Long payId ,Long id, int paymentCnt){
		//엔티티 조회
		Product product = productRepository.findOne(productId);
		Payment payment = paymentRepository.findOne(payId);

		Delivery delivery =new Delivery();
		delivery.setDeliveryStatus(DeliveryStatus.READY);

		//주문가격 생성
		Payment payments = Payment.createPayment(product, payment.getPrice(), paymentCnt);

		//주문 생성
		Order order = Order.createOrder(delivery, LocalDateTime.now(), product);

		orderRepository.save(order);
		return order.getId();
	}

	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long id){
		//주문 인티티 조회
		Order order = orderRepository.findOne(id);
		//주문 취소
		order.cancel();
	}
}
