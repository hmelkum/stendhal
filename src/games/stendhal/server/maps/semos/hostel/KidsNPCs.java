package games.stendhal.server.maps.semos.hostel;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.Map;

/**
 * Creates kids who are sleeping in semos hostel
 *
 * @author kymara
 */
public class KidsNPCs implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildNPCs(zone);
	}

	private void buildNPCs(StendhalRPZone zone) {
		String[] names = {"Taylor","Covester","Mick","Richard"};
		String[] classes = { "kid2npc", "kid1npc", "kid9npc","kid8npc" };
		int[][] start = { {3, 3}, {9, 3}, {15, 3}, {21, 3} };
		for (int i = 0; i < 4; i++) {
			SpeakerNPC npc = new SpeakerNPC(names[i]) {

				@Override
				protected void createPath() {
					// they sleeping!
					setPath(null);
				}

				@Override
				protected void createDialog() {
					add(
			     		ConversationStates.IDLE,
						ConversationPhrases.GREETING_MESSAGES,
						ConversationStates.IDLE,
						"ZZzzzz ... ",
						null);
			
				}
			};
			npc.setEntityClass(classes[i]);
			npc.setPosition(start[i][0], start[i][1]);
			npc.setDirection(Direction.LEFT);
			npc.initHP(100);
			zone.add(npc);
		}
	}
}
