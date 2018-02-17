package com.vmax.nearby;

/**
 * Created by welcome on 2/9/2018.
 */

public interface FetchFromServerUser {
    void onFetchCompletion(String str, int i);

    void onPreFetch();
}
