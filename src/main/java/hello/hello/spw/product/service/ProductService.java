package hello.hello.spw.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hello.spw.product.repository.ProductRepository;
import hello.hello.spw.product.entity.Product;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //트랜잭션 멈위를 설정해주는 역할 (readOnly = true) 읽기 전용 트랜잭션 명시
@RequiredArgsConstructor //final 필드나 @nonNull 필드를 가진 생성자를 자동으로 생성
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional
	public void save(Product product){
		productRepository.save(product);
	}

	public List<Product> findProducts(){
		return productRepository.findAll();
	}

	public Product findOne(Long product_Id){
		return productRepository.findOne(product_Id);
	}

}
