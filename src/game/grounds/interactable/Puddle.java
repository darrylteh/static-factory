package game.grounds.interactable;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.ActorAttributeOperations;
import engine.actors.attributes.BaseActorAttributes;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.template.Consumable;
/**
 * A class that represents a puddle.
 * 
 * @author Darryl Teh
 * @version 1.0.0
 */
public class Puddle extends Ground implements Consumable{
    /**
     * Constructs a new Puddle object.
     */
    public Puddle() {
        super('~');
    }


    /**
     * Returns the value of the effect gained by consuming the item.
     *
     * @return the int consume effect value
     */
    public int getConsumeEffectValue(){
        return 1;
    }

    /**
     * Returns the verb used to describe the consumption action.
     *
     * @return the String consume verb
     */
    public String getConsumeVerb(){
        return "increases";
    }

    /**
     * Consumes the consumable item, applying its effect to the actor. The item is then removed from
     * the actor's inventory.
     *
     * @param actor the actor consuming the item
     * @return the consume verb
     */
    @Override
    public String consumeConsumable(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, this.getConsumeEffectValue());
        String result = actor + " drinks from "+this.toString()+". ";
        result += actor+" feels invigorated.";
        return result;
    }
    /**
     * Returns a list of allowable actions that the consumable can perform to the current actor. In
     * this case, a ConsumeAction to consume the consumable.
     *
     * @param actor the owner of the consumable
     * @param location the location of this puddle
     *  @param direction the direction of the Ground from the Actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if(location.map().locationOf(actor).equals(location)){
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * It will return "water from Puddle"
     * @return "water from Puddle"
     */
    @Override
    public String toString(){
        return "A random puddle of water";
    }
}
