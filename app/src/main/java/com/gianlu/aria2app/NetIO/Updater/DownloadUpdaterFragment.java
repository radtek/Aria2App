package com.gianlu.aria2app.NetIO.Updater;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gianlu.aria2app.NetIO.Aria2.Download;
import com.gianlu.aria2app.NetIO.Aria2.DownloadWithHelper;

public abstract class DownloadUpdaterFragment<P> extends UpdaterFragment<P> {

    @Nullable
    protected abstract Download getDownload(@NonNull Bundle args);

    @Nullable
    protected final Download getDownload() {
        DownloadWithHelper helper = getDownloadWithHelper();
        return helper != null ? helper.get() : null;
    }

    @Nullable
    protected final DownloadWithHelper getDownloadWithHelper() {
        BaseDownloadUpdater updater = ((BaseDownloadUpdater) getUpdater());
        return updater != null ? updater.download : null;
    }

    @NonNull
    @Override
    public final BaseUpdater<P> createUpdater(@NonNull Bundle args) throws Exception {
        Download download = getDownload(args);
        if (download != null) return createUpdater(download);
        else throw new IllegalStateException("Download is null!");
    }

    @NonNull
    protected abstract BaseUpdater<P> createUpdater(@NonNull Download download) throws Exception;
}
