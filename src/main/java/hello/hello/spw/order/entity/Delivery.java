package hello.hello.spw.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery")
	private Order order;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus; //READY, COMP
}
