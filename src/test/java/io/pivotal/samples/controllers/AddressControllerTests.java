package io.pivotal.samples.controllers;

import io.spring.samples.Application;
import io.spring.samples.controllers.AddressController;
import io.spring.samples.domain.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AddressControllerTests {

    @Autowired
    private AddressController controller;

    @Test()
    public void listInsertAndRetrieve() throws Exception {
        Iterable<Address> list = controller.list();
        Assert.assertThat(list, iterableWithSize(3));

        String email = "test@mail.com";
        String name = "prova";
        String surname = "test";

        Address result = controller.find(email);
        Assert.assertNull(result);

        Address address = new Address(email, name, surname);

        controller.insert(address);

        result = controller.find(email);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getEmail(), email);
        Assert.assertEquals(result.getName(), name);
        Assert.assertEquals(result.getSurname(), surname);
    }
}
