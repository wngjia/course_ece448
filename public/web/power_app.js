/**
 * The App class is a controller holding the global state.
 * It creates all children controllers in render().
 */
class PowerApp extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
		<div className="container">
			<div className="row">
				<div className="col-sm-12">
				<Power display={this.props.display} />
				</div>
			</div>
		</div>);
	}
}

// export
window.PowerApp = PowerApp;
