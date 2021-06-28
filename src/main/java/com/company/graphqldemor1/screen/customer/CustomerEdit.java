package com.company.graphqldemor1.screen.customer;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Customer;

@UiController("Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {
}