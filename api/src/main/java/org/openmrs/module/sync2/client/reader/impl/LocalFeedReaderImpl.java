package org.openmrs.module.sync2.client.reader.impl;

import org.openmrs.module.atomfeed.client.AtomFeedClient;
import org.openmrs.module.atomfeed.client.impl.AtomFeedClientImpl;
import org.openmrs.module.sync2.api.SyncConfigurationService;
import org.openmrs.module.sync2.api.exceptions.SyncException;
import org.openmrs.module.sync2.api.model.configuration.ClassConfiguration;
import org.openmrs.module.sync2.client.reader.LocalFeedWorker;
import org.openmrs.module.sync2.client.reader.LocalFeedReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component("sync2.localFeedReader")
public class LocalFeedReaderImpl implements LocalFeedReader {

    private static final String RECENT_FEED = "recent";
    private static final String WS_ATOMFEED = "/ws/atomfeed/";

    private AtomFeedClient atomFeedClient;

    @Autowired
    private SyncConfigurationService configurationService;

    public LocalFeedReaderImpl() {
        this.atomFeedClient = new AtomFeedClientImpl(new LocalFeedWorker());
    }

    @Override
    public void readAllFeedsForPush() {
        List<ClassConfiguration> pushConf = configurationService.getSyncConfiguration().getPush().getClasses();

        for (ClassConfiguration classConf : pushConf) {
            if (classConf.isEnabled()) {
                readFeedByCategory(classConf.getCategory());
            }
        }
    }

    private void readFeedByCategory(String category) {
        try {
            URI uri = new URI(getResourceUrlWithCategory(category));
            atomFeedClient.setUri(uri);
            atomFeedClient.process();
        } catch (URISyntaxException e) {
            throw new SyncException("Atomfeed URI is not correct. ", e);
        } catch (Exception e) {
            throw new SyncException(String.format("Error during processing atomfeeds for category %s: ", category), e);
        }
    }

    private String getResourceUrlWithCategory(String category) {
        String localFeedUri = configurationService.getSyncConfiguration().getGeneral().getLocalFeedLocation();
        // TODO: Start reading from the last page read. Marks table.
        return localFeedUri + WS_ATOMFEED + category + "/" + RECENT_FEED;
    }
}
