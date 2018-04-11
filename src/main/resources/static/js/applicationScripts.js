//
	// function definition
	//

	function popDialog(opts) {
		var newDialog = $('<div id="dialog-confirm"><p>' + opts.message
				+ '</p></div>');

		if (!$('#dialog-confirm').length) {
			$('body').append(newDialog);
		}

		newDialog.dialog({
			resizable : false,
			modal : true,
			title : opts.title,
			height : opts.height,
			width : opts.width,
			buttons : opts.buttons
		});

	};