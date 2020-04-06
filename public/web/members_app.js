/**
 * The App class is a controller holding the global state.
 * It creates all children controllers in render().
 */
class MembersApp extends React.Component {

	constructor(props) {
		super(props);
		console.info("MembersApp constructor()");
	}

	render() {
		console.info("MembersApp render()");
		return (
		<div className="container">
			<div className="row">
				<div className="col-sm-12">
				<h2>Manage Groups and Members</h2>
				<Members />
				</div>
			</div>
		</div>);
	}
}

// export
window.MembersApp = MembersApp;