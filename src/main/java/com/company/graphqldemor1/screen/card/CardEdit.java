package com.company.graphqldemor1.screen.card;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Card;

@UiController("Card.edit")
@UiDescriptor("card-edit.xml")
@EditedEntityContainer("cardDc")
public class CardEdit extends StandardEditor<Card> {
}