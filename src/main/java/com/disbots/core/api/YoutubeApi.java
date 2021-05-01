package com.disbots.core.api;

import com.disbots.core.Main;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.disbots.core.api.YoutubeApiService.getService;

public class YoutubeApi implements IRequests
{
    @Override
    public SearchListResponse SearchVideoByQuery(Long MaxResults, String Order, String Query) throws GeneralSecurityException, IOException
    {
        YouTube youtubeService = getService();
        YouTube.Search.List request = youtubeService.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = request.setMaxResults(MaxResults).setKey(Main.DEVELOPER_KEY)
                .setOrder(Order)
                .setQ(Query)
                .execute();

        return response;
    }
}
