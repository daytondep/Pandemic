package GamePackage;

public enum Roles {
    CONTINGENCY_PLANNER,    //take an event card from discard pile (does not count against hand limit), once used, card removed from game
    DISPATCHER,     //move any pawn to any city containing another pawn, or move another pawn as if it were their own
    MEDIC,     //removes all cubes of same colour when using "treat disease" action, removes all cubes of a cured disease in any given city, without using treat disease action
    OPERATIONS_EXPERT,     //build research station in current city *without* discarding a city card, or, once per turn, move from a research station to any city by discarding any city card
    QUARANTINE_SPECIALIST,     //prevents outbreaks in the city they are in, and all cities connected
    RESEARCHER,     //can share *any* city to another player in the same city
    SCIENTIST     //needs only 4 (not 5) city cards of the same disease color to discover a cure
}
