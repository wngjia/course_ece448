package ece448.lec16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GroupsModel {
	private HashMap<String, HashSet<String>> groups = new HashMap<>();

	synchronized public List<String> getGroups() {
		return new ArrayList<>(groups.keySet());
	}

	synchronized public List<String> getGroupMembers(String group) {
		HashSet<String> members = groups.get(group);
		return (members == null)? new ArrayList<>(): new ArrayList<>(members);
	}

	synchronized public void setGroupMembers(String group, List<String> members) {
		groups.put(group, new HashSet<>(members));
	}

	synchronized public void removeGroup(String group) {
		groups.remove(group);
	}
}
