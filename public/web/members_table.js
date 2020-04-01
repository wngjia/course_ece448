const btnClassAdd = "btn btn-primary btn-block";
const btnClassDel = "btn btn-danger btn-block";

/**
 * This is a stateless view showing the table header.
 */
function Header(props) {
	var ths = [];
	for (var groupName of props.groupNames) {
		ths.push(
			<th key={groupName}>
				<button className={btnClassAdd}>{groupName}</button>
			</th>
		);
	}

	return (
		<thead>
			<tr>
				<th rowSpan="2" width="10%">Members</th>
				<th colSpan={props.groupNames.length}>Groups</th>
				<th rowSpan="2" width="10%">Remove from All Groups</th>
			</tr>
			<tr>
				{ths}
			</tr>
		</thead>
	);
}

/**
 * This is a stateless view showing one row.
 */
function Row(props) {
	var members = props.members;
	var tds = members.get_group_names().map(groupName => {
		if (members.is_member_in_group(props.memberName, groupName)) {
			return (<td key={groupName}>
				<input type="checkbox" checked/></td>);
		}
		else {
			return (<td key={groupName}>
				<input type="checkbox"/></td>);
		}
	});

	return (
		<tr>
			<td><button className={btnClassAdd}>{props.memberName}</button></td>
			{tds}
			<td><button className={btnClassDel}>X</button></td>
		</tr>
	);
}

/**
 * This is a stateless view showing the table body.
 */
function Body(props) {
	var rows = props.members.get_member_names().map(memberName =>
		<Row key={memberName} memberName={memberName} members={props.members} />);

	return (
		<tbody>
			{rows}
		</tbody>
	);
}

/**
 * This is a stateless view showing the whole members table.
 */
function MembersTable(props) {
	if (props.members.get_group_names().length == 0)
		return (<div>There are no groups.</div>);

	return (
		<table className="table table-striped table-bordered">
			<Header groupNames={props.members.get_group_names()} />
			<Body members={props.members} />
		</table>);
}

//export
window.MembersTable = MembersTable;