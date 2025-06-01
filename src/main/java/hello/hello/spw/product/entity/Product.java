package hello.hello.spw.product.entity;


import java.util.ArrayList;
import java.util.List;

import hello.hello.spw.order.entity.Order;
import hello.hello.spw.payment.entity.Payment;
import hello.hello.spw.product.exception.NotEnoughStockException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {
	@Id
	@GeneratedValue
	private Long productId;

	private String productName;

	private int productCnt;

	private int inventory;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Payment> payments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	//==생성 메서드==//
	public static Product createProduct(String productName, int productCnt ,int inventory){
		Product products = new Product();
		products.setProductName(productName);
		products.setInventory(inventory);
		products.setProductCnt(productCnt);
		products.removeStock(productCnt);
		return products;
	}


	Product products =new Product();
	//==비즈니스 로직==//
	public void cancel() {
		products.addStock(productCnt);
	}

	//조회 로직
	/*
	 * 전체 주문 가격 조회
	 */
	public int getTotalPrice(){
		int totalPrice =0;
		for (Payment payment :payments){
			totalPrice += payment.getTotalPrice();
		}
		return totalPrice;
	}
/**
 * stock 증가
 * */
public void addStock(int quantity){
	this.inventory +=quantity;
}
/**
 * stock 감소
* */
public void removeStock(int quantity){
	int restStock = this.inventory - quantity;
	if(restStock < 0){
		throw new NotEnoughStockException("need more stock");
	}
	this.inventory =restStock;
}


}
