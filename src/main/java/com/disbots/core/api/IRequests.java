package com.disbots.core.api;

import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @apiNote Interface, that contains all requests to
 * youtube api.
 */
public interface IRequests
{
    SearchListResponse SearchVideoByQuery(Long MaxResults, String setOrder, String Query) throws GeneralSecurityException, IOException;
}
