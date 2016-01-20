package com.example.dongja94.samplefragmentbuttontab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

    public interface OnMessageReceiver {
        public void onMessageReceived(String message);
    }

    OnMessageReceiver mCallback;

    public static Tab1Fragment newInstance(String message) {
        Tab1Fragment f = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(PARAM_MESSAGE, message);
        f.setArguments(args);
        return f;
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }

    public static final String PARAM_MESSAGE = "message";
    String message;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            message = b.getString(PARAM_MESSAGE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageReceiver) {
            mCallback = (OnMessageReceiver)context;
        }
    }

    EditText inputView;
    TextView messageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        inputView = (EditText)v.findViewById(R.id.edit_input);
        messageView = (TextView)v.findViewById(R.id.text_message);
        messageView.setText(message);
        Button btn = (Button)v.findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputView.getText().toString();
                messageView.setText(message);
//                ((MainActivity)getActivity()).onMessageReceived(message);
                if (mCallback != null) {
                    mCallback.onMessageReceived(message);
                }
            }
        });
        return v;
    }

}
