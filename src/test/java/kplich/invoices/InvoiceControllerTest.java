package kplich.invoices;

import kplich.invoices.controller.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith (SpringRunner.class)
@SpringBootTest
public class InvoiceControllerTest {
	@Autowired
	InvoiceController invoiceController;

	@Test
	public void contextLoads() {
		assertNotNull(invoiceController);
	}

}