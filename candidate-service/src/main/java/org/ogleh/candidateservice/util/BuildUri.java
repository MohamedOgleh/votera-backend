package org.ogleh.candidateservice.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BuildUri {

    public static String changeImageToUriLocator(String urlPath, String partyFlag) {
        URI locator = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(urlPath)
                .path(partyFlag)
                .build()
                .toUri();

        return locator.toString();
    }
}
