package com.company.graphqldemor1.screen.customer;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Customer;

@UiController("Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}