package com.company.graphqldemor1.screen.card;

import io.jmix.ui.screen.*;
import com.company.graphqldemor1.entity.Card;

@UiController("Card.browse")
@UiDescriptor("card-browse.xml")
@LookupComponent("cardsTable")
public class CardBrowse extends StandardLookup<Card> {
}