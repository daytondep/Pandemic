package Player;

import GamePackage.Card;

public class ContingencyPlanner extends Player {

    private Card bonusEvent;

    public ContingencyPlanner(){
        super();
    }
    @Override
    public void Drive() {

    }

    @Override
    public void directFlight() {

    }

    @Override
    public void charterFlight() {

    }

    @Override
    public void shuttleFlight() {

    }

    @Override
    public void treat() {

    }

    @Override
    public void shareKnowledge() {

    }

    @Override
    public void discoverCure() {

    }

    @Override
    public void buildResearchStation() {

    }

    public void recoverEvent(String cardName){
        this.bonusEvent = this.getThisGame().getPlayerDeck().getDiscardedCard(cardName);
    }
}
