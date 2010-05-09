package games.stendhal.server.entity.npc.action;

import games.stendhal.common.Grammar;
import games.stendhal.common.Rand;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.parser.Sentence;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.util.StringUtils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Start recording random item collection request.
 * For a quest to use this it needs the list of items with their quantities as a Map<String, Integer> 
 */
public class StartRecordingRandomItemCollectionAction implements ChatAction {

	private final String questname;
	private Map<String, Integer> items;
	private final int index;
	private final String message;

	/**
	 * Creates a new StartRecordingRandomItemCollectionAction.
	 * 
	 * @param questname
	 *            name of quest-slot to change
	 * @param items
	 *            List of items to select from, with quantity
	 * @param message
	 *            Message which NPC asks for items with. We add the item name and quantity to end of message.
	 */
	public StartRecordingRandomItemCollectionAction(final String questname, final Map<String, Integer>
    items, final String message) {
		this.questname = questname;
		this.index = -1;
		this.items = items;
		this.message = message;
	}

	/**
	 * Creates a new StartRecordingRandomItemCollectionAction.
	 * 
	 * @param questname
	 *            name of quest-slot to change
	 * @param index
	 *            index of sub state
	 * @param items
	 *            List of items to select from, with quantity 
	 * @param message
	 *            Message which NPC asks for items with. We add the item name and quantity to end of message.
	 */
	public StartRecordingRandomItemCollectionAction(final String questname, final int index, final Map<String, Integer>
    items, final String message) {
		this.questname = questname;
		this.index = index;
		this.items = items;
		this.message = message;
	}

	public void fire(final Player player, final Sentence sentence, final SpeakerNPC engine) {
		final String itemname = Rand.rand(items.keySet());
		final int quantity = items.get(itemname);
		
		Map<String, String> substitutes = new HashMap<String, String>();
		substitutes.put("item", Grammar.quantityplnoun(quantity,itemname));
		substitutes.put("#item", Grammar.quantityplnounWithHash(quantity,itemname));
		substitutes.put("the item", "the " + Grammar.plnoun(quantity,itemname));
		
		
		engine.say(StringUtils.substitute(message,substitutes));		
		if (index > -1) {
			player.setQuest(questname, index, itemname + "=" + quantity);
		} else {
			player.setQuest(questname, itemname + "=" + quantity);
		}
	}

	@Override
	public String toString() {
		return "StartRecordingRandomItemCollection<" + items.toString() + ">";
	}


	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false,
				StartRecordingRandomItemCollectionAction.class);
	}
}
