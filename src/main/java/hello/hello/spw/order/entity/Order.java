package hello.hello.spw.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hello.hello.spw.payment.entity.Payment;
import hello.hello.spw.product.entity.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();


	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToOne
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	private int orderCnt;

	private LocalDateTime deliveryDate;

	private LocalDateTime orderTime;

	//==연관관계 메서드==//
	public void addProduct(Product product){
		products.add(product);
		product.setOrder(this);
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

 	//==생성 메서드==//
	public static Order createOrder(Delivery delivery, OrderStatus orderStatus, int orderCnt, LocalDateTime deliveryDate,Product... products){
		Order order = new Order();
		order.setDelivery(delivery);
		for (Product product : products) {
			order.addProduct(product);
		}
		order.orderCnt      = orderCnt;
		order.deliveryDate  = deliveryDate;
		order.setOrderStatus(OrderStatus.ORDER);
		order.setOrderTime(LocalDateTime.now());
		return order;
	}

	//주문 취소
	public void cancel(){
		if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){
			throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
		}
		this.setOrderStatus(OrderStatus.CANCEL);
		for(Product product : products){
			product.cancel();

		}
	}

}
