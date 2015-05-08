package com.banno.norbertreader;

import net.dean.jraw.models.Submission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedditUtil {

    private List<String> mSubmissionsRead = new ArrayList<>();
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

    public void markAsRead(Submission submission) {
        if (!isRead(submission)) {
            mSubmissionsRead.add(submission.getId());
        }
    }

    public boolean isRead(Submission submission) {
        return mSubmissionsRead.contains(submission.getId());
    }
}
