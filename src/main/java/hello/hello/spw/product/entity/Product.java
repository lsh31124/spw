package hello.hello.spw.product.entity;

import hello.hello.spw.product.exception.NotEnoughStockException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {
	@Id
	@GeneratedValue
	private Long product_id;
	private String product_name;
	private int product_cnt;
	private int inventory;

//==비즈니스 로직==//
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
