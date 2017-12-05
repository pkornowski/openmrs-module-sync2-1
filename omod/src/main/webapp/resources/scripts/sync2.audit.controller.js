jQuery(document).ready(function() {
    var success = [
        { name: "Success & Failure", id: "SUCCESS_AND_FAILURE" },
        { name: "Success", id: "SUCCESS" },
        { name: "Failure", id: "FAILURE" }
    ];

    var action = [
        { name: "Push & Pull", id: "PUSH_AND_PULL" },
        { name: "Push", id: "PUSH" },
        { name: "Pull", id: "PULL" }
    ];

    var resources = [
        { name: "All", id: "ALL" },
        { name: "Patient", id: "PATIENT" }
    ];

    $("#jsGrid").jsGrid({
        height: "auto",
        width: "100%",

        sorting: true,
        paging: true,
        pageLoading: true,
        autoload: true,
        filtering: true,
        pageSize: 10,
        pageButtonCount: 5,
        pageIndex: getPageIndex(),

        controller: {
            loadData: function (filter) {
                var d = $.Deferred();

                jQuery.ajax({
                    url: "/openmrs/ws/rest/sync2/messages",
                    type: "GET",
                    dataType: "json",
                    data: filter
                }).done(function (response) {
                    d.resolve(response);
                });

                return d.promise();
            }
        },
        fields: [ {
                name: 'id', type: "number", visible: false
            }, {
                title: titles[0], name: "resourceName", type: "select", items: resources, valueField: "id", textField: "name", sorting: true, filtering: true
            }, {
                title: titles[1], name: "timestamp", sorting: true, filtering: false
            }, {
                title: titles[2], name: "resourceUrl", sorting: true, filtering: false
            }, {
                title: titles[3], name: "success", type: "select", items: success, valueField: "id", textField: "name" , sorting: true, filtering: true, align: "center",
                itemTemplate: function(value) {
                    var result;
                    if (value === true) {
                        result = $("<div>").prepend('<img id="successImage" src="/openmrs/ms/uiframework/resource/sync2/images/success.png" />');
                    } else {
                        result = $("<div>").prepend('<img id="failureImage" src="/openmrs/ms/uiframework/resource/sync2/images/failure.png" />');
                    }
                    return result;
                }
            }, {
                title: titles[4], name: "action" , type: "select", items: action, valueField: "id", textField: "name", filtering: true
            }
        ]
    });
});

function getPageIndex(){
    var url = new URL(window.location.href);
    var param = url.searchParams.get("pageIndex");
	if (param == null) {
		return 1;
	}
	return parseInt(param);
}