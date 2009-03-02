package games.stendhal.server.core.rule.defaultruleset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LowerCaseMap<V> implements Map<String, V> {
	private Map<String, V> data;

	public LowerCaseMap() {
		data = new HashMap<String, V>();
	}

	public void clear() {
		data.clear();
		
	}

	
	public boolean containsValue(final Object value) {
		return data.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, V>> entrySet() {
		return data.entrySet();
	}

	public V get(final Object key) {
		if (key instanceof String) {
			final String new_name = (String) key;
			return data.get(new_name.toLowerCase());
		}
		return null;
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public Set<String> keySet() {
		return data.keySet();
	}

	public V put(final String key, final V value) {
		return data.put(key.toLowerCase(), value);
	}

	public void putAll(final Map< ? extends String, ? extends V> m) {
		   for (final Iterator< ? extends Map.Entry< ? extends String, ? extends V>> i = m.entrySet().iterator(); i.hasNext();) {
			                 final Map.Entry< ? extends String, ? extends V> e = i.next();
			                 put(e.getKey(), e.getValue());
			              }
	}

	public V remove(final Object key) {
		if (key instanceof String) {
			final String new_name = (String) key;
			return data.remove(new_name.toLowerCase());
		}
		return null;
	}

	public int size() {
		return data.size();
	}

	public Collection<V> values() {
		return data.values();
	}

	public boolean containsKey(final Object key) {
		if (key instanceof String) {
			final String new_name = (String) key;
			return data.containsKey(new_name.toLowerCase());
		}
		return false;
	}
}
