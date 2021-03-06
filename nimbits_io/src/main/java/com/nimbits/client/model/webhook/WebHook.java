package com.nimbits.client.model.webhook;

import com.nimbits.client.model.UrlContainer;
import com.nimbits.client.model.entity.Entity;

import java.io.Serializable;

public interface WebHook extends Entity, Serializable {

    HttpMethod getMethod();

    DataChannel getPathChannel();

    void setPathChannel(DataChannel dataChannel);

    DataChannel getBodyChannel();

    void setBodyChannel(DataChannel dataChannel);

    UrlContainer getUrl();

    boolean isEnabled();

    void setMethod(HttpMethod method);

    void setUrl(UrlContainer url);

    void setEnabled(boolean enabled);

    String getDownloadTarget();

    void setDownloadTarget(String key);




}
