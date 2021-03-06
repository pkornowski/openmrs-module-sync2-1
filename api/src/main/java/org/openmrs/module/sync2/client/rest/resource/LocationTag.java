package org.openmrs.module.sync2.client.rest.resource;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.api.context.Context;

import java.util.List;

public class LocationTag implements RestResource {
    private String uuid;
    private String name;
    private String description;
    private Boolean retired;
    private List<Link> links;
    private String resourceVersion;

    // region getters
    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getRetired() {
        return retired;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }
    // endregion

    // region setters
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }
    // endregion

    @Override
    public BaseOpenmrsObject getOpenMrsObject() {
        if (uuid != null) {
            org.openmrs.LocationTag locationTag = Context.getLocationService().getLocationTagByUuid(uuid);
            if (locationTag != null) {
                return locationTag;
            }
        }

        return Context.getLocationService().getLocationTagByName(name);
    }
}
