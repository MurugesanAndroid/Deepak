package mis.tvscs.com.deepak.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import mis.tvscs.com.deepak.R;

public class CommonMethods {
    public static void alertException(Context context, Throwable t) {
        try {
            if (t instanceof SocketTimeoutException) {
                showAlert("Network Time out. Please try again.", context);
            } else if (t instanceof ConnectException) {
                showAlert("Server Time out. Please try again.", context);
            } else {
                showAlert("Error!!! " + t, context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(String message, final Context context) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.alert_message_layout, null);

        alertDialog.setView(dialogView);

        TextView errorMessage = dialogView.findViewById(R.id.textViewDilog);
        errorMessage.setText(message);

        dialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

}
