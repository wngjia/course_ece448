/**
 * A model for managing members in groups.
 */
function create_members_model(groups) {
	// create the data structure
	var all_members = new Set(); // all unique member names
	var group_names = [];
	var group_members = new Map(); // group_name to set of group members 
	for (var group of groups) {
		group_names.push(group.name);
		var members = new Set(group.members);
		group_members.set(group.name, members);
		members.forEach(member => all_members.add(member));
	}
	var member_names = Array.from(all_members);
	group_names.sort();
	member_names.sort();

	// create the object
	var that = {}
	that.get_group_names = () => group_names;
	that.get_member_names = () => member_names;
	that.is_member_in_group = (member_name, group_name) =>
		!group_members.has(group_name)? false:
			group_members.get(group_name).has(member_name);

	console.debug("Members Model",
		groups, group_names, member_names, group_members);

	return that;
}

/**
 * The Members controller holds the state of groups.
 * It creates its view in render().
 */
class Members extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			members: create_members_model([
				{name: "A", members: ["a", "b", "c"]},
				{name: "B", members: ["c", "d", "e"]},
				{name: "C", members: ["a", "c", "e"]}
			]),
		};
	}

	render() {
		return (<MembersTable members={this.state.members} />);
	}
}

// export
window.Members = Members;