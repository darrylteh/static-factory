package game.items.template;

import engine.actors.Actor;

/**
 * The Monologuable interface serves as a way for objects to monologue.
 *
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public interface Monologuable {
    /**
     * When called, returns a String monologue from the monologist.
     *
     * @param actor the actor listening to the monologist
     * @return a String monologue from the monologist
     */
    String generateMonologue(Actor actor);
}

