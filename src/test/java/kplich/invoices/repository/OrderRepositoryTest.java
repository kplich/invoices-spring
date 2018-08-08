package kplich.invoices.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import kplich.invoices.model.*;
import kplich.invoices.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.math.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class,
					      TransactionalTestExecutionListener.class,
						  DbUnitTestExecutionListener.class})
@DatabaseSetup ("/dataset.xml")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository repository;

	//TODO: zly, niedzialajacy test
	@Test
	public void findById() {
		Optional<TransportOrder> found = repository.findById(1);
		assertTrue(found.isPresent());

		TransportOrder expected = new TransportOrder(1,
		  								"Tomaszów Mazowiecki PL",
									"Łódź PL", new BigDecimal("999.99"));

		assertEquals(found.get(), expected);

		assertEquals(repository.findById(0), Optional.empty());
	}

	//TODO: zly, niefunkcjonalny test
	@Test
	public void getAll() {
		Iterable<TransportOrder> found = repository.findAll();
		assertEquals(3, ((Collection<?>) found).size());
	}

}