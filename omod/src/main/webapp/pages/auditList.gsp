<%
    ui.decorateWith("appui", "standardEmrPage")
    if (context.hasPrivilege("Sync2 Audit Privilege")) {
%>
<% ui.includeJavascript("sync2", "sync2.audit.controller.js") %>
<% ui.includeJavascript("sync2", "jsGrid.min.js") %>

<link href="/${ ui.contextPath() }/ms/uiframework/resource/sync2/styles/jsGrid.css"  rel="stylesheet" type="text/css" />
<link href="/${ ui.contextPath() }/ms/uiframework/resource/sync2/styles/theme.css"  rel="stylesheet" type="text/css" />
<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        {
            label: "${ ui.message("sync2.audit.label") }",
            link: "${ ui.pageLink("sync2", "auditList") }"
        },
        { label: "${ ui.message('sync2.audit.label') }" }
    ];
    var titles = [
        "${ ui.message('sync2.log.header.resource') }",
        "${ ui.message('sync2.log.header.timestamp') }",
        "${ ui.message('sync2.log.header.Url') }",
        "${ ui.message('sync2.log.header.status') }",
        "${ ui.message('sync2.log.header.action') }"
    ];
</script>

<div id="jsGrid" class="jsgrid" style="position: relative; height: auto; width: 100%;"></div>

<% } %>