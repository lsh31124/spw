package hello.hello.spw.product.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import hello.hello.spw.product.entity.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
	private final EntityManager em;

	public void save(Product product) {
		 if (product.getProductId() == null){
			 em.persist(product);
		 }else{
			 em.merge(product);
		 }
	}

	public Product findOne(Long id){
		return em.find(Product.class, id);
	}

	public List<Product> findAll() {
		return em.createQuery("select i from Product i",Product.class)
			.getResultList();
	}
}
