package hello.hello.spw.payment.entity;

import java.time.LocalDateTime;

import hello.hello.spw.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter @Setter
public class Payment {
	@Id
	@GeneratedValue
	private Long payId;

	private LocalDateTime payDate;					//결제 일자

	private LocalDateTime payCreTime;					//결제 생성시간

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private int price;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('PAID', 'PENDING', 'FAILED')")
	private PayStatus payStatus;

	public int getTotalPrice() {
		return getPrice() * product.getProductCnt();
	}
}
