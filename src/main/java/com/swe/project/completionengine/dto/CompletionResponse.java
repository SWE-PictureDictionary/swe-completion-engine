package com.swe.project.completionengine.dto;

public class CompletionResponse {

    public enum Status {
        COMPLETE,
        IN_PROGRESS
    }

    private Status status;
    private int remaining;

    public CompletionResponse() {
    }

    public CompletionResponse(Status status, int remaining) {
        this.status = status;
        this.remaining = remaining;
    }

    public static CompletionResponse complete() {
        return new CompletionResponse(Status.COMPLETE, 0);
    }

    public static CompletionResponse inProgress(int remaining) {
        return new CompletionResponse(Status.IN_PROGRESS, remaining);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return status == Status.COMPLETE
                ? "COMPLETE"
                : "IN_PROGRESS (remaining: " + remaining + ")";
    }
}