package com.banno.norbertreader;

import java.util.HashMap;
import java.util.Map;

public class RedditUtil {

    private Map<String, Long> sSubmissionIds = new HashMap<>();
    private long sCurrentSubmissionId = 1;

    public long getId(String submissionId) {
        Long id = sSubmissionIds.get(submissionId);

        if (id == null) {
            id = ++sCurrentSubmissionId;
            sSubmissionIds.put(submissionId, id);
        }

        return id;
    }
}
