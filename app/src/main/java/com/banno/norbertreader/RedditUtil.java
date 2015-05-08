package com.banno.norbertreader;

import java.util.HashMap;
import java.util.Map;

public class RedditUtil {

    private Map<String, Long> mSubmissionIds = new HashMap<>();
    private long mCurrentSubmissionId = 1;

    public long getId(String submissionId) {
        Long id = mSubmissionIds.get(submissionId);

        if (id == null) {
            id = ++mCurrentSubmissionId;
            mSubmissionIds.put(submissionId, id);
        }

        return id;
    }
}
