package com.au.auloktra.models;

import com.au.auloktra.R;

/**
 * Cross lead adapter it
 */
public class CrossLeadItem {

    public CrossLeadItem() {
    }

    public CrossLeadItem(String name, String subtitle, String status, String assignedTo) {
        this.name = name;
        this.subtitle = subtitle;
        this.status = status;
        this.assignedTo = assignedTo;
        initBackgroundColour();
    }

    public String name;
    public String subtitle;
    public String status;
    public String assignedTo;

    public int color;

    // TODO : Find better solution
    private void initBackgroundColour() {
        switch (status.toLowerCase()) {
            case "closed":
                color = R.color.completed_bar_colour;
                break;
            case "not started":
                color = R.color.not_started_bar_colour;
                break;
            case "pending":
                color = R.color.pending_bar_colour;
                break;
            case "rejected":
                color = R.color.rejected_bar_colour;
                break;
            default:
                color = 0;
                break;
        }
    }

    private void initBackgroundDrawable() {
        switch (status.toLowerCase()) {
            case "closed":
                color = R.drawable.completed_bar;
                break;
            case "not started":
                color = R.drawable.not_started_bar;
                break;
            case "pending":
                color = R.drawable.pending_bar;
                break;
            case "rejected":
                color = R.drawable.rejected_bar;
                break;
            default:
                color = 0;
                break;
        }
    }
}
