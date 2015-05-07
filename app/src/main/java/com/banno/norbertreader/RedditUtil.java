package com.banno.norbertreader;

import java.util.HashMap;
import java.util.Map;

public class RedditUtil {

    private static Map<String, Long> sSubmissionIds = new HashMap<>();
    private static long sCurrentSubmissionId = 1;

    public static long getId(String submissionId) {
        Long id = sSubmissionIds.get(submissionId);

        if (id == null) {
            id = ++sCurrentSubmissionId;
            sSubmissionIds.put(submissionId, id);
        }

        return id;
    }
}
