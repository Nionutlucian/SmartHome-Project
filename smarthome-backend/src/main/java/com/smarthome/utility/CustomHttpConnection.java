package com.smarthome.utility;

import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.io.SessionInputBuffer;

class CustomHttpConnection extends DefaultBHttpClientConnection {

    public CustomHttpConnection(final int buffersize) {
        super(buffersize);
    }

    @Override
    public SessionInputBuffer getSessionInputBuffer() {
        return super.getSessionInputBuffer();
    }
}