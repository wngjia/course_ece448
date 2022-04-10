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
	that.get_group_members = group_name => group_members.get(group_name);

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
		console.info("Members constructor()");
		this.state = {
			members: create_members_model([
				{name: "A", members: ["a", "b", "c"]},
				{name: "B", members: ["c", "d", "e"]},
				{name: "C", members: ["a", "c", "e"]}
			]),
		};
	}

	componentDidMount() {
		console.info("Members componentDidMount()");
		this.getGroups();
		// setTimeout(this.getGroups, 10000);
	}

	render() {
		console.info("Members render()");
		return (<MembersTable members={this.state.members}
			onMemberChange={this.onMemberChange}
			onDeleteGroup={this.onDeleteGroup} />);
	}

	getGroups = () => {
		console.info("RESTful: get groups");
		fetch("api/groups")
			.then(rsp => rsp.json())
			.then(groups => this.showGroups(groups))
			.catch(err => console.error("Members: getGroups", err));
	}

	showGroups = groups => {
		this.setState({
			members: create_members_model(groups)
		});
	}

	createGroup = (groupName, groupMembers) => {
		console.info("RESTful: create group "+groupName
			+" "+JSON.stringify(groupMembers));
		
		var postReq = {
			method: "POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(groupMembers)
		};
		fetch("api/groups/"+groupName, postReq)
			.then(rsp => this.getGroups())
			.catch(err => console.error("Members: createGroup", err));
	}

	deleteGroup = groupName => {
		console.info("RESTful: delete group "+groupName);
	
		var delReq = {
			method: "DELETE"
		};
		fetch("api/groups/"+groupName, delReq)
			.then(rsp => this.getGroups())
			.catch(err => console.error("Members: deleteGroup", err));
	}	

	onMemberChange = (memberName, groupName) => {
		var groupMembers = new Set(this.state.members.get_group_members(groupName));
		if (groupMembers.has(memberName))
			groupMembers.delete(memberName);
		else
			groupMembers.add(memberName);

		this.createGroup(groupName, Array.from(groupMembers));
	}

	onDeleteGroup = groupName => {
		this.deleteGroup(groupName);
	}
}

// export
window.Members = Members;