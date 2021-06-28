package com.company.graphqldemor1.screen.order;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Order;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
}