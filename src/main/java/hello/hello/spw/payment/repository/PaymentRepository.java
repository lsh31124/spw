package hello.hello.spw.payment.repository;

import org.springframework.stereotype.Repository;

import hello.hello.spw.product.entity.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

	private final EntityManager em;
}
