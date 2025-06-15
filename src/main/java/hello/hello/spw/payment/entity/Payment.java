package hello.hello.spw.payment.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hello.hello.spw.order.entity.Order;
import hello.hello.spw.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
public class Payment {
	@Id
	@GeneratedValue
	private Long payId;

	private LocalDateTime payDate;					//결제 일자

	private LocalDateTime payCreTime;					//결제 생성시간
	@Setter
	private int price;
	@Setter
	private int paymentCnt;

	@Setter
	@ManyToOne
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Product product;

	@Setter
	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Order order;


	//==생성 메서드==//
	public static Payment createPayment(Product product, int price,int paymentCnt){
		Payment payments = new Payment();
		payments.setProduct(product);
		payments.setPrice(price);
		payments.setPaymentCnt(paymentCnt);

		product.removeStock(paymentCnt);
		return payments;
	}

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('PAID', 'PENDING', 'FAILED')")
	private PayStatus payStatus;


	//==조회 로직==//
	/**
	* 주문상품 전체 가격 조회
	**/
	public int getTotalPrice() {
		return getPrice() * getPaymentCnt();
	}


}
