package com.company.graphqldemor1.screen.order;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Order;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
}