package kplich.invoices.controller;

import kplich.invoices.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

	@Autowired
	OrderController orderController;

	@Test
	public void contextLoads() {
		assertNotNull(orderController);
	}

	@Test
	public void getAll() {
	}
}
