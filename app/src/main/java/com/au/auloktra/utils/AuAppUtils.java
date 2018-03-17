package com.au.auloktra.utils;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.au.auloktra.base.BaseAUActivity;
import com.au.auloktra.models.CrossLeadItem;

import java.util.ArrayList;
import java.util.List;

/**
 * App utility class for common functions
 */
public class AuAppUtils {

    private static Object dummyLeadsList;

    /**
     * Shows toast with input text
     *
     * @param activity BaseActivity
     * @param text Text to show
     */
    public static void showToast(@NonNull BaseAUActivity activity, @NonNull String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public static List<CrossLeadItem> getDummyLeadsList() {
        List<CrossLeadItem> list = new ArrayList<>();

        list.add(new CrossLeadItem("Lalit Patil", "Loan > Personal Loan", "Not Started", "Rakesh Tiwari"));
        list.add(new CrossLeadItem("Devika Singh", "Loan > House Loan", "Closed", "Nita Rathod"));
        list.add(new CrossLeadItem("Lakshmi Narasimham", "Loan > Car Loan", "Pending", "Shweta Dharam"));
        list.add(new CrossLeadItem("Lalit Patil", "Loan > Personal Loan", "Not Started", "Rakesh Tiwari"));
        list.add(new CrossLeadItem("Devika Singh", "Loan > House Loan", "Closed", "Nita Rathod"));
        list.add(new CrossLeadItem("Lakshmi Narasimham", "Loan > Car Loan", "Pending", "Shweta Dharam"));
        list.add(new CrossLeadItem("Lalit Patil", "Loan > Personal Loan", "Not Started", "Rakesh Tiwari"));
        list.add(new CrossLeadItem("Devika Singh", "Loan > House Loan", "Closed", "Nita Rathod"));
        list.add(new CrossLeadItem("Lakshmi Narasimham", "Loan > Car Loan", "Pending", "Shweta Dharam"));
        list.add(new CrossLeadItem("Lalit Patil", "Loan > Personal Loan", "Not Started", "Rakesh Tiwari"));
        list.add(new CrossLeadItem("Devika Singh", "Loan > House Loan", "Closed", "Nita Rathod"));
        list.add(new CrossLeadItem("Lakshmi Narasimham", "Loan > Car Loan", "Pending", "Shweta Dharam"));

        return list;
    }
}
